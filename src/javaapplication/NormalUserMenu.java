/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication;
import java.util.Scanner;


/**
 *
 * @author dev
 */

public class NormalUserMenu {

    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        String email = CurrentUserManager.getCurrentUserEmail();

        while (!exit) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("Welcome " + email);
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("1. Modify Your Own Profile");
            System.out.println("2. Tax Calculation Operations");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = getIntInput(scanner);

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

    private static int getIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Consume the invalid input
        }
        return scanner.nextInt();
    }
}
