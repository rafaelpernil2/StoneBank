/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.servlet;

import com.sun.org.apache.bcel.internal.generic.PushInstruction;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import stonebank.ejb.TmovimientoFacade;
import stonebank.ejb.TusuarioFacade;
import stonebank.entity.Tmovimiento;
import stonebank.entity.Tusuario;

/**
 *
 * @author rafaelpernil
 * @Editors Victor Garcia Kaikkonen, Jesus Contreras Herreras, Francisco Gambero Salinas, Eduardo Pertierra Puche
 */
@WebServlet(name = "empleado/ServletCreaMovimiento", urlPatterns = {"/empleado/ServletCreaMovimiento"})

public class ServletCreaMovimiento extends HttpServlet {

    @EJB
    private TusuarioFacade tusuarioFacade;

    @EJB
    private TmovimientoFacade tmovimientoFacade;

    
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean ready=true;
      //  HttpSession session = request.getSession();
        HttpSession session = request.getSession();
        Tmovimiento movimiento= new Tmovimiento();
        Integer dni = Integer.parseInt(request.getParameter("dni"));
        Tusuario usuario =  tusuarioFacade.find(dni); 
        
        movimiento.setTusuariodniUsuario(usuario);
        movimiento.setConcepto(request.getParameter("concepto"));
        movimiento.setFecha(new java.util.Date());
        movimiento.setIbanEntidad(request.getParameter("iban"));
        movimiento.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
       
        
        if (ready){
            tmovimientoFacade.create(movimiento);
            
            //List<Tusuario> listaUsuarios = this.tusuarioFacade.findAll();
            //session.setAttribute("listaUsuarios", listaUsuarios); //antes request
            request.removeAttribute("usuario");
            request.setAttribute("usuarioCrea", usuario);
            List<Tusuario> listaUsuarios = this.tusuarioFacade.findAll();
            session.removeAttribute("listaUsuarios");
            session.setAttribute("listaUsuarios", listaUsuarios); //antes request
            
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/ServletVerUsuario");
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
