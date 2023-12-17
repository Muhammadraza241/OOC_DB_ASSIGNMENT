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
public class UserRegister {
    
    // Method to take user inputs from console and create a user
    public static boolean createUserFromConsoleInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username:");
        String username = getInputWithValidation(scanner, "Username");

        System.out.println("Enter surname:");
        String surname = getInputWithValidation(scanner, "Surname");

        String email;
        boolean isUnique;
        do {
            email = getInputWithValidation(scanner, "Email");
            isUnique = isEmailUnique(email);
            if (!isUnique) {
                System.out.println("Email is already taken. Please enter a different email.");
            }
        } while (!isUnique);

        System.out.println("Enter password:");
        String password = getInputWithValidation(scanner, "Password");

        boolean isAdmin = false;

        CurrentUserManager.setCurrentUserEmail(email);
        // Call the createUser function with inputs from console
        return User.createUser(username, surname, email, password, isAdmin);
    }
    
    
    // Function to get input from console with validation
    private static String getInputWithValidation(Scanner scanner, String fieldName) {
        String input;
        do {
            System.out.print(fieldName + ": ");
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(fieldName + " cannot be empty. Please enter a value.");
            }
        } while (input.isEmpty());
        return input;
    }
    

    // Function to check if email is unique in the database
    private static boolean isEmailUnique(String email) {
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String selectSQL = "SELECT COUNT(*) AS count FROM users WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count == 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
