package API;

import java.util.Map;

public class TerminalNodes extends Component {
    TerminalNodes(String id, Double minValue, Double maxValue, Map<String, String> netList,Double defVal) {
        super(id,minValue,maxValue,defVal,netList);
    }

    // setters
    void setT1(String t1) {
        netList.put("t1", t1);
    }

    void setT2(String t2) {
        netList.put("t2", t2);
    }

    //getters
    String getT1() {
        return netList.get("t1");
    }

    String getT2() {
        return netList.get("t2");
    }
}
