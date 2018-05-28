/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.bean;

import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import stonebank.ejb.TusuarioFacade;
import stonebank.entity.Trol;
import stonebank.entity.Tusuario;
import stonebank.utils.PassUtil;

/**
 *
 * @author Victor
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    @EJB
    private TusuarioFacade tusuarioFacade;

    protected Tusuario usuarioLoggeado;
    protected int dniLogin;
    protected String passwordLogin = "";
    Trol rolUsuario = new Trol(1);
    Trol rolEmpleado = new Trol(2);    

    public int getDniLogin() {
        return dniLogin;
    }

    public void setUsuarioLoggeado(Tusuario usuarioLoggeado) {
        this.usuarioLoggeado = usuarioLoggeado;
    }
    
    public void setDniLogin(int dniLogin) {
        this.dniLogin = dniLogin;
    }

    public String getPasswordLogin() {
        return passwordLogin;
    }

    public void setPasswordLogin(String passwordLogin) {
        this.passwordLogin = passwordLogin;
    }
        
    public LoginBean() {
    }
    
    public String doLogin() throws NoSuchAlgorithmException{
        
        setUsuarioLoggeado(this.tusuarioFacade.find(dniLogin));
       
        
        String contrasenaHash = PassUtil.generarHash(passwordLogin);
        
        if(contrasenaHash.equalsIgnoreCase(usuarioLoggeado.getHashContrasena())){
            //Comprobamos el rol del usuario
            if(usuarioLoggeado.getTrolIdtrol().equals(rolUsuario)){
                return "/usuario/indexUsuario";
            } else if (usuarioLoggeado.getTrolIdtrol().equals(rolEmpleado)){
                return "/empleado/indexEmpleado";
            }
        } 
        
        return "error"; //Redirige a error si no lo ha hecho antes
        
    }
    
    public String doCerrarSesion(){
        
        return "login";
    }
    
    @PostConstruct
    public void init(){
        if(dniLogin != 1){
            usuarioLoggeado = this.tusuarioFacade.find(dniLogin);
        }
    }
    
}
