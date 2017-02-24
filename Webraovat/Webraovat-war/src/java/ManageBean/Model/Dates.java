/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean.Model;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class Dates {

    private String time;
    private int hour;
    private int minute;
    private int day;
    private int second;

    /**
     * Creates a new instance of Date
     */
    public Dates() {

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
