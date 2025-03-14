<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>

    <%-- Display error or success messages --%>
    <% if (request.getParameter("error") != null) { %>
        <p style="color: red;"><%= request.getParameter("error") %></p>
    <% } %>
    <% if (request.getParameter("success") != null) { %>
        <p style="color: green;"><%= request.getParameter("success") %></p>
    <% } %>

    <form action="login" method="post">
        Username: <input type="text" name="username" required><br>
        Password: <input type="password" name="password" required><br>
        <input type="submit" value="Login">
    </form>

    <a href="register.jsp">Don't have an account? Register</a>
</body>
</html>
