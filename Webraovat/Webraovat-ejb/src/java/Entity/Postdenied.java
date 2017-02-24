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
@Table(name = "postdenied")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Postdenied.findAll", query = "SELECT p FROM Postdenied p"),
    @NamedQuery(name = "Postdenied.findByIdpostdenied", query = "SELECT p FROM Postdenied p WHERE p.idpostdenied = :idpostdenied"),
    @NamedQuery(name = "Postdenied.findByReason", query = "SELECT p FROM Postdenied p WHERE p.reason = :reason"),
    @NamedQuery(name = "Postdenied.findByDateDenied", query = "SELECT p FROM Postdenied p WHERE p.dateDenied = :dateDenied")})
public class Postdenied implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpostdenied")
    private Integer idpostdenied;
    @Size(max = 100)
    @Column(name = "Reason")
    private String reason;
    @Column(name = "DateDenied")
    @Temporal(TemporalType.DATE)
    private Date dateDenied;
    @JoinColumn(name = "PostID", referencedColumnName = "PostId")
    @OneToOne(optional = false)
    private Post postID;
    @JoinColumn(name = "MemberID", referencedColumnName = "MemberId")
    @ManyToOne(optional = false)
    private Members memberID;

    public Postdenied() {
    }

    public Postdenied(Integer idpostdenied) {
        this.idpostdenied = idpostdenied;
    }

    public Integer getIdpostdenied() {
        return idpostdenied;
    }

    public void setIdpostdenied(Integer idpostdenied) {
        this.idpostdenied = idpostdenied;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getDateDenied() {
        return dateDenied;
    }

    public void setDateDenied(Date dateDenied) {
        this.dateDenied = dateDenied;
    }

    public Post getPostID() {
        return postID;
    }

    public void setPostID(Post postID) {
        this.postID = postID;
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
        hash += (idpostdenied != null ? idpostdenied.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Postdenied)) {
            return false;
        }
        Postdenied other = (Postdenied) object;
        if ((this.idpostdenied == null && other.idpostdenied != null) || (this.idpostdenied != null && !this.idpostdenied.equals(other.idpostdenied))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Postdenied[ idpostdenied=" + idpostdenied + " ]";
    }
    
}
