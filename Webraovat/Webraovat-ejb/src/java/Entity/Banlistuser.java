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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "banlistuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banlistuser.findAll", query = "SELECT b FROM Banlistuser b"),
    @NamedQuery(name = "Banlistuser.findByBanID", query = "SELECT b FROM Banlistuser b WHERE b.banID = :banID"),
    @NamedQuery(name = "Banlistuser.findByReason", query = "SELECT b FROM Banlistuser b WHERE b.reason = :reason"),
    @NamedQuery(name = "Banlistuser.findByDateBaned", query = "SELECT b FROM Banlistuser b WHERE b.dateBaned = :dateBaned")})
public class Banlistuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BanID")
    private Integer banID;
    @Size(max = 100)
    @Column(name = "Reason")
    private String reason;
    @Column(name = "DateBaned")
    @Temporal(TemporalType.DATE)
    private Date dateBaned;
    @JoinColumn(name = "MemberID", referencedColumnName = "MemberId")
    @OneToOne(optional = false)
    private Members memberID;

    public Banlistuser() {
    }

    public Banlistuser(Integer banID) {
        this.banID = banID;
    }

    public Integer getBanID() {
        return banID;
    }

    public void setBanID(Integer banID) {
        this.banID = banID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getDateBaned() {
        return dateBaned;
    }

    public void setDateBaned(Date dateBaned) {
        this.dateBaned = dateBaned;
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
        hash += (banID != null ? banID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banlistuser)) {
            return false;
        }
        Banlistuser other = (Banlistuser) object;
        if ((this.banID == null && other.banID != null) || (this.banID != null && !this.banID.equals(other.banID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Banlistuser[ banID=" + banID + " ]";
    }
    
}
