
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Camiones</h1>
        <br>
        <a href="CamionControlador?action=add">Nuevo</a>
        <table>
            <tr>
                <th>Id</th>
                <th>Modelo</th>
                <th>Capacidad</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${camiones}">
                <tr>
                    <td>${item.idCamion}</td>
                    <td>${item.modelo}</td>
                    <td>${item.capacidadKg}</td>
                    
                    <td><a href="CamionControlador?action=edit&id=${item.idCamion}&objeto=camion">Editar</a></td>
                    <td><a href="CamionControlador?action=delete&id=${item.idCamion}" onclick="return(confirm('Estas seguro de eliminar???????'))">Eliminar</a></td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
