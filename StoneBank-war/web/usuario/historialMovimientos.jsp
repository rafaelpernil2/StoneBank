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
        <title><%= mensaje%></title>
    </head>
    <body>
        <div align="left">
            <form action="indexUsuario.jsp">
                <input type="submit" value="<<< Volver atrás" />
            </form>
        </div>
        
        <h1><%= mensaje %></h1>
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
