/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.Post;
import Entity.Province;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface PostFacadeLocal {

    void create(Post post);

    void edit(Post post);

    void remove(Post post);

    Post find(Object id);

    public List<Post> FindPostByCity(String name);

    public List<Post> FindPostByIDCity(int id);

    public List<Post> FindPostByCatagoriesName(String name);

    public List<Post> FindPostByCatagoriesAndCity(String catagories, String city);

    public List<Post> FindPostByTittleAndCity(String tittle, String city);

    public List<Post> FindPostByTittleAndCityAndCatagories(String tittle, String city, String catagories);

    public List<Post> FindPostByMember(int id);

    public List<Post> FindAllPostByReport(int report);

    public List<Post> FindAllPostByCheck(int check);

    public List<Post> FindAllPostExperiDate();

    public List<Post> FindAllPostByPage(int limit, int tranghientai);
    
    public List<Post> FindExpPostByPage(int limit, int tranghientai) ;
    
    public List<Post> FindNoCheckPostByPage(int limit, int tranghientai);

    public Post FindPostByID(int id);

    public List<Post> FindAllPost(String Tittle, String CatagoriesName, String ProvinceName, String Type, int limit, int tranghientai);
    
    public int countFindAllPost(String Tittle, String CatagoriesName, String ProvinceName, String Type);
    
    public List<Post> findAll();

    public List<Post> findRange(int[] range);

    
    
    int count();

}
