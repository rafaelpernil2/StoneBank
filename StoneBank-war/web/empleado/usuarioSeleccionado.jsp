<%-- 
    Document   : usuarioSeleccionado
    Created on : 19-abr-2018, 13:32:09
    Author     : JesusContreras
--%>

<%@page import="stonebank.entity.Tusuario"%>
<%@page import="stonebank.entity.Ttransferencia"%>
<%@page import="stonebank.entity.Tmovimiento"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Tmovimiento> listaMovimientos;  
    List<Ttransferencia> listaTransferencias;
    Tusuario usuario =(Tusuario) request.getAttribute("usuario"); 
    listaMovimientos = usuario.getTmovimientoList();
    listaTransferencias = usuario.getTtransferenciaList();
    
    
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario Seleccionado</title>
    </head>
    <body>
                    <table>
                <tr>
                    <td>Nombre: </td>
                    <td><input type="text" name="nombre" value="<%=usuario.getNombre()%>" readonly="readonly"/></td>                    
                </tr>
                <tr>
                    <td>Apellido: </td>
                    <td><input type="text" name="apellido" value="<%=usuario.getApellidos()%>" readonly="readonly"/></td>                    
                </tr>
                <tr>
                    <td>DNI: </td>
                    <td><input type="text" name="dni" value="<%=usuario.getDniUsuario()%>" readonly="readonly"/></td>                    
                </tr>

                <tr>
                    <td>Telefono: </td>
                    <td><input type="text" name="telefono" value="<%=usuario.getTelefono()%>" readonly="readonly"/></td>                    
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><input type="text" name="email" value="<%=usuario.getEmail() %>" readonly="readonly"/></td>                    
                </tr>
                <tr>
                    <td>Domicilio: </td>
                    <td><input type="text" name="domicilio" value="<%= usuario.getDomicilio() %>" readonly="readonly"/></td>                    
                </tr>
            </table>
                <table>
            <th>IDMovimiento</th>             
            <th>DNIReceptor</th>            
            <th>Concepto</th>               
            <th>Cantidad</th>                          
            <th>ibanEntidad</th>               
            <th>Fecha</th>                
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
            </tr>
            
            <%
              }  
            %>
           
                </table>
            <a href="nuevoMovimiento.jsp?dni=<%=usuario.getDniUsuario()%>"> Crear Nuevo Movimiento </a> 
                
                    <h1>Historial Transferencia</h1>
        <table>
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
            
            
        
    </body>
</html>