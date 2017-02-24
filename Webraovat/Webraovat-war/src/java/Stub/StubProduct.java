/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import Entity.Productsentity;
import SessionBean.ProductsentityFacadeLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Asus
 */
public class StubProduct {

    ProductsentityFacadeLocal productsentityFacade = lookupProductsentityFacadeLocal();

    private ProductsentityFacadeLocal lookupProductsentityFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (ProductsentityFacadeLocal) c.lookup("java:global/Webraovat/Webraovat-ejb/ProductsentityFacade!SessionBean.ProductsentityFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void create(Productsentity productsentity) {
        productsentityFacade.create(productsentity);
    }

    public void edit(Productsentity productsentity) {
        productsentityFacade.edit(productsentity);
    }

    public void remove(Productsentity productsentity) {
        productsentityFacade.remove(productsentity);
    }

    public Productsentity find(Object id) {
        return productsentityFacade.find(id);
    }

    public List<Productsentity> findAll() {
        return productsentityFacade.findAll();
    }

    public List<Productsentity> findRange(int[] range) {
        return productsentityFacade.findRange(range);
    }

    public int count() {
        return productsentityFacade.count();
    }

    public Productsentity FindProductentityByID(int id) {
        return productsentityFacade.FindProductentityByID(id);
    }
    
}
