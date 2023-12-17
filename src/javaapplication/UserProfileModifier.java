/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication;
import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author dev
 */

public class UserProfileModifier {

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Profile Menu");
            System.out.println("1. Update Username");
            System.out.println("2. Update Surname");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    updateUsername();
                    break;
                case 2:
                    updateSurname();
                    break;
                case 3:
                    System.out.println("Exiting the menu...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }
    }

    private static void updateUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new username:");
        String newUsername = scanner.nextLine().trim();

        // Update the database with the new username
        if (updateDatabaseUsername(newUsername)) {
           System.out.println("Username updated successfully.");
        } else {
            System.out.println("Failed to update username.");
        }
    }

    private static void updateSurname() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new surname:");
        String newSurname = scanner.nextLine().trim();

        // Update the database with the new surname
        if (updateDatabaseSurname(newSurname)) {
            System.out.println("Surname updated successfully.");
        } else {
            System.out.println("Failed to update surname.");
        }
    }

    private static boolean updateDatabaseUsername(String newUsername) {
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String updateSQL = "UPDATE users SET username = ? WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
                preparedStatement.setString(1, newUsername);
                preparedStatement.setString(2, getCurrentUserEmail());
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean updateDatabaseSurname(String newSurname) {
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String updateSQL = "UPDATE users SET surname = ? WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
                preparedStatement.setString(1, newSurname);
                preparedStatement.setString(2, getCurrentUserEmail());
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static int getIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Consume the invalid input
        }
        return scanner.nextInt();
    }

    private static String getCurrentUserEmail() {
        return CurrentUserManager.getCurrentUserEmail();
    }
}
