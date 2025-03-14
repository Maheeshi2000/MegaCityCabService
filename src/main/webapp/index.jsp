<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Vehicle Reservation System</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            background: url('https://source.unsplash.com/1600x900/?vehicles,cars') no-repeat center center fixed;
            background-size: cover;
            color: #fff;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            backdrop-filter: brightness(50%);
        }

        .container {
            background-color: rgba(0, 0, 0, 0.7);
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.4);
            text-align: center;
        }

        h1 {
            margin-bottom: 30px;
            font-size: 36px;
        }

        .section {
            margin-bottom: 20px;
        }

        .section a {
            text-decoration: none;
            color: #00d8ff;
            font-weight: 500;
            margin: 0 15px;
            transition: color 0.3s ease-in-out;
        }

        .section a:hover {
            color: #ffcc00;
        }

        .section-title {
            font-weight: 700;
            font-size: 22px;
            margin-bottom: 10px;
            color: #ffcc00;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Welcome to the Vehicle Reservation System</h1>

    <div class="section">
        <div class="section-title">Company Employees</div>
        <a href="register.jsp">Register</a> |
        <a href="login.jsp">Login</a>
    </div>

    <div class="section">
        <div class="section-title">Customers</div>
        <a href="customerRegister.jsp">Register</a> |
        <a href="customerLogin.jsp">Login</a>
    </div>
</div>

</body>
</html>
