<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    if (session == null || session.getAttribute("username") == null) {
        response.sendRedirect("customerLogin.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: url('https://source.unsplash.com/1600x900/?city,customer') no-repeat center center fixed;
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
            width: 400px;
            text-align: center;
        }

        h2 {
            color: #ffcc00;
            margin-bottom: 20px;
        }

        p {
            font-size: 16px;
            margin: 8px 0;
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

        .main-btn {
            background-color: #ffcc00;
        }

        .main-btn:hover {
            background-color: #00d8ff;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Welcome, <%= session.getAttribute("fullname") %>!</h2>
    <p><strong>Email:</strong> <%= session.getAttribute("email") %></p>
    <p><strong>Phone:</strong> <%= session.getAttribute("phone") %></p>
    <p><strong>Address:</strong> <%= session.getAttribute("address") %></p>
    <button onclick="window.location.href='makeBooking.jsp'">Make a Booking</button>
    <button onclick="window.location.href='index.jsp'">Logout</button>
    
</body>
</html>
