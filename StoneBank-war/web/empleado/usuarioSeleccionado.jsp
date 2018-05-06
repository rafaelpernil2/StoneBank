<%-- 
    Document   : usuarioSeleccionado
    Created on : 19-abr-2018, 13:32:09
    Author     : JesusContreras
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="stonebank.entity.Tusuario"%>
<%@page import="stonebank.entity.Ttransferencia"%>
<%@page import="stonebank.entity.Tmovimiento"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Tmovimiento> listaMovimientos = (List<Tmovimiento>) request.getAttribute("listaMov");  
    List<Ttransferencia> listaTransferencias = (List<Ttransferencia>) request.getAttribute("listaTrans");
    Tusuario usuario =(Tusuario) request.getAttribute("usuarioVer");
    listaMovimientos = usuario.getTmovimientoList();
    listaTransferencias = usuario.getTtransferenciaList();
    
    
    
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
        <title>Usuario Seleccionado</title>
    </head>
    <body class="container-fluid">
        
         <jsp:include page="../headerEmpleadoUsuarioSeleccionado.jsp"/>
         <div class="row">
             
             <div class="col-md-3">
                    <table class="table">
                <tr>
                    <td>Nombre: </td>
                    <td><input class="form-control"  type="text" name="nombre" value="<%=usuario.getNombre()%>" readonly="readonly"/></td>                    
                </tr>
                <tr>
                    <td>Apellido: </td>
                    <td><input class="form-control" type="text" name="apellido" value="<%=usuario.getApellidos()%>" readonly="readonly"/></td>                    
                </tr>
                <tr>
                    <td>DNI: </td>
                    <td><input class="form-control" type="text" name="dni" value="<%=usuario.getDniUsuario()%>" readonly="readonly"/></td>                    
                </tr>

                <tr>
                    <td>Telefono: </td>
                    <td><input class="form-control" type="text" name="telefono" value="<%=usuario.getTelefono()%>" readonly="readonly"/></td>                    
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><input class="form-control" type="text" name="email" value="<%=usuario.getEmail() %>" readonly="readonly"/></td>                    
                </tr>
                <tr>
                    <td>Domicilio: </td>
                    <td><input  class="form-control" type="text" name="domicilio" value="<%= usuario.getDomicilio() %>" readonly="readonly"/></td>                    
                </tr>
            </table>
             </div>
                
                <div class="col-md-9">
                    <h3>Historial de Movimientos</h3>
        <table class="table">
            <tr>
            <th>IDMovimiento</th>             
            <th>DNIReceptor</th>            
            <th>Concepto</th>               
            <th>Cantidad</th>                          
            <th>ibanEntidad</th>               
            <th>Fecha</th> 
            </tr>
            <%
              for (Tmovimiento movimiento : listaMovimientos){  
            %>
            <tr>
                <td><%= movimiento.getIdtmovimiento() %></td>
                <td><%= movimiento.getTusuariodniUsuario().getDniUsuario() %></td>
                <td><%= movimiento.getConcepto() %></td>
                <td><%= movimiento.getCantidad() %></td>
                <td><%= movimiento.getIbanEntidad() %></td>
                <td><%= movimiento.getFecha() %></td>
                        <td><form method="post" action="${pageContext.request.contextPath}/empleado/confirmarEliminarMovimiento.jsp">
                                <input type="hidden" name="idmov" value="<%=movimiento.getIdtmovimiento() %>" />
                                <input type="hidden" name="dni" value="<%=usuario.getDniUsuario()%>" />
                                       <input type="submit" class="btn btn-danger" value="Eliminar movimiento" />
                    </form>
            </tr>
            
            <%
              }  
            %>
           
                </table>
                </div>
           <div class="col-md-9">
                    <h3>Historial de Transferencias</h3>
        <table class="table">
            <th>DNIEmisor</th>             
            <th>DNIReceptor</th>            
            <th>Concepto</th>               
            <th>Cantidad</th>                                       
            <th>Fecha</th>                
            <%
              for (Ttransferencia transferencia : listaTransferencias){  
            %>
            <tr>
                <td><%= transferencia.getDNIEmisor() %></td>
                <td><%= transferencia.getDNIReceptor() %></td>
                <td><%= transferencia.getConcepto() %></td>
                <td><%= transferencia.getCantidad() %></td>
                <td><%= transferencia.getFecha() %></td>
            </tr>
            <%
              }  
            %>
           
            
        </table>
        
         </div>   
         </div>
    </body>
</html>