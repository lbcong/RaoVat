/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "makeorder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Makeorder.findAll", query = "SELECT m FROM Makeorder m"),
    @NamedQuery(name = "Makeorder.findByIdOrder", query = "SELECT m FROM Makeorder m WHERE m.idOrder = :idOrder"),
    @NamedQuery(name = "Makeorder.findByRequiredDated", query = "SELECT m FROM Makeorder m WHERE m.requiredDated = :requiredDated"),
    @NamedQuery(name = "Makeorder.findByCreateDated", query = "SELECT m FROM Makeorder m WHERE m.createDated = :createDated"),
    @NamedQuery(name = "Makeorder.findByStatus", query = "SELECT m FROM Makeorder m WHERE m.status = :status"),
    @NamedQuery(name = "Makeorder.findByTotalPrice", query = "SELECT m FROM Makeorder m WHERE m.totalPrice = :totalPrice"),
    @NamedQuery(name = "Makeorder.findByAddress", query = "SELECT m FROM Makeorder m WHERE m.address = :address"),
    @NamedQuery(name = "Makeorder.findByPhone", query = "SELECT m FROM Makeorder m WHERE m.phone = :phone"),
    @NamedQuery(name = "Makeorder.findByDescription", query = "SELECT m FROM Makeorder m WHERE m.description = :description"),
    @NamedQuery(name = "Makeorder.findByEmail", query = "SELECT m FROM Makeorder m WHERE m.email = :email")})
public class Makeorder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrder")
    private Integer idOrder;
    @Column(name = "RequiredDated")
    @Temporal(TemporalType.DATE)
    private Date requiredDated;
    @Column(name = "CreateDated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDated;
    @Size(max = 45)
    @Column(name = "Status")
    private String status;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TotalPrice")
    private Double totalPrice;
    @Size(max = 500)
    @Column(name = "Address")
    private String address;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "Phone")
    private String phone;
    @Size(max = 2000)
    @Column(name = "Description")
    private String description;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "Email")
    private String email;
    @OneToMany(mappedBy = "orderID" , fetch = FetchType.EAGER)
    private List<Orderdetails> orderdetailsCollection;
    @JoinColumn(name = "MemberID", referencedColumnName = "MemberId")
    @ManyToOne
    private Members memberID;

    public Makeorder() {
    }

    public Makeorder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Date getRequiredDated() {
        return requiredDated;
    }

    public void setRequiredDated(Date requiredDated) {
        this.requiredDated = requiredDated;
    }

    public Date getCreateDated() {
        return createDated;
    }

    public void setCreateDated(Date createDated) {
        this.createDated = createDated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<Orderdetails> getOrderdetailsCollection() {
        return orderdetailsCollection;
    }

    public void setOrderdetailsCollection(List<Orderdetails> orderdetailsCollection) {
        this.orderdetailsCollection = orderdetailsCollection;
    }

    public Members getMemberID() {
        return memberID;
    }

    public void setMemberID(Members memberID) {
        this.memberID = memberID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrder != null ? idOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Makeorder)) {
            return false;
        }
        Makeorder other = (Makeorder) object;
        if ((this.idOrder == null && other.idOrder != null) || (this.idOrder != null && !this.idOrder.equals(other.idOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Makeorder[ idOrder=" + idOrder + " ]";
    }
    
}
