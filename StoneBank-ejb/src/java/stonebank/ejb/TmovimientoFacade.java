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
import stonebank.entity.Tmovimiento;
import static stonebank.entity.Ttransferencia_.id;

/**
 *
 * @author rafaelpernil
 */
@Stateless
public class TmovimientoFacade extends AbstractFacade<Tmovimiento> {

    @PersistenceContext(unitName = "StoneBank-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TmovimientoFacade() {
        super(Tmovimiento.class);
    }
    
    public List<Tmovimiento> buscarMovimientoPorConceptoYDNI(String concepto, Integer dni){
      
        Query q = this.em.createQuery("select m from Tmovimiento m where m.concepto like :str and m.tusuariodniUsuario.dniUsuario = :id"); 
        q.setParameter("str","%" + concepto + "%"); 
        q.setParameter("id", dni); 
        
         return q.getResultList(); // Devuelvo la lista con todos los parámetros que necesito wei no más .
    }
    
    
    
}
