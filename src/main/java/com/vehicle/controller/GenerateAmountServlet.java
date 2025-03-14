package com.vehicle.controller;

import com.vehicle.dao.BookingDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GenerateAmountServlet")
public class GenerateAmountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDAO bookingDAO;

    public void init() {
        bookingDAO = new BookingDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookingId;

        try {
            bookingId = Integer.parseInt(request.getParameter("bookingId"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid booking ID.");
            return;
        }

        // ✅ Generate amount based on updated distance
        boolean updated = bookingDAO.updateFareAmount(bookingId);
        if (updated) {
            response.getWriter().write("success");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to calculate fare.");
        }
    }
}
