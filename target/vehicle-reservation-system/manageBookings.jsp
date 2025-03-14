<%@ page import="java.util.List" %>
<%@ page import="com.vehicle.model.Driver" %>

<html>
<head>
    <title>Manage Bookings</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        // Function to load pending bookings
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

        // Function to load accepted bookings
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

        // Function to load trip progress table
        function loadTripProgress() {
            $.ajax({
                url: "ManageBookingServlet",
                type: "GET",
                data: { type: "tripProgress" },
                success: function(response) {
                    $("#tripProgressTable tbody").html(response);
                    $("#tripProgressSection").show(); // Show the section
                },
                error: function() {
                    alert("Failed to load trip progress.");
                }
            });
        }
    </script>
</head>
<body>

    <h2>Manage Bookings</h2>

    <!-- Buttons to fetch Pending, Accepted, and Trip Progress -->
    <button onclick="loadPendingBookings()">View Pending Bookings</button>
    <button onclick="loadAcceptedBookings()">View Accepted Bookings</button>
    <button onclick="loadTripProgress()">Update Progress</button>

    <h3>Pending Bookings</h3>
    <table border="1" id="pendingBookingsTable">
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
            <!-- AJAX will load pending bookings here -->
        </tbody>
    </table>

    <h3>Accepted Bookings</h3>
    <table border="1" id="acceptedBookingsTable">
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
            <!-- AJAX will load accepted bookings here -->
        </tbody>
    </table>

    <!-- Trip Progress Section (Hidden by Default) -->
    <div id="tripProgressSection" style="display: none;">
        <h3>Update Trip Progress</h3>
        <table border="1" id="tripProgressTable">
            <thead>
                <tr>
                    <th>Booking Number</th>
                    <th>Customer ID</th>
                    <th>Pickup Location</th>
                    <th>Drop Location</th>
                    <th>Vehicle ID</th>
                    <th>Driver</th>
                    <th>Status</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <!-- AJAX will load trip progress bookings here -->
            </tbody>
        </table>
    </div>

</body>
</html>