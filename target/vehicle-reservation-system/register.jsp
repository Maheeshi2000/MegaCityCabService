<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>User Registration</h2>

    <%-- Display error or success messages --%>
    <% if (request.getParameter("error") != null) { %>
        <p style="color: red;"><%= request.getParameter("error") %></p>
    <% } %>
    <% if (request.getParameter("success") != null) { %>
        <p style="color: green;"><%= request.getParameter("success") %></p>
    <% } %>

    <form action="register" method="post">
        Full Name: <input type="text" name="fullname" required><br>
        Username: <input type="text" name="username" required><br>
        Email: <input type="email" name="email" required><br>
        Password: <input type="password" name="password" required><br>
        Role:
        <select name="role">
            <option value="User">User</option>
            <option value="Admin">Admin</option>
        </select><br>
        <input type="submit" value="Register">
    </form>

    <a href="login.jsp">Already have an account? Login</a>
</body>
</html>
