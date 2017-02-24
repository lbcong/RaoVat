/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.Banlistpost;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface BanlistpostFacadeLocal {

    void create(Banlistpost banlistpost);

    void edit(Banlistpost banlistpost);

    void remove(Banlistpost banlistpost);

    Banlistpost find(Object id);

    List<Banlistpost> findAll();

    List<Banlistpost> findRange(int[] range);

    public Banlistpost FindAllPostBanedByID(int postID);
    
    public List<Banlistpost> FindAllPostBanedByMember(int MemberID);
    
    public List<Banlistpost> FindAllPostBanedByPage(int limit, int tranghientai);
    int count();
    
}
