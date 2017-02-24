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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "supplier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s"),
    @NamedQuery(name = "Supplier.findByIdsupplier", query = "SELECT s FROM Supplier s WHERE s.idsupplier = :idsupplier"),
    @NamedQuery(name = "Supplier.findByCompany", query = "SELECT s FROM Supplier s WHERE s.company = :company")})
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsupplier")
    private Integer idsupplier;
    @Size(max = 45)
    @Column(name = "Company")
    private String company;
    @JoinColumn(name = "MemberID", referencedColumnName = "MemberId")
    @OneToOne
    private Members memberID;

    public Supplier() {
    }

    public Supplier(Integer idsupplier) {
        this.idsupplier = idsupplier;
    }

    public Integer getIdsupplier() {
        return idsupplier;
    }

    public void setIdsupplier(Integer idsupplier) {
        this.idsupplier = idsupplier;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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
        hash += (idsupplier != null ? idsupplier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.idsupplier == null && other.idsupplier != null) || (this.idsupplier != null && !this.idsupplier.equals(other.idsupplier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Supplier[ idsupplier=" + idsupplier + " ]";
    }
    
}
