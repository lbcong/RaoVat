/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.Postchecked;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface PostcheckedFacadeLocal {

    void create(Postchecked postchecked);

    void edit(Postchecked postchecked);

    void remove(Postchecked postchecked);

    Postchecked find(Object id);

    public List<Postchecked> FindAllPostCheckedByMember(int MemberID);

    public Postchecked FindAllPostCheckeddByID(int postID);

    List<Postchecked> findAll();

    List<Postchecked> findRange(int[] range);

    int count();

}
