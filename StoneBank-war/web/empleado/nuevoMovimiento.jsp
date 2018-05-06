<%-- 
    Document   : nuevoMovimiento
    Created on : 19-abr-2018, 13:34:02
    Author     : rafaelpernil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <% 
    String dni = request.getParameter("dni");
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
        <title>Crear Movimiento</title>
    </head>
    <body class="container-fluid">
         <jsp:include page="../headerEmpleado.jsp"/>
        <div align="center" >
        <h1>Crear nuevo movimiento</h1>
        <h2>para el usuario con DNI: <%= dni %></h2>
        <form  class="form-horizontal"  action="${pageContext.request.contextPath}/ServletCreaMovimiento" method="post">
            <table>
                <tr class="form-group">
                    <td>Concepto: </td>
                    <td><input class="form-control" type="text" name="concepto" ></td>                    
                </tr>
                <tr class="form-group">
                    <td>Cantidad: </td>
                    <td><input class="form-control" type="text" name="cantidad" /></td>                    
                </tr>
                <tr class="form-group">
                    <td>IBAN Entidad: </td>
                    <td><input  class="form-control" type="text" name="iban"/></td>                    
                </tr>
                
            </table>
            <br>
            <input type ="hidden" value ="<%= dni %>"  name ="dni" /> 
            <input type="submit" onclick="javascript: form.action='${pageContext.request.contextPath}/ServletCreaMovimiento';"class="btn btn-default" value="Crear Movimiento" />
            <input type="submit" onclick="javascript: form.action='${pageContext.request.contextPath}/ServletVerUsuario?dni=<%=dni%>';" value="Cancelar" class="btn btn-link"> 
        </form>
        </div>
    </body>
</html>
