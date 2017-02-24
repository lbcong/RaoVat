/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.Makeorder;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface MakeorderFacadeLocal {

    void create(Makeorder makeorder);

    void edit(Makeorder makeorder);

    void remove(Makeorder makeorder);

    Makeorder find(Object id);

    List<Makeorder> findAll();

    List<Makeorder> findRange(int[] range);

    public List<Makeorder> FindAllMakeorderByID(int MemberID);

    public List<Makeorder> FindAllMakeorder(int MemberID,String Name, int limit, int tranghientai);

    public int countAllMakeorder(int MemberID,String Name);

    int count();

}
