/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import Entity.Catagories;
import Entity.Productsentity;
import SessionBean.CatagoriesFacadeLocal;
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
public class StubCatagories {

    CatagoriesFacadeLocal catagoriesFacade = lookupCatagoriesFacadeLocal();

    private CatagoriesFacadeLocal lookupCatagoriesFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (CatagoriesFacadeLocal) c.lookup("java:global/Webraovat/Webraovat-ejb/CatagoriesFacade!SessionBean.CatagoriesFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void create(Catagories catagories) {
        catagoriesFacade.create(catagories);
    }

    public void edit(Catagories catagories) {
        catagoriesFacade.edit(catagories);
    }

    public void remove(Catagories catagories) {
        catagoriesFacade.remove(catagories);
    }

    public Catagories test(Productsentity pr) {
        return catagoriesFacade.test(pr);
    }

    public Catagories find(Object id) {
        return catagoriesFacade.find(id);
    }

    public List<Catagories> findAll() {
        return catagoriesFacade.findAll();
    }

    public List<Catagories> findRange(int[] range) {
        return catagoriesFacade.findRange(range);
    }

    public int count() {
        return catagoriesFacade.count();
    }

    public Catagories FindCatagoriesByID(int id) {
        return catagoriesFacade.FindCatagoriesByID(id);
    }

    
}
