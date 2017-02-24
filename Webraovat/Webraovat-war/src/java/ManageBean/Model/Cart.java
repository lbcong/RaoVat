/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean.Model;

import Entity.Post;
import Entity.Productsentity;
import java.util.List;

/**
 *
 * @author Asus
 */
public class Cart {
    private int index;
    private Post postid;
    private Productsentity Productsentity;
    private int soluong;
    private List<Integer> count =null;
    private double TotalPrice;
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Post getPostid() {
        return postid;
    }

    public void setPostid(Post postid) {
        this.postid = postid;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Productsentity getProductsentity() {
        return Productsentity;
    }

    public void setProductsentity(Productsentity Productsentity) {
        this.Productsentity = Productsentity;
    }

    public List<Integer> getCount() {
        return count;
    }

    public void setCount(List<Integer> count) {
        this.count = count;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }
    
    
    
}
