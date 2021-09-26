<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div style="text-align: center">
    <form action="Servlet_login" method="post">
        <label for="userid">Username:</label>
        <input type="text" id="userid" name="userid" size="30" />
        <br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" size="30" />
        <br>${message}
        <br><br>
        <button type="submit">Login</button>
    </form>
</div>
</body>
</html>