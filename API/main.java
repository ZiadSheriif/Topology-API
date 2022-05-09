package API;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.util.*;
import java.util.List;

public class main {
    public static void main(String[] args) {

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

    void WriteJSON(Topology topology, String fileName) throws IOException {
        JSONObject obj = new JSONObject();
        FileWriter file = new FileWriter("C:/Users/20114/Desktop/Tasks/Task2/" + fileName + ".json");
        // TODO: Write TO  JSON
        // get id and getting each component in component list and convert it to string
        // obj.put("id",topology.getId(),);
        file.write(obj.toJSONString());
        System.out.println("\nJSON Object: " + obj);
        file.flush();
        file.close();
    }
}


