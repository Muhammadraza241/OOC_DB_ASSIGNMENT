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
public class TaxCalculator {

    public static void calculate_tax() {
        Scanner scanner = new Scanner(System.in);

        // Get user input
       String name;
        do {
            System.out.println("Enter your name: ");
            name = scanner.nextLine().trim();
        } while (name.isEmpty());
        
        double grossIncome;
        do {
            System.out.println("Enter your gross income: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid number for gross income.");
                scanner.next(); // consume the invalid input
            }
            grossIncome = scanner.nextDouble();
        } while (grossIncome <= 0);

       double taxCredits;
        do {
            System.out.println("Enter your tax credits: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid number for tax credits.");
                scanner.next(); // consume the invalid input
            }
            taxCredits = scanner.nextDouble();
        } while (taxCredits < 0);

        // Calculate taxes
        double incomeTax = calculateIncomeTax(grossIncome);
        double usc = calculateUSC(grossIncome);
        double prsi = calculatePRSI(grossIncome);

        double totalTax = incomeTax + usc + prsi;

        // Store information in the database
        saveToDatabase(name, grossIncome, taxCredits, totalTax);
    }

    private static double calculateIncomeTax(double grossIncome) {
        // For simplicity, let's assume a fixed rate for income tax
        return 0.3 * grossIncome;
    }

    private static double calculateUSC(double grossIncome) {
        // For simplicity, let's assume a fixed rate for USC
        return 0.05 * grossIncome;
    }

    private static double calculatePRSI(double grossIncome) {
        // For simplicity, let's assume a fixed rate for PRSI
        return 0.02 * grossIncome;
    }

    private static void saveToDatabase(String name, double grossIncome, double taxCredits, double totalTax) {
        try {
            String url = "jdbc:mysql://localhost:3306/myDatabase";
            String username = "root";
            String password = "";

            Connection connection = DriverManager.getConnection(url, username, password);

            // SQL query to insert data into the database
            String sql = "INSERT INTO tax_records (name, gross_income, tax_credits, total_tax) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, grossIncome);
            preparedStatement.setDouble(3, taxCredits);
            preparedStatement.setDouble(4, totalTax);

            // Execute the query
            preparedStatement.executeUpdate();

            // Close the connections
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
