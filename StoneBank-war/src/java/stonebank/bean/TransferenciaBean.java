/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.bean;

import java.io.Serializable;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;
import stonebank.ejb.TmovimientoFacade;
import stonebank.ejb.TtransferenciaFacade;
import stonebank.ejb.TusuarioFacade;
import stonebank.entity.Ttransferencia;
import stonebank.entity.Tusuario;
import stonebank.utils.CuentaUtil;

/**
 *
 * @author Rafael Pernil
 */
@Named(value = "transferenciaBean")
@SessionScoped
public class TransferenciaBean implements Serializable {

    @EJB
    private TusuarioFacade tusuarioFacade;

    @EJB
    private TtransferenciaFacade ttransferenciaFacade;

    @EJB
    private TmovimientoFacade tmovimientoFacade;

    protected int dniemisor;
    protected int dnireceptor;
    protected double cantidad;
    protected String concepto;
    DateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:MM:SS", Locale.US);
    Date fecha;
    protected Tusuario usuario;

    @Inject
    protected LoginBean loginBean;

    @Inject
    protected UsuarioBean usuarioBean;

    /**
     * Creates a new instance of TransferenciaBean
     */
    public TransferenciaBean() {
    }
    @PostConstruct
    public void init(){
        setUsuario(loginBean.getUsuarioLoggeado());
        
    }

    public void setUsuario(Tusuario usuario) {
        this.usuario = usuario;
    }

    public Tusuario getUsuario() {
        return usuario;
    }
    
    public int getDniemisor() {
        return dniemisor;
    }

    public void setDniemisor(int dniemisor) {
        this.dniemisor = dniemisor;
    }
    
    public int getDnireceptor() {
        return dnireceptor;
    }

    public void setDnireceptor(int dniReceptor) {
        this.dnireceptor = dniReceptor;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String doTransferencia() {
        dniemisor=usuario.getDniUsuario();
        dnireceptor = getDnireceptor();
        cantidad = getCantidad();
        concepto = getConcepto();
        boolean ready = true;
        fecha = new Date();
        /*
        *Coge los atributos que hay en la tabla, todos los campos son obligatorios
         */
        
        if (Integer.toString(dnireceptor).equals("") || Double.toString(cantidad).equals("")) {
            ready = false;
            /*request.setAttribute("mensaje", "Faltan datos");
            request.setAttribute("url", "ServletCreaTransferencia?dni=" + emisor.getDniUsuario());
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
             */
        }

        if (!CuentaUtil.esCorrectoFormatoDNI(Integer.toString(dnireceptor))) {
            ready = false;
            /* request.setAttribute("mensaje", "Introduce el DNI sin letra");
            request.setAttribute("url", "ServletCreaTransferencia?dni=" + emisor.getDniUsuario());
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
             */
        }

        if (!CuentaUtil.esCorrectoFormatoDinero(Double.toString(cantidad))) {
            ready = false;
            /*request.setAttribute("mensaje", "La cantidad debe ser numérica y con decimales válidos. Use . para separar euros de céntimos");
            request.setAttribute("url", "ServletCreaTransferencia?dni=" + emisor.getDniUsuario());
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
             */
        }

        //Tusuario emisor = (Tusuario) session.getAttribute("usuarioLogin");
        /*
        *Compruebo que el dniEmisor puede realizar la transferencia, para ello sumo todos los movimientos 
        *y transferencias entrantes y le resto las transferencias salientes
         */
        Double sumaMovimientos = this.tmovimientoFacade.dineroEntrantePorMovimientos(usuario.getDniUsuario());
        Double sumaTransferencias = this.ttransferenciaFacade.dineroEntranteTransferencia(usuario.getDniUsuario());
        Double restaTransferencias = this.ttransferenciaFacade.dineroSalienteTransferencia(usuario.getDniUsuario());

        //si no se han hecho transferencias se pone
        if (sumaMovimientos == null) {
            sumaMovimientos = 0.0;
        }
        if (sumaTransferencias == null) {
            sumaTransferencias = 0.0;
        }
        if (restaTransferencias == null) {
            restaTransferencias = 0.0;
        }
        if (cantidad <= 0) {
            //lanza error
        }

        if (ready && (sumaMovimientos + sumaTransferencias) >= (restaTransferencias + cantidad)) {

            //Compruebo que el dniReceptor existe
            Tusuario receptor;
            receptor = this.tusuarioFacade.find(dnireceptor);
            {

                if (receptor != null) {
                    Ttransferencia transferencia = new Ttransferencia();
                    transferencia.setDNIEmisor(usuario);
                    transferencia.setDNIReceptor(receptor);
                    transferencia.setCantidad(cantidad);
                    transferencia.setConcepto(concepto);
                    transferencia.setFecha(fecha);

                    this.ttransferenciaFacade.create(transferencia);

                    Double sumaMovimientosAfter = this.tmovimientoFacade.dineroEntrantePorMovimientos(usuario.getDniUsuario());
                    Double sumaTransferenciasAfter = this.ttransferenciaFacade.dineroEntranteTransferencia(usuario.getDniUsuario());
                    Double restaTransferenciasAfter = this.ttransferenciaFacade.dineroSalienteTransferencia(usuario.getDniUsuario());

                    if (sumaMovimientosAfter == null) {
                        sumaMovimientosAfter = 0.0;
                    }
                    if (sumaTransferenciasAfter == null) {
                        sumaTransferenciasAfter = 0.0;
                    }
                    if (restaTransferenciasAfter == null) {
                        restaTransferenciasAfter = 0.0;
                    }
                    if (cantidad <= 0) {
                        //lanza error
                    }

                    Double saldoAfter = sumaMovimientosAfter + sumaTransferenciasAfter - restaTransferenciasAfter;
                    usuarioBean.setSaldo(saldoAfter);

//                    request.setAttribute("mensajeExito", "¡Transferencia creada con éxito!");
//                    //request.setAttribute("saldo",saldo);
//                    request.setAttribute("proximaURL", "usuario/indexUsuario.jsp"); //Atención, envia sin / inicial
//                    //RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.jsp");
//                    RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/exito.jsp");
//                    rd.forward(request, response);
                    return "usuario/indexUsuario.jsf";
                } else {

//                    String url = "ServletCreaTransferencia?dni=" + request.getParameter("dniemisor");
//                    request.setAttribute("mensaje", "Error, usuario inexistente");
//                    request.setAttribute("url", url);
//                    RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/error.jsp");
//                    rd.forward(request, response);
                    return "";
                }
            }

        } else {
            //lanza error no tiene dinero suficiente
            //pagina de error por hacer, todavia no hace nada
//            String url = "ServletCreaTransferencia?dni=" + request.getParameter("dniemisor");
//            request.setAttribute("mensaje", "Error, dinero insuficiente");
//            request.setAttribute("url", url);
//            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/error.jsp");
//            rd.forward(request, response);
            return "";
        }

    }
}
