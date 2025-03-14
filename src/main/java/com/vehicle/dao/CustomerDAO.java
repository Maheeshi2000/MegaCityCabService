package com.vehicle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vehicle.model.Customer;
import com.vehicle.util.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

public class CustomerDAO {

    // Register a new customer
    public boolean registerCustomer(Customer customer) {
        String sql = "INSERT INTO customers (fullname, username, email, password, phone, address) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getFullname());
            stmt.setString(2, customer.getUsername());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPassword()); // Hashed password
            stmt.setString(5, customer.getPhone());
            stmt.setString(6, customer.getAddress());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Authenticate customer login
    public Customer authenticateCustomer(String username, String password) {
        String sql = "SELECT * FROM customers WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() && BCrypt.checkpw(password, rs.getString("password"))) {
                return new Customer(
                    rs.getInt("id"),
                    rs.getString("fullname"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone"),
                    rs.getString("address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
