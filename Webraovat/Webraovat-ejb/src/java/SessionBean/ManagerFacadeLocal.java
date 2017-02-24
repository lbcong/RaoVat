/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.Manager;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface ManagerFacadeLocal {

    void create(Manager manager);

    void edit(Manager manager);

    void remove(Manager manager);

    Manager find(Object id);
    
    public Manager CheckLoginManager(String username, String password);
    
    public Manager findByUsernameManager(String username);

    List<Manager> findAll();

    List<Manager> findRange(int[] range);

    int count();
    
}
