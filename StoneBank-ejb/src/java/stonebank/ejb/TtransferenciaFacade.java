/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import stonebank.entity.Ttransferencia;

/**
 *
 * @author rafaelpernil
 */
@Stateless
public class TtransferenciaFacade extends AbstractFacade<Ttransferencia> {

    @PersistenceContext(unitName = "StoneBank-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TtransferenciaFacade() {
        super(Ttransferencia.class);
    }
    
    public Double dineroEntranteTransferencia(Integer dni){
        Query q = this.em.createQuery("SELECT SUM(r.cantidad) from Ttransferencia r where r.dNIReceptor.dniUsuario = :par");
        q.setParameter("par", dni);
        Double total = (Double) q.getResultList().get(0);
        return total;
    }
    
        public Double dineroSalienteTransferencia(Integer dni){
        Query q = this.em.createQuery("SELECT SUM(r.cantidad) from Ttransferencia r where r.dNIEmisor.dniUsuario =:par");
        q.setParameter("par", dni);
        Double total = (Double) q.getResultList().get(0);
        return total;
    }
    
}
