<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employee Registration</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: url('https://source.unsplash.com/1600x900/?city,car,office') no-repeat center center fixed;
            background-size: cover;
            color: #ffffff;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            backdrop-filter: brightness(50%);
        }

        .container {
            background-color: rgba(0, 0, 0, 0.7);
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.4);
            width: 400px;
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
            color: #ffcc00;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border-radius: 5px;
            border: none;
        }

        input[type="submit"] {
            background-color: #00d8ff;
            color: #000;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #ffcc00;
        }

        a {
            color: #00d8ff;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        a:hover {
            color: #ffcc00;
        }

        .message {
            margin-bottom: 15px;
        }

        .back-button {
            display: inline-block;
            margin-top: 15px;
            padding: 8px 15px;
            background-color: #ffcc00;
            color: #000;
            border-radius: 5px;
            text-decoration: none;
            font-weight: 500;
        }

        .back-button:hover {
            background-color: #00d8ff;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Employee Registration</h2>

    <% if (request.getParameter("error") != null) { %>
        <div class="message" style="color: #ff4d4d;"><%= request.getParameter("error") %></div>
    <% } %>
    <% if (request.getParameter("success") != null) { %>
        <div class="message" style="color: #00ff00;"><%= request.getParameter("success") %></div>
    <% } %>

    <form action="register" method="post">
        <input type="text" name="fullname" placeholder="Full Name" required>
        <input type="text" name="username" placeholder="Username" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="password" placeholder="Password" required>
        <select name="role">
            <option value="User">User</option>
            <option value="Admin">Admin</option>
        </select>
        <input type="submit" value="Register">
    </form>

    <a href="login.jsp">Already have an account? Login</a><br>
    <a class="back-button" href="index.jsp">Back to Main Page</a>
</div>
</body>
</html>
