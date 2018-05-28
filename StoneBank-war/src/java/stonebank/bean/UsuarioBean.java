package stonebank.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.SessionScoped;

import javax.inject.Named;

import javax.inject.Inject;
import stonebank.ejb.TusuarioFacade;
import stonebank.entity.Tmovimiento;
import stonebank.entity.Tusuario;

/**
 *
 * @author Victor
 */
@Named(value = "usuarioBean")
@RequestScoped
public class UsuarioBean {

    @Inject
    private LoginBean loginBean;
    
    @EJB
    private TusuarioFacade tusuarioFacade;
    
    
    
    protected List<Tmovimiento> listaMovimientos;
    protected Tusuario usuario;
    
    

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }
    
    @PostConstruct
    public void init(){
        
        usuario= loginBean.usuarioLoggeado;
        listaMovimientos = usuario.getTmovimientoList();
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
    
    public void setContraseña(){
        usuario.getHashContrasena();
    }
}