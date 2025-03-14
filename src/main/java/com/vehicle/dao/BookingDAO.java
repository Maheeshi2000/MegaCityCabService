package com.vehicle.dao;

import com.vehicle.model.Booking;
import com.vehicle.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
	
	
	public boolean addBooking(Booking booking) {
	    String sql = "INSERT INTO bookings (booking_number, user_id, vehicle_id, driver_id, pickup_location, drop_location, booking_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, booking.getBookingNumber());
	        stmt.setInt(2, booking.getUserId());
	        stmt.setInt(3, booking.getVehicleId());

	        // ✅ Fix: Set driver_id to NULL if no driver is assigned
	        if (booking.getDriverId() == 0) {
	            stmt.setNull(4, java.sql.Types.INTEGER);
	        } else {
	            stmt.setInt(4, booking.getDriverId());
	        }

	        stmt.setString(5, booking.getPickupLocation());
	        stmt.setString(6, booking.getDropLocation());
	        stmt.setTimestamp(7, new Timestamp(booking.getBookingDate().getTime()));
	        stmt.setString(8, "Pending");

	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	// ✅ Method to check if the user exists in the database
	private boolean checkUserExists(int userId) {
	    String sql = "SELECT id FROM users WHERE id = ?";
	    
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, userId);
	        ResultSet rs = stmt.executeQuery();
	        return rs.next(); // Returns true if user exists
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	
	
	
	// ✅ Fetch only pending bookings
    public List<Booking> getPendingBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";
        try (Connection conn = DBConnection.getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                bookings.add(mapResultSetToBooking(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }


    public boolean assignDriverToBooking(int bookingId, int driverId) {
        String query = "UPDATE bookings SET driver_id = ?, status = 'Accepted' WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setInt(1, driverId);
            preparedStatement.setInt(2, bookingId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // ✅ Returns true if update was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings"; // Get all bookings

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Booking booking = new Booking(
                    rs.getInt("id"),
                    rs.getString("booking_number"),
                    rs.getInt("user_id"),
                    rs.getInt("vehicle_id"),
                    rs.getInt("driver_id"),
                    rs.getString("pickup_location"),
                    rs.getString("drop_location"),
                    rs.getDate("booking_date"),
                    rs.getString("status")
                );
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
    
    public boolean updateBookingStatus(int bookingId, String status) {
        String sql = "UPDATE bookings SET status = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, bookingId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public void moveToAcceptedBookings(int bookingId) {
        String sql = "INSERT INTO accepted_bookings SELECT * FROM bookings WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, bookingId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 // 🔥 **Fetch Booking by ID (For Manage Bookings)**
    public Booking getBookingById(int bookingId) {
        String sql = "SELECT * FROM bookings WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Booking(
                    rs.getInt("id"),
                    rs.getString("booking_number"),
                    rs.getInt("user_id"),
                    rs.getInt("vehicle_id"),
                    rs.getInt("driver_id"),
                    rs.getString("pickup_location"),
                    rs.getString("drop_location"),
                    rs.getDate("booking_date"),
                    rs.getDouble("distance"),
                    rs.getDouble("fare_amount"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Booking> getBookingsByStatus(String status) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE status = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking(
                    rs.getInt("id"),
                    rs.getString("booking_number"),
                    rs.getInt("user_id"),
                    rs.getInt("vehicle_id"),
                    rs.getInt("driver_id"),
                    rs.getString("pickup_location"),
                    rs.getString("drop_location"),
                    rs.getDate("booking_date"),
                    rs.getString("status")
                );
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
    
    
 // ✅ Fetch only accepted bookings
    public List<Booking> getAcceptedBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE status = 'Accepted'";
        try (Connection conn = DBConnection.getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                bookings.add(mapResultSetToBooking(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
    
 // Helper method to map ResultSet to Booking object
    private Booking mapResultSetToBooking(ResultSet rs) throws SQLException {
        return new Booking(
                rs.getInt("id"),
                rs.getString("booking_number"),
                rs.getInt("user_id"),
                rs.getInt("vehicle_id"),
                rs.getInt("driver_id"),
                rs.getString("pickup_location"),
                rs.getString("drop_location"),
                rs.getDate("booking_date"),
                rs.getString("status")
        );
    }
    
    public List<Booking> getAllPendingBookings() {
        List<Booking> pendingBookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE status = 'Pending'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Booking booking = new Booking(
                    rs.getInt("id"),
                    rs.getString("booking_number"),
                    rs.getInt("user_id"),
                    rs.getInt("vehicle_id"),
                    rs.getInt("driver_id"),
                    rs.getString("pickup_location"),
                    rs.getString("drop_location"),
                    rs.getDate("booking_date"),
                    rs.getString("status")
                );
                pendingBookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingBookings;
    }
    
    public List<Booking> getAllAcceptedBookings() {
        List<Booking> acceptedBookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE status = 'Accepted'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Booking booking = new Booking(
                    rs.getInt("id"),
                    rs.getString("booking_number"),
                    rs.getInt("user_id"),
                    rs.getInt("vehicle_id"),
                    rs.getInt("driver_id"),
                    rs.getString("pickup_location"),
                    rs.getString("drop_location"),
                    rs.getDate("booking_date"),
                    rs.getString("status")
                );
                acceptedBookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acceptedBookings;
    }
    
    public int getVehicleIdByBooking(int bookingId) {
        int vehicleId = -1; // Default value if not found
        String query = "SELECT vehicle_id FROM bookings WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bookingId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                vehicleId = resultSet.getInt("vehicle_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleId;
    }
    
    public boolean updateTripStatus(int bookingId, String status) {
        String query = "UPDATE bookings SET status = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, bookingId);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Booking> getAllProgressBookings() {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE status IN ('Accepted', 'In Progress')";
        try (
        	Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                bookings.add(new Booking(
                    rs.getInt("id"),
                    rs.getString("booking_number"),
                    rs.getInt("user_id"),
                    rs.getInt("vehicle_id"),
                    rs.getInt("driver_id"),
                    rs.getString("pickup_location"),
                    rs.getString("drop_location"),
                    rs.getDate("booking_date"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
    
    public double calculateFare(int bookingId) {
        double farePerKm = 0;
        double distance = getTripDistance(bookingId); // Assume this function retrieves distance

        String vehicleType = getVehicleTypeByBookingId(bookingId); // Get vehicle type
        switch (vehicleType) {
            case "Car":
                farePerKm = 80;
                break;
            case "Van":
                farePerKm = 100;
                break;
            case "Bus":
                farePerKm = 150;
                break;
        }
        return distance * farePerKm;
    }
    
    public boolean updateTripDistance(int bookingId, double distance) {
        String query = "UPDATE bookings SET distance = ? WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, distance);
            stmt.setInt(2, bookingId);
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public double getTripDistance(int bookingId) {
        double distance = 0;
        String query = "SELECT distance FROM bookings WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                distance = rs.getDouble("distance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return distance;
    }
    
    public String getVehicleTypeByBookingId(int bookingId) {
        String vehicleType = "";
        String query = "SELECT v.type FROM bookings b INNER JOIN vehicles v ON b.vehicle_id = v.id WHERE b.id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                vehicleType = rs.getString("type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleType;
    }
    
 // ✅ Delete Booking (Only if payment is completed)
    public boolean deleteBooking(int bookingId) {
        String sql = "DELETE FROM bookings WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, bookingId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }





    public List<Booking> getAllCompletedTrips() {
        List<Booking> trips = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE status = 'Completed'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Booking booking = new Booking(
                    rs.getInt("id"),
                    rs.getString("booking_number"),
                    rs.getInt("user_id"),
                    rs.getInt("vehicle_id"),
                    rs.getInt("driver_id"),
                    rs.getString("pickup_location"),
                    rs.getString("drop_location"),
                    rs.getTimestamp("booking_date")
                );

                // ✅ Calculate fare dynamically based on distance (Example: 80 Rs per km)
                double distance = rs.getDouble("distance");
                booking.setFareAmount(distance * 80); // Rs. 80 per km (Modify as needed)

                trips.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }
    
    public boolean updateDistance(int bookingId, double distance) {
        String sql = "UPDATE bookings SET distance = ? WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDouble(1, distance);
            stmt.setInt(2, bookingId);
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateFareAmount(int bookingId) {
        String sql = "UPDATE bookings SET fare_amount = distance * 80 WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookingId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String getCustomerEmailByBookingId(int bookingId) {
        String email = null;
        String query = "SELECT c.email FROM customers c " +
                "JOIN bookings b ON c.id = b.user_id " +  // Ensure correct foreign key relation
                "WHERE b.id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                email = rs.getString("email");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("⚠️ Error fetching customer email: " + e.getMessage());
        }
        return email;
    }

    
    public String getCustomerNameByBookingId(int bookingId) {
        String sql = "SELECT c.fullname FROM customers c JOIN bookings b ON c.id = b.user_id WHERE b.id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("fullname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    


    















}
