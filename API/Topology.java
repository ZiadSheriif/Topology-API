package API;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.ls.LSException;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Topology {

    private String id;
    private List<Component> components;

    Topology(String id, List<Component> componentss) {
        this.id = id;
        setComponents(componentss);

    }

    void setId(String id) {
        this.id = id;
    }

    final String getId() {
        return id;
    }

    void setComponents(final List<Component> components) {
        this.components = components;
    }

    final List<Component> getComponents() {
        return components;
    }

    final List<Component> getConnectedComponents(String node) {
        List<Component> dummyComponents = null;
        for (Component component : components) {
            HashMap<String, String> tempNetList = (HashMap<String, String>) component.getNetList();
            for (String word : tempNetList.values()) {
                if (word.equals(node))
                    dummyComponents.add(component);
            }
        }
        return dummyComponents;
    }


//    public static void main(String[] args) throws IOException, ParseException {
//        readJSON("test");
//    }

//    private static void readJSON(String file) throws IOException, ParseException {
//        JSONParser parser = new JSONParser();
//        components = new Vector<>();
//        Nodes = new HashMap<>();
//        Object obj = parser.parse(new FileReader(file));
//        JSONObject jsonObject = (JSONObject) obj;
//        for (JSONObject component : (List<JSONObject>) jsonObject.get("components")) {
//            JSONObject netlist = (JSONObject) component.get("netlist");
//            Set<String> keys = netlist.keySet();
//            for (String key : keys) {
//                if (!Nodes.containsKey(netlist.get(key))) {
//                    List<Component> Vec = new Vector<>();
//                    Nodes.put((String) netlist.get(key), Vec);
//                }
//            }
//        }
//        System.out.println(Nodes);
//    }

//    private static void writeJSON(String input) throws IOException {
//        JSONObject obj = new JSONObject();
//        FileWriter file = new FileWriter("C:/Users/20114/Desktop/Tasks/Task2/" + input + ".json");
//        file.write(obj.toJSONString());
//        System.out.println("\nJSON Object: " + obj);
//        file.flush();
//        file.close();
//    }

}