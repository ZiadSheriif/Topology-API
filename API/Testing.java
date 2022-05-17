package API;

import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Testing {
    private static Topologies topos = new Topologies();

    public static void main(String[] args) throws IOException, ParseException {
        testReadFile();
        testWriteFile();
        testGetTopologies();
        testQueryDevices();
        testQueryDevicesWithNetlistNode();
        testDeleteTopologies();
    }

    private static void testReadFile() throws IOException, ParseException {
        topos.readJSON("test.json", topos);
        if (topos.getTopologies().isEmpty()) {
            System.out.println("Testing ReadJSON Failed");
        } else {
            System.out.println("Testing ReadJSON Succeeded");
        }
    }

    private static void testWriteFile() throws IOException {
        topos.writeJSON("top1", "topology", topos);
        File file = new File("../Task2");
        List<String> filesNames = List.of(file.list());
        if (!filesNames.contains("topology.json")) {
            System.out.println("Testing WriteJSON Failed");
        } else {
            System.out.println("Testing WriteJSON Succeeded");
        }
    }

    private static void testGetTopologies() {
        List<Topology> set = topos.getTopologies();
        if (!set.get(0).getId().equals("top1")) {
            System.out.println("Testing getTopologies Failed");
        } else {
            System.out.println("Testing getTopologies Succeeded");
        }
    }

    private static void testDeleteTopologies() {
        topos.deleteTopology("top1");
        List<Topology> myList = topos.getTopologies();
        if (myList.contains("top1")) {
            System.out.println("Testing deleteTopologies Failed");
        } else {
            System.out.println("Testing deleteTopologies Succeeded");
        }
    }

    private static void testQueryDevices() {
        List<HashMap<String, String>> listOfMap = topos.getDevices("top1");
        if (listOfMap.get(0).get("id").equals("res1") && listOfMap.get(0).get("type").equals("resistor")
                && listOfMap.get(0).get("id").equals("m1") && listOfMap.get(0).get("type").equals("nmos")) {
            System.out.println("Testing queryDevices Succeeded");
        } else {
            System.out.println("Testing queryDevices Succeeded");
        }
    }

    private static void testQueryDevicesWithNetlistNode() {
        boolean got = topos.getConnectedDevices("n1", "top1",0);
        if (got) {
            System.out.println("Testing queryDevicesWithNetlistNode Succeeded");
        } else {
            System.out.println("Testing queryDevicesWithNetlistNode Failed");
        }
    }
}