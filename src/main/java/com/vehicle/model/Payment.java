package com.vehicle.model;

import java.util.Date;

public class Payment {
    private int id;
    private int bookingId;
    private int customerId;
    private double amount;
    private String paymentStatus;
    private Date paymentDate;

    // ✅ Default constructor
    public Payment() {
    }

    // ✅ Constructor with all fields
    public Payment(int id, int bookingId, int customerId, double amount, String paymentStatus, Date paymentDate) {
        this.id = id;
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }
    
    

    // ✅ Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    // ✅ ToString method for debugging
    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", bookingId=" + bookingId +
                ", customerId=" + customerId +
                ", amount=" + amount +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
