package com.vehicle.dao;

import com.vehicle.model.Driver;
import com.vehicle.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {
    public void addDriver(Driver driver) {
        String sql = "INSERT INTO drivers (name, license_number, phone) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getLicenseNumber());
            stmt.setString(3, driver.getPhone());
            stmt.executeUpdate();

            System.out.println("✅ Driver added successfully: " + driver.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM drivers";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Driver driver = new Driver(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("license_number"),
                        rs.getString("phone"),
                        rs.getObject("assigned_vehicle_id", Integer.class)
                );
                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drivers;
    }
    
 // ✅ Method to Fetch a Driver by ID
    public Driver getDriverById(int driverId) {
        Driver driver = null;
        String sql = "SELECT * FROM drivers WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, driverId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                driver = new Driver(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("license_number")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
