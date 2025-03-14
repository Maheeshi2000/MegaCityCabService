<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employee Login - Vehicle Reservation System</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            background: url('https://source.unsplash.com/1600x900/?city,vehicle') no-repeat center center fixed;
            background-size: cover;
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            backdrop-filter: brightness(50%);
        }

        .login-container {
            background-color: rgba(0, 0, 0, 0.7);
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.4);
            text-align: center;
            width: 350px;
        }

        h2 {
            color: #ffcc00;
            margin-bottom: 25px;
        }

        input[type=text], input[type=password] {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border-radius: 5px;
            border: none;
        }

        input[type=submit] {
            background-color: #00d8ff;
            border: none;
            padding: 10px;
            width: 100%;
            color: #000;
            font-weight: bold;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        input[type=submit]:hover {
            background-color: #ffcc00;
        }

        .message {
            margin: 15px 0;
        }

        a {
            color: #00d8ff;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        a:hover {
            color: #ffcc00;
        }

        .back-button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #ffcc00;
            color: #000;
            text-decoration: none;
            border-radius: 5px;
            font-weight: 500;
            transition: background-color 0.3s ease;
        }

        .back-button:hover {
            background-color: #00d8ff;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>Employee Login</h2>

    <% if (request.getParameter("error") != null) { %>
        <p style="color: #ff4d4d;" class="message"><%= request.getParameter("error") %></p>
    <% } %>

    <form action="login" method="post">
        <input type="text" name="username" placeholder="Username" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="submit" value="Login">
    </form>

    <a href="register.jsp">Don't have an account? Register</a>

    <br>
    <a class="back-button" href="index.jsp">Back to Main Page</a>
</div>
</body>
</html>
