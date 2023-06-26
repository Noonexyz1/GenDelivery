<%@page import="com.miempresa.entidades.Electrodomestico"%>
<%
    Electrodomestico electrodomestico = (Electrodomestico) request.getAttribute("electrodomestico");
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
            <c:if test="${electrodomestico.idElectrodomestico == null}">
                Nuevo Registro
            </c:if>

                <c:if test="${electrodomestico.idElectrodomestico != null}">
                Editar Registro
            </c:if>

        </h1>
        
        
        
        <form action="ElectrodomesticoControlador" method="POST">

            <input type="hidden" name="id" value="${electrodomestico.idElectrodomestico}"> 

            <table>
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="nombre" value="${electrodomestico.nombre}"></td>
                </tr>
                <tr>
                    <td>Peso [Kg]:</td>
                    <td><input type="number" name="peso" value="${electrodomestico.pesoKg}"></td>
                </tr>
                <tr>
                    <td>Beneficio:</td>
                    <td><input type="number" name="beneficio" value="${electrodomestico.beneficio}"></td>
                </tr>
               

                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar"></td>
                </tr>

            </table>
        </form>
                
                
                
                
                
    </body>
</html>
