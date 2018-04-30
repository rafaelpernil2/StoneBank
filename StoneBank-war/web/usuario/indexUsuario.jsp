<%-- 
    Document   : indexUsuario
    Modified on : 30-abr-2018, 12:14:37
    Author     : Fran Gambero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index Usuario</title>
    </head>
    <body>
        <h1>¡Bievenido/a usuario/a (nombre)!</h1>
        <hr>
        
        <div class = "padre">
        <div class = "subdiv-izquierdo" style="width:40%;display:inline-block;">
        <h3>Últimas transacciones</h3>
        <table border="1">
            <tbody>
                <tr>
                    <td>Fecha</td>
                    <td>Cliente</td>
                    <td>Concepto</td>
                    <td>Importe</td>
                </tr>
            </tbody>
            
        </table>
        
        <a href="ServletListaMovimientos" >Ver todas las transacciones</a>
        </div>
        
            <div class = "subdiv-derecho" style="width:40%;display:inline-block;">
                Hola
                quertal
            </div>
    </div>
    </body>
</html>
