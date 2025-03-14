<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Registration</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: url('https://source.unsplash.com/1600x900/?city,people') no-repeat center center fixed;
            background-size: cover;
            color: #ffffff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            backdrop-filter: brightness(50%);
            margin: 0;
        }

        .container {
            background-color: rgba(0, 0, 0, 0.7);
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.4);
            width: 400px;
            text-align: center;
        }

        h2 {
            color: #ffcc00;
            margin-bottom: 20px;
        }

        input[type=text], input[type=email], input[type=password] {
            width: 90%;
            padding: 8px;
            margin: 10px 0;
            border-radius: 5px;
            border: none;
        }

        button {
            background-color: #00d8ff;
            color: #000;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            cursor: pointer;
            border: none;
            transition: background-color 0.3s ease;
            width: 90%;
            font-weight: bold;
        }

        button:hover {
            background-color: #ffcc00;
        }

        .main-btn {
            background-color: #ffcc00;
        }

        .main-btn:hover {
            background-color: #00d8ff;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Customer Registration</h2>
    <form action="customerRegister" method="post">
        <input type="text" name="fullname" placeholder="Full Name" required>
        <input type="text" name="username" placeholder="Username" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="text" name="phone" placeholder="Phone">
        <input type="text" name="address" placeholder="Address">
        <button type="submit">Register</button>
    </form>
    <p>Already have an account? <a href="customerLogin.jsp">Login here</a></p>
    <button class="main-btn" onclick="location.href='index.jsp'">Back to Main Page</button>
</div>
</body>
</html>
