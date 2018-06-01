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
    LoginBean loginBean; 
        
    public MovimientoBean() {
        
    }
    
    protected String concepto, ibanEntidad; 
    protected Double cantidad; 
    protected Date fecha; 
    
    @PostConstruct
    public void init(){
        
    }
    
    public List<Tmovimiento> getMovimientosUsuarioLoggeado(){
        return loginBean.getUsuarioLoggeado().getTmovimientoList(); 
    }
    
    public void doBorrar(Tmovimiento movimientoAEliminar){
        tmovimientoFacade.remove(movimientoAEliminar);
    }

  
    

    
    
    
}
