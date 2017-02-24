/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "catagories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catagories.findAll", query = "SELECT c FROM Catagories c"),
    @NamedQuery(name = "Catagories.findByCatagoriesId", query = "SELECT c FROM Catagories c WHERE c.catagoriesId = :catagoriesId"),
    @NamedQuery(name = "Catagories.findByName", query = "SELECT c FROM Catagories c WHERE c.name = :name"),
    @NamedQuery(name = "Catagories.findByDescription", query = "SELECT c FROM Catagories c WHERE c.description = :description"),
    @NamedQuery(name = "Catagories.findByImage", query = "SELECT c FROM Catagories c WHERE c.image = :image")})
public class Catagories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CatagoriesId")
    private Integer catagoriesId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "Name")
    private String name;
    @Size(max = 1000)
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "Image")
    private String image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriesId")
    private Collection<Productsentity> productsentityCollection;

    public Catagories() {
    }

    public Catagories(Integer catagoriesId) {
        this.catagoriesId = catagoriesId;
    }

    public Catagories(Integer catagoriesId, String name, String image) {
        this.catagoriesId = catagoriesId;
        this.name = name;
        this.image = image;
    }

    public Integer getCatagoriesId() {
        return catagoriesId;
    }

    public void setCatagoriesId(Integer catagoriesId) {
        this.catagoriesId = catagoriesId;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlTransient
    public Collection<Productsentity> getProductsentityCollection() {
        return productsentityCollection;
    }

    public void setProductsentityCollection(Collection<Productsentity> productsentityCollection) {
        this.productsentityCollection = productsentityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catagoriesId != null ? catagoriesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catagories)) {
            return false;
        }
        Catagories other = (Catagories) object;
        if ((this.catagoriesId == null && other.catagoriesId != null) || (this.catagoriesId != null && !this.catagoriesId.equals(other.catagoriesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Catagories[ catagoriesId=" + catagoriesId + " ]";
    }
    
}
