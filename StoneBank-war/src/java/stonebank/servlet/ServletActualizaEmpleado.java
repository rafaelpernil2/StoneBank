/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import stonebank.ejb.TusuarioFacade;
import stonebank.entity.Tusuario;
import stonebank.utils.PasswordUtil;

/**
 *
 * @author JesusContreras
 * @editor Rafael Pernil
 */
//@WebServlet(name = "ServletActualizaEmpleado", urlPatterns = {"/empleado/ServletActualizaEmpleado"})
public class ServletActualizaEmpleado extends HttpServlet {

    @EJB
    private TusuarioFacade tusuarioFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {

        HttpSession session = request.getSession();

        String nombre, apellido, contrasena, email, domicilio;
        int dni, telefono;
        Tusuario usuario, empleado;

        if (request.getParameter("nombre").equalsIgnoreCase("") || request.getParameter("apellido").equalsIgnoreCase("")) {
            request.setAttribute("mensaje", "No puede dejar el nombre vacío");
            request.setAttribute("url", "ServletEditarEmpleado?dni=" + request.getParameter("dni"));
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);

        }

        empleado = (Tusuario) session.getAttribute("empleadoLogin");

        nombre = request.getParameter("nombre");
        apellido = request.getParameter("apellido");
        dni = Integer.parseInt(request.getParameter("dni"));
        contrasena = request.getParameter("contrasena");

        if (!request.getParameter("telefono").isEmpty()) {
            telefono = Integer.parseInt(request.getParameter("telefono"));
        } else {
            telefono = 0;
        }
        if (!request.getParameter("email").isEmpty()) {
            email = request.getParameter("email");
        } else {
            email = "";
        }
        if (!request.getParameter("domicilio").isEmpty()) {
            domicilio = request.getParameter("domicilio");
        } else {
            domicilio = "";
        }

        usuario = (Tusuario) this.tusuarioFacade.find(dni);

        
        // OPERACIONES
        if (usuario.getHashContrasena().equalsIgnoreCase(PasswordUtil.generateHash(contrasena))) {

            String nuevaContrasena = request.getParameter("nuevacontrasena");

            // Comprobación de contraseña vacía para cambiar la contrasena
            if (!PasswordUtil.contrasenaVacia(nuevaContrasena)) {
                usuario.setHashContrasena(PasswordUtil.generateHash(nuevaContrasena));
            }

            usuario.setNombre(nombre);
            usuario.setApellidos(apellido);
            usuario.setDniUsuario(dni);
            usuario.setTelefono(telefono);
            usuario.setEmail(email);
            usuario.setDomicilio(domicilio);
            
            
            
            this.tusuarioFacade.edit(usuario); //Actualiza en BD
            
            List<Tusuario> listaUsuarios = this.tusuarioFacade.findAll();
            session.setAttribute("listaUsuarios", listaUsuarios); //antes request
            request.setAttribute("mensajeExito", "¡Empleado MODIFICADO con éxito!");
            request.setAttribute("proximaURL", "empleado/indexEmpleado.jsp");
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/exito.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("mensaje", "Contraseña incorrecta");
            request.setAttribute("url", "ServletEditarEmpleado?dni=" + request.getParameter("dni"));
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServletActualizaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServletActualizaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
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
