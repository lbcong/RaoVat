/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.Bookmark;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface BookmarkFacadeLocal {

    void create(Bookmark bookmark);

    void edit(Bookmark bookmark);

    void remove(Bookmark bookmark);

    Bookmark find(Object id);

    List<Bookmark> findAll();

    public List<Bookmark> FindBookmarkByMember(int id);

    public Bookmark FindBookmarkByID(int id);

    List<Bookmark> findRange(int[] range
    );

    int count();

}
