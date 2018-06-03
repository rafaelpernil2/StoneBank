/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import stonebank.entity.Trol;

/**
 *
 * @author rafaelpernil
 */
@Stateless
public class TrolFacade extends AbstractFacade<Trol> {

    @PersistenceContext(unitName = "StoneBank-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrolFacade() {
        super(Trol.class);
    }
    
}
