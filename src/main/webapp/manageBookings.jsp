<%@ page import="java.util.List" %>
<%@ page import="com.vehicle.model.Driver" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Bookings</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: url('https://source.unsplash.com/1600x900/?booking,travel') no-repeat center center fixed;
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

        h2, h3 {
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

        button {
            background-color: #00d8ff;
            color: #000;
            padding: 10px;
            margin-top: 15px;
            border-radius: 5px;
            cursor: pointer;
            border: none;
            width: 45%;
            font-weight: bold;
        }

        button:hover {
            background-color: #ffcc00;
        }
    </style>
    <script>
        function loadPendingBookings() {
            $.ajax({
                url: "ManageBookingServlet",
                type: "GET",
                data: { type: "pending" },
                success: function(response) {
                    $("#pendingBookingsTable tbody").html(response);
                },
                error: function() {
                    alert("Failed to load pending bookings.");
                }
            });
        }

        function loadAcceptedBookings() {
            $.ajax({
                url: "ManageBookingServlet",
                type: "GET",
                data: { type: "accepted" },
                success: function(response) {
                    $("#acceptedBookingsTable tbody").html(response);
                },
                error: function() {
                    alert("Failed to load accepted bookings.");
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <h2>Manage Bookings</h2>
    <button onclick="loadPendingBookings()">View Pending Bookings</button>
    <button onclick="loadAcceptedBookings()">View Accepted Bookings</button>
    <h3>Pending Bookings</h3>
    <table id="pendingBookingsTable">
        <thead>
            <tr>
                <th>Booking Number</th>
                <th>Customer ID</th>
                <th>Pickup Location</th>
                <th>Drop Location</th>
                <th>Vehicle ID</th>
                <th>Driver</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    <h3>Accepted Bookings</h3>
    <table id="acceptedBookingsTable">
        <thead>
            <tr>
                <th>Booking Number</th>
                <th>Customer ID</th>
                <th>Pickup Location</th>
                <th>Drop Location</th>
                <th>Vehicle ID</th>
                <th>Driver</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    <button onclick="window.location.href='dashboard.jsp'">Back to Dashboard</button>
</div>
</body>
</html>
