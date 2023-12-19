/*
 * This class manages the current user's email and provides functionality to determine if the user is an admin.
 */
package javaapplication;

import java.sql.*;

/**
 * The CurrentUserManager class handles the current user's email and admin status.
 * It allows setting and retrieving the current user's email and checks if the user is an admin based on the stored email.
 */
public class CurrentUserManager {
    private static String currentUserEmail;

    // Private constructor to prevent instantiation
    private CurrentUserManager() {
    }

    /**
     * Sets the current user's email.
     * @param email The email of the current user.
     */
    public static void setCurrentUserEmail(String email) {
        currentUserEmail = email;
    }

    /**
     * Retrieves the current user's email.
     * @return The email of the current user.
     */
    public static String getCurrentUserEmail() {
        return currentUserEmail;
    }

    /**
     * Checks if the current user is an admin by querying the database.
     * @return true if the current user is an admin, false otherwise.
     */
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
