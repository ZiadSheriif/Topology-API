package API;//package crunchify.com.tutorials;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Node;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Topology {

    private static String id;
    private static String type;
    private static HashMap<String, List<Component>> Nodes;
    private static List<Component> components;


    public static void main(String[] args) throws IOException, ParseException {
        readJSON("test");
    }

    private static void readJSON(String file) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        components = new Vector<>();
        Nodes = new HashMap<>();
        Object obj = parser.parse(new FileReader(file));
        JSONObject jsonObject = (JSONObject) obj;
        for (JSONObject component : (List<JSONObject>) jsonObject.get("components")) {
            JSONObject netlist = (JSONObject) component.get("netlist");
            Set<String> keys = netlist.keySet();
            for (String key : keys) {
                if (!Nodes.containsKey(netlist.get(key))) {
                    List<Component> Vec = new Vector<>();
                    Nodes.put((String) netlist.get(key), Vec);
                }
            }
        }
        System.out.println(Nodes);
    }

    private static void writeJSON(String input) throws IOException {
        JSONObject obj = new JSONObject();
        FileWriter file = new FileWriter("C:/Users/20114/Desktop/Tasks/Task2/" + input + ".json");
        file.write(obj.toJSONString());
        System.out.println("\nJSON Object: " + obj);
        file.flush();
        file.close();
    }

}