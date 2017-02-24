/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.*;
import Entity.*;
import Stub.*;
import Stub.StubCustomer;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Asus
 */
@Named(value = "makeOrderController")
@SessionScoped
public class MakeOrderController implements Serializable {

    private int tongsotrang = 0;
    private int tranghientai = 0;
    private int tongrecord = 0;
    private List<Integer> trang = null;

    private String TypeForm = "";
    private Chart Chart;
    private int index = 0;

    private String SearchName = "";
    private Date BeforeDate = null;
    private Date AfterDate = null;
    private Date BeforeDate1 = null;
    private Date AfterDate1 = null;
    private List<Makeorder> listmakeorder = null;
    private Makeorder makeorder = null;
    private Orderdetails orderdetails = null;

    /**
     * Creates a new instance of MakeOrderController
     */
    public MakeOrderController() {
        makeorder = new Makeorder();
        orderdetails = new Orderdetails();
    }

//cac pt ho tro   
    public void taotrang(int limit, int tongrecord) {
        trang = new ArrayList<>();
        this.tongrecord = tongrecord;
        if (tongrecord % 3 >= 1) {
            tongsotrang = tongrecord / limit + 1;
        } else {
            tongsotrang = tongrecord / limit;
        }
        for (int i = 0; i < (tongsotrang); i++) {
            trang.add(i + 1);

        }
    }

    public void NextPrvPage(String np) {
        if (np.equals("next")) {
            if (tranghientai < tongsotrang && tranghientai > 0) {
                tranghientai++;

            }
        } else if (np.equals("prv")) {
            if (tranghientai > 1 && tranghientai <= tongsotrang) {
                tranghientai--;
            }

        }

    }

    public void Setstatus() {
        FacesContext fc = FacesContext.getCurrentInstance();

        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        StubMakeorder StubMakeorder = new StubMakeorder();
        Makeorder Makeorder = new Makeorder();
        String status = params.get("orderstatus");
        String index = params.get("index");
        try {

            Makeorder = StubMakeorder.find(listmakeorder.get(Integer.parseInt(index)).getIdOrder());
            Makeorder.setStatus(status);
            StubMakeorder.edit(Makeorder);

        } catch (Exception e) {
            e.getMessage();
        }

    }

//cac pt setup
    public String SetupViewAllSearchMakeorderManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String id = params.get("id");
        String Name = params.get("Name");
        String np = params.get("np");
        String Action = params.get("Action");
        StubMakeorder StubMakeorder = new StubMakeorder();

        try {
            if (Action == null) {
                listmakeorder = new ArrayList<>();
                SearchName = "";

                tongrecord = StubMakeorder.countAllMakeorder(0, SearchName);
                if (tongrecord > 0) {
                    taotrang(3, tongrecord);
                    tranghientai = 1;
                    listmakeorder = StubMakeorder.FindAllMakeorder(0, SearchName, 3, tranghientai);

                    TypeForm = "";
                    return "AllMakeOrder";
                } else {
                    return "blank";
                }
            } else if (Action.equals("numberpage")) {
                listmakeorder = new ArrayList<>();
                tongrecord = StubMakeorder.countAllMakeorder(0, SearchName);
                if (tongrecord > 0) {
                    taotrang(3, tongrecord);

                    tranghientai = Integer.parseInt(id);

                    listmakeorder = StubMakeorder.FindAllMakeorder(0, SearchName, 3, tranghientai);

                    TypeForm = "";
                    return "AllMakeOrder";
                } else {
                    return "blank";
                }
            } else if (Action.equals("nextprv")) {
                listmakeorder = new ArrayList<>();
                tongrecord = StubMakeorder.countAllMakeorder(0, SearchName);
                if (tongrecord > 0) {
                    taotrang(3, tongrecord);
                    NextPrvPage(np);
                    listmakeorder = StubMakeorder.FindAllMakeorder(0, SearchName, 3, tranghientai);

                    TypeForm = "";
                    return "AllMakeOrder";
                } else {
                    return "blank";
                }

            } else if (Action.equals("search")) {
                listmakeorder = new ArrayList<>();
                tongrecord = StubMakeorder.countAllMakeorder(0, Name);
                if (tongrecord > 0) {
                    tranghientai = 1;

                    SearchName = Name;

                    taotrang(3, tongrecord);
                    listmakeorder = StubMakeorder.FindAllMakeorder(0, Name, 3, tranghientai);

                    TypeForm = "";

                    return "AllMakeOrder";
                } else {
                    return "blank";
                }
            } else if (Action.equals("capnhat")) {

                tongrecord = StubMakeorder.countAllMakeorder(0, SearchName);
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    Setstatus();
                    listmakeorder = new ArrayList<>();
                    listmakeorder = StubMakeorder.FindAllMakeorder(0, SearchName, 3, tranghientai);

                    TypeForm = "";

                    return "AllMakeOrder";
                } else {
                    return "blank";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "trangloi";
        }
        return "trangloi";
    }

    public String SetupViewDetail() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String id = params.get("index");
        Stuborderdetail Stuborderdetail= new Stuborderdetail();
        
        try {
            makeorder=listmakeorder.get(Integer.parseInt(id));
            makeorder.setOrderdetailsCollection(Stuborderdetail.FindAllOrderdetailsByID(makeorder.getIdOrder()));
            
            TypeForm="detail";
        } catch (Exception e) {
        }
        return null;
    }
//-------------------------------------

    public int getTongsotrang() {
        return tongsotrang;
    }

    public void setTongsotrang(int tongsotrang) {
        this.tongsotrang = tongsotrang;
    }

    public int getTranghientai() {
        return tranghientai;
    }

    public void setTranghientai(int tranghientai) {
        this.tranghientai = tranghientai;
    }

    public int getTongrecord() {
        return tongrecord;
    }

    public void setTongrecord(int tongrecord) {
        this.tongrecord = tongrecord;
    }

    public List<Integer> getTrang() {
        return trang;
    }

    public void setTrang(List<Integer> trang) {
        this.trang = trang;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Date getBeforeDate() {
        return BeforeDate;
    }

    public void setBeforeDate(Date BeforeDate) {
        this.BeforeDate = BeforeDate;
    }

    public Date getAfterDate() {
        return AfterDate;
    }

    public void setAfterDate(Date AfterDate) {
        this.AfterDate = AfterDate;
    }

    public List<Makeorder> getListmakeorder() {
        return listmakeorder;
    }

    public void setListmakeorder(List<Makeorder> listmakeorder) {
        this.listmakeorder = listmakeorder;
    }

    public Makeorder getMakeorder() {
        return makeorder;
    }

    public void setMakeorder(Makeorder makeorder) {
        this.makeorder = makeorder;
    }

    public Orderdetails getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(Orderdetails orderdetails) {
        this.orderdetails = orderdetails;
    }

    public String getTypeForm() {
        return TypeForm;
    }

    public void setTypeForm(String TypeForm) {
        this.TypeForm = TypeForm;
    }

}
