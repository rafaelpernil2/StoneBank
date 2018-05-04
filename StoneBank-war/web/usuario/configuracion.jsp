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
        <title>Editar usuario</title>
    </head>
    <body>
          <div align="center">
        <h1>Datos usuario</h1>
        <form action="ServletActualizarUsuario" method="post">
            <table>
                <tr>
                    <td>Nombre: </td>
                    <td><input type="text" name="nombre" value="<%=usuario.getNombre()%>"/></td>                    
                </tr>
                <tr>
                    <td>Apellido: </td>
                    <td><input type="text" name="apellido" value="<%=usuario.getApellidos()%>"/></td>                    
                </tr>
                <tr>
                    <td>DNI: </td>
                    <td><input type="text" name="dni" value="<%=usuario.getDniUsuario()%>" readonly="readonly"/></td>                    
                </tr>
                <tr>
                    <td>Contraseña: </td>
                    <td><input type="text" name="contrasena"  /></td>
                                        
                    <td>Nueva contraseña: </td>
                    <td><input type="text" name="nuevacontrasena" /></td>        
                </tr>
                <tr>
                    <td>Telefono: </td>
                    <td><input type="text" name="telefono" value="<%=usuario.getTelefono()%>"/></td>                    
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><input type="text" name="email" value="<%=usuario.getEmail() %>"/></td>                    
                </tr>
                <tr>
                    <td>Domicilio: </td>
                    <td><input type="text" name="domicilio" value=" <%= usuario.getDomicilio() %>"/></td>                    
                </tr>
            </table>
            <br>
            <input type="submit" value="Editar usuario" />
        </form>
                <p> Es necesario introducir la contraseña para aplicar cambios</p>
        </div>
    </body>
</html>
