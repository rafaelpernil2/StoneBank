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
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>JSP Page</title>
    </head>
    <%= listamov.toString() %> 
   <% if (listamov == null ) { %>
    Lista Vacia
   <% } %> 
   <%= concepto%> 
   <%= dni%> 
    <body class="container-fluid">
        <jsp:include page="headerUsuario.jsp"/>
        <h1>Equisde</h1>
    </body>

