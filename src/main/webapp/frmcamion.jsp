
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.miempresa.entidades.Camion"%>
<%
    Camion camion = (Camion) request.getAttribute("camion");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>
            <c:if test="${camion.idCamion == null}">
                Nuevo Registro
            </c:if>

                <c:if test="${camion.idCamion != null}">
                Editar Registro
            </c:if>

        </h1>
        
        
        <form action="CamionControlador" method="POST">

            <input type="hidden" name="id" value="${camion.idCamion}"> 

            <table>
                <tr>
                    <td>Modelo:</td>
                    <td><input type="text" name="modelo" value="${camion.modelo}"></td>
                </tr>
                <tr>
                    <td>Capacidad [Kg]:</td>
                    <td><input type="number" name="capacidad" value="${camion.capacidadKg}"></td>
                </tr>
                

                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar"></td>
                </tr>

            </table>
        </form>
        
        
    </body>
</html>
