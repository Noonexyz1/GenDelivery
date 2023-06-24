<%@page import="java.util.List"%>
<%@page import="com.miempresa.entidades.Camion"%>
<%@page import="com.miempresa.entidades.Electrodomestico"%>
<%@page import="com.miempresa.entidades.Carga"%>
<%
    Carga carga = (Carga) request.getAttribute("carga");
    List<Camion> camiones = (List<Camion>) request.getAttribute("camiones");
    List<Electrodomestico> electrodomesticos = (List<Electrodomestico>) request.getAttribute("electrodomesticos");
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
           Solamente debe haber de editar

        </h1>
        
        
        
        
        <form action="CargaControlador" method="POST">

            <input type="hidden" name="id" value="${carga.idCarga}"> 

            <table>
                
                
                <tr>
                    <select name="idCamio">
                        <option value="">--- Seleccione ---</option>
                        <c:forEach var="item" items="${camiones}">
                            <option value="${item.idCamion}" 
                                    <c:if test="${carga.idCamion == item.idCamion}">
                                        selected
                                    </c:if>
                                        >${item.modelo}</option>

                        </c:forEach>
                    </select>
                
                </tr>
                
                <tr>
                 
                    <c:forEach var="item" items="${electrodomesticos}">
                        <input type="checkbox" name="idElectrodomestico" value="${item.idElectrodomestico}"> ${item.nombre}<br>
                        <br>
                    </c:forEach>
                        
                </tr>
                
                
                <tr>
                    <td>Fecha:</td>
                    <td><input type="date" name="fecha" value="${carga.fecha}"></td>
                </tr>
                

                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar"></td>
                </tr>
                
                
                
            </table>
        </form>
                
                
                
    </body>
</html>
