/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.Roles;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface RolesFacadeLocal {

    void create(Roles roles);

    void edit(Roles roles);

    void remove(Roles roles);

    Roles find(Object id);

    List<Roles> findAll();

    public Roles findRoleByUserName(String Name);

    List<Roles> findRange(int[] range);

    int count();

}
