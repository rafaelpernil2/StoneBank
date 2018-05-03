<%-- 
    Document   : historialTransferencia
    Created on : 19-abr-2018, 13:27:07
    Author     : JesusContreras
--%>

<%@page import="stonebank.entity.Ttransferencia"%>
<%@page import="java.util.List"%>
<%@page import="stonebank.entity.Tusuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    Tusuario usuario;
    List<Ttransferencia> listaTransferencias;
    
    usuario = (Tusuario) request.getAttribute("user");
    listaTransferencias = usuario.getTtransferenciaList();
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transferencia de Sr <%= usuario.getApellidos() %></title>
    </head>
    <body>
        
        <h1>Historial</h1>
        <table>
            <th>DNIEmisor</th>             
            <th>DNIReceptor</th>            
            <th>Concepto</th>               
            <th>Cantidad</th>                                       
            <th>Fecha</th>                
            <%
              for (Ttransferencia transferencia : listaTransferencias){  
            %>
            <tr>
                <td><%= transferencia.getDNIEmisor() %></td>
                <td><%= transferencia.getDNIEmisor() %></td>
                <td><%= transferencia.getConcepto() %></td>
                <td><%= transferencia.getCantidad() %></td>
                <td><%= transferencia.getFecha() %></td>
            </tr>
            <%
              }  
            %>
           
            
        </table>
    </body>
</html>
