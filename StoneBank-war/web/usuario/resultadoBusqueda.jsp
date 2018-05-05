<%-- 
    Document   : resultadoBusqueda
    Created on : 19-abr-2018, 13:31:53
    Author     : rafaelpernil
--%>

<%@page import="java.util.List"%>
<%@page import="stonebank.entity.Tmovimiento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% List<Tmovimiento> listamov = (List<Tmovimiento>)request.getAttribute("resultadoBusqueda");
    String concepto = (String)request.getAttribute("concepto"); 
    Integer dni = (Integer)request.getAttribute("dni"); 

%> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%= listamov.toString() %> 
   <% if (listamov == null ) { %>
    Lista Vacia
   <% } %> 
   <%= concepto%> 
   <%= dni%> 
    <body>
        <h1>Equisde</h1>
    </body>

