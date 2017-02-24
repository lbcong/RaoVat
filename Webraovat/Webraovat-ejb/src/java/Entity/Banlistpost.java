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
@Table(name = "banlistpost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banlistpost.findAll", query = "SELECT b FROM Banlistpost b"),
    @NamedQuery(name = "Banlistpost.findByBanListPostID", query = "SELECT b FROM Banlistpost b WHERE b.banListPostID = :banListPostID"),
    @NamedQuery(name = "Banlistpost.findByReason", query = "SELECT b FROM Banlistpost b WHERE b.reason = :reason"),
    @NamedQuery(name = "Banlistpost.findByDateBaned", query = "SELECT b FROM Banlistpost b WHERE b.dateBaned = :dateBaned")})
public class Banlistpost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BanListPostID")
    private Integer banListPostID;
    @Size(max = 100)
    @Column(name = "Reason")
    private String reason;
    @Column(name = "DateBaned")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBaned;
    @JoinColumn(name = "MemberID", referencedColumnName = "MemberId")
    @ManyToOne(optional = false)
    private Members memberID;
    @JoinColumn(name = "PostID", referencedColumnName = "PostId")
    @OneToOne(optional = false)
    private Post postID;

    public Banlistpost() {
    }

    public Banlistpost(Integer banListPostID) {
        this.banListPostID = banListPostID;
    }

    public Integer getBanListPostID() {
        return banListPostID;
    }

    public void setBanListPostID(Integer banListPostID) {
        this.banListPostID = banListPostID;
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

    public Post getPostID() {
        return postID;
    }

    public void setPostID(Post postID) {
        this.postID = postID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (banListPostID != null ? banListPostID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banlistpost)) {
            return false;
        }
        Banlistpost other = (Banlistpost) object;
        if ((this.banListPostID == null && other.banListPostID != null) || (this.banListPostID != null && !this.banListPostID.equals(other.banListPostID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Banlistpost[ banListPostID=" + banListPostID + " ]";
    }
    
}
