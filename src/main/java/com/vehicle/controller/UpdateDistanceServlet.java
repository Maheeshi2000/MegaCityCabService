package com.vehicle.controller;

import com.vehicle.dao.BookingDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateDistanceServlet")
public class UpdateDistanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDAO bookingDAO;

    public void init() {
        bookingDAO = new BookingDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookingId;
        double distance;

        try {
            bookingId = Integer.parseInt(request.getParameter("bookingId"));
            distance = Double.parseDouble(request.getParameter("distance"));
            if (distance <= 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid distance value.");
                return;
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input.");
            return;
        }

        // ✅ Update distance in database
        boolean updated = bookingDAO.updateDistance(bookingId, distance);
        if (updated) {
            response.getWriter().write("success");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update distance.");
        }
    }
}
