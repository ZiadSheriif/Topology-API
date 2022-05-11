package API;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Topologies {
    private final List<Topology> topologies = new ArrayList<>();


    final List<Topology> getTopologies() {
        return topologies;
    }

    void insertTopology(Topology topology) {
        for (Topology topo : topologies) {
            if (topo.getId().equals(topology.getId())) {
                return;
            }
        }
        topologies.add(topology);
    }

    void deleteTopology(String id) {
        boolean done = false;
        for (Topology topology : topologies) {
            if (topology.getId().equals(id)) {
                topologies.remove(topology);
                done = true;
                break;
            }
        }
        if (done)
            System.out.println("Topology With ID " + id + " has been Deleted Successfully\n");
        else
            System.out.println("Topology With ID " + id + " has been Failed to be Deleted\n");
    }

    List<Component> getConnectedDevices(String node, String topId) {
        for (Topology topology : topologies) {
            if (topology.getId().equals(topId)) {
                System.out.println(topology.getConnectedComponents(node));
                List<Component> tempComp = topology.getConnectedComponents(node);

            }
        }
        return null;
    }

    List<HashMap<String, String>> getDevices(String topId) {
        List<Component> comps = null;
        List<HashMap<String, String>> device = new ArrayList<>();
        for (Topology topology : topologies) {
            if (topology.getId().equals(topId)) {
                comps = topology.getComponents();
                for (Component component : comps) {
                    HashMap<String, String> dummy = new HashMap<>();
                    dummy.put("type", component.getType());
                    dummy.put("id", component.getId());
                    device.add(dummy);
                }
                break;
            }
        }
        return device;
    }

    Topology readJSON(String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(fileName));
        JSONObject jsonObject = (JSONObject) obj;

        String topId = (String) jsonObject.get("id");
        List<JSONObject> arrOfComponents = (List<JSONObject>) jsonObject.get("components");
        int n = arrOfComponents.size();
        List<Component> components = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            components.add(createComponent(arrOfComponents, i));
        }
        Topology topo = new Topology(topId, components);
        return topo;

    }

    Component createComponent(List<JSONObject> components, int index) {
        Double mx, mn, defVal;
        String id, type;
        Component comp;
        type = (String) components.get(index).get("type");
        id = (String) components.get(index).get("id");
        if (type.equals("resistor")) {
            JSONObject device1 = (JSONObject) components.get(index).get("resistance");
            mn = Double.parseDouble(device1.get("min").toString());
            mx = Double.parseDouble(device1.get("max").toString());
            defVal = Double.parseDouble(device1.get("default").toString());
            comp = new Resistor(id, (Map<String, String>) components.get(index).get("netlist"), mn, mx, defVal);
        } else if (type.equals("nmos")) {
            JSONObject device2 = (JSONObject) components.get(index).get("m(l)");
            mn = Double.parseDouble(device2.get("min").toString());
            mx = Double.parseDouble(device2.get("max").toString());
            defVal = Double.parseDouble(device2.get("default").toString());
            comp = new Nmos(id, mn, mx, (Map<String, String>) components.get(index).get("netlist"), defVal);
        } else {
            JSONObject device2 = (JSONObject) components.get(index).get("m(l)");
            mn = Double.parseDouble(device2.get("min").toString());
            mx = Double.parseDouble(device2.get("max").toString());
            defVal = Double.parseDouble(device2.get("default").toString());
            comp = new Pmos(id, mn, mx, (Map<String, String>) components.get(index).get("netlist"), defVal);
        }
        return comp;
    }

    void writeJSON(String topId, String fileName) throws IOException {
        FileWriter file = new FileWriter(fileName + ".json");
        JSONObject json = new JSONObject();

        // get id and getting each component in component list and convert it to string
        for (Topology topology : topologies) {
            if (topology.getId().equals(topId)) {
                json.put(topId, convertComponentsToJson(topology.getComponents()));
                break;
            }
        }
        file.write(json.toJSONString());
        file.flush();
        file.close();
    }

    List<JSONObject> convertComponentsToJson(List<Component> componentList) {
//        "components": [
//        {
//            "type": "resistor",
//                "id": "res2",
//                "resistance": {
//            "default": 200,
//                    "min": 20,
//                    "max": 2000
//        },
//            "netlist": {
//            "t1": "vdd",
//                    "t2": "n1"
//        }
//        },
//        {
//            "type": "nmos",
//                "id": "m1",
//                "m(l)": {
//            "default": 5.5,
//                    "min": 3,
//                    "max": 4
//        },
//            "netlist": {
//            "drain": "n1",
//                    "gate": "vin",
//                    "source": "vss"
//        }
//        }
//  ]
        JSONObject jsonObject = null;
        JSONObject defObj = null;
        List<JSONObject> jsonList = new ArrayList<>();
        String devVal, type;
        for (Component component : componentList) {
            type = component.getType();
            if (type.equals("resistor"))
                devVal = "resistance";
            else
                devVal = "m(l)";
            jsonObject = new JSONObject();
            defObj = new JSONObject();
            defObj.put("default", component.getDefValue());
            defObj.put("min", component.getMin());
            defObj.put("max", component.getMax());
            jsonObject.put(devVal, defObj);
            jsonObject.put("type", type);
            jsonObject.put("id", component.getId());
            jsonObject.put("netlist", component.getMax());
            jsonList.add(jsonObject);
        }
        System.out.println(jsonList);
        return jsonList;
    }

    void printTopologiesID() {
        for (Topology topology : topologies) {
            System.out.print(topology.getId() + " ");
        }
        System.out.println();

    }
}