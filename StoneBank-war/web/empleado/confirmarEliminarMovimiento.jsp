<%-- 
    Document   : confirmarEliminarMovimiento
    Created on : 19-abr-2018, 13:35:06
    Author     : rafaelpernil
--%>
<%
   int idmov = Integer.parseInt(request.getParameter("idmov")); 
   int dni = Integer.parseInt(request.getParameter("dni"));
%>    
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
        <title>Confirmar eliminar movimiento</title>
    
    </head>
    <body class="container-fluid">
        <h1>¿Está seguro de que desea eliminar el movimiento <%=idmov %> ?</h1>
            <form action="${pageContext.request.contextPath}/ServletEliminaMovimiento" method="post">
            <input type="hidden" name="idm" value="<%=idmov%>"/>
            <input type="hidden" name="dni" value="<%=dni%>"/>
             <input type="submit" class="btn btn-danger" onclick="javascript: form.action='${pageContext.request.contextPath}/ServletEliminaMovimiento';"value="Sí"/>
             <form action="${pageContext.request.contextPath}/empleado/indexEmpleado.jsp">
            <input type="submit" onclick="javascript: form.action='${pageContext.request.contextPath}/ServletVerUsuario?dni=<%=dni%>';"class="btn btn-default" value="No"/>
        </form>
         </form>
        
    </body>
</html>
