/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import Entity.Orderdetails;
import SessionBean.OrderdetailsFacadeLocal;
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
public class Stuborderdetail {

    OrderdetailsFacadeLocal orderdetailsFacade = lookupOrderdetailsFacadeLocal();

    private OrderdetailsFacadeLocal lookupOrderdetailsFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (OrderdetailsFacadeLocal) c.lookup("java:global/Webraovat/Webraovat-ejb/OrderdetailsFacade!SessionBean.OrderdetailsFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void create(Orderdetails orderdetails) {
        orderdetailsFacade.create(orderdetails);
    }

    public void edit(Orderdetails orderdetails) {
        orderdetailsFacade.edit(orderdetails);
    }

    public void remove(Orderdetails orderdetails) {
        orderdetailsFacade.remove(orderdetails);
    }

    public Orderdetails find(Object id) {
        return orderdetailsFacade.find(id);
    }

    public List<Orderdetails> findAll() {
        return orderdetailsFacade.findAll();
    }

    public List<Orderdetails> findRange(int[] range) {
        return orderdetailsFacade.findRange(range);
    }

    public List<Orderdetails> FindAllOrderdetailsByID(int makeorderID) {
        return orderdetailsFacade.FindAllOrderdetailsByID(makeorderID);
    }

    
    
    public int count() {
        return orderdetailsFacade.count();
    }
    
    
    
}
