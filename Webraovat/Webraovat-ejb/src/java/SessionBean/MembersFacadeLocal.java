/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.Members;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface MembersFacadeLocal {

    void create(Members members);

    void edit(Members members);

    void remove(Members members);

    Members find(Object id);

    List<Members> findAll();

    List<Members> findRange(int[] range);

    Members CheckLogin(String email, String password);

    Members findByPhoneMember(String phone);

    Members findByUsername(String name);

    Members findByEmailMember(String email);
    
    List<Members> findMemberOnline(Date date);
    
    public int countFindAllMembers(String Name, String Email, String ProvinceName,String Role, String Type);
    
    public List<Members> FindAllMembers(String Name, String Email, String ProvinceName,String Role, String Type, int limit, int tranghientai);
    
    public void edit1(Members admin);
//phuong-----------------------------------------------------
    public Members findByActivityMember(Integer activityCode);
//------------------------------------------------------------            
    public boolean findByIsActivity(String email);

    int count();

}
