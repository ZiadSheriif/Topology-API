package API;

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

    List<Component> getDevices(String id) {
        for (Topology topology : topologies) {
            if (topology.getId() == id) {
                return topology.getComponents();
            }
        }
        return null;
    }
}
