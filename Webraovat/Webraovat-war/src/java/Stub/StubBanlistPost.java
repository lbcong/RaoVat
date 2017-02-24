/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import Entity.Banlistpost;
import SessionBean.BanlistpostFacadeLocal;
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
public class StubBanlistPost {

    BanlistpostFacadeLocal banlistpostFacade = lookupBanlistpostFacadeLocal();

    private BanlistpostFacadeLocal lookupBanlistpostFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (BanlistpostFacadeLocal) c.lookup("java:global/Webraovat/Webraovat-ejb/BanlistpostFacade!SessionBean.BanlistpostFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void create(Banlistpost banlistpost) {
        banlistpostFacade.create(banlistpost);
    }

    public void edit(Banlistpost banlistpost) {
        banlistpostFacade.edit(banlistpost);
    }

    public void remove(Banlistpost banlistpost) {
        banlistpostFacade.remove(banlistpost);
    }

    public Banlistpost find(Object id) {
        return banlistpostFacade.find(id);
    }

    public List<Banlistpost> findAll() {
        return banlistpostFacade.findAll();
    }

    public List<Banlistpost> findRange(int[] range) {
        return banlistpostFacade.findRange(range);
    }

    public Banlistpost FindAllPostBanedByID(int postID) {
        return banlistpostFacade.FindAllPostBanedByID(postID);
    }

    public List<Banlistpost> FindAllPostBanedByPage(int limit, int tranghientai) {
        return banlistpostFacade.FindAllPostBanedByPage(limit, tranghientai);
    }

    public List<Banlistpost> FindAllPostBanedByMember(int MemberID) {
        return banlistpostFacade.FindAllPostBanedByMember(MemberID);
    }

    
    
    
    public int count() {
        return banlistpostFacade.count();
    }
    
}
