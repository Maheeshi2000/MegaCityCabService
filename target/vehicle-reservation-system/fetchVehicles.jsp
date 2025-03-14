<%@ page import="java.util.List, com.vehicle.dao.VehicleDAO, com.vehicle.model.Vehicle" %>

<%
    VehicleDAO vehicleDAO = new VehicleDAO();
    List<Vehicle> vehicles = vehicleDAO.getAllVehicles();
    request.setAttribute("vehicles", vehicles);
%>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Vehicle Number</th>
        <th>Model</th>
        <th>Type</th>
        <th>Seating Capacity</th>
        <th>Availability</th>
    </tr>
    <% 
        if (vehicles != null && !vehicles.isEmpty()) {
            for (Vehicle v : vehicles) { 
    %>
    <tr>
        <td><%= v.getId() %></td>
        <td><%= v.getVehicleNumber() %></td>
        <td><%= v.getModel() %></td>
        <td><%= v.getType() %></td>
        <td><%= v.getSeatingCapacity() %></td>
        <td><%= (v.getAvailability() == 1 ? "Available" : "Unavailable") %></td>
    </tr>
    <% 
            }
        } else { 
    %>
    <tr><td colspan="6">No vehicles found.</td></tr>
    <% } %>
</table>
