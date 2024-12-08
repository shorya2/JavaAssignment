public class SipCalculator {
    public static double calculateSIP(double monthlyValue, double annualRate, int years){
        double monthlyRate = annualRate/12/100;
        int totalMonths = years*12;
        double futureValue = monthlyValue * (Math.pow(1+monthlyRate,totalMonths)-1)/monthlyRate*(1+monthlyRate);
        return futureValue;
    }
}
