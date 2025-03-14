package com.vehicle.controller;

import com.vehicle.dao.BookingDAO;
import com.vehicle.dao.PaymentDAO;
import com.vehicle.model.Booking;
import com.vehicle.model.Payment;
import com.vehicle.util.EmailSender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/MakePaymentServlet")
public class MakePaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDAO bookingDAO;
    private PaymentDAO paymentDAO;

    public void init() {
        bookingDAO = new BookingDAO();
        paymentDAO = new PaymentDAO();
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

        // ✅ Fetch booking details
        Booking booking = bookingDAO.getBookingById(bookingId);
        if (booking == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Booking not found.");
            return;
        }

        double amount = booking.getFareAmount();
        String customerEmail = bookingDAO.getCustomerEmailByBookingId(bookingId);
        String customerName = bookingDAO.getCustomerNameByBookingId(bookingId);

     // ✅ Create Payment object separately
        Payment payment = new Payment();
        payment.setBookingId(bookingId);
        payment.setAmount(amount);
        payment.setPaymentStatus("Completed");
        payment.setPaymentDate(new Date());

        // ✅ Now pass the object to addPayment
        boolean paymentSuccess = paymentDAO.addPayment(payment);
     // ✅ Fetch customer email from database
        



        if (paymentSuccess) {
            // ✅ Delete booking after payment
            bookingDAO.deleteBooking(bookingId);

            // ✅ Send Email with Receipt
            try {
                String emailContent = "Dear " + customerName + ",\n\n"
                        + "Your payment has been successfully processed!\n\n"
                        + "Payment Details:\n"
                        + "📌 Booking Number: " + booking.getBookingNumber() + "\n"
                        + "📍 Pickup Location: " + booking.getPickupLocation() + "\n"
                        + "📍 Drop Location: " + booking.getDropLocation() + "\n"
                        + "💰 Total Fare: Rs. " + amount + "\n"
                        + "📆 Date: " + new Date() + "\n\n"
                        + "Thank you for choosing our service!\n"
                        + "Best Regards,\nMegaCityCab Team";

                EmailSender.sendEmail(customerEmail, "Payment Receipt - Booking " + booking.getBookingNumber(), emailContent);
            } catch (Exception e) {
                System.err.println("⚠️ Email sending failed: " + e.getMessage());
            }

            response.getWriter().write("success");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to process payment.");
        }
    }
}
