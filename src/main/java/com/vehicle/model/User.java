package com.vehicle.model;

public class User {
    private int id;
    private String fullname;
    private String username;
    private String email;
    private String role;
    private String password;

    public User(int id, String fullname, String username, String email, String role) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public User(String fullname, String username, String email, String password, String role) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() { return id; }
    public String getFullname() { return fullname; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getPassword() { return password; }
}
