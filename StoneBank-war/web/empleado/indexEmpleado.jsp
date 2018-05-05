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
        <title>Vista General</title>
    </head>
    <body>
        <h1>Usuarios</h1> <%-- Falta poner el nombre del usuario en cuestión --%>
        <h2>&Uacute;ltimas Transacciones</h2>
        
        <form action="ServletBuscarUsuarioEmpleado">
            Nombre: <input type="text" name="nombre" value="">
            <input type="submit" value="Buscar">
        </form>
        <br>
        
        
        <table>
            <tr>
                <th>Nombre</th>
                <th>Número de cuenta</th>
                <th>Rol </th>
            </tr>
        <%-- Cuerpo de la lista --%>
        <% for (Tusuario user : listaUsuarios){ %>
        <tr>
            <td ><%= user.getNombre() %> </td>
            <td><%= user.getNumCuenta() %> </td>
            <td><%= user.getTrolIdtrol().getIdtrol() %></td>
           <td><a href="${pageContext.request.contextPath}/ServletVerUsuario?dni=<%= user.getDniUsuario() %>">Ver</a> </td>
           <td><a href="${pageContext.request.contextPath}/ServletGestionarUsuario?dni=<%= user.getDniUsuario() %>">Editar</a></td> 
        </tr>
        
        <% } %>
        </table>
        <br>
        <br>
        <form action="${pageContext.request.contextPath}/alta.jsp">
            <input type="submit" value="Dar de alta" />
        </form>
        <br>
        <br>
        <form action="${pageContext.request.contextPath}/ServletCerrarSesion">
            <input type="submit"  value="Cerrar Sesión" />
        </form>
        
    </body>
</html>