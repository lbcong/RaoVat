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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),
    @NamedQuery(name = "Report.findByIdreport", query = "SELECT r FROM Report r WHERE r.idreport = :idreport"),
    @NamedQuery(name = "Report.findByDesreport", query = "SELECT r FROM Report r WHERE r.desreport = :desreport"),
    @NamedQuery(name = "Report.findByReportDate", query = "SELECT r FROM Report r WHERE r.reportDate = :reportDate")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idreport")
    private Integer idreport;
    @Size(max = 1000)
    @Column(name = "desreport")
    private String desreport;
    @Column(name = "ReportDate")
    @Temporal(TemporalType.DATE)
    private Date reportDate;
    @JoinColumn(name = "MemberId", referencedColumnName = "MemberId")
    @ManyToOne
    private Members memberId;
    @JoinColumn(name = "PostId", referencedColumnName = "PostId")
    @ManyToOne
    private Post postId;

    public Report() {
    }

    public Report(Integer idreport) {
        this.idreport = idreport;
    }

    public Integer getIdreport() {
        return idreport;
    }

    public void setIdreport(Integer idreport) {
        this.idreport = idreport;
    }

    public String getDesreport() {
        return desreport;
    }

    public void setDesreport(String desreport) {
        this.desreport = desreport;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Members getMemberId() {
        return memberId;
    }

    public void setMemberId(Members memberId) {
        this.memberId = memberId;
    }

    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idreport != null ? idreport.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.idreport == null && other.idreport != null) || (this.idreport != null && !this.idreport.equals(other.idreport))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Report[ idreport=" + idreport + " ]";
    }
    
}
