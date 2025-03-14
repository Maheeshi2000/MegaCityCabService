package com.vehicle.controller;

import com.vehicle.dao.DriverDAO;
import com.vehicle.model.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/drivers")
public class DriverServlet extends HttpServlet {
    private DriverDAO driverDAO;

    @Override
    public void init() {
        driverDAO = new DriverDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String name = request.getParameter("name");
            String licenseNumber = request.getParameter("licenseNumber");
            String phone = request.getParameter("phone");

            Driver driver = new Driver(0, name, licenseNumber, phone, null);
            driverDAO.addDriver(driver);
        }

        response.sendRedirect("drivers");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Driver> drivers = driverDAO.getAllDrivers();
        request.setAttribute("drivers", drivers);

        // Debugging output
        System.out.println("✅ Retrieved " + drivers.size() + " drivers from database.");

        // Check if the request is from AJAX (fetchDrivers.jsp)
        String ajaxRequest = request.getParameter("ajax");
        if ("true".equals(ajaxRequest)) {
            request.getRequestDispatcher("fetchDrivers.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("drivers.jsp").forward(request, response);
        }
    }

}
