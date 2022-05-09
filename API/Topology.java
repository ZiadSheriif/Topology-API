package API;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Topology {

    private static String id;
    private static List<Component> components;

    Topology(String id, List<Component> components) {

    }

    void setId(String id) {
        this.id = id;
    }

    String getId() {
        return id;
    }

    void setComponents(List<Component> components) {
        Map<String, String> netlist;
        int maxval, minval, defval;
        String id;
        for (int i = 0; i < components.size(); i++) {
            id = components.get(i).getId();

        }
    }

    List<Component> getComponents() {
        return components;
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