/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import Entity.Manager;
import Entity.Members;
import Entity.Post;
import Entity.Province;
import Stub.StubProvine;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Asus
 */
@Named(value = "provineController")
@SessionScoped
public class ProvineController implements Serializable {

    public ProvineController() {
        entity = new Province();
    }

    private Province entity = null;
    private StubProvine Stub = null;

    public List<Province> FindAllCity() {
        Stub = new StubProvine();
        return Stub.findAll();
    }

    public Province FindProvinceByID(int id) {

        Stub = new StubProvine();

       

        try {

            entity = Stub.FindProvinceByID(id);
            if (entity == null) {
                System.out.println("entity null");
            }
            if (entity != null) {

                return entity;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public String test() {

        //System.out.println(entity.getProvinceid());
        entity.setProvinceid(101);
        entity.setName("bbbbb");
        entity.setType("bbbbb");
        Stub = new StubProvine();
        Stub.edit(entity);
        return "trangloi";
    }

    public Province getEntity() {
        return entity;
    }

    public void setEntity(Province entity) {
        this.entity = entity;
    }

    public StubProvine getStub() {
        return Stub;
    }

    public void setStub(StubProvine Stub) {
        this.Stub = Stub;
    }

    public Integer getProvinceid() {
        return entity.getProvinceid();
    }

    public void setProvinceid(Integer provinceid) {
        entity.setProvinceid(provinceid);
    }

    public String getName() {
        return entity.getName();
    }

    public void setName(String name) {
        entity.setName(name);
    }

    public String getType() {
        return entity.getType();
    }

    public void setType(String type) {
        entity.setType(type);
    }

    public Collection<Manager> getManagerCollection() {
        return entity.getManagerCollection();
    }

    public void setManagerCollection(Collection<Manager> managerCollection) {
        entity.setManagerCollection(managerCollection);
    }

    public Collection<Post> getPostCollection() {
        return entity.getPostCollection();
    }

    public void setPostCollection(Collection<Post> postCollection) {
        entity.setPostCollection(postCollection);
    }

    public Collection<Members> getMembersCollection() {
        return entity.getMembersCollection();
    }

    public void setMembersCollection(Collection<Members> membersCollection) {
        entity.setMembersCollection(membersCollection);
    }

    public int hashCode() {
        return entity.hashCode();
    }

    public boolean equals(Object object) {
        return entity.equals(object);
    }

    public String toString() {
        return entity.toString();
    }

}
