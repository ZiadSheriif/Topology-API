package API;

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
        List<Component> dummyComponents = new ArrayList<>();
        for (Component component : components) {
            HashMap<String, String> tempNetList = (HashMap<String, String>) component.getNetList();
            for (String word : tempNetList.values()) {
                if (word.equals(node))
                    dummyComponents.add(component);
            }
        }
        return dummyComponents;
    }
}