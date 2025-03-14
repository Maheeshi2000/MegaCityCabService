<%@ page import="java.util.List" %>
<%@ page import="com.vehicle.model.Driver" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Driver> driverList = (List<Driver>) request.getAttribute("drivers");

    if (driverList == null) {
        out.println("<tr><td colspan='4'>❌ Error: Driver list is null</td></tr>");
    } else if (driverList.isEmpty()) {
        out.println("<tr><td colspan='4'>No drivers found.</td></tr>");
    } else {
        for (Driver d : driverList) {
%>
<tr>
    <td><%= d.getId() %></td>
    <td><%= d.getName() %></td>
    <td><%= d.getLicenseNumber() %></td>
    <td><%= d.getPhone() %></td>
</tr>
<%
        }
    }
%>
