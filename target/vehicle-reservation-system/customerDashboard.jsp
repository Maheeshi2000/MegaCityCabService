<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    if (session == null || session.getAttribute("username") == null) {
        response.sendRedirect("customerLogin.jsp");
        return;
    }
%>
<html>
<head>
    <title>Customer Dashboard</title>
</head>
<body>
    <h2>Welcome, <%= session.getAttribute("fullname") %>!</h2>

    <p><strong>Email:</strong> <%= session.getAttribute("email") %></p>
    <p><strong>Phone:</strong> <%= session.getAttribute("phone") %></p>
    <p><strong>Address:</strong> <%= session.getAttribute("address") %></p>

    <button onclick="window.location.href='makeBooking.jsp'">Make a Booking</button>
    <button onclick="window.location.href='logout'">Logout</button>
</body>
</html>
