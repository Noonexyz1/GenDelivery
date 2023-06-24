
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GenDelivery</title>
    </head>
    <body>
        <form action="LoginControlador" method="POST">
            <label for="username">Correo:</label>
            <input type="email" id="username" name="correo" required><br>

            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="contrasena" required><br>

            <input type="submit" value="Iniciar sesión">
        </form>
    </body>
</html>
