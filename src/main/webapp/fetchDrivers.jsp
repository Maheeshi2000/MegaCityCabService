<%@ page import="java.util.List" %>
<%@ page import="com.vehicle.model.Driver" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Driver List</title>
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

        .error-message, .no-data {
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
            <th>Name</th>
            <th>License Number</th>
            <th>Phone</th>
        </tr>
    </thead>
    <tbody>
    <%
        List<Driver> driverList = (List<Driver>) request.getAttribute("drivers");
        if (driverList == null) {
    %>
        <tr><td colspan='4' class="error-message">❌ Error: Driver list is null</td></tr>
    <%
        } else if (driverList.isEmpty()) {
    %>
        <tr><td colspan='4' class="no-data">No drivers found.</td></tr>
    <%
        } else {
            for (Driver d : driverList) {
    %>
        <tr>
            <td><%= d.getId() %></td>
            <td><%= d.getName() %></td>
            <td><%= d.getLicenseNumber() %></td>
            <td><%= d.getPhone() %></td>
        </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
</body>
</html>