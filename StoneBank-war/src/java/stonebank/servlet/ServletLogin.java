
package stonebank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;
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
import stonebank.ejb.TmovimientoFacade;
import stonebank.ejb.TtransferenciaFacade;
import stonebank.ejb.TusuarioFacade;
import stonebank.entity.Trol;
import stonebank.entity.Tusuario;

/**
 *
 * @author Fran Gambero
 */
public class ServletLogin extends HttpServlet {

    @EJB
    private TusuarioFacade tusuarioFacade; 

    @EJB
    private TmovimientoFacade tmovimientoFacade;

    @EJB
    private TtransferenciaFacade ttransferenciaFacade;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        
        HttpSession session = request.getSession();
        
        List<Tusuario> listaUsuarios = this.tusuarioFacade.findAll();
        session.setAttribute("listaUsuarios", listaUsuarios); //antes request
        
        int userDNI;
        String password;
        Tusuario usuario;
        
        userDNI = Integer.parseInt(request.getParameter("user"));
        password = request.getParameter("pass");
        Trol rolEmpleado = new Trol(2);// Lo ideal sería coger los roles de la fachada
        Trol rolUsuario = new Trol(1);
        
        usuario = this.tusuarioFacade.find(userDNI);
        
        
        //SHA-256 HASH
        MessageDigest msgdgst = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = msgdgst.digest(password.getBytes(StandardCharsets.UTF_8));
        
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < encodedhash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedhash[i]);
            if(hex.length() == 1) 
                hexString.append('0');
            hexString.append(hex);
        }
        //
        
        request.setAttribute("usuarioLogin", usuario);
        
        if(usuario.getHashContrasena().equalsIgnoreCase(hexString.toString())){
            //Usuario existe y tiene contraseña valida
            //Comparamos rol para ver si iniciamos en Usuario o Empleado
            
        Double sumaMovimientos = this.tmovimientoFacade.dineroEntrantePorMovimientos(usuario.getDniUsuario());
        Double sumaTransferencias = this.ttransferenciaFacade.dineroEntranteTransferencia(usuario.getDniUsuario());
        Double restaTransferencias = this.ttransferenciaFacade.dineroSalienteTransferencia(usuario.getDniUsuario());
        
        //si no se han hecho transferencias se pone

        if(sumaMovimientos==null){
            sumaMovimientos = 0.0;
        }
        if(sumaTransferencias==null){
            sumaTransferencias = 0.0;
        }
        if(restaTransferencias==null){
            restaTransferencias = 0.0;
        }
        
        Double saldo = sumaMovimientos + sumaTransferencias - restaTransferencias;
        session.setAttribute("saldoUsuario", saldo);
            
            if(usuario.getTrolIdtrol().equals(rolEmpleado)){

               //request.setAttribute("empleadoLogin", usuario);
               session.setAttribute("empleadoLogin", usuario);
                //RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/empleado/indexEmpleado.jsp");
                //rd.forward(request, response);
                response.sendRedirect("empleado/indexEmpleado.jsp");

            }else if (usuario.getTrolIdtrol().equals(rolUsuario)){
                
               session.setAttribute("usuarioLogin", usuario);
               
               response.sendRedirect("usuario/indexUsuario.jsp");
            } 
            //Falta por añadir jsp Error para controlar mejor
        }else{
            System.out.print("Error, contraseña incorrecta");
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
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
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
