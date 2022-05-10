package API;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;
import java.util.List;

public class main {
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
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    // TODO: handle case if empty
    private static void readJson() throws IOException, ParseException {
        System.out.println("File Name: ");
        Scanner input = new Scanner(System.in);
        Topology topology = topos.readJSON(input.next());
        if (!topology.getId().isEmpty()) {
            System.out.println("Read Successfully");
            topos.insertTopology(topology);
            System.out.println(topos.getTopologies().size());
        }

    }

    private static void writeJson() throws IOException {
        System.out.println("Topology ID: ");
        Scanner input1 = new Scanner(System.in);
        System.out.println("File Name: ");
        Scanner input2 = new Scanner(System.in);
        topos.writeJSON(input1.next(), input2.next());
    }

    private static void printTopologies() {
        topos.printTopologiesID();
    }

    private static void deleteTopology() {
        System.out.println("Topology ID: ");
        Scanner input = new Scanner(System.in);
        topos.deleteTopology(input.next());
    }

}


