/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import stonebank.ejb.TusuarioFacade;
import stonebank.entity.Tmovimiento;
import stonebank.entity.Ttransferencia;
import stonebank.entity.Tusuario;

/**
 *
 * @author JesusContreras
 */
@WebServlet(name = "ServletVerUsuario", urlPatterns = {"/ServletVerUsuario"})
public class ServletVerUsuario extends HttpServlet {

    @EJB
    private TusuarioFacade tusuarioFacade;

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
       
        HttpSession session = request.getSession();
        //Tusuario u = (Tusuario) request.getAttribute("usuarioCrea");
        //if (u==null){
        int dni = Integer.parseInt(request.getParameter("dni"));
        Tusuario u= tusuarioFacade.find(dni);
        List<Tmovimiento> listMov = u.getTmovimientoList();
        List<Ttransferencia> listTrans = u.getTtransferenciaList();
        request.setAttribute("usuarioVer", u);
       // }
        //else{
           //request.setAttribute("usuarioVer", u);
       // }
       request.setAttribute("listaMov",listMov);
       request.setAttribute("listaTrans",listTrans);
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/empleado/usuarioSeleccionado.jsp");
        rd.forward(request, response);
    }

   
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