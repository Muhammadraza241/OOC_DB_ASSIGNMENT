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
public class AdminUserMenu {
    
    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        String email = CurrentUserManager.getCurrentUserEmail();

        while (!exit) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("Welcome " + email);
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("1. List all users");
            System.out.println("2. Remove a user from the system");
            System.out.println("3. Modify Your Own Profile");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                switch (choice) {
                    case 1:
                        System.out.println("List of all users selected.");
                        User.displayAllUsers();
                        break;
                    case 2:
                        System.out.println("Remove other users from the system selected.");
                        UserDestroy.deleteUserByID();
                        break;
                    case 3:
                        UserProfileModifier.menu();
                        break;
                    case 4:
                        exit = true;
                        System.out.println("Exiting the system...");
                        break;
                    default:
                        System.out.println("Invalid option. Please choose again.");
                        break;
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }
    
}
