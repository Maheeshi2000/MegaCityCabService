package com.vehicle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Prevent creating new session
        if (session != null) {
            session.invalidate(); // Destroy session
            System.out.println("✅ User logged out successfully!"); // Debugging log
        }
        response.sendRedirect("login.jsp?success=Logged out successfully"); // Redirect
    }
}

