package stonebank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import stonebank.ejb.TmovimientoFacade;
import stonebank.ejb.TtransferenciaFacade;
import stonebank.ejb.TusuarioFacade;
import stonebank.entity.Ttransferencia;
import stonebank.entity.Tusuario;

/**
 *
 * @author Jesus Contreras
 */
//@WebServlet(name = "ServletTransferencia", urlPatterns = {"/usuario/ServletTransferencia"})
public class ServletTransferencia extends HttpServlet {

    @EJB
    private TusuarioFacade tusuarioFacade;

    @EJB
    private TmovimientoFacade tmovimientoFacade;

    @EJB
    private TtransferenciaFacade ttransferenciaFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        /*
        *Coge los atributos que hay en la tabla, todos los campos son obligatorios
         */
        int dniReceptor;
        double cantidad;
        String concepto;

        DateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:MM:SS", Locale.US);
        Date fecha = new Date();

        Tusuario emisor = (Tusuario) session.getAttribute("usuarioLogin");

        if (request.getParameter("dnireceptor").equals("") || request.getParameter("cantidad").equals("")) {
            request.setAttribute("mensaje", "Faltan datos");
            request.setAttribute("url", "ServletCreaTransferencia?dni=" + emisor.getDniUsuario());
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);

        }

        if (!request.getParameter("dnireceptor").matches("^\\d{1,8}$")) {
            request.setAttribute("mensaje", "Introduce el DNI sin letra");
            request.setAttribute("url", "ServletCreaTransferencia?dni=" + emisor.getDniUsuario());
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }

        if (!request.getParameter("cantidad").matches(".*\\d+.*") || request.getParameter("cantidad").contains(",")) {
            request.setAttribute("mensaje", "La cantidad debe ser numérica. Use . para los decimales");
            request.setAttribute("url", "ServletCreaTransferencia?dni=" + emisor.getDniUsuario());
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }

        //Tusuario emisor = (Tusuario) session.getAttribute("usuarioLogin");
        dniReceptor = Integer.parseInt(request.getParameter("dnireceptor"));
        cantidad = Double.parseDouble(request.getParameter("cantidad"));
        concepto = request.getParameter("concepto");

        /*
        *Compruebo que el dniEmisor puede realizar la transferencia, para ello sumo todos los movimientos 
        *y transferencias entrantes y le resto las transferencias salientes
         */
        Double sumaMovimientos = this.tmovimientoFacade.dineroEntrantePorMovimientos(emisor.getDniUsuario());
        Double sumaTransferencias = this.ttransferenciaFacade.dineroEntranteTransferencia(emisor.getDniUsuario());
        Double restaTransferencias = this.ttransferenciaFacade.dineroSalienteTransferencia(emisor.getDniUsuario());

        //si no se han hecho transferencias se pone
        if (sumaMovimientos == null) {
            sumaMovimientos = 0.0;
        }
        if (sumaTransferencias == null) {
            sumaTransferencias = 0.0;
        }
        if (restaTransferencias == null) {
            restaTransferencias = 0.0;
        }
        if (cantidad <= 0) {
            //lanza error
        }

        if ((sumaMovimientos + sumaTransferencias) >= (restaTransferencias + cantidad)) {

            //Compruebo que el dniReceptor existe
            Tusuario receptor;
            receptor = this.tusuarioFacade.find(dniReceptor);
            {

                if (receptor != null) {
                    Ttransferencia transferencia = new Ttransferencia();
                    transferencia.setDNIEmisor(emisor);
                    transferencia.setDNIReceptor(receptor);
                    transferencia.setCantidad(cantidad);
                    transferencia.setConcepto(concepto);
                    transferencia.setFecha(fecha);

                    this.ttransferenciaFacade.create(transferencia);

                    Double sumaMovimientosAfter = this.tmovimientoFacade.dineroEntrantePorMovimientos(emisor.getDniUsuario());
                    Double sumaTransferenciasAfter = this.ttransferenciaFacade.dineroEntranteTransferencia(emisor.getDniUsuario());
                    Double restaTransferenciasAfter = this.ttransferenciaFacade.dineroSalienteTransferencia(emisor.getDniUsuario());

                    if (sumaMovimientosAfter == null) {
                        sumaMovimientosAfter = 0.0;
                    }
                    if (sumaTransferenciasAfter == null) {
                        sumaTransferenciasAfter = 0.0;
                    }
                    if (restaTransferenciasAfter == null) {
                        restaTransferenciasAfter = 0.0;
                    }
                    if (cantidad <= 0) {
                        //lanza error
                    }

                    Double saldoAfter = sumaMovimientosAfter + sumaTransferenciasAfter - restaTransferenciasAfter;
                    session.setAttribute("saldoUsuario", saldoAfter);

                    request.setAttribute("mensajeExito", "¡Transferencia creada con éxito!");
                    //request.setAttribute("saldo",saldo);
                    request.setAttribute("proximaURL", "usuario/indexUsuario.jsp"); //Atención, envia sin / inicial
                    //RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.jsp");
                    RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/exito.jsp");
                    rd.forward(request, response);

                } else {

                    String url = "ServletCreaTransferencia?dni=" + request.getParameter("dniemisor");
                    request.setAttribute("mensaje", "Error, usuario inexistente");
                    request.setAttribute("url", url);
                    RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/error.jsp");
                    rd.forward(request, response);
                }
            }

        } else {
            //lanza error no tiene dinero suficiente
            //pagina de error por hacer, todavia no hace nada
            String url = "ServletCreaTransferencia?dni=" + request.getParameter("dniemisor");
            request.setAttribute("mensaje", "Error, dinero insuficiente");
            request.setAttribute("url", url);
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
