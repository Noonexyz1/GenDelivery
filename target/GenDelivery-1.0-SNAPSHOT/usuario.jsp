
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Usuario</h1>
        <br>
        <a href="UsuarioControlador?action=add">Nuevo</a>
        <table>
            <tr>
                <th>Id</th>
                <th>Correo</th>
                <th>Nombre</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${usuarios}">
                <tr>
                    <td>${item.idUsuario}</td>
                    <td>${item.correo}</td>
                    <td>${item.nombre}</td>
                    
                    <td><a href="UsuarioControlador?action=edit&id=${item.idUsuario}&objeto=usuario">Editar</a></td>
                    <td><a href="UsuarioControlador?action=delete&id=${item.idUsuario}" onclick="return(confirm('Estas seguro de eliminar???????'))">Eliminar</a></td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
