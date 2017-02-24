/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean.Model;

import Entity.Banlistpost;
import Entity.Banlistuser;
import Entity.Members;
import Entity.Post;
import Entity.Postchecked;
import Entity.Province;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Asus
 */
public class UserData {

    private Members Member;
    private List<String> Role;

    private Banlistuser Banlistuser = null;
    private Province ProvinceId;
    private List<ViewData> listpost = null;
    

    private List<ViewData> listpostOnDate = null;

    private List<ViewData> listpostBaned = null;

    private List<ViewData> listpostChecked = null;

    private List<ViewData> listpostDenied = null;

    public List<ViewData> getListpostChecked() {
        return listpostChecked;
    }

    public List<ViewData> getListpostOnDate() {
        return listpostOnDate;
    }

  
    
    
    public void setListpostOnDate(List<ViewData> listpostOnDate) {
        this.listpostOnDate = listpostOnDate;
    }

    public List<ViewData> getListpostDenied() {
        return listpostDenied;
    }

    public void setListpostDenied(List<ViewData> listpostDenied) {
        this.listpostDenied = listpostDenied;
    }

    public void setListpostChecked(List<ViewData> listpostChecked) {
        this.listpostChecked = listpostChecked;
    }

    public List<ViewData> getListpostBaned() {
        return listpostBaned;
    }

    public void setListpostBaned(List<ViewData> listpostBaned) {
        this.listpostBaned = listpostBaned;
    }

    public List<String> getRole() {
        return Role;
    }

    public void setRole(List<String> Role) {
        this.Role = Role;
    }

    public List<ViewData> getListpost() {
        return listpost;
    }

    public void setListpost(List<ViewData> listpost) {
        this.listpost = listpost;
    }

    public Province getProvinceId() {
        return ProvinceId;
    }

    public void setProvinceId(Province ProvinceId) {
        this.ProvinceId = ProvinceId;
    }

    public Members getMember() {
        return Member;
    }

    public void setMember(Members Member) {
        this.Member = Member;
    }

    public Banlistuser getBanlistuser() {
        return Banlistuser;
    }

    public void setBanlistuser(Banlistuser Banlistuser) {
        this.Banlistuser = Banlistuser;
    }

}
