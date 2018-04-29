<%-- 
    Document   : login
    Created on : 19-abr-2018, 13:04:11
    Author     : rafaelpernil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stone Bank</title>
    </head>
    <body>
    <center>
        <h1 style="text-align: center">StoneBank</h1>
        
        <form action="LoginServlet" method="post">
            
            <table width="25%">
                <tr>
                  <th width="23%"><label for="user">Usuario: </label>
                  </th>
                  <td width="87%"><input type="text" name="user">
                  </td>
                </tr>
                <tr>
                  <th><label for="pass">Contraseña: </label></th>
                  <td><input type="password" name="pass" ></td>
                </tr>
                <tr>
                  <td colspan="2" align="center"><input type="submit" name="button" value="Acceder"></td>
                </tr>
                <tr>    
                   <td colspan="2" align="center"> <a href="alta.jsp">¿No te has registrado aun? Pulsa aquí</a></td>
                </tr>
                
             </table>
        </form> 
    </center>
    </body>
</html>
