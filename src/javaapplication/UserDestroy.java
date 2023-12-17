/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author dev
 */
public class UserDestroy {
    
    // Function to delete a user by ID
    public static void deleteUserByID() {
        Scanner scanner = new Scanner(System.in);
        boolean deleteAnother = true;

        while (deleteAnother) {
            System.out.println("Enter user ID to delete:");
            int userID = scanner.nextInt();

            if (isUserExists(userID)) {
                deleteUser(userID);
                System.out.println("User with ID " + userID + " has been deleted.");
            } else {
                System.out.println("User with ID " + userID + " does not exist in the database.");
            }

            System.out.println("Do you want to delete another user? (y/n)");
            String choice = scanner.next();

            deleteAnother = choice.equalsIgnoreCase("y");
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
