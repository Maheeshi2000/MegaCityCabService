<%@ page import="java.util.List, com.vehicle.dao.VehicleDAO, com.vehicle.model.Vehicle" %>

<%
    VehicleDAO vehicleDAO = new VehicleDAO();
    List<Vehicle> vehicles = vehicleDAO.getAllVehicles();
    request.setAttribute("vehicles", vehicles);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Vehicle List</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: transparent;
            color: #fff;
            margin: 0;
            padding: 0;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: rgba(0, 0, 0, 0.8);
            border-radius: 10px;
            overflow: hidden;
        }

        th, td {
            padding: 12px;
            text-align: center;
        }

        th {
            background-color: #ffcc00;
            color: #000;
        }

        tr:nth-child(even) {
            background-color: rgba(255, 255, 255, 0.1);
        }

        tr:hover {
            background-color: rgba(255, 255, 255, 0.2);
        }

        td {
            border-bottom: 1px solid rgba(255,255,255,0.3);
        }

        .no-data {
            color: #ff4d4d;
            font-weight: 500;
        }
    </style>
</head>
<body>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Vehicle Number</th>
            <th>Model</th>
            <th>Type</th>
            <th>Seating Capacity</th>
            <th>Availability</th>
        </tr>
    </thead>
    <tbody>
    <% if (vehicles != null && !vehicles.isEmpty()) {
        for (Vehicle v : vehicles) { %>
        <tr>
    <td><%= v.getId() %></td>
    <td><%= v.getVehicleNumber() %></td>
    <td><%= v.getModel() %></td>
    <td><%= v.getType() %></td>
    <td><%= v.getSeatingCapacity() %></td>
    <td><%= (v.getAvailability() == 1 ? "Available" : "Unavailable") %></td>
</tr>
    <% }
    } else { %>
        <tr>
            <td colspan="6" class="no-data">No vehicles found.</td>
        </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>
