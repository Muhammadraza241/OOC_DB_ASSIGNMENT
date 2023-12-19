/*
 * This class represents the menu for a normal user with specific options like modifying profile and tax calculations.
 */
package javaapplication;

import java.util.Scanner;

/**
 * The NormalUserMenu class provides a menu interface for a normal user.
 * Options include modifying their profile, performing tax calculation operations, and exiting the menu.
 * The menu is displayed in a loop until the user chooses to exit.
 */
public class NormalUserMenu {

    /**
     * Displays the menu options for a normal user and processes user input accordingly.
     */
    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        String email = CurrentUserManager.getCurrentUserEmail();

        // Display menu loop
        while (!exit) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("Welcome " + email);
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("1. Modify Your Own Profile");
            System.out.println("2. Tax Calculation Operations");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            // Get integer input from user
            int choice = getIntInput(scanner);

            // Process user choice
            switch (choice) {
                case 1:
                    System.out.println("Option 1: Modifying Your Own Profile");
                    UserProfileModifier.menu();
                    break;
                case 2:
                    System.out.println("Option 2: Tax Calculation Operations");
                    TaxCalculator.calculate_tax();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting the menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }
    }

    /**
     * Helper method to validate and get integer input from the user.
     *
     * @param scanner The scanner object used for input.
     * @return The integer input obtained from the user.
     */
    private static int getIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Consume the invalid input
        }
        return scanner.nextInt();
    }
}
