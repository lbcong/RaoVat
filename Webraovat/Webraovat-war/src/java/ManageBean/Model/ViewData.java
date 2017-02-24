/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean.Model;

import Entity.Banlistpost;
import Entity.Catagories;
import Entity.Members;
import Entity.Post;
import Entity.Postchecked;
import Entity.Postdenied;
import Entity.Productsentity;
import Entity.Province;
import Entity.Report;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Asus
 */
public class ViewData {

    private int PostId;
    private String Tittle;
    private boolean TypeOfPost;
    private Dates datetime;
    private int MemberId;
    private String MemberName;
    private Date Postdate;
    private Date Expdate;
    private int ProductId;
    private int ProvinceID;
    private boolean IsCheck;
    private boolean Isdenied;
    private String NameProduct;
    private double Price;
    private int CatagoriesID;
    private String CatagoriesName;
    private String ProvinceName;
    private String Imgname;
    
    private int index;
    private String ReasonBaned;
    private Date Bandate;
    private String Namepersondobaned;
    private Productsentity productentity = null;
    private Members memberentity = null;
    private Catagories Catagoriesentity = null;
    //giup kiem tra xem bai nay co bi block ko cung cap thong tin ve ngay block nguoi block ly do block bai nay
    private Banlistpost Banlistpostentity = null;
    //giup kiem tra xem bai nay co trong danh sach da duyet chua , cung cap thong tin ngay duyet nguoi duyet bai nay
    private Postchecked postChecked=null;
    //giup kiem tra xem bai nay co trong danh sach bi tu choi khong
    private Postdenied Postdenied=null;
    private Province provinceentity = null;
    //giup tim` ra list nguoi` report bai nay va ly do report
    private List<Report> listReport =null;
    private Post postentity = null;

    
    
    public int getPostId() {
        return PostId;

    }

    public List<Report> getListReport() {
        return listReport;
    }

    public void setListReport(List<Report> listReport) {
        this.listReport = listReport;
    }

    public int getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(int ProvinceID) {
        this.ProvinceID = ProvinceID;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String ProvinceName) {
        this.ProvinceName = ProvinceName;
    }

    public void setPostId(int PostId) {
        this.PostId = PostId;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String Tittle) {
        this.Tittle = Tittle;
    }

    public boolean getTypeOfPost() {
        return TypeOfPost;
    }

    public void setTypeOfPost(boolean TypeOfPost) {
        this.TypeOfPost = TypeOfPost;
    }

    public Dates getDatetime() {
        return datetime;
    }

    public void setDatetime(Dates datetime) {
        this.datetime = datetime;
    }

    public int getMemberId() {
        return MemberId;
    }

    public void setMemberId(int MemberId) {
        this.MemberId = MemberId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int ProductId) {
        this.ProductId = ProductId;
    }

    public boolean isIsCheck() {
        return IsCheck;
    }

    public void setIsCheck(boolean IsCheck) {
        this.IsCheck = IsCheck;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String NameProduct) {
        this.NameProduct = NameProduct;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public int getCatagoriesID() {
        return CatagoriesID;
    }

    public void setCatagoriesID(int CatagoriesID) {
        this.CatagoriesID = CatagoriesID;
    }

    public String getCatagoriesName() {
        return CatagoriesName;
    }

    public void setCatagoriesName(String CatagoriesName) {
        this.CatagoriesName = CatagoriesName;
    }

    public Date getPostdate() {
        return Postdate;
    }

    public void setPostdate(Date Postdate) {
        this.Postdate = Postdate;
    }

    public String getImgname() {
        return Imgname;
    }

    public void setImgname(String Imgname) {
        this.Imgname = Imgname;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String MemberName) {
        this.MemberName = MemberName;
    }

    public Date getExpdate() {
        return Expdate;
    }

    public void setExpdate(Date Expdate) {
        this.Expdate = Expdate;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getReasonBaned() {
        return ReasonBaned;
    }

    public void setReasonBaned(String ReasonBaned) {
        this.ReasonBaned = ReasonBaned;
    }

    public Date getBandate() {
        return Bandate;
    }

    public void setBandate(Date Bandate) {
        this.Bandate = Bandate;
    }

    public String getNamepersondobaned() {
        return Namepersondobaned;
    }

    public void setNamepersondobaned(String Namepersondobaned) {
        this.Namepersondobaned = Namepersondobaned;
    }

    public Postchecked getPostChecked() {
        return postChecked;
    }

    public void setPostChecked(Postchecked postChecked) {
        this.postChecked = postChecked;
    }

   

    public boolean isIsdenied() {
        return Isdenied;
    }

    public void setIsdenied(boolean Isdenied) {
        this.Isdenied = Isdenied;
    }

    public Productsentity getProductentity() {
        return productentity;
    }

    public void setProductentity(Productsentity productentity) {
        this.productentity = productentity;
    }

    public Members getMemberentity() {
        return memberentity;
    }

    public void setMemberentity(Members memberentity) {
        this.memberentity = memberentity;
    }

    public Catagories getCatagoriesentity() {
        return Catagoriesentity;
    }

    public void setCatagoriesentity(Catagories Catagoriesentity) {
        this.Catagoriesentity = Catagoriesentity;
    }

    public Banlistpost getBanlistpostentity() {
        return Banlistpostentity;
    }

    public void setBanlistpostentity(Banlistpost Banlistpostentity) {
        this.Banlistpostentity = Banlistpostentity;
    }

    public Province getProvinceentity() {
        return provinceentity;
    }

    public void setProvinceentity(Province provinceentity) {
        this.provinceentity = provinceentity;
    }

    public Post getPostentity() {
        return postentity;
    }

    public void setPostentity(Post postentity) {
        this.postentity = postentity;
    }

    public Postdenied getPostdenied() {
        return Postdenied;
    }

    public void setPostdenied(Postdenied Postdenied) {
        this.Postdenied = Postdenied;
    }

    
    
}
