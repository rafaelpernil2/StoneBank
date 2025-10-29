<%@page import="java.util.ArrayList"%>
<%@page import="stonebank.ejb.TusuarioFacade"%>
<%@page import="stonebank.entity.Tmovimiento"%>
<%@page import="java.util.List"%>
<%@page import="stonebank.entity.Tusuario"%>

<!DOCTYPE html>

<%
    Tusuario usuario = (Tusuario)session.getAttribute("usuarioLogin"); //antes request
    List<Tusuario> listaUsuarios = (List<Tusuario>)session.getAttribute("listaUsuarios");
    //A lo mejor hay que usar session en lugar de request
    //usuario.getTmovimientoList()
     List<Tmovimiento> sublista;
     Double saldo = (Double)session.getAttribute("saldoUsuario");
    if(usuario.getTmovimientoList().size() > 2){
    sublista = (usuario.getTmovimientoList().subList(0, 2));
    //Sublista empieza en 0

    }    else {
        
        sublista = new ArrayList<Tmovimiento>(); 
        for(Tmovimiento m : usuario.getTmovimientoList()){
            sublista.add(m); 
        }
        
    }
%>

<html lang="es" >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index Usuario</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body class="container-fluid">
        <jsp:include page="headerUsuario.jsp"/>
        <div class="row">
           
            <div class="col-md-6"><h1>�Bienvenido/a  <%= usuario.getNombre() %> <%= usuario.getApellidos() %>!</h1>
                
            </div>
            <div class="col-md-3"></div>
            
            <div class="col-md-2"><p> Su saldo es de <h1><%= saldo  %> euros</h1></p></div>
            
                
              
              
              <br><br>
              
         
                
                
            </div>
        </div>    
        <hr>
        
        
        
        <div class = "padre" style="width:100%">
            
          <div class = "subdiv-izquierdo" style="width:40%;display:inline-table;">
            <h3>�ltimos movimientos</h3>
            
            <table border="1" class="table">
                <thead>
                    <tr>
                        <th>Concepto</th>
                        <th>IBAN</th>
                        <th>Importe</th>
                        <th>Fecha</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        for(Tmovimiento movi: sublista){      

                        %>
                    <tr>
                        <%--<td><%= movi.getTusuariodniUsuario().toString() %></td>--%>
                        <td><%= movi.getConcepto() %></td>
                        <td><%= movi.getIbanEntidad() %></td>
                        <td><%= movi.getCantidad() %> </td>
                        <td><%= movi.getFecha() %></td>
                    </tr>
                        <%
                            
                       }
                        %>
                </tbody>
            </table>
            
            <br>
            <a href="${pageContext.request.contextPath}/ServletListaMovimientos" >Ver todos los movimientos</a>
          </div>
            
          
                   
        </div>
              <br><br>
              
        <%-- <a href="${pageContext.request.contextPath}/ServletEditarUsuario?dni=<%= usuario.getDniUsuario() %>">Configuraci�n</a>
        <br>
        <a href="${pageContext.request.contextPath}/ServletCerrarSesion">Cerrar sesi�n</a> --%>
    </body>
</html>
