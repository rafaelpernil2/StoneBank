<%-- 
    Document   : historialMovimientos
    Created on : 19-abr-2018, 13:31:39
    Author     : JesusContreras
--%>

<%@page import="stonebank.entity.Tmovimiento"%>
<%@page import="java.util.List"%>
<%@page import="stonebank.entity.Tusuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Tmovimiento> listaMovimientos = (List<Tmovimiento>)request.getAttribute("listaMovimientos");
    String mensaje = (String)request.getAttribute("mensaje"); 
   
    
%>
<!DOCTYPE html>
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
        <title><%= mensaje%></title>
    </head>
    <body class="container-fluid">
        <jsp:include page="../headerUsuario.jsp"/>
        <h1><%= mensaje %></h1>
        <br>
        
        <table class="table">
            <th>IDMovimiento</th>             
            <th>DNIReceptor</th>            
            <th>Concepto</th>               
            <th>Cantidad</th>                          
            <th>ibanEntidad</th>               
            <th>Fecha</th>                
            <%
              for (Tmovimiento movimiento : listaMovimientos){  
            %>
            <tr>
                <td><%= movimiento.getIdtmovimiento() %></td>
                <td><%= movimiento.getTusuariodniUsuario().getDniUsuario() %></td>
                <td><%= movimiento.getConcepto() %></td>
                <td><%= movimiento.getCantidad() %></td>
                <td><%= movimiento.getIbanEntidad() %></td>
                <td><%= movimiento.getFecha() %></td>
            </tr>
            <%
              }  
            %>
           
            
        </table>
    </body>
</html>
