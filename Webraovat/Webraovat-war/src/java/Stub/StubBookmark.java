/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import Entity.Bookmark;
import SessionBean.BookmarkFacadeLocal;
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
public class StubBookmark {

    BookmarkFacadeLocal bookmarkFacade = lookupBookmarkFacadeLocal();

    private BookmarkFacadeLocal lookupBookmarkFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (BookmarkFacadeLocal) c.lookup("java:global/Webraovat/Webraovat-ejb/BookmarkFacade!SessionBean.BookmarkFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void create(Bookmark bookmark) {
        bookmarkFacade.create(bookmark);
    }

    public void edit(Bookmark bookmark) {
        bookmarkFacade.edit(bookmark);
    }

    public void remove(Bookmark bookmark) {
        bookmarkFacade.remove(bookmark);
    }

    public Bookmark find(Object id) {
        return bookmarkFacade.find(id);
    }

    public List<Bookmark> findAll() {
        return bookmarkFacade.findAll();
    }

    public List<Bookmark> FindBookmarkByMember(int id) {
        return bookmarkFacade.FindBookmarkByMember(id);
    }

    public List<Bookmark> findRange(int[] range) {
        return bookmarkFacade.findRange(range);
    }

    public int count() {
        return bookmarkFacade.count();
    }

    public Bookmark FindBookmarkByID(int id) {
        return bookmarkFacade.FindBookmarkByID(id);
    }
    
    
    
}
