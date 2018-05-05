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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import stonebank.ejb.TusuarioFacade;
import stonebank.entity.Tusuario;

/**
 *
 * @author JesusContreras
 */
@WebServlet(name = "ServletActualizaEmpleado", urlPatterns = {"/empleado/ServletActualizaEmpleado"})
public class ServletActualizaEmpleado extends HttpServlet {

    @EJB
    private TusuarioFacade tusuarioFacade;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
       
        
        HttpSession session = request.getSession(); 
        
        String nombre, apellido, contrasena, email, domicilio;
        int dni,telefono;
        Tusuario usuario,empleado;
        
        empleado=(Tusuario) session.getAttribute("empleadoLogin");
        
        nombre = request.getParameter("nombre");
        apellido = request.getParameter("apellido");
        dni = Integer.parseInt(request.getParameter("dni"));
        contrasena = request.getParameter("contrasena");
        telefono = Integer.parseInt(request.getParameter("telefono"));
        email = request.getParameter("email");
        domicilio = request.getParameter("domicilio");
        
        usuario = (Tusuario) this.tusuarioFacade.find(dni);
        
         //SHA-256 HASH, esto lo movería a un .utils :) 
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
        
        if(usuario.getHashContrasena().equals(hexString.toString())){
            String nuevaContrasena = request.getParameter("nuevacontrasena"); //antes request
           
            encodedhash = msgdgst.digest(nuevaContrasena.getBytes(StandardCharsets.UTF_8));
            hexString = new StringBuilder();
        for (int i = 0; i < encodedhash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedhash[i]);
            if(hex.length() == 1) 
                hexString.append('0');
            hexString.append(hex);
            
            
        }
            usuario.setHashContrasena(hexString.toString());
        }else{
            //Lanza error
            //pero por ahora simplemente no lo modificara
        }
        
        usuario.setNombre(nombre);
        usuario.setApellidos(apellido);
        usuario.setDniUsuario(dni);
        usuario.setTelefono(telefono);
        usuario.setEmail(email);
        usuario.setDomicilio(domicilio);
        
        this.tusuarioFacade.edit(usuario); //Actualiza en BD
        
        request.setAttribute("mensajeExito", "¡Usuario MODIFICADO con éxito!");
        request.setAttribute("proximaURL", "indexEmpleado.jsp");
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/exito.jsp");
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