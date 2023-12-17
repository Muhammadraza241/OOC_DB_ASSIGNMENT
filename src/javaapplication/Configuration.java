/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dev
 */
public class Configuration {
    
    public static void databaseConnection() {
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String query = "create database myDatabase";
            statement.execute(query);
            System.out.println("Database connected successfully");
            connection.close();
        } catch (SQLException e) {
            if (e.getSQLState().equals("HY000")) {
                System.out.println("Database already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }
    
}
