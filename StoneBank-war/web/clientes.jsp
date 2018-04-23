<%-- 
    Document   : clientes
    Created on : 09-abr-2018, 12:16:51
    Author     : guzman
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="stonebank.entity.Tusuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Tusuario> lista = (List)request.getAttribute("listau");
    if (lista == null){
        lista = new ArrayList<Tusuario>();
    }
%>    

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de clientes</h1>
        <table>
            <th>
            <td>IDCUSTOMER</td>                
            </th>
            <% 
                for (Tusuario cliente:lista) {
            %>    
            <tr>
                <td>
                <%= cliente.getNombre() %>
                </td>
            </tr>
            <% 
                }
            %>    
            
        </table>
    </body>
</html>
