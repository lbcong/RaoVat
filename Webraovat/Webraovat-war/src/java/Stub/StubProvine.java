/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import Entity.Catagories;
import Entity.Province;
import SessionBean.CatagoriesFacadeLocal;
import SessionBean.ProvinceFacadeLocal;
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
public class StubProvine {

    ProvinceFacadeLocal provinceFacade = lookupProvinceFacadeLocal();

    private ProvinceFacadeLocal lookupProvinceFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (ProvinceFacadeLocal) c.lookup("java:global/Webraovat/Webraovat-ejb/ProvinceFacade!SessionBean.ProvinceFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void create(Province province) {
        provinceFacade.create(province);
    }

    public void edit(Province province) {
        provinceFacade.edit(province);
    }

    public void remove(Province province) {
        provinceFacade.remove(province);
    }

    public Province find(Object id) {
        return provinceFacade.find(id);
    }

    public List<Province> findAll() {
        return provinceFacade.findAll();
    }

    public List<Province> findRange(int[] range) {
        return provinceFacade.findRange(range);
    }

    public int count() {
        return provinceFacade.count();
    }

    public Province FindProvinceByID(int id) {
        return provinceFacade.FindProvinceByID(id);
    }

    
}
