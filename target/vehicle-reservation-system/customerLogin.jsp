<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Login</title>
</head>
<body>
    <h2>Customer Login</h2>

    <form action="login" method="post">
        <input type="hidden" name="isCustomer" value="true">
        <label>Username:</label>
        <input type="text" name="username" required>
        <label>Password:</label>
        <input type="password" name="password" required>
        <button type="submit">Login</button>
    </form>

    <p>New user? <a href="customerRegister.jsp">Register here</a></p>
</body>
</html>
