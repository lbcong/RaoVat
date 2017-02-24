/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import Entity.Postdenied;
import SessionBean.PostdeniedFacadeLocal;
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
public class StubDeniedPost {

    PostdeniedFacadeLocal postdeniedFacade = lookupPostdeniedFacadeLocal();

    private PostdeniedFacadeLocal lookupPostdeniedFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (PostdeniedFacadeLocal) c.lookup("java:global/Webraovat/Webraovat-ejb/PostdeniedFacade!SessionBean.PostdeniedFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void create(Postdenied postdenied) {
        postdeniedFacade.create(postdenied);
    }

    public void edit(Postdenied postdenied) {
        postdeniedFacade.edit(postdenied);
    }

    public void remove(Postdenied postdenied) {
        postdeniedFacade.remove(postdenied);
    }

    public Postdenied find(Object id) {
        return postdeniedFacade.find(id);
    }

    public List<Postdenied> findAll() {
        return postdeniedFacade.findAll();
    }

    public List<Postdenied> findRange(int[] range) {
        return postdeniedFacade.findRange(range);
    }

    public int count() {
        return postdeniedFacade.count();
    }

    public List<Postdenied> FindAllPostDeniedByMember(int MemberID) {
        return postdeniedFacade.FindAllPostDeniedByMember(MemberID);
    }

    public Postdenied FindAllPostDeniedByID(int postID) {
        return postdeniedFacade.FindAllPostDeniedByID(postID);
    }
    
    
    
}
