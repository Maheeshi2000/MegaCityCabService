<%@ page import="java.util.List" %>
<%@ page import="com.vehicle.dao.PaymentDAO" %>
<%@ page import="com.vehicle.model.Payment" %>
<%@ page import="com.vehicle.dao.BookingDAO" %>
<%@ page import="com.vehicle.model.Booking" %>

<%
    PaymentDAO paymentDAO = new PaymentDAO();
    BookingDAO bookingDAO = new BookingDAO();
    List<Booking> completedBookings = bookingDAO.getAllCompletedTrips();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Payments</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: url('https://source.unsplash.com/1600x900/?payment,business') no-repeat center center fixed;
            background-size: cover;
            color: #ffffff;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            background-color: rgba(0, 0, 0, 0.8);
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.4);
            width: 90%;
            max-width: 900px;
            text-align: center;
        }

        h2 {
            color: #ffcc00;
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

        input[type="number"] {
            width: 80%;
            padding: 8px;
            border-radius: 5px;
            border: none;
            text-align: center;
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
            width: 90%;
            font-weight: bold;
        }

        button:hover {
            background-color: #ffcc00;
        }
    </style>
    <script>
        function updateDistance(bookingId) {
            var distance = document.getElementById("distance_" + bookingId).value;
            if (distance.trim() === "" || isNaN(distance) || distance <= 0) {
                alert("Please enter a valid distance.");
                return;
            }

            $.ajax({
                url: "UpdateDistanceServlet",
                type: "POST",
                data: { bookingId: bookingId, distance: distance },
                success: function(response) {
                    alert("Distance updated successfully!");
                    location.reload();
                },
                error: function() {
                    alert("Failed to update distance.");
                }
            });
        }

        function makePayment(bookingId) {
            $.ajax({
                url: "MakePaymentServlet",
                type: "POST",
                data: { bookingId: bookingId },
                success: function(response) {
                    alert("Payment successful! A receipt has been sent to the customer.");
                    location.reload();
                },
                error: function() {
                    alert("Payment failed. Please try again.");
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <h2>Manage Payments - Awaiting Calculation</h2>
    <table>
        <tr>
            <th>Booking ID</th>
            <th>Customer ID</th>
            <th>Pickup Location</th>
            <th>Drop Location</th>
            <th>Vehicle ID</th>
            <th>Distance (km)</th>
            <th>Fare (Rs.)</th>
            <th>Action</th>
        </tr>
        <% if (completedBookings != null && !completedBookings.isEmpty()) {
            for (Booking booking : completedBookings) { %>
        <tr>
            <td><%= booking.getId() %></td>
            <td><%= booking.getUserId() %></td>
            <td><%= booking.getPickupLocation() %></td>
            <td><%= booking.getDropLocation() %></td>
            <td><%= booking.getVehicleId() %></td>
            <td>
                <input type="number" id="distance_<%= booking.getId() %>" value="<%= booking.getDistance() %>" min="1">
                <button onclick="updateDistance(<%= booking.getId() %>)">Submit Distance</button>
            </td>
            <td>Rs. <%= booking.getFareAmount() %></td>
            <td><button onclick="makePayment(<%= booking.getId() %>)">Make Payment</button></td>
        </tr>
        <% } } else { %>
        <tr><td colspan="8">No completed trips awaiting payment.</td></tr>
        <% } %>
    </table>
</div>
</body>
</html>