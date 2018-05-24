package stonebank.servlet;

import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import javax.servlet.http.HttpSession;
import stonebank.ejb.TusuarioFacade;
import stonebank.entity.Trol;
import stonebank.entity.Tusuario;
import stonebank.utils.BankAccountUtil;
import stonebank.utils.PasswordUtil;

/**
 *
 * @author Fran Gambero
 */
//@WebServlet(name = "ServletCreaUsuario", urlPatterns = {"/empleado/ServletCreaUsuario"})
public class ServletCreaUsuario extends HttpServlet {

    @EJB
    private TusuarioFacade tusuarioFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {

        String nombre, apellido, contrasena, email, domicilio;
        int dni, telefono;
        Tusuario usuario, empleado;
        
        
        if (request.getParameter("nombre").equals("") || request.getParameter("apellido").equals("")
                || request.getParameter("contrasena").equals("") || request.getParameter("dni").equals("")) {
            request.setAttribute("mensaje", "Faltan datos");
            request.setAttribute("url", "alta.jsp");
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);

        }
        if (!BankAccountUtil.correctDNIFormat(request.getParameter("dni"))) {
            request.setAttribute("mensaje", "Introduce los 8 dígitos del  DNI sin letra");
            request.setAttribute("url", "alta.jsp");
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }
        if (!BankAccountUtil.correctTelephoneFormat(request.getParameter("telefono"))) {
            request.setAttribute("mensaje", "Número de telefono mal introducido");
            request.setAttribute("url", "alta.jsp");
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }
        nombre = request.getParameter("nombre");
        apellido = request.getParameter("apellido");
        dni = Integer.parseInt(request.getParameter("dni"));
        contrasena = request.getParameter("contrasena");
        if (!request.getParameter("telefono").isEmpty()) {
            telefono = Integer.parseInt(request.getParameter("telefono"));
        } else {
            telefono = 111111111;
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
        Trol trol = new Trol(1);
        usuario = new Tusuario();

        usuario.setNombre(nombre);
        usuario.setApellidos(apellido);
        usuario.setTelefono(telefono);
        usuario.setEmail(email);
        usuario.setDomicilio(domicilio);

        usuario.setHashContrasena(PasswordUtil.generateHash(contrasena));
        usuario.setTrolIdtrol(trol);
        // Generacíon número de cuenta

        usuario.setNumCuenta(BankAccountUtil.generateAccountNumber(tusuarioFacade));

        if (!BankAccountUtil.alreadyRegisteredDNI(tusuarioFacade, dni)) {
            usuario.setDniUsuario(dni);
        } else {
            request.setAttribute("mensaje", "DNI ya registrado");
            request.setAttribute("url", "alta.jsp");
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }

        HttpSession session = request.getSession();
        empleado = (Tusuario) session.getAttribute("empleadoLogin");
        this.tusuarioFacade.create(usuario);
        List<Tusuario> listaUsuarios = this.tusuarioFacade.findAll();
        session.setAttribute("listaUsuarios", listaUsuarios); //antes request
        request.setAttribute("usuarioCreado", usuario);//Creado para el alta.jsp
        request.setAttribute("mensajeExito", "¡Usuario creado con éxito!");
        request.setAttribute("proximaURL", "empleado/indexEmpleado.jsp");
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/exito.jsp");
        rd.forward(request, response);

    }

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
