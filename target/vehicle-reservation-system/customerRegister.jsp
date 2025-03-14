<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Registration</title>
</head>
<body>
    <h2>Customer Registration</h2>

    <form action="customerRegister" method="post">
        <label>Full Name:</label>
        <input type="text" name="fullname" required>
        <label>Username:</label>
        <input type="text" name="username" required>
        <label>Email:</label>
        <input type="email" name="email" required>
        <label>Password:</label>
        <input type="password" name="password" required>
        <label>Phone:</label>
        <input type="text" name="phone">
        <label>Address:</label>
        <input type="text" name="address">
        <button type="submit">Register</button>
    </form>

    <p>Already have an account? <a href="customerLogin.jsp">Login here</a></p>
</body>
</html>
