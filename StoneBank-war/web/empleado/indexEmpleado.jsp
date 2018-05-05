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
        
        <form action="/StoneBank-war/ServletBuscarUsuarioEmpleado">
            Nombre: <input type="text" name="nombre" value="">
            <input type="submit" value="Buscar">
        </form>
        <br>
        
        <%-- Cabecera de la lista --%>
        <div class="row">
            <div class="column"><b>Nombre</b> &nbsp;&nbsp;&nbsp;&nbsp; <b>Número de cuenta</b></div>
        </div>
        
        <%-- Cuerpo de la lista --%>
        <% for (Tusuario user : listaUsuarios){ %>
        <div class="row">
            <div class="column"><%= user.getNombre() %> &nbsp;&nbsp;&nbsp;&nbsp; <%= user.getNumCuenta() %> &nbsp;&nbsp;&nbsp;&nbsp; <%= user.getTrolIdtrol().getIdtrol() %> &nbsp;&nbsp;&nbsp;&nbsp; <a href="usuarioSeleccionado.jsp?dni=<%= user.getDniUsuario() %>">Ver</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="gestionarUsuario.jsp?dni=<%= user.getDniUsuario() %>">Editar</a></div>
        </div>
        
        <% } %>
        
        <br>
        <br>
        <form action="/StoneBank-war/alta.jsp">
            <input type="submit" value="Dar de alta" />
        </form>
        <br>
        <br>
        <form action="ServletCerrarSesion.java?=<%= usuario.getDniUsuario() %>">
            <input type="submit" value="Cerrar Sesión" />
        </form>
        
    </body>
</html>