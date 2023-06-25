
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Envios</h1>
        <br>
        <a href="ReporteControlador?action=add">Nuevo / Usar Algoritmo Genetico</a>
        <table>
            <tr>
                <th>Id Envio</th>
                <th>Camion</th>
                <th>Capacidad Camion</th>
                <th>Fecha Envio</th>
                <th>Producto</th>
                <th>Peso Producto</th>
            </tr>
            <c:forEach var="item" items="${reportes}">
                <tr>
                    <td>${item.idEnvio}</td>
                    <td>${item.modelo}</td>
                    <td>${item.capacidadKg}</td>
                    <td>${item.fechaEnvio}</td>
                    <td>${item.nombre}</td>
                    <td>${item.pesoKg}</td>
                    
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
