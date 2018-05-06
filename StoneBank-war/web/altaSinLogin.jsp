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
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Alta Cliente</title>
    </head>
    <body class="container-fluid">
        <div align="center">
        <h1>Crear nuevo cliente</h1>
        <form class="form-horizontal" action="ServletCreaUsuario" method="post">
            <table >
                <tr class="form-group">
                    <td>Nombre: </td>
                    <td><input type="text"  class="form-control" name="nombre"  maxlength="30"/></td>                    
                </tr>
                <tr class="form-group">
                    <td>Apellido: </td>
                    <td><input type="text"  class="form-control" name="apellido"/></td>                    
                </tr>
                <tr class="form-group">
                    <td>DNI: </td>
                    <td><input type="text"  class="form-control" name="dni"/></td>                    
                </tr>
                <tr class="form-group">
                    <td>Contraseña: </td>
                    <td><input type="password"  class="form-control" name="contrasena"/></td>                    
                </tr>
                <tr class="form-group">
                    <td>Telefono: </td>
                    <td><input type="text"  class="form-control" name="telefono"/></td>                    
                </tr>
                <tr class="form-group">
                    <td>Email: </td>
                    <td><input type="text"  class="form-control" name="email"/></td>                    
                </tr>
                <tr class="form-group">
                    <td>Domicilio: </td>
                    <td><input type="text"  class="form-control" name="domicilio"/></td>                    
                </tr>
            </table>
            <br>
            <input type="submit" class="btn-primary" value="Crear usuario" />
        </form>
        <form action="login.jsp" method="post" >
            <input type="submit" class="btn-default" value="Cancelar" />
            
            
        </form>
        </div>
    </body>
</html>
