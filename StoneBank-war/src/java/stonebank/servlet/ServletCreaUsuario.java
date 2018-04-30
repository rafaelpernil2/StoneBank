/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import stonebank.ejb.TusuarioFacade;
import stonebank.entity.Trol;
import stonebank.entity.Tusuario;

/**
 *
 * @author Fran Gambero
 */
public class ServletCreaUsuario extends HttpServlet {

    @EJB
    private TusuarioFacade tusuarioFacade;
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombre, apellido, contrasena, email, domicilio;
        int dni,telefono;
        Tusuario usuario;
        
        nombre = request.getParameter("nombre");
        apellido = request.getParameter("apellido");
        dni = Integer.parseInt(request.getParameter("dni"));
        contrasena = request.getParameter("contrasena");
        telefono = Integer.parseInt(request.getParameter("telefono"));
        email = request.getParameter("email");
        domicilio = request.getParameter("domicilio");
        Trol trol = new Trol(1);
        /* Esto se usará cuando se implementa crear y editar aquí
        if("".equals(id)){ //Nuevo usuario
            usuario = new Tusuario();
        }else{ //Editar usuario
            usuario = this.tusuarioFacade.find(dni);
        }
        */
        
        /*if(!"".equals(dni)){ //Solo sirve para crear usuarios, asegura que existe dni
            usuario = new Tusuario();
        } else{
            System.out.println("Error en usuario");
        } */
        
        usuario = new Tusuario();
        
        usuario.setNombre(nombre);
        usuario.setApellidos(apellido);
        usuario.setDniUsuario(dni);
        usuario.setTelefono(telefono);
        usuario.setEmail(email);
        usuario.setDomicilio(domicilio);
        usuario.setHashContrasena(contrasena);
        usuario.setTrolIdtrol(trol);
        usuario.setNumCuenta(11);
        
        this.tusuarioFacade.create(usuario);
        
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.jsp");
        rd.forward(request, response);  
        
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
