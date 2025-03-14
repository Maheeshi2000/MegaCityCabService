package com.vehicle.dao;

import com.vehicle.model.Payment;
import com.vehicle.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    private Connection conn;

    public PaymentDAO() {
        conn = DBConnection.getConnection();
    }

    // ✅ Retrieve Completed Trips Awaiting Payment
    public List<Payment> getAllCompletedTrips() {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payments WHERE payment_status = 'Pending'";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                payments.add(new Payment(
                    rs.getInt("id"),
                    rs.getInt("booking_id"),
                    rs.getInt("customer_id"),
                    rs.getDouble("amount"),
                    rs.getString("payment_status"),
                    rs.getTimestamp("payment_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    // ✅ Mark Payment as Completed
    public boolean markPaymentAsCompleted(int paymentId) {
        String query = "UPDATE payments SET payment_status = 'Paid' WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, paymentId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Get Booking ID by Payment ID
    public int getBookingIdByPayment(int paymentId) {
        String query = "SELECT booking_id FROM payments WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, paymentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("booking_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
 // ✅ Get All Pending Payments
    public List<Payment> getAllPendingPayments() {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payments WHERE payment_status = 'Pending'";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                payments.add(new Payment(
                    rs.getInt("id"),
                    rs.getInt("booking_id"),
                    rs.getInt("customer_id"),
                    rs.getDouble("amount"),
                    rs.getString("payment_status"),
                    rs.getTimestamp("payment_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
    
    public boolean addPayment(Payment payment) {
        String sql = "INSERT INTO payments (booking_id, amount, payment_status, payment_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, payment.getBookingId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setString(3, payment.getPaymentStatus());
            stmt.setTimestamp(4, new Timestamp(payment.getPaymentDate().getTime()));

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Payment added successfully for Booking ID: " + payment.getBookingId());
                return true;
            } else {
                System.err.println("❌ Payment insertion failed for Booking ID: " + payment.getBookingId());
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}


