/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "productstore")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productstore.findAll", query = "SELECT p FROM Productstore p"),
    @NamedQuery(name = "Productstore.findByIdProductStore", query = "SELECT p FROM Productstore p WHERE p.idProductStore = :idProductStore"),
    @NamedQuery(name = "Productstore.findBySaleProduct", query = "SELECT p FROM Productstore p WHERE p.saleProduct = :saleProduct"),
    @NamedQuery(name = "Productstore.findByCreateDate", query = "SELECT p FROM Productstore p WHERE p.createDate = :createDate")})
public class Productstore implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProductStore")
    private Integer idProductStore;
    @Column(name = "SaleProduct")
    private Integer saleProduct;
    @Column(name = "CreateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID")
    @ManyToOne
    private Productsentity productID;

    public Productstore() {
    }

    public Productstore(Integer idProductStore) {
        this.idProductStore = idProductStore;
    }

    public Integer getIdProductStore() {
        return idProductStore;
    }

    public void setIdProductStore(Integer idProductStore) {
        this.idProductStore = idProductStore;
    }

    public Integer getSaleProduct() {
        return saleProduct;
    }

    public void setSaleProduct(Integer saleProduct) {
        this.saleProduct = saleProduct;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
        hash += (idProductStore != null ? idProductStore.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productstore)) {
            return false;
        }
        Productstore other = (Productstore) object;
        if ((this.idProductStore == null && other.idProductStore != null) || (this.idProductStore != null && !this.idProductStore.equals(other.idProductStore))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Productstore[ idProductStore=" + idProductStore + " ]";
    }
    
}
