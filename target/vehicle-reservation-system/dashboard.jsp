<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userRole = (String) session.getAttribute("role");
    if (userRole == null) {
        response.sendRedirect("login.jsp?error=Please login first!");
        return;
    }
%>

<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
    <h2>Welcome, <%= session.getAttribute("username") %></h2>

    <% if ("Admin".equals(userRole)) { %>
        <button onclick="window.location.href='vehicles.jsp'">Manage Vehicles</button>
        <button onclick="window.location.href='drivers.jsp'">Manage Drivers</button>
         <button onclick="window.location.href='manageBookings.jsp'">Manage Bookings</button>
         <button onclick="window.location.href='managePayments.jsp'">Manage Payments</button>
         
    <% } %>

    <button onclick="window.location.href='logout'">Logout</button>
</body>
</html>
