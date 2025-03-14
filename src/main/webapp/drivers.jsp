<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Drivers</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: url('https://source.unsplash.com/1600x900/?drivers,transport') no-repeat center center fixed;
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

        input[type=text] {
            width: 90%;
            padding: 8px;
            margin: 10px 0;
            border-radius: 5px;
            border: none;
        }

        button {
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

        button:hover {
            background-color: #ffcc00;
        }

        .dashboard-btn {
            background-color: #ffcc00;
        }

        .dashboard-btn:hover {
            background-color: #00d8ff;
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
    </style>
    <script>
        var driverListVisible = false;
        function toggleDriverList() {
            var table = document.getElementById("driverTable");
            if (!driverListVisible) {
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
                table.style.display = "none";
                driverListVisible = false;
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h2>Manage Drivers</h2>
    <button class="dashboard-btn" onclick="window.location.href='dashboard.jsp'">Go to Dashboard</button>
    <button onclick="toggleDriverList()">View Driver List</button>
    <hr>
    <h3>Add Driver</h3>
    <form action="drivers" method="post">
        <input type="hidden" name="action" value="add">
        <input type="text" name="name" placeholder="Name" required>
        <input type="text" name="licenseNumber" placeholder="License Number" required>
        <input type="text" name="phone" placeholder="Phone" required>
        <button type="submit">Add Driver</button>
    </form>
    <hr>
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
</div>
</body>
</html>
