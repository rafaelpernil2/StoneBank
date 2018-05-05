/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import stonebank.entity.Tusuario;

/**
 *
 * @author rafaelpernil
 */
@Stateless
public class TusuarioFacade extends AbstractFacade<Tusuario> {

    @PersistenceContext(unitName = "StoneBank-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TusuarioFacade() {
        super(Tusuario.class);
    }
    public List<Tusuario> buscarTUsuarioPorNombre(String nombre){
        
     Query q = em.createNamedQuery("Tusuario.findByNombre");
    List<Tusuario> res; 
     if(nombre.equals("")){
        res = findAll(); 
     } else {
        res = q.setParameter("nombre", nombre).getResultList();
     }
     return res; 
     
           
    }
}
