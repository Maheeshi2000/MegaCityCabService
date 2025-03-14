package com.vehicle.controller;

import com.vehicle.dao.CustomerDAO;
import com.vehicle.dao.UserDAO;
import com.vehicle.model.Customer;
import com.vehicle.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();
    private CustomerDAO customerDAO = new CustomerDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isCustomer = "true".equals(request.getParameter("isCustomer"));

        HttpSession session = request.getSession();

        if (isCustomer) {
            Customer customer = customerDAO.authenticateCustomer(username, password);
            if (customer != null) {
                session.setAttribute("userId", customer.getId());  // ✅ Store customer ID in session
                session.setAttribute("username", customer.getUsername());
                session.setAttribute("fullname", customer.getFullname());
                session.setAttribute("email", customer.getEmail());
                session.setAttribute("phone", customer.getPhone());
                session.setAttribute("address", customer.getAddress());
                session.setAttribute("role", "customer");

                response.sendRedirect("customerDashboard.jsp");
                return;
            }
        } else {
            User user = userDAO.getUserByUsername(username);
            if (user != null) {
                session.setAttribute("userId", user.getId());  // ✅ Store employee ID in session
                session.setAttribute("username", user.getUsername());
                session.setAttribute("role", user.getRole());

                response.sendRedirect("dashboard.jsp");
                return;
            }
        }

        response.sendRedirect("login.jsp?error=Invalid credentials");
    }
}
