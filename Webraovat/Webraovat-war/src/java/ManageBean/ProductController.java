/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import Entity.*;
import Stub.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author Asus
 */
@Named(value = "productController")
@SessionScoped
public class ProductController implements Serializable {

    private int tongsotrang = 0;
    private int tranghientai = 0;
    private int tongrecord = 0;
    private String SearchName = "";
    private int index = 0;
    private String TypeForm = null;
    private List<Integer> trang = null;
    private Part part;

    private Date BeforeDate = null;
    private Date AfterDate = null;
    private Date BeforeDate1 = null;
    private Date AfterDate1 = null;

    private List<Post> list = null;
    private Post Postentity = null;
    private Productsentity Productsentity = null;
    private StubPost Stub = null;

    @ManagedProperty(value = "#{postController}")
    PostController PostController = null;

    /**
     * Creates a new instance of ProductController
     */
    public ProductController() {
        Postentity = new Post();
        Productsentity = new Productsentity();
        PostController = new PostController();
    }

//----------------------------------
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
//giai quyet hanh dong cua nut next va prv

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
//----------------------------------

    public void SetupProductForview() {
        StubProduct StubProduct = new StubProduct();
        for (int i = 0; i < list.size(); i++) {
            Productsentity P = new Productsentity();
            P = StubProduct.FindProductentityByID(list.get(i).getPostId());
            list.get(i).setProductsentity(P);
        }

    }

    public String SetupViewAllSearchProductForManager() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String id = params.get("id");

        String name = params.get("Name");
        String postid = params.get("index");

        String np = params.get("np");
        String Action = params.get("Action");
        //int index = 0;

        Stub = new StubPost();
        int tongrecord = 0;

        try {

            if (Action == null) {
                list = new ArrayList<Post>();
                tongrecord = Stub.countFindAllPost("", "", null, null, "", "", "Tất cả", "", "");

                SearchName = "Tất cả";
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    tranghientai = 1;
                    list = Stub.FindAllPost("", "", null, null, "", "", SearchName, "", "", 3, tranghientai);
                    SetupProductForview();
                    TypeForm = "";

                    return "AllProduct";
                } else {
                    return "blank";
                }
            } else if (Action.equals("numberpage")) {
                list = new ArrayList<Post>();
                tongrecord = Stub.countFindAllPost("", "", null, null, "", "", SearchName, "", "");
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    tranghientai = Integer.parseInt(id);
                    list = Stub.FindAllPost("", "", null, null, "", "", SearchName, "", "", 3, tranghientai);
                    SetupProductForview();
                    TypeForm = "";

                    return "AllProduct";
                } else {
                    return "blank";
                }
            } else if (Action.equals("nextprv")) {
                list = new ArrayList<Post>();
                tongrecord = Stub.countFindAllPost("", "", null, null, "", "", SearchName, "", "");
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    NextPrvPage(np);
                    list = Stub.FindAllPost("", "", null, null, "", "", SearchName, "", "", 3, tranghientai);
                    SetupProductForview();
                    TypeForm = "";

                    return "AllProduct";
                } else {
                    return "blank";
                }
            } else if (Action.equals("search")) {
                list = new ArrayList<Post>();
                if (name == null) {
                    name = "Tất cả";
                }
                tongrecord = Stub.countFindAllPost("", "", null, null, "", "", name, "", "");
                if (tongrecord > 0) {
                    tranghientai = 1;

                    SearchName = name;
                    taotrang(3, tongrecord);
                    list = Stub.FindAllPost("", "", null, null, "", "", name, "", "", 3, tranghientai);
                    SetupProductForview();
                    TypeForm = "";

                    return "AllProduct";
                } else {
                    return "blank";
                }
            } else if (Action.equals("add")) {
                list = new ArrayList<Post>();

                PostController.createProductsup();

                tongrecord = Stub.countFindAllPost("", "", null, null, "", "", SearchName, "", "");
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    list = Stub.FindAllPost("", "", null, null, "", "", SearchName, "", "", 3, tranghientai);
                    SetupProductForview();
                    TypeForm = "";

                    return "AllProduct";
                } else {
                    return "blank";
                }
            } else if (Action.equals("edit")) {
                list = new ArrayList<Post>();

                PostController.EditPostsup();

                tongrecord = Stub.countFindAllPost("", "", null, null, "", "", SearchName, "", "");
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    list = Stub.FindAllPost("", "", null, null, "", "", SearchName, "", "", 3, tranghientai);
                    SetupProductForview();
                    TypeForm = "";

                    return "AllProduct";
                } else {
                    return "blank";
                }
            } else if (Action.equals("delete")) {

                PostController.DeletePost(list.get(Integer.parseInt(postid)).getPostId());

                tongrecord = Stub.countFindAllPost("", "", null, null, "", "", SearchName, "", "");
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    list = new ArrayList<Post>();
                    list = Stub.FindAllPost("", "", null, null, "", "", SearchName, "", "", 3, tranghientai);
                    SetupProductForview();
                    TypeForm = "";

                    request.setAttribute("messeger", "Xóa thành công");
                    return "AllProduct";
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

    public String SetupAddProduct() {
        PostController.setEntity(new Post());
        try {
            TypeForm = "add";
        } catch (Exception e) {
        }
        return null;
    }

    public String SetupEditProduct() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String id = params.get("index");
        
        try {
           
            PostController.setProvinceentity(list.get(Integer.parseInt(id)).getProvinceId());
            PostController.setProductentity(list.get(Integer.parseInt(id)).getProductsentity());
            PostController.setCatagoriesentity(list.get(Integer.parseInt(id)).getProductsentity().getCategoriesId());
            PostController.setEntity(list.get(Integer.parseInt(id)));
            
            TypeForm = "edit";
        } catch (Exception e) {
        }
        return null;
    }
//-----------------------------------

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

    public String getSearchName() {
        return SearchName;
    }

    public void setSearchName(String SearchName) {
        this.SearchName = SearchName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTypeForm() {
        return TypeForm;
    }

    public void setTypeForm(String TypeForm) {
        this.TypeForm = TypeForm;
    }

    public List<Integer> getTrang() {
        return trang;
    }

    public void setTrang(List<Integer> trang) {
        this.trang = trang;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
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

    public Date getBeforeDate1() {
        return BeforeDate1;
    }

    public void setBeforeDate1(Date BeforeDate1) {
        this.BeforeDate1 = BeforeDate1;
    }

    public Date getAfterDate1() {
        return AfterDate1;
    }

    public void setAfterDate1(Date AfterDate1) {
        this.AfterDate1 = AfterDate1;
    }

    public List<Post> getList() {
        return list;
    }

    public void setList(List<Post> list) {
        this.list = list;
    }

    public Post getPostentity() {
        return Postentity;
    }

    public void setPostentity(Post Postentity) {
        this.Postentity = Postentity;
    }

    public Productsentity getProductsentity() {
        return Productsentity;
    }

    public void setProductsentity(Productsentity Productsentity) {
        this.Productsentity = Productsentity;
    }

    public PostController getPostController() {
        return PostController;
    }

    public void setPostController(PostController PostController) {
        this.PostController = PostController;
    }

}
