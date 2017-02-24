/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.Report;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Asus
 */
@Local
public interface ReportFacadeLocal {

    void create(Report report);

    void edit(Report report);

    void remove(Report report);

    Report find(Object id);

    List<Report> findAll();

    public List<Report> FindAllPostReportedByID(int PostID);
    
    List<Report> findRange(int[] range);

    int count();
    
}
