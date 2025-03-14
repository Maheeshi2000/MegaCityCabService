<%@ page import="java.util.List" %>
<%@ page import="com.vehicle.dao.VehicleDAO" %>
<%@ page import="com.vehicle.model.Vehicle" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj == null || sessionObj.getAttribute("userId") == null) {
        response.sendRedirect("customerLogin.jsp?error=Please log in first");
        return;
    }

    VehicleDAO vehicleDAO = new VehicleDAO();
    List<Vehicle> vehicles = vehicleDAO.getAllVehicles();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Make a Booking</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: url('https://source.unsplash.com/1600x900/?car,road') no-repeat center center fixed;
            background-size: cover;
            color: #ffffff;
            margin: 0;
            padding: 40px;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            background-color: rgba(0, 0, 0, 0.8);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.4);
            width: 80%;
            max-width: 800px;
            text-align: center;
        }

        h2 {
            color: #ffcc00;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: rgba(255, 255, 255, 0.1);
        }

        th, td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid rgba(255,255,255,0.3);
        }

        th {
            background-color: #ffcc00;
            color: #000;
        }

        input[type="text"] {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: none;
            display: block;
            font-size: 16px;
        }

        button {
            background-color: #00d8ff;
            color: #000;
            padding: 10px;
            margin-top: 15px;
            border-radius: 5px;
            cursor: pointer;
            border: none;
            transition: background-color 0.3s ease;
            width: 100%;
            font-weight: bold;
        }

        button:hover {
            background-color: #ffcc00;
        }
    </style>
    <script>
        function selectVehicle(vehicleId) {
            document.getElementById("selectedVehicleId").value = vehicleId;
            document.getElementById("bookingForm").submit();
            alert("Booking confirmed! A confirmation email has been sent.");
        }
    </script>
</head>
<body>
<div class="container">
    <h2>Available Vehicles for Booking</h2>
    <form id="bookingForm" action="BookingServlet" method="post">
        <label for="pickupLocation">Pickup Location:</label>
        <input type="text" id="pickupLocation" name="pickupLocation" required>
        
        <label for="dropLocation">Drop Location:</label>
        <input type="text" id="dropLocation" name="dropLocation" required>
        
        <input type="hidden" id="selectedVehicleId" name="vehicleId">
    </form>
    <table>
        <tr>
            <th>Vehicle Number</th>
            <th>Model</th>
            <th>Type</th>
            <th>Seats</th>
            <th>Action</th>
        </tr>
        <% if (vehicles != null && !vehicles.isEmpty()) {
            for (Vehicle v : vehicles) { %>
        <tr>
            <td><%= v.getVehicleNumber() %></td>
            <td><%= v.getModel() %></td>
            <td><%= v.getType() %></td>
            <td><%= v.getSeatingCapacity() %></td>
            <td>
                <% if (v.getAvailability() == 1) { %>
                    <button type="button" onclick="selectVehicle('<%= v.getId() %>')">Select</button>
                <% } else { %>
                    <span style="color: red;">Unavailable</span>
                <% } %>
            </td>
        </tr>
        <% }} else { %>
        <tr><td colspan="5">No available vehicles</td></tr>
        <% } %>
    </table>
    <button onclick="window.location.href='customerDashboard.jsp'">Back to Dashboard</button>
</div>
</body>
</html>