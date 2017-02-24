/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.Rolesmanager;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface RolesmanagerFacadeLocal {

    void create(Rolesmanager rolesmanager);

    void edit(Rolesmanager rolesmanager);

    void remove(Rolesmanager rolesmanager);

    Rolesmanager find(Object id);

    List<Rolesmanager> findAll();

    List<Rolesmanager> findRange(int[] range);

    public List<Rolesmanager> findByUserID(int id);
    
    int count();
    
}
