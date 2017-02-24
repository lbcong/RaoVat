/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import Entity.Makeorder;
import SessionBean.MakeorderFacadeLocal;
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
public class StubMakeorder {

    MakeorderFacadeLocal makeorderFacade = lookupMakeorderFacadeLocal();

    private MakeorderFacadeLocal lookupMakeorderFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (MakeorderFacadeLocal) c.lookup("java:global/Webraovat/Webraovat-ejb/MakeorderFacade!SessionBean.MakeorderFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void create(Makeorder makeorder) {
        makeorderFacade.create(makeorder);
    }

    public void edit(Makeorder makeorder) {
        makeorderFacade.edit(makeorder);
    }

    public void remove(Makeorder makeorder) {
        makeorderFacade.remove(makeorder);
    }

    public Makeorder find(Object id) {
        return makeorderFacade.find(id);
    }

    public List<Makeorder> findAll() {
        return makeorderFacade.findAll();
    }

    public List<Makeorder> findRange(int[] range) {
        return makeorderFacade.findRange(range);
    }

    public List<Makeorder> FindAllMakeorderByID(int MemberID) {
        return makeorderFacade.FindAllMakeorderByID(MemberID);
    }

    public List<Makeorder> FindAllMakeorder(int MemberID, String Name, int limit, int tranghientai) {
        return makeorderFacade.FindAllMakeorder(MemberID, Name, limit, tranghientai);
    }

    public int countAllMakeorder(int MemberID, String Name) {
        return makeorderFacade.countAllMakeorder(MemberID, Name);
    }

    

    
    
    public int count() {
        return makeorderFacade.count();
    }
    
    
}
