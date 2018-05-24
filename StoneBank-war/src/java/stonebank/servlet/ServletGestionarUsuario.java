/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import stonebank.ejb.TusuarioFacade;

/**
 *
 * @author JesusContreras
 */
//@WebServlet(name = "ServletGestionarUsuario", urlPatterns = {"/empleado/ServletGestionarUsuario"})
public class ServletGestionarUsuario extends HttpServlet {

    @EJB
    private TusuarioFacade tusuarioFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        //HttpSession session = request.getSession();

        int dni = Integer.parseInt(request.getParameter("dni"));

        //Tusuario u = tusuarioFacade.find(2);
        request.setAttribute("usuarioSeleccionado", tusuarioFacade.find(dni));
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/empleado/gestionarUsuario.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
