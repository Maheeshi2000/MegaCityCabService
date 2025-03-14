package com.vehicle.controller;

import com.vehicle.dao.BookingDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TripProgressServlet")
public class TripProgressServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDAO bookingDAO;

    public void init() {
        bookingDAO = new BookingDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookingIdStr = request.getParameter("bookingId");
        String newStatus = request.getParameter("newStatus");

        if (bookingIdStr == null || newStatus == null || bookingIdStr.trim().isEmpty() || newStatus.trim().isEmpty()) {
            response.sendRedirect("manageBookings.jsp?error=Missing booking ID or status");
            return;
        }

        int bookingId;
        try {
            bookingId = Integer.parseInt(bookingIdStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("manageBookings.jsp?error=Invalid booking ID format");
            return;
        }

        boolean success = bookingDAO.updateBookingStatus(bookingId, newStatus);
        if (success) {
            response.sendRedirect("manageBookings.jsp?success=Trip progress updated successfully");
        } else {
            response.sendRedirect("manageBookings.jsp?error=Failed to update trip progress");
        }
    }
}
