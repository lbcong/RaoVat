/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.Banlistuser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface BanlistuserFacadeLocal {

    void create(Banlistuser banlistuser);

    void edit(Banlistuser banlistuser);

    void remove(Banlistuser banlistuser);

    Banlistuser find(Object id);

    List<Banlistuser> findAll();

    List<Banlistuser> findRange(int[] range);
    
    public Banlistuser FindAllUSerBanedByID(int UserID);

    int count();
    
}
