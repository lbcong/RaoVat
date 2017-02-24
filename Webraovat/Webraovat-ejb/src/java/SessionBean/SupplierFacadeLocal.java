/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.Supplier;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface SupplierFacadeLocal {

    void create(Supplier supplier);

    void edit(Supplier supplier);

    void remove(Supplier supplier);

    Supplier find(Object id);

    List<Supplier> findAll();

    List<Supplier> findRange(int[] range);

    int count();
    
}
