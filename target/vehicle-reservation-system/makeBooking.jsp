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
    List<Vehicle> vehicles = vehicleDAO.getAllVehicles(); // Fetch all vehicles
%>

<html>
<head>
    <title>Make a Booking</title>
</head>
<body>
    <h2>Available Vehicles for Booking</h2>

    <!-- ✅ New Pickup & Drop Location Form -->
    <form id="bookingForm" action="BookingServlet" method="post">
        <label for="pickupLocation">Pickup Location:</label>
        <input type="text" id="pickupLocation" name="pickupLocation" required><br><br>

        <label for="dropLocation">Drop Location:</label>
        <input type="text" id="dropLocation" name="dropLocation" required><br><br>

        <!-- Hidden input for selected vehicle ID -->
        <input type="hidden" id="selectedVehicleId" name="vehicleId">
    </form>

    <table border="1">
        <tr>
            <th>Vehicle Number</th>
            <th>Model</th>
            <th>Type</th>
            <th>Seats</th>
            <th>Action</th>
        </tr>
        <% 
        if (vehicles != null && !vehicles.isEmpty()) {
            for (Vehicle v : vehicles) {
        %>
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
        <% 
            }
        } else { %>
        <tr>
            <td colspan="5">No available vehicles</td>
        </tr>
        <% } %>
    </table>

    <!-- JavaScript to select a vehicle and submit the form -->
    <script>
        function selectVehicle(vehicleId) {
            document.getElementById("selectedVehicleId").value = vehicleId;
            document.getElementById("bookingForm").submit();
        }
    </script>

</body>
</html>
