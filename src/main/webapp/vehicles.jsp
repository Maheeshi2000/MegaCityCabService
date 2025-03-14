<%@ page import="java.util.List" %>
<%@ page import="com.vehicle.model.Vehicle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userRole = (String) session.getAttribute("role");
    if (userRole == null) {
        response.sendRedirect("login.jsp?error=Please login first!");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Vehicle Management</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: url('https://source.unsplash.com/1600x900/?cars,transport') no-repeat center center fixed;
            background-size: cover;
            color: #ffffff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            backdrop-filter: brightness(50%);
            margin: 0;
        }

        .container {
            background-color: rgba(0, 0, 0, 0.7);
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.4);
            width: 500px;
            text-align: center;
        }

        h2, h3 {
            color: #ffcc00;
            margin-bottom: 15px;
        }

        input[type=text], input[type=number], select {
            width: 90%;
            padding: 8px;
            margin: 10px 0;
            border-radius: 5px;
            border: none;
        }

        button, input[type="submit"] {
            background-color: #00d8ff;
            color: #000;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            cursor: pointer;
            border: none;
            transition: background-color 0.3s ease;
            width: 90%;
            font-weight: bold;
        }

        button:hover, input[type="submit"]:hover {
            background-color: #ffcc00;
        }

        .dashboard-btn {
            background-color: #ffcc00;
        }

        .dashboard-btn:hover {
            background-color: #00d8ff;
        }

        hr {
            border: 1px solid #00d8ff;
            margin: 20px 0;
        }

    </style>
    <script>
        function toggleVehicleList() {
            var listDiv = document.getElementById("vehicleList");
            if (listDiv.style.display === "none") {
                listDiv.style.display = "block";
                document.getElementById("showListBtn").innerText = "Hide Vehicle List";
                fetchVehicles();
            } else {
                listDiv.style.display = "none";
                document.getElementById("showListBtn").innerText = "Show Vehicle List";
            }
        }

        function fetchVehicles() {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "fetchVehicles.jsp", true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    document.getElementById("vehicleList").innerHTML = xhr.responseText;
                }
            };
            xhr.send();
        }
    </script>
</head>
<body>
<div class="container">
    <h2>Vehicle Management</h2>

    <% if ("Admin".equals(userRole)) { %>
        <h3>Add New Vehicle</h3>
        <form action="VehicleServlet" method="post">
            <input type="hidden" name="action" value="add">
            <input type="text" name="vehicleNumber" placeholder="Vehicle Number" required>
            <input type="text" name="model" placeholder="Model" required>
            <input type="text" name="type" placeholder="Type" required>
            <input type="number" name="seatingCapacity" placeholder="Seating Capacity" required>
            <select name="availability">
                <option value="true">Available</option>
                <option value="false">Unavailable</option>
            </select>
            <input type="submit" value="Add Vehicle">
        </form>
    <% } %>

    <hr>
    <button id="showListBtn" onclick="toggleVehicleList()">Show Vehicle List</button>
    <div id="vehicleList" style="display: none;"></div>
    <hr>
    <button class="dashboard-btn" onclick="location.href='dashboard.jsp'">Back to Dashboard</button>
</div>
</body>
</html>
