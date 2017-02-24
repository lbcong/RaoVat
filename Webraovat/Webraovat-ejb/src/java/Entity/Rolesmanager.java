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
@Table(name = "rolesmanager")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rolesmanager.findAll", query = "SELECT r FROM Rolesmanager r"),
    @NamedQuery(name = "Rolesmanager.findByRoleManagerID", query = "SELECT r FROM Rolesmanager r WHERE r.roleManagerID = :roleManagerID")})
public class Rolesmanager implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RoleManagerID")
    private Integer roleManagerID;
    @JoinColumn(name = "UserID", referencedColumnName = "MemberId")
    @ManyToOne(optional = false)
    private Members userID;
    @JoinColumn(name = "RoleID", referencedColumnName = "RoleID")
    @ManyToOne(optional = false)
    private Roles roleID;

    public Rolesmanager() {
    }

    public Rolesmanager(Integer roleManagerID) {
        this.roleManagerID = roleManagerID;
    }

    public Integer getRoleManagerID() {
        return roleManagerID;
    }

    public void setRoleManagerID(Integer roleManagerID) {
        this.roleManagerID = roleManagerID;
    }

    public Members getUserID() {
        return userID;
    }

    public void setUserID(Members userID) {
        this.userID = userID;
    }

    public Roles getRoleID() {
        return roleID;
    }

    public void setRoleID(Roles roleID) {
        this.roleID = roleID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleManagerID != null ? roleManagerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rolesmanager)) {
            return false;
        }
        Rolesmanager other = (Rolesmanager) object;
        if ((this.roleManagerID == null && other.roleManagerID != null) || (this.roleManagerID != null && !this.roleManagerID.equals(other.roleManagerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Rolesmanager[ roleManagerID=" + roleManagerID + " ]";
    }
    
}
