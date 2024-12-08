import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Car {
    List<CarDetails> carDetails;
    List<AccessoryDetails> accessoryDetails;

    public Car() {
        carDetails = new ArrayList<>();
        accessoryDetails = new ArrayList<>();
    }

    public void initializeCar() {
        carDetails.add(createCar("Alto K10", new String[]{"STD", "LXI", "VXI", "VXI+"}, new int[]{399000, 425000, 475000, 596000}));
        carDetails.add(createCar("WagonR", new String[]{"LXI", "VXI", "ZXI", "ZXI+"}, new int[]{554000, 625000, 675000, 700000}));
        carDetails.add(createCar("Celerio", new String[]{"LXI", "VXI", "ZXI", "ZXI+"}, new int[]{499000, 600000, 675000, 704000}));
        carDetails.add(createCar("Fronx", new String[]{"Sigma", "Delta", "Zeta", "Alpha", "Alpha Duel"}, new int[]{751000, 1000000, 1100000, 1200000, 1300000}));
        carDetails.add(createCar("Jimny", new String[]{"Zeta", "Alpha"}, new int[]{1274000, 1495000}));

        accessoryDetails.add(createAccessory("Exterior Accessories", new String[]{"Alloy Wheels", "Body Side Moulding", "Door Visors", "Mud Flaps", "Spoilers", "Chrome Garnish", "Roof Rails", "Side Skirts"}, new int[]{15000, 1500, 1000, 500, 3000, 1000, 2500, 4000}, new int[]{40000, 3000, 2500, 1200, 8000, 5000, 6000, 10000}));
        accessoryDetails.add(createAccessory("Interior Accessories", new String[]{"Seat Covers", "Floor Mats", "Steering Wheel Covers", "Sunshades", "Dashboard Kits", "Ambient Lighting", "Car Perfumes"}, new int[]{2000, 1000, 500, 500, 2000, 2000, 200}, new int[]{10000, 3000, 1500, 2000, 5000, 6000, 1000}));
        accessoryDetails.add(createAccessory("Infotainment and Electronics", new String[]{"Touchscreen Infotainment Systems", "Speakers and Amplifiers", "Reverse Parking Sensors", "Rear View Cameras", "GPS Navigation Systems", "Car Chargers"}, new int[]{10000, 5000, 2000, 3000, 5000, 300}, new int[]{50000, 20000, 5000, 7000, 15000, 1000}));
        accessoryDetails.add(createAccessory("Safety and Security", new String[]{"Car Alarms", "Central Locking Systems", "Fire Extinguishers", "First Aid Kits", "Child Safety Seats"}, new int[]{2000, 3000, 500, 300, 5000}, new int[]{5000, 8000, 2000, 1000, 20000}));
        accessoryDetails.add(createAccessory("Car Care", new String[]{"Car Covers", "Cleaning Kits", "Polish and Wax", "Pressure Washers"}, new int[]{1000, 500, 300, 5000}, new int[]{5000, 2000, 1500, 15000}));

    }

    public List<String> carsWithInBudget(int budget) {
        List<String> carsInBudget = new ArrayList<>();
        for (CarDetails car : carDetails) {
            for (String variant : car.getVariantToPriceMap().keySet()) {
                if (car.getVariantToPriceMap().get(variant) <= budget) {
                    carsInBudget.add(car.getCarName());
                    break;
                }
            }
        }
        return carsInBudget;
    }

    public List<List<String>> returnVariantsWithCustomization(int budget, String carName) {
        CarDetails selectedCar = fetchCarDetails(carName);
        List<List<String>> resultList = new ArrayList<>();
        for (String variant : selectedCar.getVariantToPriceMap().keySet()) {
            if (selectedCar.getVariantToPriceMap().get(variant) <= budget) {
                List<String> variantAndCustomizationDetails = getVariantAndCustInBudget(variant, budget, selectedCar.getVariantToPriceMap().get(variant));
                resultList.add(variantAndCustomizationDetails);
            }
        }
        return resultList;
    }

    public Double finalPrice(String carName, String variant, Map<String,Integer> accessories, int budget) {
        CarDetails selectedCar = fetchCarDetails(carName);
        Double variantPrice = Double.valueOf(selectedCar.getVariantToPriceMap().get(variant));
        for (String accessory : accessories.keySet()) {
            validateAccessoryPrice(accessory,accessories.get(accessory));
            variantPrice = variantPrice + accessories.get(accessory);
        }
        if(variantPrice > budget) {
            throw new RuntimeException("Given variant and customization is out of budget");
        }
        return variantPrice * 1.21;
    }

    public int[] getAccessoryPriceRange(String accessoryName) {
        int [] accessoryPrice = new int[2];
        for (AccessoryDetails accessoryDetail : accessoryDetails){
            for(String accessory: accessoryDetail.getAccessoryToPriceMap().keySet()){
                if(accessory.equalsIgnoreCase(accessoryName)){
                    accessoryPrice[0] = accessoryDetail.getAccessoryToPriceMap().get(accessory).getFirst();
                    accessoryPrice[1] = accessoryDetail.getAccessoryToPriceMap().get(accessory).getLast();
                    return accessoryPrice;
                }
            }
        }
        return accessoryPrice;
    }

    private void validateAccessoryPrice(String accessory, Integer price) {
        for (AccessoryDetails accessoryDetail : accessoryDetails) {
            for (String accessoryName : accessoryDetail.getAccessoryToPriceMap().keySet()) {
                if (accessoryName.equalsIgnoreCase(accessory)) {
                    if(price < accessoryDetail.getAccessoryToPriceMap().get(accessoryName).getFirst() ||
                            price > accessoryDetail.getAccessoryToPriceMap().get(accessoryName).getLast()){
                        throw new RuntimeException("Given price for accessory " + accessoryName +" is not in range");
                    }
                }
            }
        }
    }

    private List<String> getVariantAndCustInBudget(String variant, int budget, int variantPrice) {
        List<String> variantAndCustList = new ArrayList<>();
        variantAndCustList.add(variant);
        int remainingBudget = budget - variantPrice;
        for (AccessoryDetails accessory : accessoryDetails) {
            for (String accessoryName : accessory.getAccessoryToPriceMap().keySet()) {
                if (accessory.getAccessoryToPriceMap().get(accessoryName).getFirst() <= remainingBudget) {
                    variantAndCustList.add(accessoryName);
                }
            }
        }
        return variantAndCustList;
    }

    private CarDetails fetchCarDetails(String carName) {
        for (CarDetails car : carDetails) {
            if (car.getCarName().equalsIgnoreCase(carName)) {
                return car;
            }
        }
        return null;
    }


    private AccessoryDetails createAccessory(String accessoryType, String[] accessoryName,
                                             int[] minCost, int[] maxCost) {
        AccessoryDetails accessoryDetails = new AccessoryDetails();

        Map<String, List<Integer>> accessoryPriceMap = new HashMap<>();
        for (int index = 0; index < accessoryName.length; index++) {
            List<Integer> priceRange = new ArrayList<>();
            priceRange.add(minCost[index]);
            priceRange.add(maxCost[index]);
            accessoryPriceMap.put(accessoryName[index], priceRange);
        }

        accessoryDetails.setAccessoryType(accessoryType);
        accessoryDetails.setAccessoryToPriceMap(accessoryPriceMap);
        return accessoryDetails;
    }

    private CarDetails createCar(String carName, String[] variantName, int[] variantPrice) {
        CarDetails carDetails = new CarDetails();
        carDetails.setCarName(carName);
        Map<String, Integer> carVariantPriceMap = new HashMap<>();
        for (int index = 0; index < variantName.length; index++) {
            carVariantPriceMap.put(variantName[index], variantPrice[index]);
        }
        carDetails.setVariantToPriceMap(carVariantPriceMap);
        return carDetails;
    }
}