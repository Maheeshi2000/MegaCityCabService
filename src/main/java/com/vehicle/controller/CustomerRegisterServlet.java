package com.vehicle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vehicle.dao.CustomerDAO;
import com.vehicle.model.Customer;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/customerRegister")
public class CustomerRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt(12)); // Encrypt password
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        Customer customer = new Customer(0, fullname, username, email, password, phone, address);
        CustomerDAO customerDAO = new CustomerDAO();

        if (customerDAO.registerCustomer(customer)) {
            response.sendRedirect("customerLogin.jsp?success=Customer Registered Successfully");
        } else {
            response.sendRedirect("customerRegister.jsp?error=Failed to register");
        }
    }
}
