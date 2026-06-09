<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Livestock Auction System</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/auth.css">
</head>
<body>
    <div class="login-container">
        <div class="login-box">
            <h1>Livestock Auction System</h1>
            <h2>Login</h2>
            
            <% 
                String error = request.getParameter("error");
                if (error != null && !error.isEmpty()) {
            %>
                <div class="alert alert-error">
                    <%= error %>
                </div>
            <% } %>
            
            <form action="LoginServlet" method="POST">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                
                <div class="form-group">
                    <label for="userType">User Type:</label>
                    <select id="userType" name="userType" required>
                        <option value="">Select User Type</option>
                        <option value="ADMIN">Administrator</option>
                        <option value="SELLER">Seller</option>
                        <option value="BUYER">Buyer</option>
                    </select>
                </div>
                
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
            
            <p class="register-link">
                Don't have an account? <a href="register.jsp">Register here</a>
            </p>
        </div>
    </div>
</body>
</html>