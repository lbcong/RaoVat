/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean.Model;

import java.util.Comparator;
import org.joda.time.DateTime;
import org.joda.time.Seconds;

/**
 *
 * @author Asus
 */
public class ComparePrice implements Comparator<ViewData> {

    @Override
    public int compare(ViewData t, ViewData t1) {

        if (t.getPrice() > t1.getPrice()) {
            return 1;
        } else {
            return -1;
        }
    }

}
