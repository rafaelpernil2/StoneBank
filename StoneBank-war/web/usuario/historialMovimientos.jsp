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
    Tusuario usuario;
    List<Tmovimiento> listaMovimientos;
    
    usuario = (Tusuario) request.getAttribute("user");
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movimientos de Sr <%= usuario.getApellidos() %></title>
    </head>
    <body>
        
        <h1>Historial</h1>
        <table>
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
                <td><%= usuario.getDniUsuario() %></td>
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
