package stonebank.bean;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import javax.inject.Named;

import javax.inject.Inject;
import stonebank.ejb.TmovimientoFacade;
import stonebank.ejb.TtransferenciaFacade;
import stonebank.ejb.TusuarioFacade;
import stonebank.entity.Tmovimiento;
import stonebank.entity.Tusuario;
import stonebank.utils.*;

/**
 *
 * @author Victor
 */
@Named(value = "usuarioBean")
@RequestScoped
public class UsuarioBean {

    @EJB
    private TtransferenciaFacade ttransferenciaFacade;

    @EJB
    private TmovimientoFacade tmovimientoFacade;

    @Inject
    protected LoginBean loginBean;
    
    @EJB
    private TusuarioFacade tusuarioFacade;
    
    
    
    
    protected List<Tmovimiento> listaMovimientos;
    protected Tusuario usuario;
    protected double saldo;
    

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }
    
    @PostConstruct
    public void init(){
        usuario= loginBean.getUsuarioLoggeado();
        listaMovimientos = usuario.getTmovimientoList();
    }
    
    /*
    * hace falta poner el saldo que tiene justo aqui.
    */
    
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }

    public List<Tmovimiento> getListaMovimientos() {
        return listaMovimientos;
    }
    
    public void setListaMovimientos(List<Tmovimiento> lm){
        this.listaMovimientos = lm;
    }
    
    public String getNombre(){
        return usuario.getNombre();
    }
    
    public void setNombre(String n){
        usuario.setNombre(n);
    }
    
    public String getApellidos(){
        return usuario.getApellidos();
    }
    
    public void setApellidos(String ap){
        usuario.setApellidos(ap);
    }
    
    public Integer getDNI(){
        return usuario.getDniUsuario();
    }
    
    public String getDomicilio(){
        return usuario.getDomicilio();
    }
    
    public void setDomicilio(String d){
        usuario.setDomicilio(d);
    }
    
    public String getEmail(){
        return usuario.getEmail();
    }
    
    public void setEmail(String em){
        usuario.setEmail(em);
    }
    
    public Integer getTelefono(){
        return usuario.getTelefono();
    }
    
    public void setTelefono(Integer tel){
        usuario.setTelefono(tel);
    }
    
    public String getHashContrasena(){
        return usuario.getHashContrasena();
    }
    
    public void setContrasena(String contrasena){
        try {
            usuario.setHashContrasena(PassUtil.generarHash(contrasena));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public Double getSaldo(){
        Double dineroEntranteMovimientos=this.tmovimientoFacade.dineroEntrantePorMovimientos(usuario.getDniUsuario());
        Double dineroEntranteTransferencias=this.ttransferenciaFacade.dineroEntranteTransferencia(usuario.getDniUsuario());
        Double dineroSalienteTransferencias=this.ttransferenciaFacade.dineroSalienteTransferencia(usuario.getDniUsuario());
        if(dineroEntranteMovimientos==null){
            dineroEntranteMovimientos=0.0;
        }
        if(dineroEntranteTransferencias==null){
            dineroEntranteTransferencias=0.0;
        }
        if(dineroSalienteTransferencias==null){
            dineroSalienteTransferencias=0.0;
        }
        return (dineroEntranteMovimientos+dineroEntranteTransferencias)- dineroSalienteTransferencias;
    }
    
    public String doEditar(){

        //String comprobarContrasena = PassUtil.generarHash();
        
        return "indexUsuario";
    }
    

}