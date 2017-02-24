/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import Entity.Manager;
import SessionBean.ManagerFacadeLocal;
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
public class StubManager {

    ManagerFacadeLocal managerFacade = lookupManagerFacadeLocal();

    private ManagerFacadeLocal lookupManagerFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (ManagerFacadeLocal) c.lookup("java:global/Webraovat/Webraovat-ejb/ManagerFacade!SessionBean.ManagerFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void create(Manager manager) {
        managerFacade.create(manager);
    }

    public void edit(Manager manager) {
        managerFacade.edit(manager);
    }

    public void remove(Manager manager) {
        managerFacade.remove(manager);
    }

    public Manager find(Object id) {
        return managerFacade.find(id);
    }

    public List<Manager> findAll() {
        return managerFacade.findAll();
    }

    public List<Manager> findRange(int[] range) {
        return managerFacade.findRange(range);
    }

    public int count() {
        return managerFacade.count();
    }

    public Manager CheckLoginManager(String username, String password) {
        return managerFacade.CheckLoginManager(username, password);
    }

    public Manager findByUsernameManager(String username) {
        return managerFacade.findByUsernameManager(username);
    }

}
