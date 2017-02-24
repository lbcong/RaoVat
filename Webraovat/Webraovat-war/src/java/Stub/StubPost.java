/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import Entity.Post;
import SessionBean.PostFacadeLocal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Asus
 */
public class StubPost {

    PostFacadeLocal postFacade = lookupPostFacadeLocal();

    private PostFacadeLocal lookupPostFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (PostFacadeLocal) c.lookup("java:global/Webraovat/Webraovat-ejb/PostFacade!SessionBean.PostFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void create(Post post) {
        postFacade.create(post);
    }

    public void edit(Post post) {
        postFacade.edit(post);
    }

    public void remove(Post post) {
        postFacade.remove(post);
    }

    public Post find(Object id) {
        return postFacade.find(id);
    }

    public List<Post> FindPostByCity(String name) {
        return postFacade.FindPostByCity(name);
    }

    public List<Post> FindPostByIDCity(int id) {
        return postFacade.FindPostByIDCity(id);
    }

    public List<Post> FindPostByCatagoriesName(String name) {
        return postFacade.FindPostByCatagoriesName(name);
    }

    public List<Post> FindPostByCatagoriesAndCity(String catagories, String city) {
        return postFacade.FindPostByCatagoriesAndCity(catagories, city);
    }

    public List<Post> FindPostByTittleAndCity(String tittle, String city) {
        return postFacade.FindPostByTittleAndCity(tittle, city);
    }

    public List<Post> FindPostByTittleAndCityAndCatagories(String tittle, String city, String catagories) {
        return postFacade.FindPostByTittleAndCityAndCatagories(tittle, city, catagories);
    }

    public List<Post> FindPostByMember(int id) {
        return postFacade.FindPostByMember(id);
    }

    public List<Post> findAll() {
        return postFacade.findAll();
    }

    public List<Post> findRange(int[] range) {
        return postFacade.findRange(range);
    }

    public Post FindPostByID(int id) {
        return postFacade.FindPostByID(id);
    }

    public List<Post> FindAllPostByReport(int report) {
        return postFacade.FindAllPostByReport(report);
    }

    public List<Post> FindAllPostByCheck(int check) {
        return postFacade.FindAllPostByCheck(check);
    }

    public List<Post> FindAllPostExperiDate() {
        return postFacade.FindAllPostExperiDate();
    }

    public List<Post> FindAllPostByPage(int limit, int tranghientai) {
        return postFacade.FindAllPostByPage(limit, tranghientai);
    }

    public List<Post> FindExpPostByPage(int limit, int tranghientai) {
        return postFacade.FindExpPostByPage(limit, tranghientai);
    }

    public List<Post> FindNoCheckPostByPage(int limit, int tranghientai) {
        return postFacade.FindNoCheckPostByPage(limit, tranghientai);
    }

    public List<Post> FindAllPost(String Tittle, String Name, Date beforeDate, Date afterDate, String CatagoriesName, String ProvinceName, String ProductName, String SupplierName, String Type, int limit, int tranghientai) {
        return postFacade.FindAllPost(Tittle, Name, beforeDate, afterDate, CatagoriesName, ProvinceName, ProductName, SupplierName, Type, limit, tranghientai);
    }

    public int countFindAllPost(String Tittle, String Name, Date beforeDate, Date afterDate, String CatagoriesName, String ProvinceName, String ProductName, String SupplierName, String Type) {
        return postFacade.countFindAllPost(Tittle, Name, beforeDate, afterDate, CatagoriesName, ProvinceName, ProductName, SupplierName, Type);
    }

   
   
    

    
    
    public int count() {
        return postFacade.count();
    }

    
    
}
