package API;

import java.util.Map;

 class Nmos extends MosTerminals {
    Nmos(String id, Double minValue, Double maxValue, Map<String, String> netList, Double defVal) {
        super(id, minValue, maxValue, netList, defVal);
    }

    void setNoms() {
        type = "nmos";
    }

}
