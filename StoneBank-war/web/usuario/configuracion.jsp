<%-- 
    Document   : configuracion
    Created on : 19-abr-2018, 13:29:13
    Author     : JesusContreras
--%>

<%@page import="stonebank.entity.Tusuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Tusuario usuario;
    int dni,telefono;
    String nombre, apellido, email, domicilio;
    
    usuario=(Tusuario) request.getAttribute("usuario");
    if(usuario !=null){
        dni=usuario.getDniUsuario();
        nombre=usuario.getNombre();
        apellido=usuario.getApellidos();
        telefono=usuario.getTelefono();
        email=usuario.getEmail();
        domicilio=usuario.getDomicilio();
        //uno mas para contraseña preguntar como se haría a fran
    }
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
        <title>Editar empleado</title>
    </head>
    <body class="container-fluid">
        <jsp:include page="../headerUsuario.jsp"/>
          <div align="center">
        <h1>Datos usuario</h1>
        <form action="${pageContext.request.contextPath}/ServletActualizarUsuario" method="post">

            <table class="table">
                <tr>
                    <td>Nombre: </td>
                    <td><input class="form-control" type="text" name="nombre" value="<%=usuario.getNombre()%>"/></td>                    
                </tr>
                <tr>
                    <td>Apellido: </td>
                    <td><input class="form-control" type="text" name="apellido" value="<%=usuario.getApellidos()%>"/></td>                    
                </tr>
                <tr>
                    <td>DNI: </td>
                    <td><input  class="form-control" type="text" name="dni" value="<%=usuario.getDniUsuario()%>" readonly="readonly"/></td>                    
                </tr>
                <tr>
                    <td>Contraseña: </td>
                    <td><input class="form-control" type="password" name="contrasena"  /></td>
                                        
                    <td>Nueva contraseña: </td>
                    <td><input class="form-control" type="password" name="nuevacontrasena" /></td>        
                </tr>
                <tr>
                    <td>Telefono: </td>
                    <td><input  class="form-control"type="text" name="telefono" value="<%=usuario.getTelefono()%>"/></td>                    
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><input class="form-control" type="text" name="email" value="<%=usuario.getEmail() %>"/></td>                    
                </tr>
                <tr>
                    <td>Domicilio: </td>
                    <td><input class="form-control" type="text" name="domicilio" value="<%= usuario.getDomicilio() %>"/></td>                    
                </tr>
            </table>
            <br>
            <input type="submit" class="btn btn-primary" value="Editar usuario"  onclick="javascript: form.action='${pageContext.request.contextPath}/ServletActualizarUsuario';"/>
                                
                
                     <input type="submit" onclick="javascript: form.action='${pageContext.request.contextPath}/usuario/indexUsuario.jsp';" value="Cancelar" class="btn btn-default"> 
                </form>
        </form>
                <p> Es necesario introducir la contraseña para aplicar cambios</p>
        </div>
    </body>
</html>