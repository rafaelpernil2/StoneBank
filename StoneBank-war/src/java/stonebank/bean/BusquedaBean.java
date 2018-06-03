
package stonebank.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import stonebank.ejb.TusuarioFacade;
import stonebank.entity.Tusuario;


@Named(value = "busquedaBean")
@SessionScoped
public class BusquedaBean implements Serializable{

    @EJB
    private TusuarioFacade tusuarioFacade;
    private String busqueda;
    private List<Tusuario> listaUsuarios;
    
    @PostConstruct
    public void init(){
        busqueda = "";
        listaUsuarios = tusuarioFacade.findAll();
    }
    

    public BusquedaBean() {
        
    }
    
    public String buscar(){
        setListaUsuarios(tusuarioFacade.buscarTUsuarioPorNombre(busqueda));
        return "/empleado/indexEmpleado";
    }

    public List<Tusuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Tusuario> listaResultado) {
        this.listaUsuarios = listaResultado;
    }

    public String getBusqueda() {
        return busqueda;
    }
    
    public void setBusqueda(String b){
        busqueda = b;
    }
}