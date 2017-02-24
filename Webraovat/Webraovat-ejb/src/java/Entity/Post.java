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
import javax.persistence.CascadeType;
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
@Table(name = "post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"),
    @NamedQuery(name = "Post.findByPostId", query = "SELECT p FROM Post p WHERE p.postId = :postId"),
    @NamedQuery(name = "Post.findByTitle", query = "SELECT p FROM Post p WHERE p.title = :title"),
    @NamedQuery(name = "Post.findByDescription", query = "SELECT p FROM Post p WHERE p.description = :description"),
    @NamedQuery(name = "Post.findByNumberOfReport", query = "SELECT p FROM Post p WHERE p.numberOfReport = :numberOfReport"),
    @NamedQuery(name = "Post.findByTypeOfPost", query = "SELECT p FROM Post p WHERE p.typeOfPost = :typeOfPost"),
    @NamedQuery(name = "Post.findByPostDate", query = "SELECT p FROM Post p WHERE p.postDate = :postDate"),
    @NamedQuery(name = "Post.findByExperiDate", query = "SELECT p FROM Post p WHERE p.experiDate = :experiDate"),
    @NamedQuery(name = "Post.findByIsCheck", query = "SELECT p FROM Post p WHERE p.isCheck = :isCheck")})
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PostId")
    private Integer postId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "Title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "Description")
    private String description;
    @Column(name = "NumberOfReport")
    private Integer numberOfReport;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TypeOfPost")
    private boolean typeOfPost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PostDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ExperiDate")
    @Temporal(TemporalType.DATE)
    private Date experiDate;
    @Column(name = "IsCheck")
    private Boolean isCheck;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "postID")
    private Banlistpost banlistpost;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postID")
    private Collection<Postchecked> postcheckedCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idofPost")
    private Collection<Bookmark> bookmarkCollection;
    @JoinColumn(name = "MemberId", referencedColumnName = "MemberId")
    @ManyToOne(optional = false)
    private Members memberId;
    @JoinColumn(name = "ProvinceId", referencedColumnName = "provinceid")
    @ManyToOne(optional = false)
    private Province provinceId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "postID")
    private Postdenied postdenied;
    @OneToMany(mappedBy = "postId")
    private Collection<Report> reportCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "postId")
    private Productsentity productsentity;

    public Post() {
    }

    public Post(Integer postId) {
        this.postId = postId;
    }

    public Post(Integer postId, String title, String description, boolean typeOfPost, Date postDate, Date experiDate) {
        this.postId = postId;
        this.title = title;
        this.description = description;
        this.typeOfPost = typeOfPost;
        this.postDate = postDate;
        this.experiDate = experiDate;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfReport() {
        return numberOfReport;
    }

    public void setNumberOfReport(Integer numberOfReport) {
        this.numberOfReport = numberOfReport;
    }

    public boolean getTypeOfPost() {
        return typeOfPost;
    }

    public void setTypeOfPost(boolean typeOfPost) {
        this.typeOfPost = typeOfPost;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getExperiDate() {
        return experiDate;
    }

    public void setExperiDate(Date experiDate) {
        this.experiDate = experiDate;
    }

    public Boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Boolean isCheck) {
        this.isCheck = isCheck;
    }

    public Banlistpost getBanlistpost() {
        return banlistpost;
    }

    public void setBanlistpost(Banlistpost banlistpost) {
        this.banlistpost = banlistpost;
    }

    @XmlTransient
    public Collection<Postchecked> getPostcheckedCollection() {
        return postcheckedCollection;
    }

    public void setPostcheckedCollection(Collection<Postchecked> postcheckedCollection) {
        this.postcheckedCollection = postcheckedCollection;
    }

    @XmlTransient
    public Collection<Bookmark> getBookmarkCollection() {
        return bookmarkCollection;
    }

    public void setBookmarkCollection(Collection<Bookmark> bookmarkCollection) {
        this.bookmarkCollection = bookmarkCollection;
    }

    public Members getMemberId() {
        return memberId;
    }

    public void setMemberId(Members memberId) {
        this.memberId = memberId;
    }

    public Province getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Province provinceId) {
        this.provinceId = provinceId;
    }

    public Postdenied getPostdenied() {
        return postdenied;
    }

    public void setPostdenied(Postdenied postdenied) {
        this.postdenied = postdenied;
    }

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    public Productsentity getProductsentity() {
        return productsentity;
    }

    public void setProductsentity(Productsentity productsentity) {
        this.productsentity = productsentity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postId != null ? postId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.postId == null && other.postId != null) || (this.postId != null && !this.postId.equals(other.postId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Post[ postId=" + postId + " ]";
    }
    
}
