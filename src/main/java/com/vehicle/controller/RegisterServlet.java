package com.vehicle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.vehicle.dao.UserDAO;
import com.vehicle.model.User;


public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User user = new User(fullname, username, email, password, role);
        UserDAO userDAO = new UserDAO();

        if (userDAO.registerUser(user)) {
            response.sendRedirect("login.jsp?success=Registration successful");
        } else {
            response.sendRedirect("register.jsp?error=Failed to register");
        }
    }
}
