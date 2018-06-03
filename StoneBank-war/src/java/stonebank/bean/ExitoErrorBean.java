/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Fran Gambero
 */
@Named(value = "exitoErrorBean")
@SessionScoped
public class ExitoErrorBean implements Serializable{

    
    private String mensajeExito="";
    private String mensajeError="";
    private String proximaURL = "";
    
    /**
     * Creates a new instance of ExitoBean
     */
    public ExitoErrorBean() {
    }

    public String getMensajeExito() {
        return mensajeExito;
    }

    public String getProximaURL() {
        return proximaURL;
    }    

    public void setMensajeExito(String mensajeExito) {
        this.mensajeExito = mensajeExito;
    }

    public void setProximaURL(String proximaURL) {
        this.proximaURL = proximaURL;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getMensajeError() {
        return mensajeError;
    }
    
    //Funciones rebonicas    
    public String doExito(String msg, String url){
        
        this.mensajeExito = msg;
        this.proximaURL = url;
        
        return "/exito";
    }
    
    public String doError(String msg, String url){
        
        this.mensajeError = msg;
        this.proximaURL = url;
        
        return "/error";
    }
    
    @PostConstruct
    public void init(){
        //Limpio todos los strings para que no haya conflictos en sucesivas llamadas
        this.mensajeExito="";
        this.mensajeError="";
        this.proximaURL="";
    }
    
}
