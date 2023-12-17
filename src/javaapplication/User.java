/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author dev
 */
public class User {
    
    public static void createUsersTable() {
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

     
            String createTableSQL = "CREATE TABLE users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(50) NOT NULL," +
                "surname VARCHAR(50) NOT NULL," +
                "email VARCHAR(50) NOT NULL," +
                "password VARCHAR(50) NOT NULL," +
                "is_admin BOOLEAN NOT NULL DEFAULT false)";

            statement.executeUpdate(createTableSQL);
            System.out.println("Users table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to create a single user record
    public static boolean createUser(String username, String surname, String email, String password, boolean isAdmin) {
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            // SQL statement to insert a user into the users table
            String insertSQL = "INSERT INTO users (username, surname, email, password, is_admin) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, surname);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                preparedStatement.setBoolean(5, isAdmin);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("User record created successfully.");
                    return true; // Return true if user creation was successful
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if user creation failed
    }
    
    // Method to display all users record
    public static void displayAllUsers() {
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String selectSQL = "SELECT * FROM users";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                // Displaying users in a table format
                System.out.println("-------------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-15s | %-15s | %-20s | %-8s |\n", "ID", "Username", "Surname", "Email", "isAdmin");
                System.out.println("---------------------------------------------------------------------------------");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String surname = resultSet.getString("surname");
                    String email = resultSet.getString("email");
                    boolean isAdmin = resultSet.getBoolean("is_admin");

                    System.out.printf("| %-5d | %-15s | %-15s | %-20s | %-8b |\n", id, username, surname, email, isAdmin);
                }
                System.out.println("---------------------------------------------------------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Function to delete a user by ID
    public static void deleteUserByID() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter user ID to delete:");
        int userID = scanner.nextInt();

        if (isUserExists(userID)) {
            deleteUser(userID);
            System.out.println("User with ID " + userID + " has been deleted.");
        } else {
            System.out.println("User with ID " + userID + " does not exist in the database.");
        }
    }
    
    // Function to check if user exists by ID
    private static boolean isUserExists(int userID) {
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String selectSQL = "SELECT COUNT(*) AS count FROM users WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
                preparedStatement.setInt(1, userID);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
     // Function to delete user by ID
    private static void deleteUser(int userID) {
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String deleteSQL = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
                preparedStatement.setInt(1, userID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
