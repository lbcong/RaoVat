/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.Postdenied;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface PostdeniedFacadeLocal {

    void create(Postdenied postdenied);

    void edit(Postdenied postdenied);

    void remove(Postdenied postdenied);

    Postdenied find(Object id);

    List<Postdenied> findAll();

    public List<Postdenied> FindAllPostDeniedByMember(int MemberID) ;
    
    public Postdenied FindAllPostDeniedByID(int postID);
    
    
    List<Postdenied> findRange(int[] range);

    int count();
    
}
