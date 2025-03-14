package com.vehicle.model;

import java.util.Date;

public class Booking {
    private int id;
    private String bookingNumber;
    private int userId;
    private int vehicleId;
    private int driverId;
    private String pickupLocation;
    private String dropLocation;
    private Date bookingDate;
    private String status; // Booking status (e.g., Pending, Accepted, Completed, Canceled)

    // Additional attributes for UI display (Manage Booking Page)
    private String customerName;
    private String vehicleModel;
    private String customerEmail;
    private double distance; // ✅ New field
    private double fareAmount; // ✅ New field

    // ✅ Full Constructor (General Use - Booking Process)
    public Booking(int id, String bookingNumber, int userId, int vehicleId, int driverId, 
                   String pickupLocation, String dropLocation, Date bookingDate, String status) {
        this.id = id;
        this.bookingNumber = bookingNumber;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    // ✅ Constructor for Fetching Bookings in Manage Booking Page
    public Booking(int id, String bookingNumber, String customerName, String pickupLocation, 
                   String dropLocation, String vehicleModel, String status) {
        this.id = id;
        this.bookingNumber = bookingNumber;
        this.customerName = customerName;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.vehicleModel = vehicleModel;
        this.status = status;
    }
    
    public Booking(int id, String bookingNumber, String pickupLocation, String dropLocation, String status, String vehicleModel) {
        this.id = id;
        this.bookingNumber = bookingNumber;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.status = status;
        this.vehicleModel = vehicleModel;
    }
    
    public Booking(int id, String bookingNumber, int userId, int vehicleId, String pickupLocation, String dropLocation, Date bookingDate) {
    	this.id = id;
 		this.bookingNumber = bookingNumber;
 		this.userId = userId;
 		this.vehicleId = vehicleId;
 		this.pickupLocation = pickupLocation;
 		this.dropLocation = dropLocation;
 		this.bookingDate = bookingDate;
 		this.status = "Pending"; // Default status
    }
    
    public Booking(int id, String bookingNumber, int userId, int vehicleId, int driverId, String pickupLocation, String dropLocation, Date bookingDate) {
        this.id = id;
        this.bookingNumber = bookingNumber;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.bookingDate = bookingDate;
        this.status = "Pending"; // Default status
    }
    public Booking(int id, String bookingNumber, int userId, int vehicleId, int driverId, 
            String pickupLocation, String dropLocation, Date bookingDate, 
            double distance, double fareAmount, String status) {
 this.id = id;
 this.bookingNumber = bookingNumber;
 this.userId = userId;
 this.vehicleId = vehicleId;
 this.driverId = driverId;
 this.pickupLocation = pickupLocation;
 this.dropLocation = dropLocation;
 this.bookingDate = bookingDate;
 this.distance = distance;
 this.fareAmount = fareAmount;
 this.status = status;
}


    // ✅ Default Constructor
    public Booking() {}

    // ✅ Getters
    public int getId() { return id; }
    public String getBookingNumber() { return bookingNumber; }
    public int getUserId() { return userId; }
    public int getVehicleId() { return vehicleId; }
    public int getDriverId() { return driverId; }
    public String getPickupLocation() { return pickupLocation; }
    public String getDropLocation() { return dropLocation; }
    public Date getBookingDate() { return bookingDate; }
    public String getStatus() { return status; }

    // ✅ Getters for Manage Booking Page
    public String getCustomerName() { return customerName; }
    public String getVehicleModel() { return vehicleModel; }
    public String getCustomerEmail() {  // Ensure this field exists when fetching data
        return customerEmail;
    }

    // ✅ Setters
    public void setId(int id) { this.id = id; }
    public void setBookingNumber(String bookingNumber) { this.bookingNumber = bookingNumber; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    public void setDriverId(int driverId) { this.driverId = driverId; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }
    public void setDropLocation(String dropLocation) { this.dropLocation = dropLocation; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }
    public void setStatus(String status) { this.status = status; }
    

    public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	// ✅ Setters for Manage Booking Page
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setVehicleModel(String vehicleModel) { this.vehicleModel = vehicleModel; }
    
 // ✅ Getter and Setter for Distance
    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }

    // ✅ Getter and Setter for Fare Amount
    public double getFareAmount() { return fareAmount; }
    public void setFareAmount(double fareAmount) { this.fareAmount = fareAmount; }

}
