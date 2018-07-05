package stonebank.bean;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;
import stonebank.ejb.TmovimientoFacade;
import stonebank.ejb.TtransferenciaFacade;
import stonebank.ejb.TusuarioFacade;
import stonebank.entity.Tmovimiento;
import stonebank.entity.Trol;
import stonebank.entity.Ttransferencia;
import stonebank.entity.Tusuario;
import stonebank.utils.*;

/**
 *
 * @author Victor
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    @EJB
    private TtransferenciaFacade ttransferenciaFacade;

    @EJB
    private TmovimientoFacade tmovimientoFacade;

    @Inject
    protected LoginBean loginBean;

    @Inject
    protected ExitoErrorBean exitoErrorBean;

    @Inject
    protected BusquedaBean busquedaBean;

    @EJB
    private TusuarioFacade tusuarioFacade;

    protected List<Tmovimiento> listaMovimientos;
    protected List<Tusuario> listaUsuarios;
    protected Tusuario usuario;
    protected String nuevaContrasena = "", seguroContrasena = "",
            viejaContrasena = "", nuevoNombre, nuevoApellido, nuevoDomicilio, nuevoEmail;
    protected Integer nuevoTelefono, nuevoDNI;
    protected double saldo;

    protected List<Tusuario> listaUsuario;

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }

    @PostConstruct
    public void init() {
        usuario = loginBean.getUsuarioLoggeado();
        listaMovimientos = usuario.getTmovimientoList();
        nuevoNombre = usuario.getNombre();
        nuevoApellido = usuario.getApellidos();
        nuevoDomicilio = usuario.getDomicilio();
        nuevoEmail = usuario.getEmail();
        nuevoTelefono = usuario.getTelefono();
    }

    /*
    * hace falta poner el saldo que tiene justo aqui.
     */
    public Tusuario getUsuarioPorDNI(Integer dniUsuario) {
        return tusuarioFacade.find(dniUsuario);
    }

    public Tusuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Tusuario usuario) {
        this.usuario = usuario;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Tmovimiento> getListaMovimientos() {
        return listaMovimientos;
    }

    public void setListaMovimientos(List<Tmovimiento> lm) {
        this.listaMovimientos = lm;
    }

    public List<Tusuario> getListaUsuarios() {
        listaUsuarios = this.tusuarioFacade.findAll();
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Tusuario> lu) {
        this.listaUsuarios = lu;
    }

    public String getNombre() {
        return usuario.getNombre();
    }

    public void setNombre(String n) {
        usuario.setNombre(n);
    }

    public String getApellidos() {
        return usuario.getApellidos();
    }

    public void setApellidos(String ap) {
        usuario.setApellidos(ap);
    }

    public Integer getDNI() {
        return usuario.getDniUsuario();
    }

    public void setDNI(Integer dni) {
        //no debe de hacer nada, dni no se puede cambiar
    }

    public String getDomicilio() {
        return usuario.getDomicilio();
    }

    public void setDomicilio(String d) {
        usuario.setDomicilio(d);
    }

    public String getEmail() {
        return usuario.getEmail();
    }

    public void setEmail(String em) {
        usuario.setEmail(em);
    }

    public Integer getTelefono() {
        return usuario.getTelefono();
    }

    public void setTelefono(Integer tel) {
        usuario.setTelefono(tel);
    }

    public Integer getNumCuenta() {
        return usuario.getNumCuenta();
    }

    public void setNumCuenta(Integer nC) {
        usuario.setNumCuenta(nC);
    }

    public String getHashContrasena() {
        return usuario.getHashContrasena();
    }

    public void setContrasena(String contrasena) {
        try {
            usuario.setHashContrasena(PassUtil.generarHash(contrasena));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getNuevaContrasena() {
        return nuevaContrasena;
    }

    public void setNuevaContrasena(String nC) {
        nuevaContrasena = nC;
    }

    public String getSeguroContrasena() {
        return seguroContrasena;
    }

    public void setSeguroContrasena(String sC) {
        seguroContrasena = sC;
    }

    public String getViejaContrasena() {
        return viejaContrasena;
    }

    public void setViejaContrasena(String vC) {
        viejaContrasena = vC;
    }

    public Double getSaldo() {
        Double dineroEntranteMovimientos = this.tmovimientoFacade.dineroEntrantePorMovimientos(usuario.getDniUsuario());
        Double dineroEntranteTransferencias = this.ttransferenciaFacade.dineroEntranteTransferencia(usuario.getDniUsuario());
        Double dineroSalienteTransferencias = this.ttransferenciaFacade.dineroSalienteTransferencia(usuario.getDniUsuario());
        if (dineroEntranteMovimientos == null) {
            dineroEntranteMovimientos = 0.0;
        }
        if (dineroEntranteTransferencias == null) {
            dineroEntranteTransferencias = 0.0;
        }
        if (dineroSalienteTransferencias == null) {
            dineroSalienteTransferencias = 0.0;
        }
        return (dineroEntranteMovimientos + dineroEntranteTransferencias) - dineroSalienteTransferencias;
    }

    public String doEditar() throws NoSuchAlgorithmException {

        if (nuevoNombre.equals("") || nuevoApellido.equals("")) {
            restaurar();
            exitoErrorBean.setMensajeError("Falta introducir nombre y apellidos");
            exitoErrorBean.setProximaURL("/usuario/configuracion?jsf-redirect=true");
            return "/error?jsf-redirect=true";
        } else if (nuevoTelefono != 0 && !CuentaUtil.esCorrectoFormatoTelefono(nuevoTelefono.toString())) {
            restaurar();
            exitoErrorBean.setMensajeError("Introduce los 9 dígitos del número de teléfono");
            exitoErrorBean.setProximaURL("/usuario/configuracion?jsp-redirect=true");
            return "/error";
//        } else if (nuevoDomicilio.equals("")) {
//            exitoErrorBean.setMensajeError("Falta introducir domicilio");
//            exitoErrorBean.setProximaURL("/alta");
//            return "/error";
//        } else if (nuevoEmail.equals("")) {
//            exitoErrorBean.setMensajeError("Falta introducir email");
//            exitoErrorBean.setProximaURL("/alta");
//            return "/error";
        } else if (viejaContrasena.equals("")) {
            restaurar();
            exitoErrorBean.setMensajeError("Introduce tu contraseña para realizar cambios");
            exitoErrorBean.setProximaURL("/usuario/configuracion");
            return "/error";
        } else if (!nuevaContrasena.equals("") && seguroContrasena.equals("")) {

            restaurar();
            exitoErrorBean.setMensajeError("Confirma la contraseña");
            exitoErrorBean.setProximaURL("/usuario/configuracion");
            return "/error";
        } //        } else if (!nuevaContrasena.equals(seguroContrasena)) {
        //
        //            exitoErrorBean.setMensajeError("Las contraseña introducida no coincide con la confirmación de contraseña");
        //            exitoErrorBean.setProximaURL("/alta");
        //            return "/error";
        //        } else {
        else {

            if (PassUtil.generarHash(viejaContrasena).equals(usuario.getHashContrasena())) {
                if (!"".equals(nuevaContrasena)) {
                    if (nuevaContrasena.equals(seguroContrasena)) {
                        usuario.setHashContrasena(PassUtil.generarHash(nuevaContrasena));
                        actualizar();
//                        usuario.setNombre(nuevoNombre);
//                        usuario.setApellidos(nuevoApellido);
//                        usuario.setDomicilio(nuevoDomicilio);
//                        usuario.setEmail(nuevoEmail);
//                        usuario.setTelefono(nuevoTelefono);
                        this.tusuarioFacade.edit(usuario);
                        listaUsuarios = this.getListaUsuarios();
                        busquedaBean.setListaUsuarios(listaUsuarios);
                        return "/usuario/indexUsuario";
                    } else {
                        restaurar();
                        exitoErrorBean.setMensajeError("Las contraseña nueva introducida no coincide con la confirmación de contraseña");
                        exitoErrorBean.setProximaURL("/usuario/configuracion");
                        return "/error";//mostar mensajes de algun tipo
                    }
                }

//                usuario.setNombre(nuevoNombre);
//                usuario.setApellidos(nuevoApellido);
//                usuario.setDomicilio(nuevoDomicilio);
//                usuario.setEmail(nuevoEmail);
//                usuario.setTelefono(nuevoTelefono);
                actualizar();
                this.tusuarioFacade.edit(usuario);
                listaUsuarios = this.getListaUsuarios();
                return "/usuario/indexUsuario";
            } else {
                restaurar();
                exitoErrorBean.setMensajeError("Introduce tu contraseña para realizar cambios");
                exitoErrorBean.setProximaURL("/usuario/configuracion");
                return "/error";

            }
        }

//        String comprobarContrasena = PassUtil.generarHash(viejaContrasena);
//
//        if (comprobarContrasena.equals(usuario.getHashContrasena()) && !"".equals(viejaContrasena)) {
//            if (!"".equals(nuevaContrasena)) {
//                if (nuevaContrasena.equals(seguroContrasena)) {
//                    this.usuario.setHashContrasena(PassUtil.generarHash(nuevaContrasena));
//                    actualizar();
//                    this.tusuarioFacade.edit(usuario);
//                    return "indexUsuario";
//                } else {
//                    restaurar();
//                    return "configuracion";//mostar mensajes de algun tipo
//                }
//            }
//            actualizar();
//            this.tusuarioFacade.edit(usuario);
//            return "indexUsuario";
//        } else {
//            restaurar();
//            return "configuracion";//esto se deberia de cambiar o mostrar un mensaje de error
//        }
    }

    public String crearUsuario() {
        nuevoDNI = -1;
        nuevoNombre = "";
        nuevoApellido = "";
        nuevaContrasena = "";
        seguroContrasena = "";
        nuevoDomicilio = "";
        nuevoEmail = "";
        nuevoTelefono = -1;
        return "/alta";
    }

    public String doCrear() throws NoSuchAlgorithmException {
        /*
    * No soy partidario de hacer tanto if/else asique, 
    * No se si deberia hacer un metodo privado para que me lo compruebe todo  
         */

        if (!(CuentaUtil.esCorrectoFormatoDNI(nuevoDNI.toString()))) {
            //mensaje de error de fallo al introducir DNI

            exitoErrorBean.setMensajeError("Introduce los 8 dígitos del DNI sin letra");
            exitoErrorBean.setProximaURL("/alta");
            return "/error";
        } else if (CuentaUtil.DNIyaRegistrado(tusuarioFacade, nuevoDNI)) {

            exitoErrorBean.setMensajeError("Usuario ya registrado");
            exitoErrorBean.setProximaURL("/alta");
            return "/error";

        } else if (nuevoNombre.equals("") || nuevoApellido.equals("")) {
            exitoErrorBean.setMensajeError("Falta introducir nombre y apellidos");
            exitoErrorBean.setProximaURL("/alta");
            return "/error";
        } else if (nuevoTelefono != 0 && !CuentaUtil.esCorrectoFormatoTelefono(nuevoTelefono.toString())) {
            exitoErrorBean.setMensajeError("Introduce los 9 dígitos del número de teléfono");
            exitoErrorBean.setProximaURL("/alta");
            return "/error";
//        } else if (nuevoDomicilio.equals("")) {
//            exitoErrorBean.setMensajeError("Falta introducir domicilio");
//            exitoErrorBean.setProximaURL("/alta");
//            return "/error";
//        } else if (nuevoEmail.equals("")) {
//            exitoErrorBean.setMensajeError("Falta introducir email");
//            exitoErrorBean.setProximaURL("/alta");
//            return "/error";
        } else if (nuevaContrasena.equals("")) {
            exitoErrorBean.setMensajeError("Falta introducir contraseña");
            exitoErrorBean.setProximaURL("/alta");
            return "/error";
        } else if (seguroContrasena.equals("")) {

            exitoErrorBean.setMensajeError("Confirma la contraseña");
            exitoErrorBean.setProximaURL("/alta");
            return "/error";

        } else if (!nuevaContrasena.equals(seguroContrasena)) {

            exitoErrorBean.setMensajeError("Las contraseña introducida no coincide con la confirmación de contraseña");
            exitoErrorBean.setProximaURL("/alta");
            return "/error";
        } else {
            Tusuario nuevoUsuario = new Tusuario();
            nuevoUsuario.setTrolIdtrol(new Trol(1));
            nuevoUsuario.setDniUsuario(nuevoDNI);
            nuevoUsuario.setNombre(nuevoNombre);
            nuevoUsuario.setApellidos(nuevoApellido);
            nuevoUsuario.setHashContrasena(PassUtil.generarHash(nuevaContrasena));
            nuevoUsuario.setTelefono(nuevoTelefono);
            nuevoUsuario.setDomicilio(nuevoDomicilio);
            nuevoUsuario.setEmail(nuevoEmail);
            nuevoUsuario.setNumCuenta(CuentaUtil.generarNumeroDeCuenta(tusuarioFacade));

            this.tusuarioFacade.create(nuevoUsuario);
            listaUsuarios.add(nuevoUsuario);

            return "/empleado/indexEmpleado";
        }

    }

    private void restaurar() {
        nuevoNombre = usuario.getNombre();
        nuevoApellido = usuario.getApellidos();
        nuevoDomicilio = usuario.getDomicilio();
        nuevoEmail = usuario.getEmail();
        nuevoTelefono = usuario.getTelefono();
    }

    private void actualizar() {

        usuario.setNombre(nuevoNombre);
        usuario.setApellidos(nuevoApellido);
        usuario.setDomicilio(nuevoDomicilio);
        usuario.setEmail(nuevoEmail);
        usuario.setTelefono(nuevoTelefono);
    }

    public Integer getNuevoDNI() {
        return nuevoDNI;
    }

    public void setNuevoDNI(Integer dni) {
        nuevoDNI = dni;
    }

    public String getNuevoNombre() {
        return nuevoNombre;
    }

    public void setNuevoNombre(String nN) {
        nuevoNombre = nN;
    }

    public String getNuevoApellido() {
        return nuevoApellido;
    }

    public void setNuevoApellido(String nA) {
        nuevoApellido = nA;
    }

    public String getNuevoDomicilio() {
        return nuevoDomicilio;
    }

    public void setNuevoDomicilio(String nD) {
        nuevoDomicilio = nD;
    }

    public String getNuevoEmail() {
        return nuevoEmail;
    }

    public void setNuevoEmail(String nE) {
        nuevoEmail = nE;
    }

    public Integer getNuevoTelefono() {
        return nuevoTelefono;
    }

    public void setNuevoTelefono(Integer nT) {
        nuevoTelefono = nT;
    }

    public String usuarioSeleccionado(Integer usuarioSeleccionadoDNI) {
        seleccionarUsuario(usuarioSeleccionadoDNI);

        return "usuarioSeleccionado";
    }

    public void seleccionarUsuario(Integer usuarioSeleccionadoDNI) {
        Tusuario usuarioSeleccionado = this.tusuarioFacade.find(usuarioSeleccionadoDNI);
        nuevoDNI = usuarioSeleccionadoDNI;
        nuevoNombre = usuarioSeleccionado.getNombre();
        nuevoApellido = usuarioSeleccionado.getApellidos();
        nuevoDomicilio = usuarioSeleccionado.getDomicilio();
        nuevoEmail = usuarioSeleccionado.getEmail();
        nuevoTelefono = usuarioSeleccionado.getTelefono();
    }

    public List<Tmovimiento> getListaMovimientoUsuarioSeleccionado(Integer usuarioSeleccionadoDNI) {
        Tusuario usuarioSeleccionado = this.tusuarioFacade.find(usuarioSeleccionadoDNI);
        List<Tmovimiento> listaMovimientoUsuarioSeleccionado = usuarioSeleccionado.getTmovimientoList();
        return listaMovimientoUsuarioSeleccionado;
    }

    public List<Ttransferencia> getListaTransferenciaUsuarioSeleccionado(Integer usuarioSeleccionadoDNI) {
        Tusuario usuarioSeleccionado = this.tusuarioFacade.find(usuarioSeleccionadoDNI);
        List<Ttransferencia> listaTransferenciaUsuarioSeleccionado = usuarioSeleccionado.getTtransferenciaList();
        listaTransferenciaUsuarioSeleccionado.addAll(usuarioSeleccionado.getTtransferenciaList1());
        return listaTransferenciaUsuarioSeleccionado;
    }

    public String gestionarUsuario(Integer usuarioSeleccionadoDNI) {
        Tusuario usuarioSeleccionado = this.tusuarioFacade.find(usuarioSeleccionadoDNI);
        this.setNuevoDNI(usuarioSeleccionadoDNI);
        this.setNuevoNombre(usuarioSeleccionado.getNombre());
        this.setNuevoApellido(usuarioSeleccionado.getApellidos());
        this.setNuevoDomicilio(usuarioSeleccionado.getDomicilio());
        this.setNuevoEmail(usuarioSeleccionado.getEmail());
        this.setNuevoTelefono(usuarioSeleccionado.getTelefono());

        return "/empleado/gestionarUsuario";
    }

    public String doEditarEmpleado(Integer usuarioSeleccionadoDNI) throws NoSuchAlgorithmException {

        Tusuario usuarioSeleccionado = this.tusuarioFacade.find(usuarioSeleccionadoDNI);
//        setNuevoNombre(usuarioSeleccionado.getNombre());
//        setNuevoApellido(usuarioSeleccionado.getApellidos());
//        setNuevoDNI(usuarioSeleccionadoDNI);
//        setNuevoDomicilio(usuarioSeleccionado.getDomicilio());
//        setNuevoTelefono(usuarioSeleccionado.getTelefono());
//        setNuevoEmail(usuarioSeleccionado.getEmail());

        if (nuevoNombre.equals("") || nuevoApellido.equals("")) {

            exitoErrorBean.setMensajeError("Falta introducir nombre y apellidos");
            exitoErrorBean.setProximaURL("/empleado/gestionarUsuario?jsf-redirect=true");
            return "/error?jsf-redirect=true";
        } else if (nuevoTelefono != 0 && !CuentaUtil.esCorrectoFormatoTelefono(nuevoTelefono.toString())) {

            exitoErrorBean.setMensajeError("Introduce los 9 dígitos del número de teléfono");
            exitoErrorBean.setProximaURL("/empleado/gestionarUsuario?jsp-redirect=true");
            return "/error";
//        } else if (nuevoDomicilio.equals("")) {
//            exitoErrorBean.setMensajeError("Falta introducir domicilio");
//            exitoErrorBean.setProximaURL("/alta");
//            return "/error";
//        } else if (nuevoEmail.equals("")) {
//            exitoErrorBean.setMensajeError("Falta introducir email");
//            exitoErrorBean.setProximaURL("/alta");
//            return "/error";
        } else if (!nuevaContrasena.equals("") && seguroContrasena.equals("")) {

            exitoErrorBean.setMensajeError("Confirma la contraseña");
            exitoErrorBean.setProximaURL("/usuario/configuracion");
            return "/error";
        } //        } else if (!nuevaContrasena.equals(seguroContrasena)) {
        //
        //            exitoErrorBean.setMensajeError("Las contraseña introducida no coincide con la confirmación de contraseña");
        //            exitoErrorBean.setProximaURL("/alta");
        //            return "/error";
        //        } else {
        else {
            if (PassUtil.generarHash(viejaContrasena).equalsIgnoreCase(usuarioSeleccionado.getHashContrasena())) {
                if (!"".equals(nuevaContrasena)) {
                    if (nuevaContrasena.equals(seguroContrasena)) {
                        usuarioSeleccionado.setHashContrasena(PassUtil.generarHash(nuevaContrasena));
                        usuarioSeleccionado.setNombre(nuevoNombre);
                        usuarioSeleccionado.setApellidos(nuevoApellido);
                        usuarioSeleccionado.setDomicilio(nuevoDomicilio);
                        usuarioSeleccionado.setEmail(nuevoEmail);
                        usuarioSeleccionado.setTelefono(nuevoTelefono);

                        this.tusuarioFacade.edit(usuarioSeleccionado);
                        listaUsuarios = this.getListaUsuarios();
                        busquedaBean.setListaUsuarios(listaUsuarios);
                        if (usuario.getDniUsuario().equals(usuarioSeleccionadoDNI)) {
                            actualizar();
                        } else {
                            restaurar();
                        }
                        return "/empleado/indexEmpleado";
                    } else {

                        exitoErrorBean.setMensajeError("Las contraseña nueva introducida no coincide con la confirmación de contraseña");
                        exitoErrorBean.setProximaURL("/empleado/gestionarUsuario");
                        return "/error";//mostar mensajes de algun tipo
                    }
                }

                usuarioSeleccionado.setNombre(nuevoNombre);
                usuarioSeleccionado.setApellidos(nuevoApellido);
                usuarioSeleccionado.setDomicilio(nuevoDomicilio);
                usuarioSeleccionado.setEmail(nuevoEmail);
                usuarioSeleccionado.setTelefono(nuevoTelefono);
                this.tusuarioFacade.edit(usuarioSeleccionado);
                listaUsuarios = this.getListaUsuarios();
                if (usuario.getDniUsuario().equals(usuarioSeleccionadoDNI)) {
                    actualizar();
                } else {
                    restaurar();
                }
                return "/empleado/indexEmpleado";
            } else {
                usuarioSeleccionado.setNombre(nuevoNombre);
                usuarioSeleccionado.setApellidos(nuevoApellido);
                usuarioSeleccionado.setDomicilio(nuevoDomicilio);
                usuarioSeleccionado.setEmail(nuevoEmail);
                usuarioSeleccionado.setTelefono(nuevoTelefono);

                this.tusuarioFacade.edit(usuarioSeleccionado);
                listaUsuarios = this.getListaUsuarios();
                busquedaBean.setListaUsuarios(listaUsuarios);
                if (usuario.getDniUsuario().equals(usuarioSeleccionadoDNI)) {
                    actualizar();
                } else {
                    restaurar();
                }
                return "/empleado/indexEmpleado";

            }
        }
    }
}
