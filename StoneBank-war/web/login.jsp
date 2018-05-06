<%-- 
    Document   : login
    Created on : 19-abr-2018, 13:04:11
    Modified on: 1-may-2018,  14:32:20
    Author     : rafaelpernil
    Modified by: Fran Gambero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <title>Stone Bank</title>
    </head>
    <body class="container-fluid">
        <jsp:include page="headerLogin.jsp"/>
    <center>
        <h1 style="text-align: center">StoneBank</h1>
        <img style="height: 180px" src="data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTkuMC4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iTGF5ZXJfMSIgeD0iMHB4IiB5PSIwcHgiIHZpZXdCb3g9IjAgMCA1MDQuMzIyIDUwNC4zMjIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUwNC4zMjIgNTA0LjMyMjsiIHhtbDpzcGFjZT0icHJlc2VydmUiIHdpZHRoPSI1MTJweCIgaGVpZ2h0PSI1MTJweCI+CjxwYXRoIHN0eWxlPSJmaWxsOiNCRjM5MkI7IiBkPSJNMzEuNjA3LDE1Ny42MzhsNjMuMDE1LTc4Ljc2OWMyNC44MTItMzEuNTA4LDI0LjAyNS0zMS41MDgsNDcuMjYyLTMxLjUwOGgyMjAuNTU0ICBjMjMuMjM3LDAsMjIuNDQ5LDAsNDcuMjYyLDMxLjUwOGw2My4wMTUsNzguNzY5YzQyLjUzNSw0Ny4yNjIsNDEuNzQ4LDQ3LjI2MiwwLDk0LjUyM0wyOTkuNDIyLDQyNS40NTMgIGMtMTUuMzYsMTUuNzU0LTIzLjIzNywzMS41MDgtNDcuMjYyLDMxLjUwOHMtMzEuOTAyLTE1Ljc1NC00Ny4yNjItMzEuNTA4TDMxLjYwNywyNTIuMTYxICBDLTEwLjE0MSwyMDQuODk5LTEwLjkyOSwyMDQuODk5LDMxLjYwNywxNTcuNjM4eiIvPgo8cGF0aCBzdHlsZT0iZmlsbDojQ0M2MTU1OyIgZD0iTTM3OC4xOTEsMjM2LjQwN0gxMjYuMTNsMTA4LjMwOCwyMTYuNjE1YzUuMTIsMi4zNjMsMTAuNjM0LDMuOTM4LDE3LjcyMywzLjkzOCAgczEyLjYwMy0xLjE4MiwxNy4zMjktMy41NDVMMzc4LjE5MSwyMzYuNDA3eiBNNTAzLjgyOCwyMDQuODk5di00LjMzMmMtMi4zNjMtMTAuNjM0LTEyLjYwMy0yMi4wNTUtMzEuMTE0LTQyLjkyOWwtNjMuMDE1LTc4Ljc2OSAgYy0yNC44MTItMzEuNTA4LTI0LjAyNS0zMS41MDgtNDcuMjYyLTMxLjUwOGgtMTEwLjY3bDEyNi4wMzEsMTg5LjA0NkMzNzkuMzczLDIzNC44MzEsNTAzLjgyOCwyMDQuODk5LDUwMy44MjgsMjA0Ljg5OXogICBNOTQuNjIyLDc4Ljg2OGwtNjMuMDE1LDc4Ljc2OWMtMjEuMjY4LDIzLjYzMS0zMS41MDgsMzUuNDQ2LTMxLjUwOCw0Ny4yNjJjMS4xODIsMC4zOTQsMTI0LjQ1NSwyOS45MzIsMTI2LjAzMSwzMS41MDggIEwyNTIuMTYxLDQ3LjM2MUgxNDEuODg0QzExOC42NDcsNDcuMzYxLDExOS40MzUsNDcuMzYxLDk0LjYyMiw3OC44Njh6Ii8+CjxwYXRoIHN0eWxlPSJmaWxsOiNEOTg4ODA7IiBkPSJNMTI2LjEzLDIzNi40MDdMMjUyLjE2MSw0Ny4zNjFsMTI2LjAzMSwxODkuMDQ2TDEyNi4xMywyMzYuNDA3TDEyNi4xMywyMzYuNDA3eiIvPgo8cGF0aCBzdHlsZT0iZmlsbDojRDQ3OTZFOyIgZD0iTTM5Mi43NjQsNTcuOTk1Yy05LjQ1Mi0xMC42MzQtMTQuMTc4LTEwLjYzNC0zMC4zMjYtMTAuNjM0SDI1Mi4xNjFsMTI2LjAzMSwxODkuMDQ2ICBDMzc4LjE5MSwyMzYuNDA3LDM5Mi43NjQsNTcuOTk1LDM5Mi43NjQsNTcuOTk1eiBNMTExLjU1OCw1OC4zODhsMTQuNTcyLDE3OC4wMThMMjUyLjE2MSw0Ny4zNjFIMTQxLjg4NCAgQzEyNS43MzYsNDcuMzYxLDEyMS40MDQsNDcuMzYxLDExMS41NTgsNTguMzg4eiIvPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8L3N2Zz4K" />
        <form  class="form-horizontal" action="ServletLogin" method="post">


            <div class="form-group">
                <label for="user" class="control-label col-sm-offset-3 col-sm-2">DNI:    </label>
                <div class="  col-sm-2">
                    <input class="form-control" type="text" name="user" >
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-offset-3 col-sm-2" for="pass">Contraseña: </label>
                <div class=" col-sm-2">
                    <input type="password" class="form-control" name="pass" >
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <input type="submit" class="btn btn-default" name="button" value="Acceder">
                </div>
            </div>
            <%--<tr>    
                <td colspan="2" align="center"> <a href="altaSinLogin.jsp">¿No te has registrado aun? Pulsa aquí</a></td>
            </tr>
            --%>

        </form> 
    </center>
</body>
</html>
