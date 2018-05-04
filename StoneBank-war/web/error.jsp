<%-- 
    Document   : error
    Created on : 04-may-2018, 18:02:08
    Author     : Fran Gambero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
        String mensaje = "Hola caracola";
        String url = "/StoneBank-war/login.jsp";
    %>
    
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SB Error</title>
    </head>
    <body>
        <div align="center">
        <h1>Página de error</h1>
        <hr>
        
        <h3 style="background-color:Tomato;">El mensaje recibido es: <%= mensaje %></h3>
        
         <form action="<%= url %>">
                <input type="submit" value="Siguiente" />
            </form>
        
        </div>
    </body>
</html>
