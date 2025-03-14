package com.vehicle.dao;

import com.vehicle.model.Vehicle;
import com.vehicle.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
	
	
	// ✅ Method to add a vehicle
	public boolean addVehicle(Vehicle vehicle) {
	    String sql = "INSERT INTO vehicles (vehicle_number, model, type, seating_capacity, availability) VALUES (?, ?, ?, ?, ?)";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, vehicle.getVehicleNumber());
	        stmt.setString(2, vehicle.getModel());
	        stmt.setString(3, vehicle.getType());
	        stmt.setInt(4, vehicle.getSeatingCapacity());
	        stmt.setInt(5, vehicle.getAvailability()); // 1 for available, 0 for unavailable

	        int rows = stmt.executeUpdate();
	        return rows > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
	
	// ✅ Method to delete a vehicle by vehicle number
    public boolean deleteVehicle(String vehicleNumber) {
        String sql = "DELETE FROM vehicles WHERE vehicle_number = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicleNumber);
            return stmt.executeUpdate() > 0; // ✅ Returns true if deletion was successful

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	
	// ✅ Get all vehicles (Both available and unavailable)
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles"; // Get all vehicles

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
                    rs.getInt("id"),
                    rs.getString("vehicle_number"),
                    rs.getString("model"),
                    rs.getString("type"),
                    rs.getInt("seating_capacity"),
                    rs.getInt("availability") // 1 = Available, 0 = Unavailable
                );
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public boolean updateVehicleAvailability(int vehicleId, int availability) {
        String sql = "UPDATE vehicles SET availability = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, availability);
            pstmt.setInt(2, vehicleId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean isVehicleAvailable(int vehicleId) {
        String query = "SELECT availability FROM vehicles WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("availability") == 1; // ✅ Available if 1
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }





    // Update vehicle availability (1 for Available, 0 for Unavailable)
    
    
    
}
