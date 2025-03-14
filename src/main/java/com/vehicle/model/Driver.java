package com.vehicle.model;

public class Driver {
    private int id;
    private String name;
    private String licenseNumber;
    private String phone;
    private Integer assignedVehicleId;

    public Driver(int id, String name, String licenseNumber, String phone, Integer assignedVehicleId) {
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.phone = phone;
        this.assignedVehicleId = assignedVehicleId;
    }
    
    public Driver(int id, String name, String licenseNumber) {
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getAssignedVehicleId() {
        return assignedVehicleId;
    }

    public void setAssignedVehicleId(Integer assignedVehicleId) {
        this.assignedVehicleId = assignedVehicleId;
    }
}
