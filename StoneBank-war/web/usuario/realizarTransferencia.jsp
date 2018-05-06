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
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Realizar Transferencia</title>
    </head>
    <body class="container-fluid">
        <jsp:include page="../headerUsuarioTransferencia.jsp"/>
        <div align="center">
        <h1>Datos transferencia</h1>
        <form class="form-horizontal" action="${pageContext.request.contextPath}/ServletTransferencia" method="post">
            <table>
                <tr>
                    <td>DNIEmisor:</td>
                    <td><input class="form-control" type="text" name="dniemisor" readonly="readonly" value="<%= usuario.getDniUsuario() %>" /></td>
                </tr>
                
                <tr>
                    <td>DNIReceptor:</td>
                    <td><input class="form-control" type="text" name="dnireceptor" /></td>
                </tr>
                                
                <tr>
                    <td>Cantidad:</td>
                    <td><input class="form-control" type="text" name="cantidad" /></td>
                </tr>
                                
                <tr>
                    <td>Concepto:</td>
                    <td><input  class="form-control" type="text" name="concepto" /></td>
                </tr>

            </table>
            <br>
            <%--<input type="submit" class="btn btn-default " value="Hacer transferencia"/> --%>
            
            
            <input type="submit" onclick="javascript: form.action='${pageContext.request.contextPath}/ServletTransferencia';"class="btn btn-default" value="Hacer transferencia" />
            <input type="submit" onclick="javascript: form.action='${pageContext.request.contextPath}/usuario/indexUsuario.jsp';" value="Cancelar" class="btn btn-link"> 
            
            
        </form>
        </div>
    </body>
</html>
