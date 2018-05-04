<%-- 
    Document   : exito.jsp
    Created on : 04-may-2018, 15:56:11
    Author     : Fran Gambero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    String mensaje = (String)request.getAttribute("mensajeExito");
    String url = (String)request.getAttribute("proximaURL");
    %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SB Exito</title>
    </head>
    <body>
        <div align="center">
        <h1>Página de éxito</h1>
        <hr>
        
        <h3 style="color: #2D9024">El mensaje recibido es: <%= mensaje %></h3>
        
         <form action="<%= url %>">
                <input type="submit" value="Siguiente" />
            </form>
         
        </div>        
    </body>
</html>
