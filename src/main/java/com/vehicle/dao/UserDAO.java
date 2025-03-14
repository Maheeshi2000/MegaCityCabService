package com.vehicle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.vehicle.util.DBConnection;
import com.vehicle.model.User;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {

    // Register a new user
	public boolean registerUser(User user) {
	    String sql = "INSERT INTO users (fullname, username, password, email, role) VALUES (?, ?, ?, ?, ?)";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        // Hash password before storing
	        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));

	        stmt.setString(1, user.getFullname());
	        stmt.setString(2, user.getUsername());
	        stmt.setString(3, hashedPassword); // Store hashed password
	        stmt.setString(4, user.getEmail());
	        stmt.setString(5, user.getRole());

	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}


    // Authenticate user login
    public User authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() && BCrypt.checkpw(password, rs.getString("password"))) {
                return new User(rs.getInt("id"), rs.getString("fullname"), rs.getString("username"),
                        rs.getString("email"), rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            System.out.println("🔹 Executing Query: " + stmt.toString()); // Debugging Log
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("✅ User found in DB: " + rs.getString("username")); // Debugging Log
                return new User(
                    rs.getString("fullname"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"), // Make sure this is the hashed password
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("❌ User not found in DB!"); // Debugging Log
        return null;
    }

}
