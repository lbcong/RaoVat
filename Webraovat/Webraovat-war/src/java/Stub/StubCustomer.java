/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import Entity.Members;
import SessionBean.MembersFacadeLocal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class StubCustomer {

    MembersFacadeLocal membersFacade = lookupMembersFacadeLocal();

    private MembersFacadeLocal lookupMembersFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (MembersFacadeLocal) c.lookup("java:global/Webraovat/Webraovat-ejb/MembersFacade!SessionBean.MembersFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void create(Members members) {
        membersFacade.create(members);
    }

    public void edit(Members members) {
        membersFacade.edit(members);
    }

    public void remove(Members members) {
        membersFacade.remove(members);
    }

    public Members find(Object id) {
        return membersFacade.find(id);
    }

    public List<Members> findAll() {
        return membersFacade.findAll();
    }

    public List<Members> findRange(int[] range) {
        return membersFacade.findRange(range);
    }

    public Members CheckLogin(String email, String password) {
        return membersFacade.CheckLogin(email, password);
    }

    public Members findByPhoneMember(String phone) {
        return membersFacade.findByPhoneMember(phone);
    }

    public Members findByUsername(String name) {
        return membersFacade.findByUsername(name);
    }

    public Members findByEmailMember(String email) {
        return membersFacade.findByEmailMember(email);
    }

    public List<Members> findMemberOnline(Date date) {
        return membersFacade.findMemberOnline(date);
    }

    public boolean findByIsActivity(String email) {
        return membersFacade.findByIsActivity(email);
    }

    public int countFindAllMembers(String Name, String Email, String ProvinceName, String Role, String Type) {
        return membersFacade.countFindAllMembers(Name, Email, ProvinceName, Role, Type);
    }

    public List<Members> FindAllMembers(String Name, String Email, String ProvinceName, String Role, String Type, int limit, int tranghientai) {
        return membersFacade.FindAllMembers(Name, Email, ProvinceName, Role, Type, limit, tranghientai);
    }

//phuong-------------------------------------------------
    
    public Members findByActivityMember(Integer activityCode) {
        return membersFacade.findByActivityMember(activityCode);    
    }

//--------------------------------------------------
    public void edit1(Members admin) {
        membersFacade.edit1(admin);
    }

    public int count() {
        return membersFacade.count();
    }

}
