/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import Entity.Banlistpost;
import ManageBean.Model.CompareTime;
import ManageBean.Model.Dates;
import Entity.Bookmark;
import Entity.Catagories;
import Entity.Makeorder;
import Entity.Manager;
import Entity.Members;
import Entity.Orderdetails;
import Entity.Post;
import Entity.Postchecked;
import Entity.Postdenied;
import Entity.Productsentity;
import Entity.Province;
import Entity.Report;
import ManageBean.Model.Cart;
import ManageBean.Model.ComparePrice;
import ManageBean.Model.MailUtilGmail;
import ManageBean.Model.ViewData;
import Stub.StubBanlistPost;
import Stub.StubBookmark;
import Stub.StubCatagories;
import Stub.StubCustomer;
import Stub.StubDeniedPost;
import Stub.StubMakeorder;
import Stub.StubPost;
import Stub.StubPostChecked;
import Stub.StubProduct;
import Stub.StubProvine;
import Stub.StubReport;
import Stub.Stuborderdetail;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Asus
 */
@Named(value = "postController")
@SessionScoped
public class PostController implements Serializable {

    private Post entity = null;
    private Manager Managerentity = null;
    private Banlistpost Banlistpostentity = null;
    private Province provinceentity = null;
    private Catagories Catagoriesentity = null;
    private Members memberentity = null;
    private Productsentity productentity = null;
    private StubPost Stub = null;
    private StubBanlistPost StubBanlistPost = null;
    private StubBookmark Stubbookmark = null;
    private StubCustomer StubCustomer = null;
    private StubCatagories StubCatagories = null;
    private StubProvine StubProvine = null;
    private StubProduct StubProduct = null;
    private ViewData Viewdata = null;
    private RepeatPaginator paginator;
    private RepeatPaginator paginator2;
    private List<Post> list = null;
    private Postdenied Postdenied = null;
    private Makeorder Makeorder = null;
    private Orderdetails Orderdetails = null;
    private Members MemberSession = null;
    Cart cart = null;
    // chua du lieu cho ngay
    private ArrayList<Dates> obj2 = null;
    // chua du lieu ve nut
    private ArrayList<String> obj1 = null;

    private List<Integer> trang = null;

    private List<Banlistpost> ListPostBaned = null;
    private List<ViewData> ListView = null;
    private List<ViewData> Listview2 = null;
    private List<Cart> ListCart = null;
    @ManagedProperty(value = "#{bookmarkController}")
    BookmarkController BookmarkController = null;
    @ManagedProperty(value = "#{catagoriesController}")
    CatagoriesController CatagoriesController = null;
    @ManagedProperty(value = "#{provineController}")
    ProvineController ProvineController = null;
    @ManagedProperty(value = "#{customerController}")
    CustomerController CustomerController = null;
    @ManagedProperty(value = "#{productsentityController}")
    ProductsentityController ProductsentityController = null;

    //private List<Catagories> listCatagories = null;
    double tongtien = 0;
    private String chuyenmuc = null;
    private String chuoi = null;
    private int index = 0;
    private Dates dates = null;
    private Part part;
    private int tongsotrang = 0;
    private int tranghientai = 0;
    private int tongrecord = 0;
    private String SearchName = "";
    private String SearchTittle = "";
    private String SearchProvince = "";
    private String SearchCatagories = "";
    private String TypeSearch = "";
    private String typedenied = "";

//tam-------------------------------------------
    private Report reportentity = null;
    private StubReport stubrp = null;
    private String message;
//--------------------------------------
    //typeform dung de xac dinh form nao se duoc hien thi neu tren cung 1 trang co nhieu form
    //neu muon tra ve ngay tran hien tai tra ve null
    //moi trang 1 ham xu li rieng ko nen gop chung vi de bi mat du lieu
    private String TypeForm = null;

//---------------------------------------------------------
//contructor
    public PostController() {
        entity = new Post();
        provinceentity = new Province();
        Catagoriesentity = new Catagories();
        memberentity = new Members();
        productentity = new Productsentity();
        Banlistpostentity = new Banlistpost();
        paginator = new RepeatPaginator();
        Viewdata = new ViewData();
        Postdenied = new Postdenied();
        ListCart = new ArrayList<>();
        MemberSession = new Members();
    }

