<%@ page import="java.util.List" %>
<%@ page import="com.vehicle.dao.PaymentDAO" %>
<%@ page import="com.vehicle.model.Payment" %>

<%
    PaymentDAO paymentDAO = new PaymentDAO();
    List<Payment> payments = paymentDAO.getAllPayments();
%>

<html>
<head>
    <title>Manage Payments</title>
</head>
<body>
    <h2>Manage Payments</h2>
    
    <table border="1">
        <tr>
            <th>Booking ID</th>
            <th>Amount</th>
            <th>Payment Status</th>
            <th>Payment Date</th>
            <th>Action</th>
        </tr>
        
        <% for (Payment payment : payments) { %>
        <tr>
            <td><%= payment.getBookingId() %></td>
            <td>Rs. <%= payment.getAmount() %></td>
            <td><%= payment.getPaymentStatus() %></td>
            <td><%= payment.getPaymentDate() %></td>
            <td>
                <% if ("Pending".equals(payment.getPaymentStatus())) { %>
                    <form action="PaymentServlet" method="post">
                        <input type="hidden" name="paymentId" value="<%= payment.getId() %>">
                        <button type="submit">Mark as Paid</button>
                    </form>
                <% } else { %>
                    <span style="color: green;">Paid</span>
                <% } %>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>
