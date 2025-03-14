package com.vehicle.controller;

import com.vehicle.dao.BookingDAO;
import com.vehicle.dao.VehicleDAO;
import com.vehicle.model.Booking;
import com.vehicle.util.EmailSender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDAO bookingDAO;
    private VehicleDAO vehicleDAO;

    public void init() {
        bookingDAO = new BookingDAO();
        vehicleDAO = new VehicleDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("customerLogin.jsp?error=Please log in first");
            return;
        }

        // Retrieve customer details
        int userId = (int) session.getAttribute("userId");
        String customerName = (String) session.getAttribute("fullname");
        String email = (String) session.getAttribute("email");

        // Retrieve booking details
        String pickupLocation = request.getParameter("pickupLocation");
        String dropLocation = request.getParameter("dropLocation");
        String vehicleIdParam = request.getParameter("vehicleId");

        // ✅ Debug Logging: Print received values
        System.out.println("Booking Request:");
        System.out.println("User ID: " + userId);
        System.out.println("Pickup Location: " + pickupLocation);
        System.out.println("Drop Location: " + dropLocation);
        System.out.println("Vehicle ID: " + vehicleIdParam);

        // ✅ Validate required parameters
        if (pickupLocation == null || pickupLocation.trim().isEmpty() ||
            dropLocation == null || dropLocation.trim().isEmpty() ||
            vehicleIdParam == null || vehicleIdParam.trim().isEmpty()) {
            response.sendRedirect("makeBooking.jsp?error=All fields are required!");
            return;
        }

        int vehicleId;
        try {
            vehicleId = Integer.parseInt(vehicleIdParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("makeBooking.jsp?error=Invalid vehicle selection!");
            return;
        }

        // ✅ Check if vehicle is available
        boolean isAvailable = vehicleDAO.isVehicleAvailable(vehicleId);
        if (!isAvailable) {
            response.sendRedirect("makeBooking.jsp?error=Selected vehicle is not available!");
            return;
        }

        // ✅ Generate booking number
        String bookingNumber = UUID.randomUUID().toString().substring(0, 8);
        Booking booking = new Booking(0, bookingNumber, userId, vehicleId, 0, pickupLocation, dropLocation, new Date());

        // ✅ Insert booking into the database
        boolean bookingSuccess = bookingDAO.addBooking(booking);
        if (bookingSuccess) {
            // ✅ Mark vehicle as unavailable
            vehicleDAO.updateVehicleAvailability(vehicleId, 0);

            // ✅ Send email confirmation (Handled safely)
            try {
                String emailContent = "Dear " + customerName + ",\n\n"
                        + "Your booking has been successfully placed!\n\n"
                        + "Booking Details:\n"
                        + "📌 Booking Number: " + bookingNumber + "\n"
                        + "📍 Pickup Location: " + pickupLocation + "\n"
                        + "📍 Drop Location: " + dropLocation + "\n"
                        + "🚗 Selected Vehicle ID: " + vehicleId + "\n"
                        + "📆 Date: " + new Date() + "\n\n"
                        + "Thank you for choosing our service!\n"
                        + "Best Regards,\nMegaCityCab Team";

                EmailSender.sendEmail(email, "Booking Confirmation - " + bookingNumber, emailContent);
            } catch (Exception e) {
                System.err.println("⚠️ Email sending failed: " + e.getMessage());
            }

            response.sendRedirect("customerDashboard.jsp?success=Booking placed successfully");
        } else {
            response.sendRedirect("makeBooking.jsp?error=Failed to place booking");
        }
    }
}
