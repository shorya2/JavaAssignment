import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.initializeCar();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your budget: ");
        int budget = scanner.nextInt();
        scanner.nextLine();
        System.out.println(car.carsWithInBudget(budget));

        System.out.println("Select your car: ");
        String carName = scanner.nextLine();
        List<List<String>> variantWithCustomization = (car.returnVariantsWithCustomization(budget, carName));
        for(List<String> variantWithCustomizationList: variantWithCustomization){
            System.out.println("This is variant of car under your budget: ");
            System.out.println(variantWithCustomizationList.getFirst());
            System.out.println("You can select following customization for "+ variantWithCustomizationList.getFirst()+ " variant: ");
            for(int index=1; index<variantWithCustomizationList.size(); index++) {
                System.out.println(index + ". " + variantWithCustomizationList.get(index) + "  " + car.getAccessoryPriceRange(variantWithCustomizationList.get(index))[0] + "-" +
                        car.getAccessoryPriceRange(variantWithCustomizationList.get(index))[1]);

            }
        }

        System.out.println("Enter your car variant: ");
        String variantName = scanner.nextLine();

        System.out.println("Enter number of customization you want: ");
        int numberOfCustom = scanner.nextInt();
        scanner.nextLine();


        Map<String,Integer> accessories = new HashMap<>();
        for(int index=1; index<=numberOfCustom; index++){
            System.out.println("Enter your customization");
            String customization = scanner.nextLine();
            System.out.println("Enter amount for your customization: ");
            Integer customizationAmount = scanner.nextInt();
            scanner.nextLine();
            accessories.put(customization,customizationAmount);
        }

        try{
            System.out.println("The Final price for your car: " + car.finalPrice(carName,variantName, accessories,budget));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}