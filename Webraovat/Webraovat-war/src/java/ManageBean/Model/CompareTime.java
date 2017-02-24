/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean.Model;

import Entity.Post;
import ManageBean.Model.ViewData;
import java.util.Comparator;
import org.joda.time.DateTime;
import org.joda.time.Seconds;

/**
 *
 * @author Asus
 */
public class CompareTime implements Comparator<ViewData> {

    @Override
    public int compare(ViewData t, ViewData t1) {

        DateTime date1 = new DateTime(t.getPostdate());
        DateTime date2 = new DateTime(t1.getPostdate());
        if (Seconds.secondsBetween(date1, date2).getSeconds() > 0) {
            return 1;
        } else {
            return -1;
        }
    }
    
        

}
