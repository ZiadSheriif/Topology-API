package API;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
    private static Topologies topos = new Topologies();

    public static void main(String[] args) throws IOException, ParseException {

        while (true) {
            Scanner input = new Scanner(System.in);

            System.out.println("1) Read Json file: ");
            System.out.println("2) Write Json file: ");
            System.out.println("3) Print Topologies: ");
            System.out.println("4) Delete Topology: ");
            System.out.println("5) Query Devices: ");
            System.out.println("6) Query Devices With Netlist Node: ");
            System.out.println("7) To exit ");
            int choice = 0;
            try {
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
            }
            switch (choice) {
                case 1 -> readJson();
                case 2 -> writeJson();
                case 3 -> printTopologies();
                case 4 -> deleteTopology();
                case 5 -> getQueryDevices();
                case 6 -> getDevicesWithNetlistNode();
                case 7 -> {
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    // TODO: handle case if empty
    private static void readJson() throws IOException, ParseException {
        System.out.println("File Name (name.json): ");
        Scanner input = new Scanner(System.in);
        topos.readJSON(input.next(), topos);
        if (!topos.getTopologies().isEmpty()) {
            System.out.println("Read Successfully");
        } else
            System.out.println("Read Failed");

    }

    private static void writeJson() throws IOException {
        System.out.print("Topology ID: ");
        Scanner input = new Scanner(System.in);
        String id = input.next();
        System.out.print("File Name: ");
        input = new Scanner(System.in);
        String fileName = input.next();
        topos.writeJSON(id, fileName, topos);
    }

    private static void printTopologies() {
        topos.printTopologiesID();
    }

    private static void deleteTopology() {
        System.out.print("Topology ID: ");
        Scanner input = new Scanner(System.in);
        topos.deleteTopology(input.next());
    }

    private static void getQueryDevices() {
        System.out.print("Topology ID: ");
        Scanner input = new Scanner(System.in);
        List<HashMap<String, String>> device = (List<HashMap<String, String>>) topos.getDevices(input.next());
        if (!device.isEmpty()) {
            System.out.println(device);
        } else
            System.out.println("There is No Topology With this ID");
    }

    private static void getDevicesWithNetlistNode() {
        System.out.print("Topology ID: ");
        Scanner input = new Scanner(System.in);
        String id = input.next();
        System.out.print("Node Name: ");
        input = new Scanner(System.in);
        String node = input.next();
        topos.getConnectedDevices(node, id,1);

    }
}


