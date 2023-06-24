<%@page import="com.miempresa.entidades.Usuario"%>
<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        
        <h1>
            <c:if test="${usuario.idUsuario == null}">
                Nuevo Registro
            </c:if>

                <c:if test="${usuario.idUsuario != null}">
                Editar Registro
            </c:if>

        </h1>
        
        
        
        <form action="UsuarioControlador" method="POST">

            <input type="hidden" name="id" value="${usuario.idUsuario}"> 

            <table>
                
                <tr>
                    <td>Correo:</td>
                    <td><input type="text" name="correo" value="${usuario.correo}"></td>
                </tr>
                <tr>
                    <td>Contraseña:</td>
                    <td><input type="password" name="contrasena" value="${usuario.contrasena}"></td>
                </tr>
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="nombre" value="${usuario.nombre}"></td>
                </tr>

                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar"></td>
                </tr>

            </table>
        </form>
                
                
                
    </body>
</html>
