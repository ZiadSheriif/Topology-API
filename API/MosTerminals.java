package API;

import java.util.Map;

 class MosTerminals  extends Component {
    MosTerminals(String id, Double minValue, Double maxValue, Map<String, String> netList, Double deVal) {
        super(id, minValue, maxValue, deVal, netList);
    }
}