    @PostConstruct
    public void init() {
        entity = new Post();
        provinceentity = new Province();
        Catagoriesentity = new Catagories();
        memberentity = new Members();
        productentity = new Productsentity();
        Banlistpostentity = new Banlistpost();
        paginator = new RepeatPaginator();
        Postdenied = new Postdenied();
    }

//------------------------------------------------------------them bai post
    public Post createPostsup() {
        Stub = new StubPost();
        StubCustomer = new StubCustomer();

        StubProvine = new StubProvine();

        try {
            Members memberentity1 = StubCustomer.findByEmailMember(memberentity.getEmail());

            provinceentity = StubProvine.find(provinceentity.getProvinceid());
            //set date create
            entity = RenewDate(entity);
            entity.setIsCheck(false);

            entity.setNumberOfReport(0);
            entity.setIsCheck(true);
            entity.setTypeOfPost(true);
            entity.setMemberId(memberentity1);
            this.memberentity = memberentity1;
            entity.setProvinceId(provinceentity);

            Stub.create(entity);

            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createProductsup() {

        StubCustomer = new StubCustomer();
        StubProduct = new StubProduct();
        StubPostChecked StubPostChecked = new StubPostChecked();
        Postchecked Postchecked = new Postchecked();
        StubCatagories = new StubCatagories();
        ProductsentityController = new ProductsentityController();
        chuoi = null;
        try {
            Post p = createPostsup();

            Members m = new Members();
            m = StubCustomer.findByEmailMember("admincong@gmail.com");
            Date date = new Date();
            Postchecked.setCheckedDate(date);
            Postchecked.setMemberID(m);
            Postchecked.setPostID(p);

            StubPostChecked.create(Postchecked);
            Catagoriesentity = StubCatagories.find(Catagoriesentity.getCatagoriesId());

            String imgname = ProductsentityController.UploadFile(part);
            productentity.setImage(imgname);
            //System.out.println(p.getPostId() + "Post ID");
            productentity.setPostId(p);
            productentity.setStroreProduct(productentity.getTotalProduct());
            productentity.setCategoriesId(Catagoriesentity);
            productentity.setStatus(true);

            //System.out.println(p.getPostId() + "----" + Catagoriesentity.getCatagoriesId());
            StubProduct.create(productentity);

        } catch (Exception e) {
            e.printStackTrace();
            return "trangloi";
        }
        return null;
    }

//------------------------------------------------------------- dang bai post
    public String SetupPosted() {

        CustomerController = new CustomerController();
        entity = new Post();
        chuoi = null;
        part = null;
        try {

            if (CustomerController.GetMemberFromSession() != null) {

                memberentity = CustomerController.GetMemberFromSession();
                System.out.println(memberentity.getMemberId() + "setup pot");
                return "posted";
            } else {
                return "trangloi";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "trangloi";
        }

    }

    public String SetupReviewPost() {
        ProductsentityController = new ProductsentityController();
        CustomerController = new CustomerController();
        StubCatagories = new StubCatagories();
        StubProvine = new StubProvine();
        try {
            if (CustomerController.GetMemberFromSession() != null) {
                memberentity = CustomerController.GetMemberFromSession();
            }

//            String x = productentity.getVideo();
//            String[] str2 = x.split("=", 5);
//            String[] str3 = str2[1].split("&", 5);
//            productentity.setVideo("https://www.youtube.com/v/" + str3[0]);
            String imgname = ProductsentityController.UploadFile(part);

            productentity.setImage(imgname);

            Catagoriesentity = StubCatagories.find(Catagoriesentity.getCatagoriesId());

            provinceentity = StubProvine.find(provinceentity.getProvinceid());

            if (entity.getTypeOfPost() == true) {
                chuyenmuc = "Cần Bán";
            } else {
                chuyenmuc = "Cần Mua";
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return "reviewpost";
    }

    //method create post
    public Post createPost() {
        Stub = new StubPost();
        StubCustomer = new StubCustomer();

        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        memberentity = (Members) session.getAttribute("customer");

        try {
            //set date create
            entity = RenewDate(entity);
            entity.setIsCheck(false);

            entity.setNumberOfReport(0);
            //System.out.println(entity.getPostDate() + "ngay post");

            // view tilte
            //System.out.println(entity.getTitle() + "title");
            //view description
            //System.out.println(entity.getDescription() + "descrp");;
            //set memberid
            entity.setMemberId(memberentity);
            //view memberId

            //System.out.println(entity.getMemberId() + "idmem");
            //set provinceid
            entity.setProvinceId(provinceentity);
            //System.out.println(provinceentity.getProvinceid() + "khu vuc");
            //set type of post

            Stub.create(entity);

            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //get idPost
    public String createProduct() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        StubProduct = new StubProduct();

        chuoi = null;
        try {
            Post p = createPost();

            //System.out.println(p.getPostId() + "Post ID");
            productentity.setPostId(p);

            productentity.setCategoriesId(Catagoriesentity);

            //System.out.println(p.getPostId() + "----" + Catagoriesentity.getCatagoriesId());
            StubProduct.create(productentity);
            request.setAttribute("messeger", "đăng bài thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return "trangloi";
        }
        return "trangchu";
    }

//---------------------------------------------------------
//cac pt tim
    public List<Post> FindAllPostByPage(int limit, int tranghientai) {

        Stub = new StubPost();
        try {
            System.out.println("tesssssssst");
            List<Post> list = Stub.FindAllPostByPage(limit, tranghientai);
            if (list != null) {
                if (list.size() >= 0) {
                    return list;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Post> FindExpPostByPage(int limit, int tranghientai) {

        Stub = new StubPost();
        try {

            List<Post> list = Stub.FindExpPostByPage(limit, tranghientai);
            if (list != null) {
                if (list.size() >= 0) {
                    return list;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Post> FindNoCheckPostByPage(int limit, int tranghientai) {

        Stub = new StubPost();
        try {
            System.out.println("tesssssssst");
            List<Post> list = Stub.FindNoCheckPostByPage(limit, tranghientai);
            if (list != null) {
                if (list.size() >= 0) {
                    return list;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Post> FindPostByCity(String name) {

        Stub = new StubPost();
        try {
            System.out.println("tesssssssst");
            List<Post> list = Stub.FindPostByCity(name);
            if (list != null) {
                if (list.size() >= 0) {
                    return list;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Post> FindPostByCatagories(String name) {

        Stub = new StubPost();
        try {
            System.out.println("tesssssssst");
            List<Post> list = Stub.FindPostByCatagoriesName(name);
            if (list != null) {
                if (list.size() >= 0) {
                    return list;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Post> FindPostByIDCity(int id) {

        Stub = new StubPost();
        try {
            System.out.println("findidcity");
            List<Post> list = Stub.FindPostByIDCity(id);
            if (list != null) {
                if (list.size() >= 0) {
                    return list;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Post> FindPostByCatagoriesAndCity(String catagories, String city) {

        Stub = new StubPost();
        try {
            System.out.println("tesssssssst");
            List<Post> list = Stub.FindPostByCatagoriesAndCity(catagories, city);
            if (list != null) {
                if (list.size() >= 0) {
                    return list;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Post> FindPostByTittleAndCity(String tittle, String city) {

        Stub = new StubPost();
        try {
            System.out.println("tesssssssst");
            List<Post> list = Stub.FindPostByTittleAndCity(tittle, city);
            if (list != null) {
                if (list.size() >= 0) {
                    return list;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Post> FindPostByTittleAndCityAndCatagories(String tittle, String city, String catagories) {

        Stub = new StubPost();
        try {
            System.out.println("tesssssssst");
            List<Post> list = Stub.FindPostByTittleAndCityAndCatagories(tittle, city, catagories);
            if (list != null) {
                if (list.size() >= 0) {
                    return list;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }

    public List<Post> FindPostByMember(int id) {

        Stub = new StubPost();
        try {
            System.out.println("FindPostByMember");
            List<Post> list = Stub.FindPostByMember(id);
            if (list != null) {
                if (list.size() >= 0) {
                    return list;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Post> FindAllPostExperiDate() {
        Stub = new StubPost();
        try {

            List<Post> list = Stub.FindAllPostExperiDate();
            if (list != null) {
                if (list.size() >= 0) {
                    return list;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
        return null;

    }
//chuc nang tim kiem

    public String ControlerFind() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String cata = params.get("formsearch:catagories");
        String province = params.get("formsearch:province");
        String tittlesearch = params.get("formsearch:tittlesearch");
        
        ListView = new ArrayList<>();
        Listview2 = new ArrayList<>();
        list = new ArrayList<>();
        Stub = new StubPost();
        list = Stub.FindAllPost(tittlesearch, "", null, null, cata, province, "", "", "CheckNotBlock", 100, 1);
        if (list != null && list.size() != 0) {
            CaculateTime();
            ListView = SetupViewData(list);

            for (int i = 0; i < ListView.size(); i++) {
                if (ListView.get(i).getProductentity().getStroreProduct() > 0) {
                    Listview2.add(ListView.get(i));
                }
            }
            for (int i = 0; i < Listview2.size(); i++) {
                Listview2.get(i).setIndex(i);
            }

            for (int i = 0; i < ListView.size(); i++) {
                if (ListView.get(i).getProductentity().getStroreProduct() > 0) {
                    ListView.remove(i);
                    i--;
                }
            }
            for (int i = 0; i < ListView.size(); i++) {
                ListView.get(i).setIndex(i);
            }
            paginator = new RepeatPaginator(ListView);
            paginator2 = new RepeatPaginator(Listview2);
        }
        entity.setTitle("");
        return "index";
    }
//------------------------------------------------------------------------

// tao don dat hang
    public Makeorder CreateOrder() {
        CustomerController = new CustomerController();
        StubMakeorder StubMakeorder = new StubMakeorder();
        Makeorder Makeorder = new Makeorder();
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String email = request.getParameter("formcheckout:email");
        String phone = request.getParameter("formcheckout:phone");
        String address = request.getParameter("formcheckout:adress");
        try {
            Members mem = CustomerController.GetMemberFromSession();
            Date date = new Date();

            Makeorder.setAddress(address);
            Makeorder.setEmail(email);
            Makeorder.setCreateDated(date);
            Makeorder.setStatus("Chưa giao");
            Makeorder.setPhone(phone);
            Makeorder.setTotalPrice(tongtien);
            Makeorder.setMemberID(mem);

            StubMakeorder.create(Makeorder);
            return Makeorder;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
//tao chi tiet dat hang

    public String CreateOrderdetail() {
        Stuborderdetail Stuborderdetail = new Stuborderdetail();
        StubProduct = new StubProduct();

        Orderdetails Orderdetails = null;
        try {
            Makeorder Makeorder = CreateOrder();
            for (int i = 0; i < ListCart.size(); i++) {
                Orderdetails = new Orderdetails();
                ListCart.get(i).getProductsentity().setStroreProduct(ListCart.get(i).getProductsentity().getStroreProduct() - ListCart.get(i).getSoluong());

                Orderdetails.setProductID(ListCart.get(i).getProductsentity());
                Orderdetails.setOrderID(Makeorder);
                Orderdetails.setNumberofproduct(ListCart.get(i).getSoluong());

                Stuborderdetail.create(Orderdetails);
                StubProduct.edit(ListCart.get(i).getProductsentity());

            }
            this.Makeorder = Makeorder;

        } catch (Exception e) {
        }
        return null;
    }
//------------------------------------------------------------
//cac pt ho tro
//sua gio hang

    public void Updatecart() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Cart cart = new Cart();
        double tongtien = 0;
        try {
            for (int i = 0; i < ListCart.size(); i++) {
                tongtien = tongtien + ListCart.get(i).getSoluong() * ListCart.get(i).getProductsentity().getPrice();
                this.tongtien = tongtien;
            }
            request.setAttribute("tongtien", tongtien);

        } catch (Exception e) {
        }

    }
//them gio hang

    public void Addtocart(ActionEvent event) {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Productsentity id = (Productsentity) event.getComponent().getAttributes().get("idpro");
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Cart cart = new Cart();

            if (ListCart.size() > 0) {
                boolean x = false;
                for (int i = 0; i < ListCart.size(); i++) {
                    if (ListCart.get(i).getProductsentity().getProductID() == id.getProductID()) {
                        x = true;
                        request.setAttribute("messeger", "Mặt hàng này đã có trong giỏ");

                    }
                }
                if (!x) {
                    cart.setProductsentity(id);
                    ListCart.add(cart);
                    request.setAttribute("messeger", "lưu vào giỏ hàng thành công");

                }
            } else {
                cart.setProductsentity(id);
                ListCart.add(cart);
                request.setAttribute("messeger", "lưu vào giỏ hàng thành công");
            }

        } catch (Exception e) {
        }

    }
//xoa gio hang

    public String Deletecart() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String id = params.get("index");

        try {
            Cart cart = new Cart();
            cart = ListCart.get(Integer.parseInt(id));
            for (int i = 0; i < ListCart.size(); i++) {
                if (ListCart.get(i).getProductsentity().getProductID() == cart.getProductsentity().getProductID()) {

                    ListCart.remove(i);
                }

            }

        } catch (Exception e) {
        }
        return null;
    }
//chuc nang sendmail thong bao cho nguoi dung

    public boolean sendMail() {
        List<Post> list1 = new ArrayList<>();
        List<ViewData> list2 = new ArrayList<>();
        Stub = new StubPost();
        entity = Stub.find(Viewdata.getPostentity().getPostId());
        list1.add(entity);
        list2 = SetupViewData(list1);
        Viewdata = list2.get(0);

        String to = Viewdata.getMemberentity().getEmail();
        String from = "lisatthu35@gmail.com";
        String subject = "Thong bao";
        String body = "";
        if (Viewdata.getBanlistpostentity() != null && Viewdata.getBanlistpostentity().getDateBaned() != null) {

            body = "Tin của bạn đã bị khóa bởi :" + Viewdata.getBanlistpostentity().getMemberID().getUsername()
                    + " , Lý do bị khóa là :" + Viewdata.getBanlistpostentity().getReason();
        } else if (Viewdata.getPostdenied() != null && Viewdata.getPostdenied().getDateDenied() != null) {
            body = "Tin của bạn đã bị từ chối bởi :" + Viewdata.getPostdenied().getMemberID().getUsername()
                    + " , Lý do bị từ chối là :" + Viewdata.getPostdenied().getReason();
        }
        // active?code=123
        boolean bodyIsHTML = true;

        try {
            MailUtilGmail.sendMail(to, from, subject, body, bodyIsHTML);

            return true;

        } catch (Exception e) {
            e.getMessage();
            return false;

        }

    }
//chuc nang sendmail cho nguoi dat ahng

    public boolean sendMailorder() {

        CustomerController = new CustomerController();
        String to = CustomerController.GetMemberFromSession().getEmail();
        String from = "lisatthu35@gmail.com";
        String subject = "Don dat hang";
        String body = "";
        String table = "";

        for (int i = 0; i < ListCart.size(); i++) {
            table = table
                    + "<tr>\n"
                    + "                                                        <td style=\"text-align: left\">\n"
                    + "\n"
                    + ListCart.get(i).getProductsentity().getName() + "\n"
                    + "                                                        </td>\n"
                    + "                                                        <td style=\"text-align: left\">\n"
                    + ListCart.get(i).getSoluong() + "\n"
                    + "                                                        </td>\n"
                    + "                                                        <td style=\"text-align: left\">\n"
                    + ListCart.get(i).getProductsentity().getPrice() + "\n"
                    + "                                                        </td>\n"
                    + "<td style=\"text-align: left\">\n"
                    + ListCart.get(i).getProductsentity().getPrice() * ListCart.get(i).getSoluong() + "\n"
                    + "                                                        </td>\n"
                    + "                                                    </tr>";

        }

        body
                = "<div  >\n"
                + "<label style=\"text-align: center\"  ><h2>Thông tin người mua</h2></label>\n"
                + "\n"
                + "                                        </div>\n"
                + "<br/>"
                + "<hr/>"
                + "<div >\n"
                + "                                            <label style=\"text-align: left\"  ><h4>Tên người mua: <b>" + Makeorder.getMemberID().getUsername() + "</b></h4></label>\n"
                + "\n"
                + "                                        </div>"
                + "<div >\n"
                + "                                            <label style=\"text-align: left\"  ><h4>Email người mua: <b>" + Makeorder.getMemberID().getEmail() + "</b></h4></label>\n"
                + "\n"
                + "                                        </div>"
                + "<div >\n"
                + "                                            <label style=\"text-align: left\"  ><h4>Số điện thoại nơi nhận hàng: <b>" + Makeorder.getPhone() + "</b></h4></label>\n"
                + "\n"
                + "                                        </div>"
                + "<div >\n"
                + "                                            <label style=\"text-align: left\"  ><h4>Địa chỉ nhận hàng: <b>" + Makeorder.getAddress() + "</b></h4></label>\n"
                + "\n"
                + "                                        </div>"
                + "<br/>"
                + "<br/>"
                + "<div >\n"
                + "                                            <label style=\"text-align: center\"  ><h2>Chi tiết đơn hàng</h2></label>\n"
                + "\n"
                + "                                        </div>"
                + "<hr/>"
                + "<br/>"
                + "<div >\n"
                + "                                            <label style=\"text-align: left\"  ><h4>Mã đơn hàng : " + Makeorder.getIdOrder() + "</h4></label>\n"
                + "\n"
                + "                                        </div>"
                + "<div >\n"
                + "                                            <label style=\"text-align: left\"  ><h4>Ngày đặt hàng : " + Makeorder.getCreateDated() + "</h4></label>\n"
                + "\n"
                + "                                        </div>"
                + "<div >\n"
                + "                                            <label style=\"text-align: left\"  ><h4>Phương thức thanh toán : Giao hàng thu tiền tận nơi</h4></label>\n"
                + "\n"
                + "                                        </div>"
                + "<div >\n"
                + "                                            <label style=\"text-align: left\"  ><h4>Phương thức giao hàng : Giao hàng tận nơi</h4></label>\n"
                + "\n"
                + "                                        </div>"
                + "<table style=\"border-collapse: collapse;\n"
                + "                                               border-color: #26cd08;\n"
                + "                                               border: solid 1px;\n"
                + "                                               border-radius: 4px;\n"
                + "                                               margin: 4px;\"  width=\"850px\" >\n"
                + "                                            <thead>\n"
                + "                                                <tr>\n"
                + "\n"
                + "                                                    <th style=\"text-align: left\"><a href=\"#\">Sản phẩm</a></th>\n"
                + "                                                    <th style=\"text-align: left\"><a href=\"#\">Số lượng</a></th>\n"
                + "                                                    <th style=\"text-align: left\"><a href=\"#\">Đơn giá</a></th>\n"
                + "                                                    <th style=\"text-align: left\"><a href=\"#\">Thành tiền</a></th>\n"
                + "\n"
                + "                                                </tr>\n"
                + "                                            </thead>\n"
                + "                                            <tbody>\n"
                + table
                + "                                                <tr>\n"
                + "                                                    <td style=\"text-align: left\">\n"
                + "                                                        <p>Tổng tiền</p>\n"
                + "                                                    </td>\n"
                + "                                                    <td colspan=\"3\" style=\"text-align: right\">\n"
                + tongtien + "\n"
                + "                                                    </td>\n"
                + "\n"
                + "                                                </tr>\n"
                + "                                            </tbody>\n"
                + "                                        </table>"
                + "<div >\n"
                + "                                            <label style=\"text-align: left\"  ><h4>Đơn đặt hàng sẽ được giao trong vòng 3 ngày ....</h4></label>\n"
                + "\n"
                + "                                        </div>";

        boolean bodyIsHTML = true;

        try {
            MailUtilGmail.sendMail(to, from, subject, body, bodyIsHTML);

            return true;

        } catch (Exception e) {
            e.getMessage();
            return false;

        }

    }
//chuc nang khoa bai post

    public boolean BlockPost() {
        StubBanlistPost = new StubBanlistPost();
        StubPostChecked StubPostChecked = new StubPostChecked();
        StubDeniedPost StubDeniedPost = new StubDeniedPost();
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Stub = new StubPost();
        HttpSession session = request.getSession();
        memberentity = (Members) session.getAttribute("manager");
        try {
            Date dateCreate = new Date();
            Banlistpostentity.setDateBaned(dateCreate);
            Banlistpostentity.setMemberID(memberentity);
            entity = Viewdata.getPostentity();
            if (Viewdata.getPostChecked() != null) {
                entity.setIsCheck(false);
                StubPostChecked.remove(Viewdata.getPostChecked());
            } else if (Viewdata.getPostdenied() != null) {
                StubDeniedPost.remove(Viewdata.getPostdenied());

            }
            Banlistpostentity.setPostID(entity);
            Stub.edit(entity);

            StubBanlistPost.create(Banlistpostentity);
            request.setAttribute("messeger", "khóa thành công");

            return true;

        } catch (Exception e) {
            e.getMessage();
            request.setAttribute("messeger", "bài này đã bị khóa không thể khóa nữa");

            return false;
        }

    }

    public boolean UnBlockPost(int id) {

        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        StubBanlistPost = new StubBanlistPost();
        Banlistpostentity = new Banlistpost();
        try {
            Banlistpostentity = StubBanlistPost.FindAllPostBanedByID(id);
            StubBanlistPost.remove(Banlistpostentity);
            request.setAttribute("messeger", "Mở khóa thành công");

            return true;

        } catch (Exception e) {
            e.getMessage();
            request.setAttribute("messeger", "mở khóa thất bại ");

            return false;
        }

    }

    public Post RenewDate(Post entity) {

        try {
            Date dateCreatePost = new Date();
            Date ExperiDate = null;
            //SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy hh:mm aaa");

            //sub days and convert calendar to date
            Calendar cal = new GregorianCalendar();
            cal.add(Calendar.DAY_OF_MONTH, 30);
            ExperiDate = cal.getTime();
            if (entity != null) {
                entity.setPostDate(dateCreatePost);
                entity.setExperiDate(ExperiDate);
                return entity;
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }
//sap xep list theo thuoc tinh

    public String SortPost() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();

        String typesort = params.get("sort");
        String product = params.get("product");
        List<ViewData> list = null;
        if (product != null) {
            list = Listview2;
        } else {
            list = ListView;
        }
        try {
            if (ListView != null) {
                if (typesort.equals("time")) {

                    Collections.sort(list, new CompareTime());

                    return null;
                } else {

                    Collections.sort(list, new ComparePrice());

                    return null;

                }
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }
//chuc nang duyet bai post

    public boolean CheckPost() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Stub = new StubPost();

        HttpSession session = request.getSession();
        StubPostChecked StubPostChecked = new StubPostChecked();
        memberentity = (Members) session.getAttribute("manager");
        Postchecked pc = new Postchecked();
        Stub = new StubPost();
        try {
            //set cho post
            entity = new Post();
            entity = Viewdata.getPostentity();
            entity.setIsCheck(true);
            Stub.edit(entity);

            //set cho pc
            Date dateCreate = new Date();
            pc.setCheckedDate(dateCreate);
            pc.setMemberID(memberentity);
            pc.setPostID(entity);
            StubPostChecked.create(pc);
            request.setAttribute("messeger", "Duyệt thành công");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("messeger", "Duyệt thất bại");
            return false;

        }

    }
//chuc nang tu choi bai post

    public boolean DeniedPost() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        memberentity = (Members) session.getAttribute("manager");
        Stub = new StubPost();
        StubDeniedPost StubDeniedPost = new StubDeniedPost();

        try {
            entity = new Post();
            entity = Viewdata.getPostentity();
            Date date = new Date();
            Postdenied.setDateDenied(date);
            Postdenied.setMemberID(memberentity);

            Postdenied.setPostID(entity);

            Stub.edit(entity);
            StubDeniedPost.create(Postdenied);
            request.setAttribute("messeger", "Từ chối tin thành công");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("messeger", "Từ Chối tin thất bại");
            return false;

        }

    }

    public boolean RenewPost() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Stub = new StubPost();

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String id = params.get("id");
        try {
            entity = new Post();
            entity = Stub.FindPostByID(Integer.parseInt(id));
            entity.setIsCheck(false);

            Stub.edit(RenewDate(entity));

            request.setAttribute("messeger", "Gia Hạn thành công đợi duyệt lại");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("messeger", "Gia Hạn thất bại");
            return false;
        }

    }
//chuc nang xoa bai post

    public boolean DeletePost(int id) {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Stub = new StubPost();

        try {
            entity = new Post();
            entity = Stub.FindPostByID(id);
            Stub.remove(entity);

            TypeForm = "";
            typedenied = "";
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("messeger", "Xóa thất bại");
            return false;
        }

    }
//chuc nang chinh sua bai post

    public String EditPost() {

        Stub = new StubPost();
        StubProduct = new StubProduct();
        chuoi = null;
        try {
            System.out.println("edit");
            Stub.edit(entity);
            
            productentity.setPostId(entity);

            productentity.setCategoriesId(Catagoriesentity);
            StubProduct.edit(productentity);
        } catch (Exception e) {
            e.printStackTrace();
            return "trangloi";

        }
        return "ListPosted";

    }

//chuc nang chinh sua bai post danh cho supplier
    public String EditPostsup() {

        Stub = new StubPost();
        StubProduct = new StubProduct();
        ProductsentityController = new ProductsentityController();

        StubCatagories = new StubCatagories();
        StubProvine = new StubProvine();

        try {
            if (part != null) {
                String imgname = ProductsentityController.UploadFile(part);

                productentity.setImage(imgname);
            }
            Catagoriesentity = StubCatagories.find(Catagoriesentity.getCatagoriesId());

            provinceentity = StubProvine.find(provinceentity.getProvinceid());

            entity.setProvinceId(provinceentity);

            Stub.edit(entity);
            productentity.setPostId(entity);

            productentity.setCategoriesId(Catagoriesentity);
            StubProduct.edit(productentity);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }
//set up thong tin cho trang view post

    public String ViewPost() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();

        String indexrow = params.get("indexrow");
        String Action = params.get("Action");

        if (params.get("indexrow") != null) {
            index = Integer.parseInt(indexrow);
        }

        System.out.println("index :" + index);
        entity = new Post();
        memberentity = new Members();
        productentity = new Productsentity();
        Catagoriesentity = new Catagories();
        provinceentity = new Province();
        StubCustomer = new StubCustomer();
        StubCatagories = new StubCatagories();
        StubProvine = new StubProvine();
        StubProduct = new StubProduct();

        if (request.getAttribute("isview") == null && session.getAttribute("customer") != null) {
            MemberSession = new Members();
            MemberSession.setUsername(CustomerController.GetMemberFromSession().getUsername());
            MemberSession.setEmail(CustomerController.GetMemberFromSession().getEmail());
            MemberSession.setPhone(CustomerController.GetMemberFromSession().getPhone());

        }

        try {

            if (Action != null && Action.equals("view2")) {
                Viewdata = Listview2.get(index);
            } else {
                Viewdata = ListView.get(index);
            }
            System.out.println("size lisv" + ListView.size());
            //set banlistpost
            Banlistpostentity.setBanListPostID(Viewdata.getPostId());
            Banlistpostentity.setDateBaned(Viewdata.getBandate());
            Banlistpostentity.setReason(Viewdata.getReasonBaned());
            memberentity = StubCustomer.findByUsername(Viewdata.getNamepersondobaned());
            Banlistpostentity.setMemberID(memberentity);
            //set property for post
            entity = Stub.find(Viewdata.getPostId());

            //set property for product
            productentity = StubProduct.find(Viewdata.getProductId());

            //set member 
            memberentity = StubCustomer.find(Viewdata.getMemberId());

            //set catagories
            Catagoriesentity = StubCatagories.find(Viewdata.getCatagoriesID());

            //set provine
            provinceentity = StubProvine.find(Viewdata.getProvinceID());

            //set date
            dates = Viewdata.getDatetime();

            System.out.println("ttile" + entity.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
            return "trangloi";

        }
        if (Action != null) {
            if (Action.equals("adminview")) {
                TypeForm = "postdetail";
            } else if (Action.equals("adminviewcheck")) {
                TypeForm = "posttocheck";
            } else if (Action.equals("adminviewuser")) {
                TypeForm = "posttouser";
            } else if (Action.equals("view2")) {
                return "ViewPost";
            }
            return null;
        } else {

            return "ViewPost";
        }

    }

    public String NextPrv() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();

        String np = params.get("np");

        System.out.println(index + "index");
        try {
            if (np.equals("next")) {
                if (index < list.size() && index >= 0 && list.get(index) != null) {

                    if (index <= list.size() - 2) {
                        index = index + 1;
                    }
                    return ViewPost();
                } else {
                    System.out.println("het bai");

                }
            } else if (np.equals("prv")) {
                if (index > 0 && index < list.size() && list.get(index) != null) {

                    if (index >= 1) {
                        index = index - 1;
                    }
                    return ViewPost();
                } else {
                    System.out.println("het bai");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "trangloi";

        }
        return "ViewPost";
    }
//tonh toan thoi gian tu ngay dang den ngay hien tai

    public void CaculateTime() {
        obj2 = new ArrayList<>();
        DateTime datenow = new DateTime();
        for (int i = 0; i < list.size(); i++) {

            DateTime datepost = new DateTime(list.get(i).getPostDate());

            Dates Date = new Dates();
            //Date.setDate(Days.daysBetween(datepost, datenow).getDays());
            if (Days.daysBetween(datepost, datenow).getDays() > 0) {
                Date.setTime("cách đây " + Days.daysBetween(datepost, datenow).getDays() + " ngày");
            } else if ((Hours.hoursBetween(datepost, datenow).getHours() % 24) > 0) {
                Date.setTime("cách đây " + Hours.hoursBetween(datepost, datenow).getHours() % 24 + "giờ");
            } else if ((Minutes.minutesBetween(datepost, datenow).getMinutes() % 60) > 0) {
                Date.setTime("cách đây " + Minutes.minutesBetween(datepost, datenow).getMinutes() % 60 + " phút");
            } else {
                Date.setTime("cách đây " + Seconds.secondsBetween(datepost, datenow).getSeconds() % 60 + " giây");
            }
            obj2.add(Date);

        }

    }
//tao phan trang

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

//------------------------------------------------------------------------
//cac pt settup
//set up list tong hop
    public List<ViewData> SetupViewData(List<Post> list) {
        Stub = new StubPost();
        StubReport StubReport = new StubReport();
        List<ViewData> ListView1 = new ArrayList<ViewData>();
        StubCustomer = new StubCustomer();
        StubBanlistPost = new StubBanlistPost();
        ProductsentityController = new ProductsentityController();
        CatagoriesController = new CatagoriesController();
        ProvineController = new ProvineController();
        CustomerController = new CustomerController();
        StubPostChecked StubPostChecked = new StubPostChecked();
        StubDeniedPost StubDeniedPost = new StubDeniedPost();
        try {
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {

                    productentity = new Productsentity();
                    Catagoriesentity = new Catagories();
                    provinceentity = new Province();
                    memberentity = new Members();
                    productentity = ProductsentityController.FindProductentityByPostID(list.get(i).getPostId());
                    Catagoriesentity = CatagoriesController.FindCatagoriesByPostID(list.get(i).getPostId());
                    provinceentity = ProvineController.FindProvinceByID(list.get(i).getPostId());
                    memberentity = StubCustomer.find(list.get(i).getMemberId().getMemberId());
                    Banlistpost Banlistpostentity1 = StubBanlistPost.FindAllPostBanedByID(list.get(i).getPostId());

                    ViewData viewdata = new ViewData();

                    Postchecked postChecked = StubPostChecked.FindAllPostCheckeddByID(list.get(i).getPostId());
                    if (postChecked != null) {
                        viewdata.setPostChecked(postChecked);
                    }

                    List<Report> listreport = new ArrayList<>();
                    listreport = StubReport.FindAllPostReportedByID(list.get(i).getPostId());
                    if (listreport != null) {
                        if (listreport.size() > 0) {
                            viewdata.setListReport(listreport);
                        }
                    }

                    Postdenied Postdenied1 = StubDeniedPost.FindAllPostDeniedByID(list.get(i).getPostId());
                    if (Postdenied1 != null) {
                        viewdata.setPostdenied(Postdenied1);
                    }

                    if (Banlistpostentity1 != null) {
                        viewdata.setBandate(Banlistpostentity1.getDateBaned());
                        viewdata.setNamepersondobaned(Banlistpostentity1.getMemberID().getUsername());
                        viewdata.setReasonBaned(Banlistpostentity1.getReason());
                        viewdata.setBanlistpostentity(Banlistpostentity1);

                    }
                    if (productentity != null) {
                        viewdata.setProductentity(productentity);
                    }
                    if (Catagoriesentity != null) {
                        viewdata.setCatagoriesentity(Catagoriesentity);
                    }
                    if (memberentity != null) {
                        viewdata.setMemberentity(memberentity);
                    }
                    if (provinceentity != null) {
                        viewdata.setProvinceentity(provinceentity);
                    }

                    viewdata.setPostentity(list.get(i));
                    viewdata.setIndex(i);
                    viewdata.setPostId(list.get(i).getPostId());
                    viewdata.setTittle(list.get(i).getTitle());
                    viewdata.setTypeOfPost(list.get(i).getTypeOfPost());
                    viewdata.setProvinceID(list.get(i).getProvinceId().getProvinceid());
                    viewdata.setPostdate(list.get(i).getPostDate());

                    viewdata.setExpdate(list.get(i).getExperiDate());
                    viewdata.setProvinceName(provinceentity.getName());
                    viewdata.setMemberId(list.get(i).getMemberId().getMemberId());
                    viewdata.setMemberName(memberentity.getName());
                    if (obj2 != null) {
                        viewdata.setDatetime(obj2.get(i));
                    }

                    viewdata.setIsCheck(list.get(i).getIsCheck());
                    viewdata.setProductId(productentity.getProductID());
                    viewdata.setNameProduct(productentity.getName());
                    viewdata.setPrice(productentity.getPrice());
                    viewdata.setImgname(productentity.getImage());
                    viewdata.setCatagoriesID(Catagoriesentity.getCatagoriesId());
                    viewdata.setCatagoriesName(Catagoriesentity.getName());

                    ListView1.add(viewdata);
                }

            }
            return ListView1;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
//set up list post theo city

    public String SetupViewCity() {
        Stub = new StubPost();
        entity = new Post();
        ListView = new ArrayList<>();
        Listview2 = new ArrayList<>();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String data = params.get("name");

        if (data != null) {
            System.out.println(data + "thanh pho");
            provinceentity.setName(data);
        }

        try {

            list = FindPostByCity(provinceentity.getName());
            if (list != null && list.size() != 0) {
            CaculateTime();
            ListView = SetupViewData(list);

            for (int i = 0; i < ListView.size(); i++) {
                if (ListView.get(i).getProductentity().getStroreProduct() > 0) {
                    Listview2.add(ListView.get(i));
                }
            }
            for (int i = 0; i < Listview2.size(); i++) {
                Listview2.get(i).setIndex(i);
            }

            for (int i = 0; i < ListView.size(); i++) {
                if (ListView.get(i).getProductentity().getStroreProduct() > 0) {
                    ListView.remove(i);
                    i--;
                }
            }
            for (int i = 0; i < ListView.size(); i++) {
                ListView.get(i).setIndex(i);
            }
            paginator = new RepeatPaginator(ListView);
            paginator2 = new RepeatPaginator(Listview2);
            return "index";
        }
        } catch (Exception e) {
            e.printStackTrace();

        }
        entity = new Post();
        return "index";

    }
//setup list post theo catalo

    public String SetupViewCatagories() {
        Stub = new StubPost();
        ListView = new ArrayList<>();
        Listview2 = new ArrayList<>();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String data = params.get("name");

        if (data != null) {
            Catagoriesentity.setName(data);
        }
        System.out.println(data);
        //System.out.println(provinceentity.getName());
        try {

            list = FindPostByCatagories(Catagoriesentity.getName());
           if (list != null && list.size() != 0) {
            CaculateTime();
            ListView = SetupViewData(list);

            for (int i = 0; i < ListView.size(); i++) {
                if (ListView.get(i).getProductentity().getStroreProduct() > 0) {
                    Listview2.add(ListView.get(i));
                }
            }
            for (int i = 0; i < Listview2.size(); i++) {
                Listview2.get(i).setIndex(i);
            }

            for (int i = 0; i < ListView.size(); i++) {
                if (ListView.get(i).getProductentity().getStroreProduct() > 0) {
                    ListView.remove(i);
                    i--;
                }
            }
            for (int i = 0; i < ListView.size(); i++) {
                ListView.get(i).setIndex(i);
            }
            paginator = new RepeatPaginator(ListView);
            paginator2 = new RepeatPaginator(Listview2);
            return "index";
        }
        } catch (Exception e) {
            e.printStackTrace();

        }
        entity = new Post();
        return "index";

    }

    public String SetupViewCatagoriesAndCity() {
        Stub = new StubPost();
        ListView = new ArrayList<>();
        System.out.println(Catagoriesentity.getName());
        System.out.println(provinceentity.getName());

        try {

            list = FindPostByCatagoriesAndCity(Catagoriesentity.getName(), provinceentity.getName());
            System.out.println(list.size());
            if (list != null && list.size() != 0) {
                CaculateTime();
                ListView = SetupViewData(list);
                return "index";
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "index";

    }

    public String SetupViewTittleAndCity() {
        Stub = new StubPost();
        ListView = new ArrayList<>();
        System.out.println(entity.getTitle());
        System.out.println(provinceentity.getName());
        
        try {

            list = FindPostByTittleAndCity(entity.getTitle(), provinceentity.getName());
            System.out.println(list.size());
            if (list != null && list.size() != 0) {
                CaculateTime();
                ListView = SetupViewData(list);
                return "index";
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "index";

    }

    public String SetupViewTittleAndCityAndCatagories() {
        Stub = new StubPost();
        ListView = new ArrayList<>();

        try {

            list = FindPostByTittleAndCityAndCatagories(entity.getTitle(), provinceentity.getName(), Catagoriesentity.getName());
            System.out.println(list.size());
            if (list != null && list.size() != 0) {
                CaculateTime();
                ListView = SetupViewData(list);
                return "index";
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "index";

    }

    public String SetupViewBookmark() {

        Stub = new StubPost();
        BookmarkController = new BookmarkController();
        CustomerController = new CustomerController();
        chuoi = "";
        ListView = new ArrayList<>();
        try {

            if (CustomerController.GetMemberFromSession() != null) {
                memberentity = CustomerController.GetMemberFromSession();
                if (memberentity != null) {
                    System.out.println(memberentity.getMemberId() + "post member id");
                    List<Bookmark> bmlist = BookmarkController.FindBookmarkByMember(memberentity.getMemberId());

                    if (bmlist == null) {
                        System.out.println("bmlist null");
                    }

                    list = new ArrayList<Post>();
                    if (bmlist != null) {

                        for (int i = 0; i < bmlist.size(); i++) {
                            Post p = new Post();
                            p = Stub.find(bmlist.get(i).getIdofPost().getPostId());

                            list.add(p);
                        }
                    }

                }

            }

            if (list != null) {
                CaculateTime();

                ListView = SetupViewData(list);
                return "BookmarkList";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "trangloi";
        }
        return "trangloi";

    }

//set up list all post de quan ly
    public String SetupViewAllSearchPostForManager() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String id = params.get("id");
        String sendmail = params.get("formblock:sendmail");
        String nameuser = params.get("nameuser");
        String postid = params.get("postid");
        String tittlepost = params.get("tittlepost");
        String typesearch = params.get("searchall:typesearch");
        String catagoriesall = params.get("searchall:catagoriesall");
        String cityall = params.get("searchall:cityall");
        String np = params.get("np");
        String Action = params.get("Action");
        //int index = 0;

        Stub = new StubPost();
        int tongrecord = 0;

        try {

            if (Action == null) {
                list = new ArrayList<Post>();
                tongrecord = Stub.countFindAllPost("", "", null, null, "", "", "", "", "");
                SearchTittle = "";
                SearchCatagories = "";
                SearchProvince = "";
                TypeSearch = "";
                SearchName = "";
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    tranghientai = 1;
                    list = Stub.FindAllPost(SearchTittle, SearchName, null, null, SearchCatagories, SearchProvince, "", "", TypeSearch, 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "AllSearchPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("onload")) {
                list = new ArrayList<Post>();
                tongrecord = Stub.countFindAllPost("", "", null, null, "", "", "", "", "");
                SearchTittle = "";
                SearchCatagories = "";
                SearchProvince = "";
                TypeSearch = "";
                SearchName = "";
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    tranghientai = 1;
                    list = Stub.FindAllPost(SearchTittle, SearchName, null, null, SearchCatagories, SearchProvince, "", "", TypeSearch, 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "AllSearchPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("numberpage")) {
                list = new ArrayList<Post>();
                tongrecord = Stub.countFindAllPost(SearchTittle, SearchName, null, null, SearchCatagories, SearchProvince, "", "", TypeSearch);
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    tranghientai = Integer.parseInt(id);
                    list = Stub.FindAllPost(SearchTittle, SearchName, null, null, SearchCatagories, SearchProvince, "", "", TypeSearch, 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "AllSearchPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("nextprv")) {
                list = new ArrayList<Post>();
                tongrecord = Stub.countFindAllPost(SearchTittle, SearchName, null, null, SearchCatagories, SearchProvince, "", "", TypeSearch);
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    NextPrvPage(np);
                    list = Stub.FindAllPost(SearchTittle, SearchName, null, null, SearchCatagories, SearchProvince, "", "", TypeSearch, 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "AllSearchPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("blockpost")) {
                list = new ArrayList<Post>();
                BlockPost();
                if (sendmail.equals("Có")) {
                    sendMail();
                }
                tongrecord = Stub.countFindAllPost(SearchTittle, SearchName, null, null, SearchCatagories, SearchProvince, "", "", TypeSearch);
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    list = Stub.FindAllPost(SearchTittle, SearchName, null, null, SearchCatagories, SearchProvince, "", "", TypeSearch, 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "AllSearchPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("unblockpost")) {
                list = new ArrayList<Post>();
                UnBlockPost(Integer.parseInt(postid));
                tongrecord = Stub.countFindAllPost(SearchTittle, SearchName, null, null, SearchCatagories, SearchProvince, "", "", TypeSearch);
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    list = Stub.FindAllPost(SearchTittle, SearchName, null, null, SearchCatagories, SearchProvince, "", "", TypeSearch, 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "AllSearchPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("search")) {
                list = new ArrayList<Post>();
                tongrecord = Stub.countFindAllPost(tittlepost, nameuser, null, null, catagoriesall, cityall, "", "", typesearch);
                if (tongrecord > 0) {
                    tranghientai = 1;
                    TypeSearch = typesearch;
                    SearchTittle = tittlepost;
                    SearchCatagories = catagoriesall;
                    SearchProvince = cityall;
                    SearchName = nameuser;
                    taotrang(3, tongrecord);
                    list = Stub.FindAllPost(tittlepost, nameuser, null, null, catagoriesall, cityall, "", "", typesearch, 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";

                    return "AllSearchPost";
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

//set up list all repost post de quan ly    
    public String SetupViewAllSearchReportForManager() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String id = params.get("id");
        String sendmail = params.get("formblock1:sendmail1");
        String nameuser = params.get("nameuser");
        String postid = params.get("postid");
        String tittlepost = params.get("tittlepost");
        String np = params.get("np");
        String Action = params.get("Action");
        //int index = 0;

        Stub = new StubPost();
        int tongrecord = 0;

        try {

            if (Action == null) {
                list = new ArrayList<Post>();
                tongrecord = Stub.countFindAllPost("", "", null, null, "", "", "", "", "Report");
                SearchTittle = "";
                SearchCatagories = "";
                SearchProvince = "";
                TypeSearch = "";
                SearchName = "";
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    tranghientai = 1;
                    list = Stub.FindAllPost(SearchTittle, SearchName, null, null, SearchCatagories, SearchProvince, "", "", "Report", 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "AllSearchReport";
                } else {
                    return "blank";
                }
            } else if (Action.equals("numberpage")) {
                list = new ArrayList<Post>();
                tongrecord = Stub.countFindAllPost(SearchTittle, SearchName, null, null, SearchCatagories, SearchProvince, "", "", "Report");
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    tranghientai = Integer.parseInt(id);
                    list = Stub.FindAllPost(SearchTittle, SearchName, null, null, SearchCatagories, SearchProvince, "", "", "Report", 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "AllSearchReport";
                } else {
                    return "blank";
                }
            } else if (Action.equals("nextprv")) {
                list = new ArrayList<Post>();
                tongrecord = Stub.countFindAllPost(SearchTittle, SearchName, null, null, SearchCatagories, SearchProvince, "", "", "Report");
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    NextPrvPage(np);
                    list = Stub.FindAllPost(SearchTittle, SearchName, null, null, SearchCatagories, SearchProvince, "", "", "Report", 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "AllSearchReport";
                } else {
                    return "blank";
                }
            } else if (Action.equals("blockpost")) {
                list = new ArrayList<Post>();
                BlockPost();
                if (sendmail.equals("Có")) {
                    sendMail();
                }
                tongrecord = Stub.countFindAllPost(SearchTittle, SearchName, null, null, SearchCatagories, SearchProvince, "", "", "Report");
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    list = Stub.FindAllPost(SearchTittle, SearchName, null, null, SearchCatagories, SearchProvince, "", "", "Report", 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "AllSearchReport";
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
//set up list all check post de quan ly

    public String SetupViewSearchCheckPostForManager() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String id = params.get("id");
        String postid = params.get("postid");
        String sendmail = params.get("formblock1:sendmail1");
        if (sendmail == null) {
            sendmail = params.get("formdenied:sendmail");
        }
        String tittlepost = params.get("tittlepost");
        String typesearch = params.get("searchall:typesearch");
        String catagoriesall = params.get("searchall:catagoriesall");
        String cityall = params.get("searchall:cityall");
        String np = params.get("np");
        String Action = params.get("Action");
        //int index = 0;

        Stub = new StubPost();
        int tongrecord = 0;

        try {
            if (Action == null) {
                list = new ArrayList<Post>();
                SearchTittle = "";
                SearchCatagories = "";
                SearchProvince = "";
                TypeSearch = "";
                tongrecord = Stub.countFindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Check");
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    tranghientai = 1;
                    list = Stub.FindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Check", 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "CheckSearchPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("numberpage")) {
                list = new ArrayList<Post>();
                tongrecord = Stub.countFindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Check");
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    tranghientai = Integer.parseInt(id);
                    list = Stub.FindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Check", 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "CheckSearchPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("nextprv")) {
                list = new ArrayList<Post>();
                tongrecord = Stub.countFindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Check");
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    NextPrvPage(np);
                    list = Stub.FindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Check", 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "CheckSearchPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("blockpost")) {
                list = new ArrayList<Post>();
                BlockPost();
                if (sendmail.equals("Có")) {
                    sendMail();
                }
                tongrecord = Stub.countFindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Check");
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    list = Stub.FindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Check", 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "CheckSearchPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("search")) {
                list = new ArrayList<>();
                tongrecord = Stub.countFindAllPost(tittlepost, "", null, null, catagoriesall, cityall, "", "", "Check");
                if (tongrecord > 0) {
                    tranghientai = 1;
                    SearchTittle = tittlepost;
                    SearchCatagories = catagoriesall;
                    SearchProvince = cityall;
                    taotrang(3, tongrecord);
                    list = Stub.FindAllPost(tittlepost, "", null, null, catagoriesall, cityall, "", "", "Check", 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";

                    return "CheckSearchPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("checkpost")) {
                list = new ArrayList<Post>();
                boolean check = CheckPost();
                tongrecord = Stub.countFindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Check");
                if (tongrecord > 0) {
                    taotrang(3, tongrecord);
                    list = Stub.FindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Check", 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    if (check) {
                        return "CheckSearchPost";
                    } else {
                        return "trangloi";
                    }
                } else {
                    return "blank";
                }
            } else if (Action.equals("deniedpost")) {
                list = new ArrayList<Post>();
                boolean check = DeniedPost();
                if (sendmail.equals("Có")) {
                    sendMail();
                }
                tongrecord = Stub.countFindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Check");
                if (tongrecord > 0) {
                    taotrang(3, tongrecord);
                    list = Stub.FindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Check", 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    if (check) {
                        return "CheckSearchPost";
                    } else {
                        return "trangloi";
                    }
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
//set up list all post het han 

    public String SetupViewExpPostForManager() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String id = params.get("id");
        String np = params.get("np");
        String Action = params.get("Action");
        int index = 0;

        Stub = new StubPost();
        int tongrecord = 0;

        try {
            if (Action == null) {
                list = new ArrayList<Post>();
                tongrecord = Stub.FindAllPostExperiDate().size();
                if (tongrecord > 0) {
                    taotrang(3, tongrecord);
                    tranghientai = 1;
                    list = Stub.FindExpPostByPage(3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "ExpPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("numberpage")) {
                list = new ArrayList<Post>();
                tongrecord = Stub.FindAllPostExperiDate().size();
                if (tongrecord > 0) {
                    taotrang(3, tongrecord);
                    tranghientai = Integer.parseInt(id);
                    list = Stub.FindExpPostByPage(3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "ExpPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("nextprv")) {
                list = new ArrayList<Post>();
                tongrecord = Stub.FindAllPostExperiDate().size();
                if (tongrecord > 0) {
                    taotrang(3, tongrecord);
                    NextPrvPage(np);
                    list = Stub.FindExpPostByPage(3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "ExpPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("blockpost")) {
                list = new ArrayList<Post>();
                BlockPost();
                tongrecord = Stub.FindAllPostExperiDate().size();
                if (tongrecord > 0) {
                    taotrang(3, tongrecord);
                    list = Stub.FindExpPostByPage(3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "ExpPost";
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
//set up list all post bi khoa

    public String SetupViewSearchBlockPostForManager() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String id = params.get("id");
        String postid = params.get("postid");
        String tittlepost = params.get("tittlepost");
        String typesearch = params.get("searchall:typesearch");
        String catagoriesall = params.get("searchall:catagoriesall");
        String cityall = params.get("searchall:cityall");
        String np = params.get("np");
        String Action = params.get("Action");
        //int index = 0;

        Stub = new StubPost();
        int tongrecord = 0;

        try {
            if (Action == null) {
                list = new ArrayList<Post>();
                SearchTittle = "";
                SearchCatagories = "";
                SearchProvince = "";
                TypeSearch = "";
                tongrecord = Stub.countFindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Block");
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    tranghientai = 1;
                    list = Stub.FindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Block", 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "BlockSearchPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("numberpage")) {
                list = new ArrayList<Post>();
                tongrecord = Stub.countFindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Block");
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    tranghientai = Integer.parseInt(id);
                    list = Stub.FindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Block", 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "BlockSearchPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("nextprv")) {
                list = new ArrayList<Post>();
                tongrecord = Stub.countFindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Block");
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    NextPrvPage(np);
                    list = Stub.FindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Block", 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "BlockSearchPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("search")) {
                list = new ArrayList<Post>();
                tongrecord = Stub.countFindAllPost(tittlepost, "", null, null, catagoriesall, cityall, "", "", "Block");
                if (tongrecord > 0) {
                    tranghientai = 1;
                    SearchTittle = tittlepost;
                    SearchCatagories = catagoriesall;
                    SearchProvince = cityall;
                    taotrang(3, tongrecord);
                    list = Stub.FindAllPost(tittlepost, "", null, null, catagoriesall, cityall, "", "", "Block", 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";

                    return "BlockSearchPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("unblockpost")) {
                list = new ArrayList<Post>();
                UnBlockPost(Integer.parseInt(postid));
                tongrecord = Stub.countFindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Block");
                if (tongrecord > 0) {
                    taotrang(3, tongrecord);
                    list = Stub.FindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Block", 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "BlockSearchPost";
                } else {
                    return "blank";
                }
            } else if (Action.equals("deletepost")) {
                list = new ArrayList<>();
                DeletePost(Integer.parseInt(postid));
                tongrecord = Stub.countFindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Block");
                if (tongrecord > 0) {
                    taotrang(3, tongrecord);

                    list = Stub.FindAllPost(SearchTittle, "", null, null, SearchCatagories, SearchProvince, "", "", "Block", 3, tranghientai);
                    CaculateTime();
                    ListView = SetupViewData(list);
                    TypeForm = "";
                    typedenied = "";
                    return "BlockSearchPost";
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

//tam------------------------------------
    public String SetupViewListPosted() {
        Stub = new StubPost();
        ListView = new ArrayList<>();
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        try {

            if (session.getAttribute("customer") != null) {
                memberentity = (Members) session.getAttribute("customer");
                System.out.println(memberentity.getName());
                System.out.println(memberentity.getMemberId());
                //list = new ArrayList<Post>();
                list = FindPostByMember(memberentity.getMemberId());

            }
            //System.out.println(list.size());
            if (list != null) {
                CaculateTime();
                ListView = SetupViewData(list);
                return "ListPosted";
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "trangloi";
    }

//chuc nang repost bai post
    public String ReportPost() {

        StubReport StubReport = new StubReport();
        CustomerController = new CustomerController();
        reportentity = new Report();
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        FacesContext context = FacesContext.getCurrentInstance();

        String report = request.getParameter("reportdetail:reporttext");
        Members memberentity = CustomerController.GetMemberFromSession();
        try {
            reportentity.setDesreport(report);
            reportentity.setPostId(entity);
            reportentity.setMemberId(memberentity);
            Date date = new Date();
            reportentity.setReportDate(date);
            StubReport.create(reportentity);

            context.addMessage(null, new FacesMessage("Thông Báo", "Phản hồi thành công!!"));

        } catch (Exception e) {
            e.printStackTrace();
            return "trangloi";

        }
        return "ViewPost";

    }
//chuc nang sendmail den nguoi post

    public String SendMailForPoster() {

        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        FacesContext context = FacesContext.getCurrentInstance();

        String email = request.getParameter("formsend:email");
        String name = request.getParameter("formsend:name");
        String des = request.getParameter("formsend:des");

        String to = Viewdata.getMemberentity().getEmail();

        if (name.length() <= 1 || des.length() <= 1 || email.length() <= 1) {
            context.addMessage(null, new FacesMessage("Thông báo", "Gửi mail không thành công!,vui lòng thử nhập đầy đủ thông tin và thử lại sau!"));
            return "ViewPost";
        } else {
            try {

                if (entity != null) {
                    String from = "lisatthu35@gmail.com";
                    String subject = "Tu Webraovat ";

                    String body = "Xin Chào,tôi tên là :  " + name + "<br/>"
                            + "Email : " + email + "<br/>"
                            + "Yêu cầu : " + des;

                    boolean bodyIsHTML = true;

                    try {
                        MailUtilGmail.sendMail(to, from, subject, body, bodyIsHTML);
                    } catch (Exception ex) {
                        ex.getMessage();
                    }

                    context.addMessage(null, new FacesMessage("Thông báo", "Gửi mail thành công!!"));
                    return "ViewPost";

                } else {
                    context.addMessage(null, new FacesMessage("Thông báo", "Gửi mail không thành công!,vui lòng thử nhập đầy đủ thông tin và lại sau!"));
                    return "ViewPost";

                }

            } catch (Exception e) {
                e.getMessage();
                message = "Mail đăng nhập không dúng, vui lòng nhập chính xác!!!";
            }
            return message;
        }
    }
//--------------------------------------------------
//set up du lieu truoc khi edit post

    public String SetupEditPost() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String id = params.get("id");

        Stub = new StubPost();
        StubCatagories = new StubCatagories();
        StubCustomer = new StubCustomer();
        StubProduct = new StubProduct();
        StubProvine = new StubProvine();
        CustomerController = new CustomerController();
        try {
            entity = Stub.FindPostByID(Integer.parseInt(id));
            productentity = StubProduct.FindProductentityByID(entity.getPostId());
            provinceentity = StubProvine.FindProvinceByID(entity.getPostId());
            memberentity = CustomerController.GetMemberFromSession();
            Catagoriesentity = StubCatagories.FindCatagoriesByID(entity.getPostId());
            chuoi = "edit";
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "posted";
    }
//set up du lieu truoc khi block post

    public String SetupBlockPost() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String index = params.get("index");
        Stub = new StubPost();
        try {

            if (index != "") {
                Viewdata = ListView.get(Integer.parseInt(index));
                TypeForm = "block";

                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return "trangloi";
    }
//set up du lieu truoc khi tu choi post

    public String SetupDeniedPost() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();

        try {

            typedenied = "denied";
            return null;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return "trangloi";
    }
//set up du lieu trc khi xem chi tiet report

    public String SetupViewReportDetails() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String index = params.get("index");
        try {
            Viewdata = ListView.get(Integer.parseInt(index));
            paginator = new RepeatPaginator(Viewdata.getListReport());
            TypeForm = "reportdetail";
            return null;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return "trangloi";
    }
//set up du lieu truoc khi dat hang

    public String SetupOrder() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        CustomerController = new CustomerController();
        HttpSession session = request.getSession();
        try {
            if (tongtien < 1) {
                request.setAttribute("messeger", "Số lượng sản phẩm đặt hàng phải lớn hơn 0 , xin mời cập nhật lại");
                return "Cartpage";
            } else {
                MemberSession = new Members();
                MemberSession.setUsername(CustomerController.GetMemberFromSession().getUsername());
                MemberSession.setEmail(CustomerController.GetMemberFromSession().getEmail());
                MemberSession.setPhone(CustomerController.GetMemberFromSession().getPhone());
                Updatecart();
            }

        } catch (Exception e) {
        }
        return "CheckoutPage";
    }
//set up du lieu cho trang ket qua dat hang

    public String SetupResultOrder() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        try {

            CreateOrderdetail();
            sendMailorder();
            request.setAttribute("messeger", "Đặt hàng thành công , thông tin đơn hàng sẽ được gửi về email");
        } catch (Exception e) {
        }
        return "ResultOrder";
    }

//cach setup cho list sau khi update
    public String SetupListorder() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        CustomerController = new CustomerController();
        StubMakeorder StubMakeorder = new StubMakeorder();
        Stuborderdetail Stuborderdetail = new Stuborderdetail();
        //list don dat hang cua member nay
        List<Makeorder> make = new ArrayList<>();
        //list chi tiet dat hang cua ddh nay
        List<Orderdetails> order = new ArrayList<>();
        HttpSession session = request.getSession();
        try {
            Members mem = new Members();
            mem = CustomerController.GetMemberFromSession();

            make = StubMakeorder.FindAllMakeorderByID(mem.getMemberId());
            if (make != null && make.size() > 0) {
                mem.setMakeorderCollection(make);
                for (int i = 0; i < make.size(); i++) {
                    order = Stuborderdetail.FindAllOrderdetailsByID(make.get(i).getIdOrder());
                    mem.getMakeorderCollection().get(i).setOrderdetailsCollection(order);
                }
                session.setAttribute("customer", mem);
            }

        } catch (Exception e) {
        }
        return "ListOrder";
    }

    public String SetupCart() {

        try {
            for (int i = 0; i < ListCart.size(); i++) {
                List<Integer> temp = new ArrayList<>();
                for (int j = 0; j < ListCart.get(i).getProductsentity().getStroreProduct(); j++) {
                    temp.add(j + 1);
                }
                ListCart.get(i).setCount(temp);
            }

        } catch (Exception e) {
        }
        return "Cartpage";
    }

//pt dieu huong
    public String ControlerSetUpView() {
        Stub = new StubPost();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();

        String Action = params.get("Action");

        if (Action.equals("viewbycity")) {
            return SetupViewCity();

        } else if (Action.equals("viewbycatagories")) {
            return SetupViewCatagories();
        } else if (Action.equals("reviewpost")) {

            return SetupReviewPost();

        } else if (Action.equals("setupposted")) {
            return SetupPosted();

        } else if (Action.equals("setupeditpost")) {
            return SetupEditPost();
        }
        return "trangloi";
    }

//------------------------------------------------------------------------
//----------------------------------------------------------------------
//getter/seter
    public Post getEntity() {
        return entity;
    }

    public void setEntity(Post entity) {
        this.entity = entity;
    }

    public Province getProvinceentity() {
        return provinceentity;
    }

    public void setProvinceentity(Province provinceentity) {
        this.provinceentity = provinceentity;
    }

    public Catagories getCatagoriesentity() {
        return Catagoriesentity;
    }

    public void setCatagoriesentity(Catagories Catagoriesentity) {
        this.Catagoriesentity = Catagoriesentity;
    }

    public String getChuoi() {
        return chuoi;
    }

    public void setChuoi(String chuoi) {
        this.chuoi = chuoi;
    }

    public List<Post> getList() {
        return list;
    }

    public void setList(List<Post> list) {
        this.list = list;
    }

    public StubBookmark getStubbookmark() {
        return Stubbookmark;
    }

    public void setStubbookmark(StubBookmark Stubbookmark) {
        this.Stubbookmark = Stubbookmark;
    }

    public ArrayList<String> getObj1() {
        return obj1;
    }

    public void setObj1(ArrayList<String> obj1) {
        this.obj1 = obj1;
    }

    public Productsentity getProductentity() {
        return productentity;
    }

    public void setProductentity(Productsentity productentity) {
        this.productentity = productentity;
    }

    public String getChuyenmuc() {
        return chuyenmuc;
    }

    public void setChuyenmuc(String chuyenmuc) {
        this.chuyenmuc = chuyenmuc;
    }

    public ArrayList<Dates> getObj2() {
        return obj2;
    }

    public void setObj2(ArrayList<Dates> obj2) {
        this.obj2 = obj2;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<ViewData> getListView() {
        return ListView;
    }

    public void setListView(List<ViewData> ListView) {
        this.ListView = ListView;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public RepeatPaginator getPaginator() {
        return paginator;
    }

    public void setPaginator(RepeatPaginator paginator) {
        this.paginator = paginator;
    }

    public List<Integer> getTrang() {
        return trang;
    }

    public void setTrang(List<Integer> trang) {
        this.trang = trang;
    }

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

    public Banlistpost getBanlistpostentity() {
        return Banlistpostentity;
    }

    public void setBanlistpostentity(Banlistpost Banlistpostentity) {
        this.Banlistpostentity = Banlistpostentity;
    }

    public String getTypeForm() {
        return TypeForm;
    }

    public void setTypeForm(String TypeForm) {
        this.TypeForm = TypeForm;
    }

    public List<Banlistpost> getListPostBaned() {
        return ListPostBaned;
    }

    public void setListPostBaned(List<Banlistpost> ListPostBaned) {
        this.ListPostBaned = ListPostBaned;
    }

    public ViewData getViewdata() {
        return Viewdata;
    }

    public void setViewdata(ViewData Viewdata) {
        this.Viewdata = Viewdata;
    }

    public Postdenied getPostdenied() {
        return Postdenied;
    }

    public void setPostdenied(Postdenied Postdenied) {
        this.Postdenied = Postdenied;
    }

    public int getTongrecord() {
        return tongrecord;
    }

    public void setTongrecord(int tongrecord) {
        this.tongrecord = tongrecord;
    }

    public String getTypedenied() {
        return typedenied;
    }

    public void setTypedenied(String typedenied) {
        this.typedenied = typedenied;
    }

    public Report getReportentity() {
        return reportentity;
    }

    public void setReportentity(Report reportentity) {
        this.reportentity = reportentity;
    }

    public List<Cart> getListCart() {
        return ListCart;
    }

    public void setListCart(List<Cart> ListCart) {
        this.ListCart = ListCart;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RepeatPaginator getPaginator2() {
        return paginator2;
    }

    public void setPaginator2(RepeatPaginator paginator2) {
        this.paginator2 = paginator2;
    }

    public List<ViewData> getListview2() {
        return Listview2;
    }

    public void setListview2(List<ViewData> Listview2) {
        this.Listview2 = Listview2;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public Makeorder getMakeorder() {
        return Makeorder;
    }

    public void setMakeorder(Makeorder Makeorder) {
        this.Makeorder = Makeorder;
    }

    public Orderdetails getOrderdetails() {
        return Orderdetails;
    }

    public void setOrderdetails(Orderdetails Orderdetails) {
        this.Orderdetails = Orderdetails;
    }

    public Members getMemberSession() {
        return MemberSession;
    }

    public void setMemberSession(Members MemberSession) {
        this.MemberSession = MemberSession;
    }

    public Members getMemberentity() {
        return memberentity;
    }

    public void setMemberentity(Members memberentity) {
        this.memberentity = memberentity;
    }

}
