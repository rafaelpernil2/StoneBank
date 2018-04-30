/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            throws ServletException, IOException, NoSuchAlgorithmException {
        
        String nombre, apellido, contrasena, email, domicilio;
        int dni,telefono;
        Tusuario usuario;
        boolean ready = true;
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
        //usuario.setDniUsuario(dni);
        usuario.setTelefono(telefono);
        usuario.setEmail(email);
        usuario.setDomicilio(domicilio);
        
        //SHA-256 HASH
        MessageDigest msgdgst = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = msgdgst.digest(contrasena.getBytes(StandardCharsets.UTF_8));
        
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < encodedhash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedhash[i]);
            if(hex.length() == 1) 
                hexString.append('0');
            hexString.append(hex);
        }
        //
        usuario.setHashContrasena(hexString.toString());
        usuario.setTrolIdtrol(trol);
        // Generacíon número de cuenta
         List<Tusuario> lista = tusuarioFacade.findAll();
        int i = 0;
        Random random = new Random();
        int rand = Math.abs(random.nextInt());
        while(i<lista.size()){
            if (lista.get(i).getNumCuenta() == rand){
                rand = Math.abs(random.nextInt());
                i=0;
            }
            i++;
        }
        usuario.setNumCuenta(rand);
        
        i=0;
        while (i<lista.size() && lista.get(i).getDniUsuario()!= dni)
            i++;
        
        if (i!=lista.size())
            ready = false;
               
        else
            usuario.setDniUsuario(dni);
                       
        
        if (ready){
            this.tusuarioFacade.create(usuario);
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.jsp");
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
            Logger.getLogger(ServletCreaUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletCreaUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
