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
@Table(name = "bookmark")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bookmark.findAll", query = "SELECT b FROM Bookmark b"),
    @NamedQuery(name = "Bookmark.findByBookMarkId", query = "SELECT b FROM Bookmark b WHERE b.bookMarkId = :bookMarkId")})
public class Bookmark implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BookMarkId")
    private Integer bookMarkId;
    @JoinColumn(name = "MemberId", referencedColumnName = "MemberId")
    @ManyToOne(optional = false)
    private Members memberId;
    @JoinColumn(name = "IdofPost", referencedColumnName = "PostId")
    @ManyToOne(optional = false)
    private Post idofPost;

    public Bookmark() {
    }

    public Bookmark(Integer bookMarkId) {
        this.bookMarkId = bookMarkId;
    }

    public Integer getBookMarkId() {
        return bookMarkId;
    }

    public void setBookMarkId(Integer bookMarkId) {
        this.bookMarkId = bookMarkId;
    }

    public Members getMemberId() {
        return memberId;
    }

    public void setMemberId(Members memberId) {
        this.memberId = memberId;
    }

    public Post getIdofPost() {
        return idofPost;
    }

    public void setIdofPost(Post idofPost) {
        this.idofPost = idofPost;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookMarkId != null ? bookMarkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bookmark)) {
            return false;
        }
        Bookmark other = (Bookmark) object;
        if ((this.bookMarkId == null && other.bookMarkId != null) || (this.bookMarkId != null && !this.bookMarkId.equals(other.bookMarkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Bookmark[ bookMarkId=" + bookMarkId + " ]";
    }
    
}
