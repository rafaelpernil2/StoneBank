<%-- 
    Document   : TestJSP
    Created on : 03-may-2018, 15:40:05
    Author     : Takox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%= session.getAttribute("fran") %></h1>
    </body>
</html>
