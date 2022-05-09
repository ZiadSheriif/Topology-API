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
}
