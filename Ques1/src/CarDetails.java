import java.util.HashMap;
import java.util.Map;


public class CarDetails {
    private String carName;
    private Map<String,Integer> variantToPriceMap;

    public CarDetails(){
        this.variantToPriceMap = new HashMap<>();
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Map<String, Integer> getVariantToPriceMap() {
        return variantToPriceMap;
    }

    public void setVariantToPriceMap(Map<String, Integer> variantToPriceMap) {
        this.variantToPriceMap = variantToPriceMap;
    }
}
