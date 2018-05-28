# Conversor de Servlets a Beans

Este documento servirá para mantener al tanto a los distintos colaboradores de este proyecto. Marcaremos qué Servlets del proyecto StoneBank se convertirán en Beans y qué JSPs se convierten en qué JSFs.


Plan de refactorización: 

1 JSP -> 1 XHTML --------------- Hasta que no se diga lo contrario

Refactorización de Servlets: 

	·ServletCerrarSesion
	·ServletLogin		--> LoginBean
	
	·ServletCreaUsuario
	·ServletEditaUsuario
	·ServletBorraUsuario	--> UsuarioBean
	·ServletListaUsuario
	
	·ServletCreaMovimiento
	·ServletBorraMovimiento --> MovimientoBean
	·ServletEditaMovimiento
	
	·ServletTransferencia --> TransferenciaBean
	
	·ServletBuscarUsuarioEmpleado  --> BusquedaBean
	·ServletBusqueda
	
	 
