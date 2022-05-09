package API;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
        String id = (String) jsonObject.get("id");
        List<Component> components = (List<Component>) jsonObject.get("components");
        for (Component component : components) {
            components.add(component);
        }
        return new Topology(id, components);

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
