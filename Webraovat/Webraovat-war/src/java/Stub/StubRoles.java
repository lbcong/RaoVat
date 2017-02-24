/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import Entity.Roles;
import SessionBean.RolesFacadeLocal;
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
public class StubRoles {

    RolesFacadeLocal rolesFacade = lookupRolesFacadeLocal();

    private RolesFacadeLocal lookupRolesFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (RolesFacadeLocal) c.lookup("java:global/Webraovat/Webraovat-ejb/RolesFacade!SessionBean.RolesFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void create(Roles roles) {
        rolesFacade.create(roles);
    }

    public void edit(Roles roles) {
        rolesFacade.edit(roles);
    }

    public void remove(Roles roles) {
        rolesFacade.remove(roles);
    }

    public Roles find(Object id) {
        return rolesFacade.find(id);
    }

    public List<Roles> findAll() {
        return rolesFacade.findAll();
    }

    public Roles findRoleByUserName(String Name) {
        return rolesFacade.findRoleByUserName(Name);
    }

    public List<Roles> findRange(int[] range) {
        return rolesFacade.findRange(range);
    }

    public int count() {
        return rolesFacade.count();
    }
    
    
    
}
