package com.vehicle.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/vehicle_reservation_system";
    private static final String USER = "root"; // Change if needed
    private static final String PASSWORD = "DHUpika123"; // Change if needed
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {  // Reconnect if closed
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Database reconnected successfully!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database connection failed.");
        }
        return connection;
    }
}
