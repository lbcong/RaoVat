/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import Entity.Banlistpost;
import Stub.StubBanlistPost;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
@Named(value = "banlistPostController")
@SessionScoped
public class BanlistPostController implements Serializable {

    private Banlistpost entity = null;
    private StubBanlistPost Stub = null;
    private List<Banlistpost> list =null;
    private String TypeTable="";
    /**
     * Creates a new instance of BanlistPostController
     */
    public BanlistPostController() {
        entity = new Banlistpost();
    }

    

    public void create(Banlistpost banlistpost) {
        Stub.create(banlistpost);
    }

    public void edit(Banlistpost banlistpost) {
        Stub.edit(banlistpost);
    }

    public void remove(Banlistpost banlistpost) {
        Stub.remove(banlistpost);
    }

    public Banlistpost find(Object id) {
        return Stub.find(id);
    }

    public List<Banlistpost> findAll() {
        return Stub.findAll();
    }

    public List<Banlistpost> findRange(int[] range) {
        return Stub.findRange(range);
    }

    public Banlistpost FindAllPostBanedByID(int postID) {
        return Stub.FindAllPostBanedByID(postID);
    }

    public int count() {
        return Stub.count();
    }

    public Banlistpost getEntity() {
        return entity;
    }

    public void setEntity(Banlistpost entity) {
        this.entity = entity;
    }

    public List<Banlistpost> getList() {
        return list;
        
    }

    public void setList(List<Banlistpost> list) {
        this.list = list;
    }

    public String getTypeTable() {
        return TypeTable;
    }

    public void setTypeTable(String TypeTable) {
        this.TypeTable = TypeTable;
    }
    
    
}
