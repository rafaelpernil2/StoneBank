<%-- 
    Document   : indexEmpleado
    Created on : 19-abr-2018, 13:24:49
    Author     : Victor Garcia
--%>

<%@page import="java.util.List"%>
<%@page import="stonebank.entity.Tusuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    Tusuario usuario = (Tusuario)session.getAttribute("empleadoLogin"); //antes request
    List<Tusuario> listaUsuarios = (List<Tusuario>)session.getAttribute("listaUsuarios");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Vista General</title>
    </head>
    <body class="container-fluid">
        <jsp:include page="../headerEmpleadoBusqueda.jsp"/>
        <h1>Usuarios</h1> <%-- Falta poner el nombre del usuario en cuestión --%>
        <h2>&Uacute;ltimas Transacciones</h2>
        
        
        
        
        <table class="table">
            <tr>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Número de cuenta</th>
                <th>Rol </th>
            </tr>
        <%-- Cuerpo de la lista --%>
        <% for (Tusuario user : listaUsuarios){ %>
        <tr>
            <td ><%= user.getNombre() %> </td>
            <td><%=user.getApellidos()%></td>
            <td><%= user.getNumCuenta() %> </td>
            <td><%= user.getTrolIdtrol().getNombre() %></td>
           <td><a href="${pageContext.request.contextPath}/ServletVerUsuario?dni=<%= user.getDniUsuario()%>" class="btn btn-primary">Ver</a> </td>
           <td><a href="${pageContext.request.contextPath}/ServletGestionarUsuario?dni=<%= user.getDniUsuario()%>" class="btn btn-warning">Editar</a></td> 
        </tr>
        
        <% } %>
        </table>
        
        <%--<form action="${pageContext.request.contextPath}/ServletCerrarSesion">
            <input type="submit"  value="Cerrar Sesión" />
        </form>
        --%>
    </body>
</html>