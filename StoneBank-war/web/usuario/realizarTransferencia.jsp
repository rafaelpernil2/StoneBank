<%-- 
    Document   : realizarTransferencia
    Created on : 19-abr-2018, 13:30:10
    Author     : JesusContreras
--%>

<%@page import="stonebank.entity.Tusuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
  Tusuario usuario;

  usuario = (Tusuario) session.getAttribute("usuarioLogin");
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Realizar Transferencia</title>
    </head>
    <body>
        <div align="center">
        <h1>Datos transferencia</h1>
        <form action="ServletTransferencia" method="post">
            <table>
                <tr>
                    <td>DNIEmisor:</td>
                    <td><input type="text" name="dniemisor" readonly="readonly" value="<%= usuario.getDniUsuario() %>" /></td>
                </tr>
                
                <tr>
                    <td>DNIReceptor:</td>
                    <td><input type="text" name="dnireceptor" /></td>
                </tr>
                                
                <tr>
                    <td>Cantidad:</td>
                    <td><input type="text" name="cantidad" /></td>
                </tr>
                                
                <tr>
                    <td>Concepto:</td>
                    <td><input type="text" name="concepto" /></td>
                </tr>

            </table>
            <br>
            <input type="submit" value="Hacer transferencia"/>
        </form>
        </div>
    </body>
</html>
