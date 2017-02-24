/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import Entity.Postchecked;
import SessionBean.PostcheckedFacadeLocal;
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
public class StubPostChecked {

    PostcheckedFacadeLocal postcheckedFacade = lookupPostcheckedFacadeLocal();

    private PostcheckedFacadeLocal lookupPostcheckedFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (PostcheckedFacadeLocal) c.lookup("java:global/Webraovat/Webraovat-ejb/PostcheckedFacade!SessionBean.PostcheckedFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void create(Postchecked postchecked) {
        postcheckedFacade.create(postchecked);
    }

    public void edit(Postchecked postchecked) {
        postcheckedFacade.edit(postchecked);
    }

    public void remove(Postchecked postchecked) {
        postcheckedFacade.remove(postchecked);
    }

    public Postchecked find(Object id) {
        return postcheckedFacade.find(id);
    }

    public List<Postchecked> FindAllPostCheckedByMember(int MemberID) {
        return postcheckedFacade.FindAllPostCheckedByMember(MemberID);
    }

    public Postchecked FindAllPostCheckeddByID(int postID) {
        return postcheckedFacade.FindAllPostCheckeddByID(postID);
    }

    public List<Postchecked> findAll() {
        return postcheckedFacade.findAll();
    }

    public List<Postchecked> findRange(int[] range) {
        return postcheckedFacade.findRange(range);
    }

    public int count() {
        return postcheckedFacade.count();
    }
    
    
    
}
