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

public class MainMenu {

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("Welcome to our Application");
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character after reading the integer

                switch (choice) {
                    case 1:
                        registerUser();
                        break;
                    case 2:
                        loginUser();
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Exiting the application...");
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
    
    private static void registerUser() {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Please enter the below details to register a new user...");
        System.out.println("-------------------------------------------------------------------------------");
        // Code for user registration
        boolean registered = UserRegister.createUserFromConsoleInput();
        if (registered) {
            System.out.println("User registered successfully!");
            NormalUserMenu.displayMenu();
        } else {
            System.out.println("User registration failed.");
        }
    }

    private static void loginUser() {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Please enter the below details to login a new user...");
        System.out.println("-------------------------------------------------------------------------------");
        // Code for logging user in
        boolean loggedIn = UserLogin.loginUser();
        if (loggedIn) {
            boolean isAdmin =  CurrentUserManager.isCurrentUserAdmin();
            System.out.println("User logged in successfully!");
            if(isAdmin){
                AdminUserMenu.displayMenu();
            }else{
                NormalUserMenu.displayMenu();
            }  
        } else {
            System.out.println("Login failed. Invalid email or password.");
        }
        
    }
}
