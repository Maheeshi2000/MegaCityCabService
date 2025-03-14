package com.vehicle.model;

public class Vehicle {
    private int id;
    private String vehicleNumber;
    private String model;
    private String type;
    private int seatingCapacity;
    private int availability; // 1 = Available, 0 = Unavailable

    // ✅ Default Constructor
    public Vehicle() {
    }
    
    

    // ✅ Constructor used in `VehicleDAO.getAvailableVehicles()`
    public Vehicle(int id, String vehicleNumber, String model, int availability) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.model = model;
        this.availability = availability;
    }

    // ✅ Full Constructor (If needed for other functionalities)
    public Vehicle(int id, String vehicleNumber, String model, String type, int seatingCapacity, int availability) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.model = model;
        this.type = type;
        this.seatingCapacity = seatingCapacity;
        this.availability = availability;
    }

    // ✅ Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
    
    // ✅ Add this method
    public boolean isAvailable() {
        return availability == 1; // Returns true if availability = 1
    }

    
}
