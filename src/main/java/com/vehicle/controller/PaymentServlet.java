package com.vehicle.controller;

import com.vehicle.dao.PaymentDAO;
import com.vehicle.dao.BookingDAO;
import com.vehicle.model.Payment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PaymentDAO paymentDAO;
    private BookingDAO bookingDAO;

    public void init() {
        paymentDAO = new PaymentDAO();
        bookingDAO = new BookingDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookingIdStr = request.getParameter("bookingId");
        String customerIdStr = request.getParameter("customerId");
        String amountStr = request.getParameter("amount");

        if (bookingIdStr == null || customerIdStr == null || amountStr == null) {
            response.sendRedirect("managePayments.jsp?error=Invalid payment request.");
            return;
        }

        int bookingId = Integer.parseInt(bookingIdStr);
        int customerId = Integer.parseInt(customerIdStr);
        double amount = Double.parseDouble(amountStr);

        // ✅ Store payment details in `payments` table
        PaymentDAO paymentDAO = new PaymentDAO();
        boolean paymentSuccess = paymentDAO.addPayment(new Payment(0, bookingId, customerId, amount, "Paid", new Date()));

        if (paymentSuccess) {
            // ✅ Delete booking after payment
            BookingDAO bookingDAO = new BookingDAO();
            bookingDAO.deleteBooking(bookingId);
            response.sendRedirect("managePayments.jsp?success=Payment processed successfully.");
        } else {
            response.sendRedirect("managePayments.jsp?error=Failed to process payment.");
        }
    }

}
