<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Drivers</title>
    <script>
        var driverListVisible = false; // Track the visibility state

        function toggleDriverList() {
            var table = document.getElementById("driverTable");

            if (!driverListVisible) {
                // If the table is hidden, fetch and show drivers
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "drivers?ajax=true", true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        document.getElementById("driverTableBody").innerHTML = xhr.responseText;
                        table.style.display = "table";
                        driverListVisible = true;
                    }
                };
                xhr.send();
            } else {
                // If already visible, hide the table
                table.style.display = "none";
                driverListVisible = false;
            }
        }
    </script>
</head>
<body>
    <h2>Manage Drivers</h2>

    <!-- Navigation Buttons -->
    <button onclick="window.location.href='dashboard.jsp'">Go to Dashboard</button>
    <button onclick="toggleDriverList()">View Driver List</button>

    <hr>

    <!-- Add Driver Form -->
    <form action="drivers" method="post">
        <input type="hidden" name="action" value="add">
        <label>Name:</label>
        <input type="text" name="name" required>
        <label>License Number:</label>
        <input type="text" name="licenseNumber" required>
        <label>Phone:</label>
        <input type="text" name="phone" required>
        <button type="submit">Add Driver</button>
    </form>

    <hr>

    <!-- Display Drivers List -->
    <h3>Driver List</h3>
    <table border="1" id="driverTable" style="display:none;">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>License Number</th>
                <th>Phone</th>
            </tr>
        </thead>
        <tbody id="driverTableBody">
            <!-- Data will be loaded dynamically via AJAX -->
        </tbody>
    </table>
</body>
</html>
