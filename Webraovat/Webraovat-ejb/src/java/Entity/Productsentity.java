/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "productsentity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productsentity.findAll", query = "SELECT p FROM Productsentity p"),
    @NamedQuery(name = "Productsentity.findByProductID", query = "SELECT p FROM Productsentity p WHERE p.productID = :productID"),
    @NamedQuery(name = "Productsentity.findByName", query = "SELECT p FROM Productsentity p WHERE p.name = :name"),
    @NamedQuery(name = "Productsentity.findByDescription", query = "SELECT p FROM Productsentity p WHERE p.description = :description"),
    @NamedQuery(name = "Productsentity.findByPrice", query = "SELECT p FROM Productsentity p WHERE p.price = :price"),
    @NamedQuery(name = "Productsentity.findByMadein", query = "SELECT p FROM Productsentity p WHERE p.madein = :madein"),
    @NamedQuery(name = "Productsentity.findByProductDate", query = "SELECT p FROM Productsentity p WHERE p.productDate = :productDate"),
    @NamedQuery(name = "Productsentity.findByStatus", query = "SELECT p FROM Productsentity p WHERE p.status = :status"),
    @NamedQuery(name = "Productsentity.findByAddInformation", query = "SELECT p FROM Productsentity p WHERE p.addInformation = :addInformation"),
    @NamedQuery(name = "Productsentity.findByManufacturer", query = "SELECT p FROM Productsentity p WHERE p.manufacturer = :manufacturer"),
    @NamedQuery(name = "Productsentity.findByImage", query = "SELECT p FROM Productsentity p WHERE p.image = :image"),
    @NamedQuery(name = "Productsentity.findByVideo", query = "SELECT p FROM Productsentity p WHERE p.video = :video"),
    @NamedQuery(name = "Productsentity.findByTotalProduct", query = "SELECT p FROM Productsentity p WHERE p.totalProduct = :totalProduct"),
    @NamedQuery(name = "Productsentity.findByStroreProduct", query = "SELECT p FROM Productsentity p WHERE p.stroreProduct = :stroreProduct")})
public class Productsentity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ProductID")
    private Integer productID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Name")
    private String name;
    @Size(max = 1000)
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private double price;
    @Size(max = 100)
    @Column(name = "Madein")
    private String madein;
    @Column(name = "ProductDate")
    @Temporal(TemporalType.DATE)
    private Date productDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private boolean status;
    @Size(max = 1000)
    @Column(name = "AddInformation")
    private String addInformation;
    @Size(max = 100)
    @Column(name = "Manufacturer")
    private String manufacturer;
    @Size(max = 1000)
    @Column(name = "Image")
    private String image;
    @Size(max = 1000)
    @Column(name = "Video")
    private String video;
    @Column(name = "TotalProduct")
    private Integer totalProduct;
    @Column(name = "StroreProduct")
    private Integer stroreProduct;
    @OneToMany(mappedBy = "productID")
    private Collection<Productstore> productstoreCollection;
    @OneToMany(mappedBy = "productID")
    private Collection<Orderdetails> orderdetailsCollection;
    @JoinColumn(name = "CategoriesId", referencedColumnName = "CatagoriesId")
    @ManyToOne(optional = false)
    private Catagories categoriesId;
    @JoinColumn(name = "PostId", referencedColumnName = "PostId")
    @OneToOne(optional = false)
    private Post postId;

    public Productsentity() {
    }

    public Productsentity(Integer productID) {
        this.productID = productID;
    }

    public Productsentity(Integer productID, String name, double price, boolean status) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMadein() {
        return madein;
    }

    public void setMadein(String madein) {
        this.madein = madein;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAddInformation() {
        return addInformation;
    }

    public void setAddInformation(String addInformation) {
        this.addInformation = addInformation;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(Integer totalProduct) {
        this.totalProduct = totalProduct;
    }

    public Integer getStroreProduct() {
        return stroreProduct;
    }

    public void setStroreProduct(Integer stroreProduct) {
        this.stroreProduct = stroreProduct;
    }

    @XmlTransient
    public Collection<Productstore> getProductstoreCollection() {
        return productstoreCollection;
    }

    public void setProductstoreCollection(Collection<Productstore> productstoreCollection) {
        this.productstoreCollection = productstoreCollection;
    }

    @XmlTransient
    public Collection<Orderdetails> getOrderdetailsCollection() {
        return orderdetailsCollection;
    }

    public void setOrderdetailsCollection(Collection<Orderdetails> orderdetailsCollection) {
        this.orderdetailsCollection = orderdetailsCollection;
    }

    public Catagories getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Catagories categoriesId) {
        this.categoriesId = categoriesId;
    }

    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productID != null ? productID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productsentity)) {
            return false;
        }
        Productsentity other = (Productsentity) object;
        if ((this.productID == null && other.productID != null) || (this.productID != null && !this.productID.equals(other.productID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Productsentity[ productID=" + productID + " ]";
    }
    
}
