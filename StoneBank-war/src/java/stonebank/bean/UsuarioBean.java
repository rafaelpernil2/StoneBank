package stonebank.bean;

import java.security.NoSuchAlgorithmException;
import java.time.Clock;
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
    protected String nuevaContrasena="", seguroContrasena="",
            viejaContrasena="",nuevoNombre,nuevoApellido,nuevoDomicilio,nuevoEmail;
    protected Integer nuevoTelefono;
    
    
    

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }
    
    @PostConstruct
    public void init(){
        usuario= loginBean.getUsuarioLoggeado();
        listaMovimientos = usuario.getTmovimientoList();
        nuevoNombre=usuario.getNombre();
        nuevoApellido=usuario.getApellidos();
        nuevoDomicilio=usuario.getDomicilio();
        nuevoEmail=usuario.getEmail();
        nuevoTelefono=usuario.getTelefono();
    }
    
    /*
    * hace falta poner el saldo que tiene justo aqui.
    */
    
    public List<Tmovimiento> getListaMovimientos(){
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
    
    public void setDNI(Integer dni){
        //no debe de hacer nada, dni no se puede cambiar
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
    
    public String getNuevaContrasena(){
        return nuevaContrasena;
    }
    
    public void setNuevaContrasena(String nC){
        nuevaContrasena=nC;
    }
    
    public String getSeguroContrasena(){
        return seguroContrasena;
    }
    
    public void setSeguroContrasena(String sC){
        seguroContrasena=sC;
    }
    
    public String getViejaContrasena(){
        return viejaContrasena;
    }
    
    public void setViejaContrasena(String vC){
        viejaContrasena=vC;
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
    
    public String doEditar() throws NoSuchAlgorithmException{

        String comprobarContrasena = PassUtil.generarHash(viejaContrasena);
        
        if(comprobarContrasena.equals(usuario.getHashContrasena()) && !"".equals(viejaContrasena)){
            if(!"".equals(nuevaContrasena) ){
                if(nuevaContrasena.equals(seguroContrasena)){
                    this.usuario.setHashContrasena(PassUtil.generarHash(nuevaContrasena));
                    actualizar();
                    this.tusuarioFacade.edit(usuario);
                    return "indexUsuario";
                }else{
                    restaurar();
                    return "configuracion";//mostar mensajes de algun tipo
                }
            } 
            actualizar();
            this.tusuarioFacade.edit(usuario);
            return "indexUsuario";
        }else{
                restaurar();
                return "configuracion";//esto se deberia de cambiar o mostrar un mensaje de error
        }
        
        
    }
    
    private void restaurar(){
        nuevoNombre=usuario.getNombre();
        nuevoApellido=usuario.getApellidos();
        nuevoDomicilio=usuario.getDomicilio();
        nuevoEmail=usuario.getEmail();
        nuevoTelefono=usuario.getTelefono();
    }
    
    private void actualizar(){
        usuario.setNombre(nuevoNombre);
        usuario.setApellidos(nuevoApellido);
        usuario.setDomicilio(nuevoDomicilio);
        usuario.setEmail(nuevoEmail);
        usuario.setTelefono(nuevoTelefono);
    }
    
    public String getNuevoNombre(){
        return nuevoNombre;
    }
    
    public void setNuevoNombre(String nN){
        nuevoNombre=nN;
    }
    
    public String getNuevoApellido(){
        return nuevoApellido;
    }
    
    public void setNuevoApellido(String nA){
        nuevoApellido=nA;
    }
    
    public String getNuevoDomicilio(){
        return nuevoDomicilio;
    }
    
    public void setNuevoDomicilio(String nD){
        nuevoDomicilio=nD;
    }
    
    public String getNuevoEmail(){
        return nuevoEmail;
    }
    
    public void setNuevoEmail(String nE){
        nuevoEmail=nE;
    }
    
    public Integer getNuevoTelefono(){
        return nuevoTelefono;
    }
    
    public void setNuevoTelefono(Integer nT){
        nuevoTelefono=nT;
    }


}