/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import Entity.Report;
import SessionBean.ReportFacadeLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Asus
 */
public class StubReport {

    ReportFacadeLocal reportFacade = lookupReportFacadeLocal();

    private ReportFacadeLocal lookupReportFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (ReportFacadeLocal) c.lookup("java:global/Webraovat/Webraovat-ejb/ReportFacade!SessionBean.ReportFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public void create(Report report) {
        reportFacade.create(report);
    }

    public void edit(Report report) {
        reportFacade.edit(report);
    }

    public void remove(Report report) {
        reportFacade.remove(report);
    }

    public Report find(Object id) {
        return reportFacade.find(id);
    }

    public List<Report> findAll() {
        return reportFacade.findAll();
    }

    public List<Report> findRange(int[] range) {
        return reportFacade.findRange(range);
    }

    public int count() {
        return reportFacade.count();
    }

    public List<Report> FindAllPostReportedByID(int PostID) {
        return reportFacade.FindAllPostReportedByID(PostID);
    }
    
    
}
