package API;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Topologies {
    private List<Topology> topologies;


    final List<Topology> getTopologies() {
        return topologies;
    }

    void insertToplogy(Topology topology) {
        topologies.add(topology);
    }

    void deleteTopology(String id) {
        boolean done = false;
        for (Topology topology : topologies) {
            if (topology.getId() == id) {
                topologies.remove(topology);
                done = true;
                break;
            }
        }
        if (done)
            System.out.println(" Topology With ID " + id + "has been Deleted Successfully\n");
        else
            System.out.println(" Topology With ID " + id + "has been Failed to be Deleted   \n");
    }

    List<Component> getConntectedDevices(String node, String topId) {
        for (Topology topology : topologies) {
            if (topology.getId().equals(topId)) {
                return topology.getConnectedComponents(node);
            }
        }
        return null;
    }

    List<Component> getDevices(String topId) {
        for (Topology topology : topologies) {
            if (topology.getId() == topId) {
                return topology.getComponents();
            }
        }
        return null;
    }

    Topology readJSON(String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(fileName));
        JSONObject jsonObject = (JSONObject) obj;

        String topId = (String) jsonObject.get("id");
        List<JSONObject> arrOfComponents = (List<JSONObject>) jsonObject.get("components");

        List<Component> components = null;
        for (int i = 0; i < arrOfComponents.size(); i++) {
            components.add(createComponent((List<JSONObject>) jsonObject.get("components"), i));
        }
        Topology topo= new Topology(topId, components);
        return topo;

    }

    Component createComponent(List<JSONObject> components, int index) {
        Double mx, mn, defVal;
        String id, type;
        Component comp;
        type = (String) components.get(index).get("type");
        id = (String) components.get(index).get("id");
//        System.out.println(type + "  " + id);
        if (type.equals("resistor")) {
            JSONObject device1 = (JSONObject) components.get(index).get("resistance");
            mn = Double.parseDouble(device1.get("min").toString());
            mx = Double.parseDouble(device1.get("max").toString());
            defVal = Double.parseDouble(device1.get("default").toString());
            comp = new Resistor(id, (Map<String, String>) components.get(index).get("netlist"), mn, mx, defVal);
        } else {
            JSONObject device2 = (JSONObject) components.get(index).get("m(1)");
            mn = Double.parseDouble(device2.get("min").toString());
            mx = Double.parseDouble(device2.get("max").toString());
            defVal = Double.parseDouble(device2.get("default").toString());
            comp = new Nmos(id, mn, mn, (Map<String, String>) components.get(index).get("netlist"), defVal);
        }
        return comp;
    }

    void writeJSON(String topId, String fileName) throws IOException {
        FileWriter file = new FileWriter("C:/Users/20114/Desktop/Tasks/Task2/" + fileName + ".json");
        JSONObject json = new JSONObject();

        // get id and getting each component in component list and convert it to string

        for (Topology topology : topologies) {
            if (topology.getId().equals(topId)) {
                json.put(topId, topology.getComponents());
                return;
            }
        }
        file.write(json.toJSONString());
        System.out.println("\nJSON Object: " + json);
        file.flush();
        file.close();
    }

    void printTopologiesID() {
        for (Topology topology : topologies) {
            System.out.print(topology.getId() + " ");
        }
        System.out.println();
    }
}
