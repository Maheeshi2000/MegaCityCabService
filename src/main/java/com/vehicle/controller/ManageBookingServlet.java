package com.vehicle.controller;

import com.vehicle.dao.BookingDAO;
import com.vehicle.dao.DriverDAO;
import com.vehicle.dao.PaymentDAO;
import com.vehicle.dao.VehicleDAO;
import com.vehicle.model.Booking;
import com.vehicle.model.Driver;
import com.vehicle.model.Payment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/ManageBookingServlet")
public class ManageBookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDAO bookingDAO;
    private DriverDAO driverDAO;
    private VehicleDAO vehicleDAO;

    public void init() {
        bookingDAO = new BookingDAO();
        driverDAO = new DriverDAO();
        vehicleDAO = new VehicleDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        response.setContentType("text/html");

        if ("pending".equals(type)) {
            List<Booking> pendingBookings = bookingDAO.getAllPendingBookings();
            response.getWriter().write(generateTableRows(pendingBookings, true)); // ✅ Return Pending Bookings
        } else if ("accepted".equals(type)) {
            List<Booking> acceptedBookings = bookingDAO.getAllAcceptedBookings();
            response.getWriter().write(generateTableRows(acceptedBookings, false)); // ✅ Return Accepted Bookings
        }
    }



    private String generateTableRows(List<Booking> bookings, boolean isPending) {
        StringBuilder tableRows = new StringBuilder();
        
        for (Booking booking : bookings) {
            tableRows.append("<tr>");
            tableRows.append("<td>").append(booking.getBookingNumber()).append("</td>");
            tableRows.append("<td>").append(booking.getUserId()).append("</td>");
            tableRows.append("<td>").append(booking.getPickupLocation()).append("</td>");
            tableRows.append("<td>").append(booking.getDropLocation()).append("</td>");
            tableRows.append("<td>").append(booking.getVehicleId()).append("</td>");
            tableRows.append("<td>").append(booking.getDriverId() == 0 ? "<span style='color: red;'>Not Assigned</span>" : booking.getDriverId()).append("</td>");
            tableRows.append("<td>").append(booking.getStatus()).append("</td>");

            // ✅ Show "Assign Driver" button only for Pending Bookings
            if (isPending) {  
                tableRows.append("<td>");
                tableRows.append("<form action='ManageBookingServlet' method='post'>");
                tableRows.append("<input type='hidden' name='bookingId' value='").append(booking.getId()).append("'>");
                tableRows.append("<label>Select Driver:</label>");
                tableRows.append("<select name='driverId'>");

                // Fetch all available drivers
                List<Driver> drivers = driverDAO.getAllDrivers(); // ✅ Ensure drivers are fetched
                if (drivers != null) {
                    for (Driver driver : drivers) {
                        tableRows.append("<option value='").append(driver.getId()).append("'>").append(driver.getName()).append("</option>");
                    }
                }

                tableRows.append("</select>");
                tableRows.append("<button type='submit'>Assign Driver</button>");
                tableRows.append("</form>");
                tableRows.append("</td>");
            } 
            // ✅ Show "Update Progress" button for Accepted/In Progress Bookings
            else if ("Accepted".equals(booking.getStatus()) || "In Progress".equals(booking.getStatus())) {
                tableRows.append("<td>");
                tableRows.append("<form action='TripProgressServlet' method='post'>");
                tableRows.append("<input type='hidden' name='bookingId' value='").append(booking.getId()).append("'>");
                tableRows.append("<select name='newStatus'>");
                tableRows.append("<option value='In Progress' ").append("In Progress".equals(booking.getStatus()) ? "selected" : "").append(">In Progress</option>");
                tableRows.append("<option value='Completed'>Completed</option>");
                tableRows.append("</select>");
                tableRows.append("<button type='submit'>Update</button>");
                tableRows.append("</form>");
                tableRows.append("</td>");
            }

            tableRows.append("</tr>");
        }
        return tableRows.toString();
    }



    
    private String generateTripProgressTable(List<Booking> bookings) {
        StringBuilder tableRows = new StringBuilder();
        for (Booking booking : bookings) {
            tableRows.append("<tr>");
            tableRows.append("<td>").append(booking.getBookingNumber()).append("</td>");
            tableRows.append("<td>").append(booking.getUserId()).append("</td>");
            tableRows.append("<td>").append(booking.getPickupLocation()).append("</td>");
            tableRows.append("<td>").append(booking.getDropLocation()).append("</td>");
            tableRows.append("<td>").append(booking.getVehicleId()).append("</td>");
            tableRows.append("<td>").append(booking.getDriverId()).append("</td>");
            tableRows.append("<td>").append(booking.getStatus()).append("</td>");

            tableRows.append("<td>");
            tableRows.append("<form action='UpdateTripServlet' method='post'>");
            tableRows.append("<input type='hidden' name='bookingId' value='").append(booking.getId()).append("'>");
            tableRows.append("<select name='newStatus'>");
            tableRows.append("<option value='In Progress'>In Progress</option>");
            tableRows.append("<option value='Completed'>Completed</option>");
            tableRows.append("</select>");
            tableRows.append("<button type='submit'>Update</button>");
            tableRows.append("</form>");
            tableRows.append("</td>");

            tableRows.append("</tr>");
        }
        return tableRows.toString();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookingIdStr = request.getParameter("bookingId");
        String driverIdStr = request.getParameter("driverId");

        if (bookingIdStr == null || driverIdStr == null || bookingIdStr.trim().isEmpty() || driverIdStr.trim().isEmpty()) {
            response.sendRedirect("manageBookings.jsp?error=Missing booking or driver parameter");
            return;
        }

        int bookingId, driverId;
        try {
            bookingId = Integer.parseInt(bookingIdStr);
            driverId = Integer.parseInt(driverIdStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("manageBookings.jsp?error=Invalid number format");
            return;
        }

        boolean success = bookingDAO.assignDriverToBooking(bookingId, driverId);
        if (success) {
            bookingDAO.updateBookingStatus(bookingId, "Accepted");

            // ✅ Fix: Ensure getVehicleIdByBooking method exists in BookingDAO
            int vehicleId = bookingDAO.getVehicleIdByBooking(bookingId);
            if (vehicleId > 0) {
                vehicleDAO.updateVehicleAvailability(vehicleId, 0);
            }

            response.sendRedirect("manageBookings.jsp?success=Booking updated successfully");
        } else {
            response.sendRedirect("manageBookings.jsp?error=Failed to update booking");
        }
        
    }
    
    

}