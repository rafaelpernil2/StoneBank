<%-- 
    Document   : alta
    Created on : 30-abr-2018, 16:52:58
    Author     : Fran Gambero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta Cliente</title>
    </head>
    <body>
        <div align="left">
            <form action="login.jsp">
                <input type="submit" value="<<< Volver atrás" />
            </form>
        </div>        
        
        <div align="center">
        <h1>Crear nuevo cliente</h1>
        <form action="ServletCreaUsuario" method="post">
            <table>
                <tr>
                    <td>Nombre: </td>
                    <td><input type="text" name="nombre"  maxlength="30"/></td>                    
                </tr>
                <tr>
                    <td>Apellido: </td>
                    <td><input type="text" name="apellido"/></td>                    
                </tr>
                <tr>
                    <td>DNI: </td>
                    <td><input type="text" name="dni"/></td>                    
                </tr>
                <tr>
                    <td>Contraseña: </td>
                    <td><input type="text" name="contrasena"/></td>                    
                </tr>
                <tr>
                    <td>Telefono: </td>
                    <td><input type="text" name="telefono"/></td>                    
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><input type="text" name="email"/></td>                    
                </tr>
                <tr>
                    <td>Domicilio: </td>
                    <td><input type="text" name="domicilio"/></td>                    
                </tr>
            </table>
            <br>
            <input type="submit" value="Crear usuario" />
        </form>
        </div>
    </body>
</html>
