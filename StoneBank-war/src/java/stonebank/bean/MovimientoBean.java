/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.bean;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import stonebank.ejb.TmovimientoFacade;
import stonebank.entity.Tmovimiento;
import stonebank.entity.Tusuario;

/**
 *
 * @author Eduardo Pertierra Puche
 */
@Named(value = "movimientoBean")
@RequestScoped
public class MovimientoBean {

    @EJB
    private TmovimientoFacade tmovimientoFacade;

    @Inject
    protected LoginBean loginBean; 
    @Inject 
    UsuarioBean usuarioBean; 
        
    public MovimientoBean() {
        
    }
    
    protected String concepto, ibanEntidad; 
    protected Double cantidad; 
    protected Date fecha;
    protected Integer idMovimientoaEditar;
    
    @PostConstruct
    public void init(){
        
    }
    
   /* ---- FUNCIONES CORRESPONDEIENTES AL CRUD ---- */ 
    
    public String doCrear(Integer dniUsuario){
        Tusuario usuarioAAnadir = usuarioBean.getUsuarioPorDNI(dniUsuario); 
        Tmovimiento movimientoAAnadir = new Tmovimiento(); 
        
        if(usuarioAAnadir != null && comprobarMovimiento()){
            movimientoAAnadir.setTusuariodniUsuario(usuarioAAnadir);
            movimientoAAnadir.setCantidad(cantidad);
            movimientoAAnadir.setConcepto(concepto);
            movimientoAAnadir.setIbanEntidad(ibanEntidad);
            movimientoAAnadir.setFecha(new java.util.Date()); //Lo crea con la fecha actual
            tmovimientoFacade.create(movimientoAAnadir);
        
            return "usuarioSeleccionado"; 
                    } else {
            
            return "error"; 
            //Error
        }   
    }
    
    public String doCargaMovimientoParaEditarlo(Integer idMovimiento){
               Tmovimiento  movimientoAEditar = tmovimientoFacade.find(idMovimiento);
                 idMovimientoaEditar = movimientoAEditar.getIdtmovimiento();
                 cantidad = movimientoAEditar.getCantidad();
                 concepto = movimientoAEditar.getConcepto(); 
                 ibanEntidad = movimientoAEditar.getIbanEntidad(); 
                 fecha = movimientoAEditar.getFecha();
                 
                 return "editarMovimiento";
    }
    
    
    public String doEditar(){
       Tmovimiento movimientoAEditar = tmovimientoFacade.find(idMovimientoaEditar);

         if(movimientoAEditar != null && comprobarMovimiento()){
            movimientoAEditar.setCantidad(cantidad);
            movimientoAEditar.setConcepto(concepto);
            movimientoAEditar.setIbanEntidad(ibanEntidad);
            
            this.tmovimientoFacade.edit(movimientoAEditar);
        
           return "usuarioSeleccionado"; 
                    } else {
            
            return "error"; 
            //Error
        }   
    }
    
    public void doBorrar(Integer idMovimiento){
        Tmovimiento movimientoABorrar = tmovimientoFacade.find(idMovimiento); 
        tmovimientoFacade.remove(movimientoABorrar);       
        }
    
    
    /* --- GETTERS Y SETTERS --- */ 

    
     public List<Tmovimiento> getMovimientosUsuarioLoggeado(){
        return loginBean.getUsuarioLoggeado().getTmovimientoList(); 
    }
    
    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getIbanEntidad() {
        return ibanEntidad;
    }

    public void setIbanEntidad(String ibanEntidad) {
        this.ibanEntidad = ibanEntidad;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdMovimientoaEditar() {
        return idMovimientoaEditar;
    }

    public void setIdMovimientoaEditar(Integer idMovimientoaEditar) {
        this.idMovimientoaEditar = idMovimientoaEditar;
    }


    
    
    /* ---- FUNCIONES AUXILIARES ----*/ 
  
    private boolean comprobarMovimiento(){
        return (cantidad > 0) && (ibanEntidad != null); 
    }

    
    
    
}
