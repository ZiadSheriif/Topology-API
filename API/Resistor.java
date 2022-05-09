package API;

import java.util.Map;

public class Resistor extends TerminalNodes {
    Resistor(String id, Map<String, String> netlistR, Double minVal, Double maxVal, Double defVal) {
        super(id, minVal, maxVal, netlistR, defVal);
    }
}
