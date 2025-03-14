package com.vehicle.controller;

import com.vehicle.dao.VehicleDAO;
import com.vehicle.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/VehicleServlet")
public class VehicleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VehicleDAO vehicleDAO;

    public void init() {
        vehicleDAO = new VehicleDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch all vehicles for both booking and vehicle management
        List<Vehicle> vehicles = vehicleDAO.getAllVehicles();

        request.setAttribute("vehicles", vehicles);

        // Determine the page to forward the data
        String targetPage = request.getParameter("page");

        if ("booking".equals(targetPage)) {
            request.getRequestDispatcher("makeBooking.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("fetchVehicles.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("🚀 VehicleServlet called - Action: " + action); // Debugging Log

        if ("add".equals(action)) {
            String vehicleNumber = request.getParameter("vehicleNumber");
            String model = request.getParameter("model");
            String type = request.getParameter("type").trim();
            int seatingCapacity = Integer.parseInt(request.getParameter("seatingCapacity"));

            // Convert "true"/"false" to 1/0 for availability
            int availability = request.getParameter("availability") != null &&
                    request.getParameter("availability").equals("true") ? 1 : 0;

            System.out.println("📌 Received Data -> Number: " + vehicleNumber + ", Model: " + model +
                    ", Type: " + type + ", Seats: " + seatingCapacity + ", Available: " + availability);

            Vehicle vehicle = new Vehicle(0, vehicleNumber, model, type, seatingCapacity, availability);

            boolean success = vehicleDAO.addVehicle(vehicle);
            System.out.println("✅ Insert Success: " + success);

            if (success) {
                response.sendRedirect("vehicles.jsp?success=Vehicle added");
            } else {
                response.sendRedirect("vehicles.jsp?error=Failed to add vehicle");
            }
        }
    }
}
