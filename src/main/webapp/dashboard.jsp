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
    <title>Employee Dashboard</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: url('https://source.unsplash.com/1600x900/?office,work') no-repeat center center fixed;
            background-size: cover;
            color: #ffffff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            backdrop-filter: brightness(50%);
            margin: 0;
        }

        .dashboard-container {
            background-color: rgba(0, 0, 0, 0.7);
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.4);
            text-align: center;
            width: 400px;
        }

        h2 {
            color: #ffcc00;
            margin-bottom: 20px;
        }

        button {
            background-color: #00d8ff;
            border: none;
            color: #000;
            padding: 10px 20px;
            margin: 10px;
            font-weight: 500;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            width: 90%;
        }

        button:hover {
            background-color: #ffcc00;
        }

        .logout-btn, .main-btn {
            margin-top: 20px;
            background-color: #ffcc00;
        }

        .logout-btn:hover, .main-btn:hover {
            background-color: #00d8ff;
        }
    </style>
</head>
<body>
<div class="dashboard dashboard-container">
    <h2>Welcome, <%= session.getAttribute("username") %></h2>

    <% if ("Admin".equals(userRole)) { %>
        <button onclick="location.href='drivers.jsp'">Manage Drivers</button>
        <button onclick="location.href='manageBookings.jsp'">Manage Bookings</button>
        <button onclick="location.href='managePayments.jsp'">Manage Payments</button>
        <button onclick="location.href='vehicles.jsp'">Manage Vehicles</button>
    <% } %>

    <button class="logout-btn" onclick="location.href='logout'">Logout</button>
    <button class="main-btn" onclick="location.href='index.jsp'">Back to Main Page</button>
</div>
</body>
</html>
