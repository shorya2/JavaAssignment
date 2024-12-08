public class HomeDepreciationCalculator {
    public static double calculateDepreciationValue(double intialValue, double salvageValue, int years){
        double depreciationRate = (intialValue - salvageValue)/ years;
        double depreciatedValue = depreciationRate * years;
        return depreciatedValue;
    }
}
