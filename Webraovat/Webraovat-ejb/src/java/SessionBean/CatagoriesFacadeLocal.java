/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.Catagories;
import Entity.Productsentity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface CatagoriesFacadeLocal {

    void create(Catagories catagories);

    void edit(Catagories catagories);

    void remove(Catagories catagories);

    public Catagories FindCatagoriesByID(int id);

    Catagories find(Object id);

    List<Catagories> findAll();

    List<Catagories> findRange(int[] range);
    
    public Catagories test(Productsentity pr);

    int count();

}
