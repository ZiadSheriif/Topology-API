package API;

import java.util.Map;

class Pmos extends MosTerminals {
    Pmos(String id, Double minValue, Double maxValue, Map<String, String> netList, Double defVal) {
        super(id, minValue, maxValue, netList, defVal);
        setType();
    }

    void setType() {
        setType("pmos");
    }

}
