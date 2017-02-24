/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.Productsentity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface ProductsentityFacadeLocal {

    void create(Productsentity productsentity);

    void edit(Productsentity productsentity);

    void remove(Productsentity productsentity);
    
    public Productsentity FindProductentityByID(int id);

    Productsentity find(Object id);

    List<Productsentity> findAll();

    List<Productsentity> findRange(int[] range);

    int count();
    
}
