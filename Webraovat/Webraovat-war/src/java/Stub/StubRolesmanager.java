/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import Entity.Rolesmanager;
import SessionBean.RolesmanagerFacadeLocal;
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
public class StubRolesmanager {

    RolesmanagerFacadeLocal rolesmanagerFacade = lookupRolesmanagerFacadeLocal();

    private RolesmanagerFacadeLocal lookupRolesmanagerFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (RolesmanagerFacadeLocal) c.lookup("java:global/Webraovat/Webraovat-ejb/RolesmanagerFacade!SessionBean.RolesmanagerFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void create(Rolesmanager rolesmanager) {
        rolesmanagerFacade.create(rolesmanager);
    }

    public void edit(Rolesmanager rolesmanager) {
        rolesmanagerFacade.edit(rolesmanager);
    }

    public void remove(Rolesmanager rolesmanager) {
        rolesmanagerFacade.remove(rolesmanager);
    }

    public Rolesmanager find(Object id) {
        return rolesmanagerFacade.find(id);
    }

    public List<Rolesmanager> findAll() {
        return rolesmanagerFacade.findAll();
    }

    public List<Rolesmanager> findRange(int[] range) {
        return rolesmanagerFacade.findRange(range);
    }

    public List<Rolesmanager> findByUserID(int id) {
        return rolesmanagerFacade.findByUserID(id);
    }

    public int count() {
        return rolesmanagerFacade.count();
    }
    
    
    
}
