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
@Table(name = "members")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Members.findAll", query = "SELECT m FROM Members m"),
    @NamedQuery(name = "Members.findByMemberId", query = "SELECT m FROM Members m WHERE m.memberId = :memberId"),
    @NamedQuery(name = "Members.findByUsername", query = "SELECT m FROM Members m WHERE m.username = :username"),
    @NamedQuery(name = "Members.findByPassword", query = "SELECT m FROM Members m WHERE m.password = :password"),
    @NamedQuery(name = "Members.findByName", query = "SELECT m FROM Members m WHERE m.name = :name"),
    @NamedQuery(name = "Members.findByEmail", query = "SELECT m FROM Members m WHERE m.email = :email"),
    @NamedQuery(name = "Members.findByDateOfBirh", query = "SELECT m FROM Members m WHERE m.dateOfBirh = :dateOfBirh"),
    @NamedQuery(name = "Members.findByPhone", query = "SELECT m FROM Members m WHERE m.phone = :phone"),
    @NamedQuery(name = "Members.findByGmail", query = "SELECT m FROM Members m WHERE m.gmail = :gmail"),
    @NamedQuery(name = "Members.findByFacebook", query = "SELECT m FROM Members m WHERE m.facebook = :facebook"),
    @NamedQuery(name = "Members.findByGender", query = "SELECT m FROM Members m WHERE m.gender = :gender"),
    @NamedQuery(name = "Members.findByStatus", query = "SELECT m FROM Members m WHERE m.status = :status"),
    @NamedQuery(name = "Members.findByDateCreated", query = "SELECT m FROM Members m WHERE m.dateCreated = :dateCreated"),
    @NamedQuery(name = "Members.findByLastAcceptDate", query = "SELECT m FROM Members m WHERE m.lastAcceptDate = :lastAcceptDate"),
    @NamedQuery(name = "Members.findByIsLockOut", query = "SELECT m FROM Members m WHERE m.isLockOut = :isLockOut"),
    @NamedQuery(name = "Members.findByDetailsAddress", query = "SELECT m FROM Members m WHERE m.detailsAddress = :detailsAddress"),
    @NamedQuery(name = "Members.findByActivityCode", query = "SELECT m FROM Members m WHERE m.activityCode = :activityCode"),
    @NamedQuery(name = "Members.findByIsActivity", query = "SELECT m FROM Members m WHERE m.isActivity = :isActivity"),
    @NamedQuery(name = "Members.findByForgetPasswordCode", query = "SELECT m FROM Members m WHERE m.forgetPasswordCode = :forgetPasswordCode"),
    @NamedQuery(name = "Members.findByAvatar", query = "SELECT m FROM Members m WHERE m.avatar = :avatar")})
public class Members implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MemberId")
    private Integer memberId;
    @Size(max = 50)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Password")
    private String password;
    @Size(max = 100)
    @Column(name = "Name")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Email")
    private String email;
    @Column(name = "DateOfBirh")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirh;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Phone")
    private String phone;
    @Size(max = 100)
    @Column(name = "Gmail")
    private String gmail;
    @Size(max = 100)
    @Column(name = "Facebook")
    private String facebook;
    @Size(max = 3)
    @Column(name = "Gender")
    private String gender;
    @Size(max = 45)
    @Column(name = "Status")
    private String status;
    @Column(name = "DateCreated")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Column(name = "LastAcceptDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAcceptDate;
    @Column(name = "IsLockOut")
    private Boolean isLockOut;
    @Size(max = 1000)
    @Column(name = "DetailsAddress")
    private String detailsAddress;
    @Column(name = "ActivityCode")
    private Integer activityCode;
    @Column(name = "IsActivity")
    private Boolean isActivity;
    @Column(name = "forgetPasswordCode")
    private Integer forgetPasswordCode;
    @Size(max = 400)
    @Column(name = "avatar")
    private String avatar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Rolesmanager> rolesmanagerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberID")
    private Collection<Banlistpost> banlistpostCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberID")
    private Collection<Postchecked> postcheckedCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    private Collection<Bookmark> bookmarkCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "memberID")
    private Banlistuser banlistuser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    private Collection<Post> postCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberID")
    private Collection<Postdenied> postdeniedCollection;
    @JoinColumn(name = "ProvinceId", referencedColumnName = "provinceid")
    @ManyToOne
    private Province provinceId;
    @OneToOne(mappedBy = "memberID")
    private Supplier supplier;
    @OneToMany(mappedBy = "memberId")
    private Collection<Report> reportCollection;
    @OneToMany(mappedBy = "memberID")
    private List<Makeorder> makeorderCollection;

    public Members() {
    }

    public Members(Integer memberId) {
        this.memberId = memberId;
    }

    public Members(Integer memberId, String password, String email, String phone) {
        this.memberId = memberId;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirh() {
        return dateOfBirh;
    }

    public void setDateOfBirh(Date dateOfBirh) {
        this.dateOfBirh = dateOfBirh;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastAcceptDate() {
        return lastAcceptDate;
    }

    public void setLastAcceptDate(Date lastAcceptDate) {
        this.lastAcceptDate = lastAcceptDate;
    }

    public Boolean getIsLockOut() {
        return isLockOut;
    }

    public void setIsLockOut(Boolean isLockOut) {
        this.isLockOut = isLockOut;
    }

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }

    public Integer getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(Integer activityCode) {
        this.activityCode = activityCode;
    }

    public Boolean getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(Boolean isActivity) {
        this.isActivity = isActivity;
    }

    public Integer getForgetPasswordCode() {
        return forgetPasswordCode;
    }

    public void setForgetPasswordCode(Integer forgetPasswordCode) {
        this.forgetPasswordCode = forgetPasswordCode;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @XmlTransient
    public Collection<Rolesmanager> getRolesmanagerCollection() {
        return rolesmanagerCollection;
    }

    public void setRolesmanagerCollection(Collection<Rolesmanager> rolesmanagerCollection) {
        this.rolesmanagerCollection = rolesmanagerCollection;
    }

    @XmlTransient
    public Collection<Banlistpost> getBanlistpostCollection() {
        return banlistpostCollection;
    }

    public void setBanlistpostCollection(Collection<Banlistpost> banlistpostCollection) {
        this.banlistpostCollection = banlistpostCollection;
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

    public Banlistuser getBanlistuser() {
        return banlistuser;
    }

    public void setBanlistuser(Banlistuser banlistuser) {
        this.banlistuser = banlistuser;
    }

    @XmlTransient
    public Collection<Post> getPostCollection() {
        return postCollection;
    }

    public void setPostCollection(Collection<Post> postCollection) {
        this.postCollection = postCollection;
    }

    @XmlTransient
    public Collection<Postdenied> getPostdeniedCollection() {
        return postdeniedCollection;
    }

    public void setPostdeniedCollection(Collection<Postdenied> postdeniedCollection) {
        this.postdeniedCollection = postdeniedCollection;
    }

    public Province getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Province provinceId) {
        this.provinceId = provinceId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    @XmlTransient
    public List<Makeorder> getMakeorderCollection() {
        return makeorderCollection;
    }

    public void setMakeorderCollection(List<Makeorder> makeorderCollection) {
        this.makeorderCollection = makeorderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberId != null ? memberId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Members)) {
            return false;
        }
        Members other = (Members) object;
        if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Members[ memberId=" + memberId + " ]";
    }
    
}
