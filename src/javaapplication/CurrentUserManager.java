/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication;
import java.sql.*;

/**
 *
 * @author dev
 */
public class CurrentUserManager {
    private static String currentUserEmail;

    // Private constructor to prevent instantiation
    private CurrentUserManager() {
    }

    public static void setCurrentUserEmail(String email) {
        currentUserEmail = email;
    }

    public static String getCurrentUserEmail() {
        return currentUserEmail;
    }
    
    public static boolean isCurrentUserAdmin() {
        // Fetch user role from the database based on the stored email
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String query = "SELECT is_admin FROM users WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, getCurrentUserEmail());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // If the user is found, check if they are an admin
                        return resultSet.getBoolean("is_admin");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
}
