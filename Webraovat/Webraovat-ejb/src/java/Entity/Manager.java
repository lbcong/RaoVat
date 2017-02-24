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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "manager")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Manager.findAll", query = "SELECT m FROM Manager m"),
    @NamedQuery(name = "Manager.findByManagerId", query = "SELECT m FROM Manager m WHERE m.managerId = :managerId"),
    @NamedQuery(name = "Manager.findByUsername", query = "SELECT m FROM Manager m WHERE m.username = :username"),
    @NamedQuery(name = "Manager.findByPassword", query = "SELECT m FROM Manager m WHERE m.password = :password"),
    @NamedQuery(name = "Manager.findByName", query = "SELECT m FROM Manager m WHERE m.name = :name"),
    @NamedQuery(name = "Manager.findByDateOfBirth", query = "SELECT m FROM Manager m WHERE m.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Manager.findByGender", query = "SELECT m FROM Manager m WHERE m.gender = :gender"),
    @NamedQuery(name = "Manager.findByPhone", query = "SELECT m FROM Manager m WHERE m.phone = :phone"),
    @NamedQuery(name = "Manager.findByEmail", query = "SELECT m FROM Manager m WHERE m.email = :email"),
    @NamedQuery(name = "Manager.findByGmail", query = "SELECT m FROM Manager m WHERE m.gmail = :gmail"),
    @NamedQuery(name = "Manager.findByFacebook", query = "SELECT m FROM Manager m WHERE m.facebook = :facebook"),
    @NamedQuery(name = "Manager.findByStatus", query = "SELECT m FROM Manager m WHERE m.status = :status"),
    @NamedQuery(name = "Manager.findByDayCreate", query = "SELECT m FROM Manager m WHERE m.dayCreate = :dayCreate"),
    @NamedQuery(name = "Manager.findByLastAceptDate", query = "SELECT m FROM Manager m WHERE m.lastAceptDate = :lastAceptDate"),
    @NamedQuery(name = "Manager.findByIsLockOut", query = "SELECT m FROM Manager m WHERE m.isLockOut = :isLockOut")})
public class Manager implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ManagerId")
    private Integer managerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Name")
    private String name;
    @Column(name = "DateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Size(max = 3)
    @Column(name = "Gender")
    private String gender;
    @Column(name = "Phone")
    private Integer phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "Email")
    private String email;
    @Size(max = 100)
    @Column(name = "Gmail")
    private String gmail;
    @Size(max = 100)
    @Column(name = "Facebook")
    private String facebook;
    @Size(max = 45)
    @Column(name = "Status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DayCreate")
    @Temporal(TemporalType.DATE)
    private Date dayCreate;
    @Column(name = "LastAceptDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAceptDate;
    @Column(name = "IsLockOut")
    private Boolean isLockOut;
    @JoinColumn(name = "ProvinceId", referencedColumnName = "provinceid")
    @ManyToOne
    private Province provinceId;

    public Manager() {
    }

    public Manager(Integer managerId) {
        this.managerId = managerId;
    }

    public Manager(Integer managerId, String username, String password, String name, Date dayCreate) {
        this.managerId = managerId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.dayCreate = dayCreate;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDayCreate() {
        return dayCreate;
    }

    public void setDayCreate(Date dayCreate) {
        this.dayCreate = dayCreate;
    }

    public Date getLastAceptDate() {
        return lastAceptDate;
    }

    public void setLastAceptDate(Date lastAceptDate) {
        this.lastAceptDate = lastAceptDate;
    }

    public Boolean getIsLockOut() {
        return isLockOut;
    }

    public void setIsLockOut(Boolean isLockOut) {
        this.isLockOut = isLockOut;
    }

    public Province getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Province provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (managerId != null ? managerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Manager)) {
            return false;
        }
        Manager other = (Manager) object;
        if ((this.managerId == null && other.managerId != null) || (this.managerId != null && !this.managerId.equals(other.managerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Manager[ managerId=" + managerId + " ]";
    }
    
}
