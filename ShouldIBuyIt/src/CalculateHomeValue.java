public class CalculateHomeValue {
    public static double getHomeValue(double initialValue, double salvageValue, double appreciateRate, int years){
        double appreciatedValue = HomeAppreciationCalculator.calculateAppreciationCost(initialValue,appreciateRate,years);
        double depreciatedValue = HomeDepreciationCalculator.calculateDepreciationValue(initialValue,salvageValue,years);
        return appreciatedValue-depreciatedValue;
    }
}
