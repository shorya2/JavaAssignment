public class EMICalculator {
    public static double calculateEMI(double principal, double annualRate, int years) {
        int months = years*12;
        double monthlyRate = annualRate / 12 / 100;
        double emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, months)) / (Math.pow(1 + monthlyRate, months) - 1);
        return emi;
    }
}
