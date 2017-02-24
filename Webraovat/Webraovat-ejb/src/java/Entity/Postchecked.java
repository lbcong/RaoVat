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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "postchecked")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Postchecked.findAll", query = "SELECT p FROM Postchecked p"),
    @NamedQuery(name = "Postchecked.findByIdpostchecked", query = "SELECT p FROM Postchecked p WHERE p.idpostchecked = :idpostchecked"),
    @NamedQuery(name = "Postchecked.findByCheckedDate", query = "SELECT p FROM Postchecked p WHERE p.checkedDate = :checkedDate")})
public class Postchecked implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpostchecked")
    private Integer idpostchecked;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CheckedDate")
    @Temporal(TemporalType.DATE)
    private Date checkedDate;
    @JoinColumn(name = "memberID", referencedColumnName = "MemberId")
    @ManyToOne(optional = false)
    private Members memberID;
    @JoinColumn(name = "postID", referencedColumnName = "PostId")
    @ManyToOne(optional = false)
    private Post postID;

    public Postchecked() {
    }

    public Postchecked(Integer idpostchecked) {
        this.idpostchecked = idpostchecked;
    }

    public Postchecked(Integer idpostchecked, Date checkedDate) {
        this.idpostchecked = idpostchecked;
        this.checkedDate = checkedDate;
    }

    public Integer getIdpostchecked() {
        return idpostchecked;
    }

    public void setIdpostchecked(Integer idpostchecked) {
        this.idpostchecked = idpostchecked;
    }

    public Date getCheckedDate() {
        return checkedDate;
    }

    public void setCheckedDate(Date checkedDate) {
        this.checkedDate = checkedDate;
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
        hash += (idpostchecked != null ? idpostchecked.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Postchecked)) {
            return false;
        }
        Postchecked other = (Postchecked) object;
        if ((this.idpostchecked == null && other.idpostchecked != null) || (this.idpostchecked != null && !this.idpostchecked.equals(other.idpostchecked))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Postchecked[ idpostchecked=" + idpostchecked + " ]";
    }
    
}
