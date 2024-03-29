package API;

import java.util.Map;

public class Component {
    protected String id, type;
    protected double minValue, maxValue, defValue;
    protected Map<String, String> netList;

    Component(String id, Double minValue, Double maxValue, Double defVal, Map<String, String> netList) {
        this.id = id;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.netList = netList;
        this.defValue = defVal;
    }

    //TODO: Setters and getters of Components Class


    // Setters

    void setId(String id) {
        this.id = id;
    }

    void setType(String type) {
        this.type=type;
    }

    void setMax(Double maxValue) {
        this.maxValue = maxValue;
    }

    void setMin(Double minValue) {
        this.maxValue = minValue;
    }

    void setNetList(Map<String, String> netList) {
        this.netList = netList;
    }

    void setDefValue(Double defValue) {
        this.defValue = defValue;
    }


    // Getters
    final String getId() {
        return id;
    }

    final String getType() {
        return this.type;
    }

    final Double getMax() {
        return this.maxValue;
    }

    final Double getMin() {
        return this.minValue;
    }

    final Map<String, String> getNetList() {
        return this.netList;
    }

    final Double getDefValue() {
        return defValue;
    }
}
