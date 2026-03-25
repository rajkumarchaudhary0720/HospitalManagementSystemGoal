<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hospital Management System</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/app.css">
</head>
<body class="login-body">
<div class="login-card">
    <div>
        <p class="eyebrow">Hospital Management System</p>
        <h1>Role-based login</h1>
        <p class="muted">Admin manages doctors and patients. Receptionist manages appointments.</p>
    </div>

    <% if (request.getAttribute("error") != null) { %>
        <div class="alert error"><%= request.getAttribute("error") %></div>
    <% } %>

    <form method="post" action="<%= request.getContextPath() %>/login" class="stack">
        <label>Username</label>
        <input type="text" name="username" required>

        <label>Password</label>
        <input type="password" name="password" required>

        <button type="submit">Sign In</button>
    </form>

    <div class="credentials">
        <p><strong>Admin:</strong> admin / admin123</p>
        <p><strong>Receptionist:</strong> reception / recep123</p>
    </div>
</div>
</body>
</html>
