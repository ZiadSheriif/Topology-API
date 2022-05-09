package API;

import java.util.Map;

 class Resistor extends ResistorTerminals {
    Resistor(String id, Map<String, String> netlistR, Double minVal, Double maxVal, Double defVal) {
        super(id, minVal, maxVal, netlistR, defVal);
    }

    void setResistor() {
        type = "resistor";
    }
}
