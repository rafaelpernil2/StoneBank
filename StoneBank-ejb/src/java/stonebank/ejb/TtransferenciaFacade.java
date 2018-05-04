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
    
    public double dineroEntranteTransferencia(Integer dni){
        Query q = this.em.createNativeQuery("select SUM(t.cantidad) from Ttransferencia t where t.DNIReceptor.dniUsuario = :par");
        q.setParameter("par", dni);
        double total = (double) q.getSingleResult();
        return total;
    }
    
        public double dineroSalienteTransferencia(Integer dni){
        Query q = this.em.createNativeQuery("SELECT t FROM Ttransferencia t WHERE t.dNIEmisor.dniUsuario = :par"); 
        q.setParameter("par", dni);
        double total = (double) q.getSingleResult();
        return total;
    }
    
    
    
}
