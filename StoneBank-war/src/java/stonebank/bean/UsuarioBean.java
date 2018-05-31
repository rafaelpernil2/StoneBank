/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import stonebank.ejb.TusuarioFacade;
import stonebank.entity.Tusuario;

/**
 *
 * @author Victor
 */
@Named(value = "usuarioBean")
@Dependent
public class UsuarioBean {

    
    protected List<Tusuario> listaUsuario; 
    
    @EJB
    private TusuarioFacade tusuarioFacade;

    /**
     * Creates a new instance of UsuarioBean
     */
    
    
    public UsuarioBean() {
    }
    
      @PostConstruct
    public void init () {
       listaUsuario = tusuarioFacade.findAll(); 
    }

    public List<Tusuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Tusuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    
   
    
}
