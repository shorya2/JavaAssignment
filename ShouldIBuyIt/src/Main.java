import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Home price:");
        double capital = scanner.nextDouble();

        System.out.println("Enter the loan interest rate: ");
        double loanInterestRate = scanner.nextDouble();

        System.out.println("Enter the loan tenure in years: ");
        int loanTenureYears = scanner.nextInt();

        System.out.println("Enter the monthly SIP contribution: ");
        double sipContribution = scanner.nextDouble();

        System.out.println("Enter the annual return rate for SIP (in %): ");
        double sipReturnRate = scanner.nextDouble();

        System.out.println("Enter the salvage value: ");
        double salvageValue = scanner.nextDouble();

        System.out.println("Enter appreciation rate: ");
        double appreciationRate = scanner.nextDouble();

        // Home Loan EMI Calculation
        double emi = EMICalculator.calculateEMI(capital, loanInterestRate, loanTenureYears);
        double totalHomeLoanCost = emi * loanTenureYears * 12;

        // Depreciation Calculation (Asset Depreciation)
        double depreciatedValue = HomeDepreciationCalculator.calculateDepreciationValue(capital, salvageValue, loanTenureYears);

        // Appreciation Calculation (Asset Appreciation)
        double appreciatedValue = HomeAppreciationCalculator.calculateAppreciationCost(capital, appreciationRate, loanTenureYears);

        // SIP Calculation
        double totalSIPInvestment = SipCalculator.calculateSIP(sipContribution, sipReturnRate, loanTenureYears);

        // Output Results
        System.out.println("\n--- Home Loan Details ---");
        System.out.println("EMI: " + emi);
        System.out.println("Total Loan Cost over " + loanTenureYears + " years: " + totalHomeLoanCost);
        System.out.println("Depreciated value of asset after " + loanTenureYears + " years: " + depreciatedValue);
        System.out.println("Appreciated value of asset after " + loanTenureYears + " years: " + appreciatedValue);

        System.out.println("\n--- SIP Investment Details ---");
        System.out.println("Total SIP Investment after " + loanTenureYears + " years: " + totalSIPInvestment);

        // Calculate Amortization Schedule
        AmortizationCal.calculateAmortization(capital, loanInterestRate, loanTenureYears);

        // Final Comparison Summary
        System.out.println("\n--- Summary ---");
        if (totalSIPInvestment > appreciatedValue) {
            System.out.println("SIP investment gives a higher return than the asset appreciation.");
        } else {
            System.out.println("Asset appreciation gives a higher return than SIP investment.");
        }

        scanner.close();
    }


}