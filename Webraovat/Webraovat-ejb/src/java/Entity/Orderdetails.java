/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "orderdetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderdetails.findAll", query = "SELECT o FROM Orderdetails o"),
    @NamedQuery(name = "Orderdetails.findByIdorderdetails", query = "SELECT o FROM Orderdetails o WHERE o.idorderdetails = :idorderdetails"),
    @NamedQuery(name = "Orderdetails.findByNumberofproduct", query = "SELECT o FROM Orderdetails o WHERE o.numberofproduct = :numberofproduct")})
public class Orderdetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idorderdetails")
    private Integer idorderdetails;
    @Column(name = "numberofproduct")
    private Integer numberofproduct;
    @JoinColumn(name = "orderID", referencedColumnName = "idOrder")
    @ManyToOne
    private Makeorder orderID;
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID")
    @ManyToOne
    private Productsentity productID;

    public Orderdetails() {
    }

    public Orderdetails(Integer idorderdetails) {
        this.idorderdetails = idorderdetails;
    }

    public Integer getIdorderdetails() {
        return idorderdetails;
    }

    public void setIdorderdetails(Integer idorderdetails) {
        this.idorderdetails = idorderdetails;
    }

    public Integer getNumberofproduct() {
        return numberofproduct;
    }

    public void setNumberofproduct(Integer numberofproduct) {
        this.numberofproduct = numberofproduct;
    }

    public Makeorder getOrderID() {
        return orderID;
    }

    public void setOrderID(Makeorder orderID) {
        this.orderID = orderID;
    }

    public Productsentity getProductID() {
        return productID;
    }

    public void setProductID(Productsentity productID) {
        this.productID = productID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idorderdetails != null ? idorderdetails.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderdetails)) {
            return false;
        }
        Orderdetails other = (Orderdetails) object;
        if ((this.idorderdetails == null && other.idorderdetails != null) || (this.idorderdetails != null && !this.idorderdetails.equals(other.idorderdetails))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Orderdetails[ idorderdetails=" + idorderdetails + " ]";
    }
    
}
