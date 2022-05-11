package API;

import java.util.Map;

class MosTerminals extends Component {

    MosTerminals(String id, Double minValue, Double maxValue, Map<String, String> netList, Double deVal) {
        super(id, minValue, maxValue, deVal, netList);
    }

    void setSrc(String src) {
        netList.put("source", src);
    }

    void setDrain(String drain) {
        netList.put("drain", drain);
    }
    void setGate(String gate){
        netList.put("gate",gate);
    }

    String getSrc() {
        return netList.get("source");
    }

    String getDrain() {
        return netList.get("drain");
    }
    String getGate(){
        return netList.get("gate");
    }
}
