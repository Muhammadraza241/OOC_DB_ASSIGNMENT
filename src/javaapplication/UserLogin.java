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
public class UserLogin {
    
    public static boolean loginUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your email:");
        String email = scanner.nextLine().trim(); // Trim extra spaces

        System.out.println("Enter your password:");
        String password = scanner.nextLine().trim(); // Trim extra spaces

        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String selectSQL = "SELECT * FROM users WHERE email = ? AND password = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                CurrentUserManager.setCurrentUserEmail(email);
                return resultSet.next(); // Returns true if login is successful, false otherwise
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if an exception occurs
    }
}
