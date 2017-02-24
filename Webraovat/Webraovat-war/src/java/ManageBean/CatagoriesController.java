/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import Entity.Catagories;
import Entity.Productsentity;
import Stub.StubCatagories;
import Stub.StubProduct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Asus
 */
@Named(value = "catagoriesController")
@SessionScoped
public class CatagoriesController implements Serializable {

    private Catagories entity = null;
    private StubCatagories Stub = null;

    public CatagoriesController() {
        entity = new Catagories();
    }

    public List<Catagories> FindAllCatagories() {
        Stub = new StubCatagories();
        return Stub.findAll();
    }

    public Catagories FindCatagoriesByPostID(int id) {
        Stub = new StubCatagories();

      

        try {

            entity = Stub.FindCatagoriesByID(id);
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

    public Catagories getEntity() {
        return entity;
    }

    public void setEntity(Catagories entity) {
        this.entity = entity;
    }

}
