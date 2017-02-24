/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import Entity.Banlistuser;
import SessionBean.BanlistuserFacadeLocal;
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
public class StubBanlistUser {

    BanlistuserFacadeLocal banlistuserFacade = lookupBanlistuserFacadeLocal();

    private BanlistuserFacadeLocal lookupBanlistuserFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (BanlistuserFacadeLocal) c.lookup("java:global/Webraovat/Webraovat-ejb/BanlistuserFacade!SessionBean.BanlistuserFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void create(Banlistuser banlistuser) {
        banlistuserFacade.create(banlistuser);
    }

    public void edit(Banlistuser banlistuser) {
        banlistuserFacade.edit(banlistuser);
    }

    public void remove(Banlistuser banlistuser) {
        banlistuserFacade.remove(banlistuser);
    }

    public Banlistuser find(Object id) {
        return banlistuserFacade.find(id);
    }

    public List<Banlistuser> findAll() {
        return banlistuserFacade.findAll();
    }

    public List<Banlistuser> findRange(int[] range) {
        return banlistuserFacade.findRange(range);
    }

    public Banlistuser FindAllUSerBanedByID(int UserID) {
        return banlistuserFacade.FindAllUSerBanedByID(UserID);
    }

    public int count() {
        return banlistuserFacade.count();
    }
    
}
