public class HomeAppreciationCalculator {
    public static double calculateAppreciationCost(double intialValue, double appreciationRate, int years){
        return intialValue * (Math.pow((1+appreciationRate),years));
    }
}
