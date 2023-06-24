
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
        <a href="CargaControlador?action=add">Nuevo</a>
        <table>
            <tr>
                <th>Id</th>
                <th>Camion</th>
                <th>Electrodomestico</th>
                <th>Fecha</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${cargas}">
                <tr>
                    <td>${item.idCarga}</td>
                    <td>${item.idCamion.modelo}</td>
                    <td>${item.idElectrodomestico.nombre}</td>
                    <td>${item.fecha}</td>
                    
                    <td><a href="CargaControlador?action=edit&id=${item.idCarga}&objeto=carga">Editar</a></td>
                    <td><a href="CargaControlador?action=delete&id=${item.idCarga}" onclick="return(confirm('Estas seguro de eliminar???????'))">Eliminar</a></td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
