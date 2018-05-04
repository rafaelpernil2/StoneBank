<%-- 
    Document   : nuevoMovimiento
    Created on : 19-abr-2018, 13:34:02
    Author     : rafaelpernil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Movimiento</title>
    </head>
    <body>
        <div align="center">
        <h1>Crear nuevo movimiento</h1>
        <form action="${pageContext.request.contextPath}/ServletCreaMovimiento" method="post">
            <table>
                <tr>
                    <td>Concepto: </td>
                    <td><input type="text" name="concepto"/></td>                    
                </tr>
                <tr>
                    <td>Cantidad: </td>
                    <td><input type="text" name="cantidad"/></td>                    
                </tr>
                <tr>
                    <td>IBAN Entidad: </td>
                    <td><input type="text" name="iban"/></td>                    
                </tr>
                
            </table>
            <br>
            <input type="submit" value="Crear Movimiento" />
        </form>
        </div>
    </body>
</html>
