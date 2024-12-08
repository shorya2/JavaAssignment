import java.util.List;
import java.util.Map;

public class AccessoryDetails {
    private String accessoryType;
    private Map<String, List<Integer>> accessoryToPriceMap;


    public AccessoryDetails(){

    }

    public String getAccessoryType() {
        return accessoryType;
    }

    public void setAccessoryType(String accessoryType) {
        this.accessoryType = accessoryType;
    }

    public Map<String, List<Integer>> getAccessoryToPriceMap() {
        return accessoryToPriceMap;
    }

    public void setAccessoryToPriceMap(Map<String, List<Integer>> accessoryToPriceMap) {
        this.accessoryToPriceMap = accessoryToPriceMap;
    }

}
