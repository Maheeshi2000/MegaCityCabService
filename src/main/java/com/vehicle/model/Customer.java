package com.vehicle.model;

public class Customer {
    private int id;
    private String fullname;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String address;

    public Customer(int id, String fullname, String username, String email, String password, String phone, String address) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    // Getters
    public int getId() { return id; }
    public String getFullname() { return fullname; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
}
