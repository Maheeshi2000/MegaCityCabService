<%@ page import="java.util.List" %>
<%@ page import="com.vehicle.model.Vehicle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Ensure user is logged in
    String userRole = (String) session.getAttribute("role");
    if (userRole == null) {
        response.sendRedirect("login.jsp?error=Please login first!");
        return;
    }
%>

<html>
<head>
    <title>Vehicle Management</title>
    <script>
        function toggleVehicleList() {
            var listDiv = document.getElementById("vehicleList");
            if (listDiv.style.display === "none") {
                listDiv.style.display = "block";
                document.getElementById("showListBtn").innerText = "Hide Vehicle List";
                fetchVehicles();  // Fetch data when showing list
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
    <h2>Vehicle Management</h2>

    <% if ("Admin".equals(userRole)) { %>
    <!-- Admin Only: Add New Vehicle Form -->
    <h3>Add New Vehicle</h3>
  <form action="VehicleServlet" method="post">
    <input type="hidden" name="action" value="add">
    <label>Vehicle Number:</label>
    <input type="text" name="vehicleNumber" required><br>

    <label>Model:</label>
    <input type="text" name="model" required><br>

    <label>Type:</label>
    <input type="text" name="type" required><br>

    <label>Seating Capacity:</label>
    <input type="number" name="seatingCapacity" required><br>

    <label>Availability:</label>
    <select name="availability">
        <option value="true">Available</option>
        <option value="false">Unavailable</option>
    </select><br>

    <button type="submit" onclick="console.log('🚀 Form Submitted!')">Add Vehicle</button>
</form>


    <% } %>

    <hr>

    <!-- Button to Show/Hide Vehicle List -->
    <button id="showListBtn" onclick="toggleVehicleList()">Show Vehicle List</button>

    <!-- Vehicle List (Initially Hidden) -->
    <div id="vehicleList" style="display: none;"></div>

    <hr>
    <!-- Button to Go Back to Dashboard -->
    <button onclick="window.location.href='dashboard.jsp'">Back to Dashboard</button>
</body>
</html>
