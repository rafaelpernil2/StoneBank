<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index Usuario</title>
    </head>
    <body>
        <h1>¡Bievenido/a usuario/a (nombre)!</h1>
        <hr>
        
        <div class = "padre" style="width:100%">
            
          <div class = "subdiv-izquierdo" style="width:40%;display:inline-table;">
            <h3>Últimas transacciones</h3>
            
            <table border="1">
                <tbody>
                    <tr>
                        <td>Fecha</td>
                        <td>Cliente</td>
                        <td>Concepto</td>
                        <td>Importe</td>
                    </tr>
                </tbody>
            </table>
            
            <br>
            <a href="ServletListaMovimientos" >Ver todas las transacciones</a>
          </div>
            
          <div class="subdiv-derecho" style="width:40%;display:inline-table;">
              <h3>Buscador de movimientos</h3>
              <form action="ServletBusqueda" method="post">
                    <input type="text" name="Buscador" max="30" maxlength="30" value="Concepto, nombre..."/>
                    <input type="submit" value="Buscar" />              
              </form>
              <br><br>
              <a href="realizarTransferencia.jsp">Realizar transferecia</a>
          </div>
        </div>
    </body>
</html>