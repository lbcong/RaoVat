/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.Productstore;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Asus
 */
@Stateless
public class ProductstoreFacade extends AbstractFacade<Productstore> implements ProductstoreFacadeLocal {

    @PersistenceContext(unitName = "Webraovat-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductstoreFacade() {
        super(Productstore.class);
    }
    
}
