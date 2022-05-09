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

    private static String id;
    private static List<Component> components;

    Topology(String id, List<Component> components) {
        this.id = id;
        setComponents(components);
    }

    void setId(String id) {
        this.id = id;
    }

    final String getId() {
        return id;
    }

    void setComponents(List<Component> components) {
        Map<String, String> netlist;
        Double maxval, minval, defval;
        String id;
        for (int i = 0; i < components.size(); i++) {
            maxval = components.get(i).getMax();
            minval = components.get(i).getMin();
            defval = components.get(i).getDefValue();
            netlist = components.get(i).getNetList();
            id = components.get(i).getId();
            if (components.get(i) instanceof Nmos) {
                components.add(new Nmos(id, minval, maxval, netlist, defval));
            } else
                components.add(new Resistor(id, netlist, minval, maxval, defval));

        }
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