public class AmortizationCal {
    public static void calculateAmortization(double principal, double annualRate, int years) {
        double monthlyRate = annualRate / 12 / 100;
        double emi = EMICalculator.calculateEMI(principal, annualRate, years);
        int months = years*12;

        double remainingBalance = principal;

        for (int month = 1; month <= months; month++) {
            double interestPayment = remainingBalance * monthlyRate;
            double principalPayment = emi - interestPayment;
            remainingBalance -= principalPayment;

            System.out.printf("%4d  | %.2f        | %.2f              | %.2f              | %.2f\n",
                    month, emi, principalPayment, interestPayment, remainingBalance);
        }
    }
}
