/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import Entity.Banlistpost;
import Entity.Banlistuser;
import Entity.Bookmark;
import Entity.Catagories;
import Entity.Manager;
import Entity.Members;
import Entity.Post;
import Entity.Postchecked;
import Entity.Postdenied;
import Entity.Productsentity;
import Entity.Province;
import Entity.Roles;
import Entity.Rolesmanager;
import ManageBean.Model.MailUtilGmail;
import ManageBean.Model.SomeDailyJob;
import ManageBean.Model.UserData;
import ManageBean.Model.ViewData;
import Stub.StubBanlistPost;
import Stub.StubBanlistUser;
import Stub.StubCatagories;
import Stub.StubCustomer;
import Stub.StubDeniedPost;
import Stub.StubManager;
import Stub.StubPost;
import Stub.StubPostChecked;
import Stub.StubProduct;
import Stub.StubProvine;
import Stub.StubRoles;
import Stub.StubRolesmanager;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
@Named(value = "managerController")
@SessionScoped
public class ManagerController implements Serializable {

    private Members entity = null;
    private StubCustomer Stub = null;
    private String messager = null;
    private StubRolesmanager StubRolesmanager = null;
    private Banlistuser Banlistuser = null;
    @ManagedProperty(value = "#{postController}")
    PostController PostController ;
//--------------------------
    private int tongsotrang = 0;
    private int tranghientai = 0;
    private int tongrecord = 0;
    private String SearchName = "";
    private String SearchEmail = "";
    private String SearchCity = "";
    private String SearchRole = "";
    private String TypeSearch = "";
    private String TypeForm = "";
    private UserData UserData = null;
    private RepeatPaginator paginator;
    private Chart Chart;
    private int index = 0;

    private Date BeforeDate = null;
    private Date AfterDate = null;
    private Date BeforeDate1 = null;
    private Date AfterDate1 = null;
    //--------------------------
    private StubBanlistUser StubBanlistUser = null;
    private StubPost StubPost = null;
    //------------------
    private List<Integer> trang = null;
    private List<Members> list = null;
    private List<UserData> ListData = null;
    private List<Post> Listpost = null;
    private List<String> Role = null;
//------------------

    public ManagerController() {
        entity = new Members();

        AfterDate = new Date();
        BeforeDate = new Date();
        AfterDate1 = new Date();
        BeforeDate1 = new Date();
    }

    @PostConstruct
    public void init() {
        //PostController = new PostController();
        Banlistuser = new Banlistuser();

    }

    //----------------------------------------------------------------------------
    //ham ho tro
    public boolean sendMail() {
        Stub = new StubCustomer();
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        Members admin = (Members) session.getAttribute("admin");

        List<Members> list1 = new ArrayList<>();
        List<UserData> list2 = new ArrayList<>();
        list1.add(UserData.getMember());
        list2 = SetupView(list1);
        UserData = list2.get(0);

        String to = UserData.getMember().getEmail();
        String from = "lisatthu35@gmail.com";
        String subject = "Thong bao";
        String body = "";

        body = "Tài khoản của bạn đã bị khóa bởi :" + admin.getUsername()
                + " , Lý do bị khóa là :" + UserData.getBanlistuser().getReason();

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

    public boolean CheckLogin(String email, String password) {
        Stub = new StubCustomer();
        try {
            if (Stub.CheckLogin(email, password) != null) {
                return true;
            }

        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    public boolean CheckRole(int id) {

        StubRolesmanager = new StubRolesmanager();
        try {
            List<Rolesmanager> RM = new ArrayList<>();
            RM = StubRolesmanager.findByUserID(id);
            if (RM.size() > 0) {
                for (int i = 0; i < RM.size(); i++) {
                    if (RM.get(i).getRoleID().getRoleName().equals("manager")) {
                        return true;
                    }
                }

            }

        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    public boolean CheckRole2(int id) {

        StubRolesmanager = new StubRolesmanager();
        try {
            List<Rolesmanager> RM = new ArrayList<>();
            RM = StubRolesmanager.findByUserID(id);
            if (RM.size() > 0) {
                for (int i = 0; i < RM.size(); i++) {
                    if (RM.get(i).getRoleID().getRoleName().equals("admin")) {
                        return true;
                    }
                }

            }

        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    public String Login2() {

        Stub = new StubCustomer();
        boolean check1 = false;
        Members entity1 = new Members();
        entity1 = Stub.findByEmailMember(entity.getEmail());

        if (entity1 != null) {
            check1 = CheckRole2(entity1.getMemberId());
        }
        boolean check = CheckLogin(entity.getEmail(), entity.getPassword());
        System.out.println(check);
        System.out.println(check1);
        try {
            if (check && check1) {
                Stub = new StubCustomer();
                HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
                HttpSession session = request.getSession();

                entity = Stub.findByEmailMember(entity.getEmail());

                Date lastAccessTime = new Date();
                entity.setLastAcceptDate(lastAccessTime);

                entity.setIsLockOut(false);
                Stub.edit(entity);

                session.setAttribute("admin", entity);
                entity = new Members();
                return "blank2";
            } else {
                messager = "Đăng Nhập Thất Bại";

            }
        } catch (Exception e) {
            e.getMessage();

        }
        return "loginadmin";
    }

    public String Logout2() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();

        try {
            Stub = new StubCustomer();
            if (session.getAttribute("admin") != null) {

                entity = (Members) session.getAttribute("admin");
                entity = Stub.findByEmailMember(entity.getEmail());

                //nen viet sop kiem tra entity trc khi dung
                entity.setIsLockOut(true);
                Stub.edit(entity);
                entity = new Members();
                session.removeAttribute("admin");

            }
        } catch (Exception e) {
            e.getMessage();

        }
        return "loginadmin";
    }

    public String Login() {
        PostController = new PostController();
        Stub = new StubCustomer();
        StubBanlistUser = new StubBanlistUser();
        Banlistuser Banlistuser1 = new Banlistuser();
        boolean check1 = false;
        boolean check2 = false;
        Members entity1 = new Members();
        entity1 = Stub.findByEmailMember(entity.getEmail());

        if (entity1 != null) {
            check1 = CheckRole(entity1.getMemberId());
            Banlistuser1 = StubBanlistUser.FindAllUSerBanedByID(entity1.getMemberId());
            if (Banlistuser1 == null) {
                check2 = true;
            }

        }
        String passForm = MD5Encyption.encryptMD5(entity.getPassword());
        boolean check = CheckLogin(entity.getEmail(), passForm);

        try {
            if (check && check1 & check2) {
                Stub = new StubCustomer();
                HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
                HttpSession session = request.getSession();

                entity = Stub.findByEmailMember(entity.getEmail());

                Date lastAccessTime = new Date();
                entity.setLastAcceptDate(lastAccessTime);

                entity.setIsLockOut(false);
                Stub.edit(entity);

                session.setAttribute("manager", entity);
                entity = new Members();

                return "blank1";
            } else {
                messager = "Đăng Nhập Thất Bại";

            }
        } catch (Exception e) {
            e.getMessage();

        }
        return "loginmanager";
    }

    public String Logout() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();

        try {
            Stub = new StubCustomer();
            if (session.getAttribute("manager") != null) {

                entity = (Members) session.getAttribute("manager");
                entity = Stub.findByEmailMember(entity.getEmail());

                //nen viet sop kiem tra entity trc khi dung
                entity.setIsLockOut(true);
                Stub.edit(entity);
                entity = new Members();
                session.removeAttribute("manager");

            }
        } catch (Exception e) {
            e.getMessage();

        }
        return "loginmanager";
    }

    public boolean BlockUser() {
        StubBanlistUser = new StubBanlistUser();
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Stub = new StubCustomer();
        HttpSession session = request.getSession();

        try {
            Date dateCreate = new Date();
            Banlistuser.setDateBaned(dateCreate);
            Banlistuser.setMemberID(UserData.getMember());

            StubBanlistUser.create(Banlistuser);
            request.setAttribute("messeger", "khóa thành công");

            return true;

        } catch (Exception e) {
            e.getMessage();
            request.setAttribute("messeger", "bài này đã bị khóa không thể khóa nữa");

            return false;
        }

    }

    public boolean SetRoleforUser(String role) {

        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        StubRolesmanager = new StubRolesmanager();

        try {
            for (int i = 0; i < UserData.getRole().size(); i++) {
                if (role.equals(UserData.getRole().get(i))) {
                    request.setAttribute("messeger", "thành viên này đã có chức vụ này , hãy chọn chức vụ khác");
                    return false;
                } else {
                    StubRoles StubRoles = new StubRoles();
                    Roles Roles = StubRoles.findRoleByUserName(role);
                    List<Rolesmanager> rolemanager = new ArrayList<>();
                    rolemanager = StubRolesmanager.findByUserID(UserData.getMember().getMemberId());
                    rolemanager.get(0).setRoleID(Roles);
                    rolemanager.get(0).setUserID(UserData.getMember());

                    StubRolesmanager.edit(rolemanager.get(0));
                    request.setAttribute("messeger", "Phân chức vị thành công");
                    return true;
                }
            }

        } catch (Exception e) {
            e.getMessage();

        }
        return false;
    }

    public boolean UnBlockUser(int id) {

        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        StubBanlistUser = new StubBanlistUser();
        Banlistuser = new Banlistuser();
        try {
            Banlistuser = StubBanlistUser.FindAllUSerBanedByID(id);
            StubBanlistUser.remove(Banlistuser);
            request.setAttribute("messeger", "Mở khóa thành công");

            return true;

        } catch (Exception e) {
            e.getMessage();
            request.setAttribute("messeger", "mở khóa thất bại ");

            return false;
        }

    }

    public List<UserData> SetupView(List<Members> list) {
        List<UserData> ListData = new ArrayList<>();
        Stub = new StubCustomer();
        StubBanlistUser = new StubBanlistUser();
        StubBanlistPost StubBanlistPost = new StubBanlistPost();
        StubPostChecked StubPostChecked = new StubPostChecked();
        PostController = new PostController();
        StubRolesmanager = new StubRolesmanager();
        StubPost = new StubPost();
        StubDeniedPost StubDeniedPost = new StubDeniedPost();
        try {
            if (list != null) {

                for (int i = 0; i < list.size(); i++) {
                    Members memberentity = new Members();

                    Banlistuser banlistuserentity = new Banlistuser();

                    List<Rolesmanager> listrm = new ArrayList<>();
                    UserData userdata = new UserData();

                    listrm = StubRolesmanager.findByUserID(list.get(i).getMemberId());
                    if (listrm.size() > 0) {
                        List<String> x = new ArrayList<>();
                        for (int j = 0; j < listrm.size(); j++) {
                            x.add(listrm.get(j).getRoleID().getRoleName());
                        }
                        userdata.setRole(x);
                    }
                    List<ViewData> listview = new ArrayList<>();
                    List<Post> listpost = new ArrayList<>();

                    listpost = StubPost.FindPostByMember(list.get(i).getMemberId());
                    if (listpost.size() > 0) {
                        listview = PostController.SetupViewData(listpost);
                        if (listview != null) {
                            userdata.setListpost(listview);
                        }
                    }

                    List<ViewData> listview5 = new ArrayList<>();
                    List<Post> listpost5 = new ArrayList<>();

                    if (BeforeDate1 != null) {
                        listpost5 = StubPost.FindAllPost("", list.get(i).getUsername(), BeforeDate1, AfterDate1, "", "", "","","", 100, 1);

                        if (listpost5.size() > 0) {
                            listview5 = PostController.SetupViewData(listpost5);
                            if (listview5 != null) {
                                userdata.setListpostOnDate(listview5);
                            }
                        }
                    }

                    banlistuserentity = StubBanlistUser.FindAllUSerBanedByID(list.get(i).getMemberId());

                    if (banlistuserentity != null) {
                        userdata.setBanlistuser(banlistuserentity);
                    }
                    userdata.setMember(list.get(i));

                    List<Banlistpost> listbanpost = StubBanlistPost.FindAllPostBanedByMember(list.get(i).getMemberId());
                    List<Post> listpost2 = new ArrayList<>();
                    List<ViewData> listview2 = new ArrayList<>();

                    if (listbanpost != null) {
                        if (listbanpost.size() > 0) {
                            for (int l = 0; l < listbanpost.size(); l++) {
                                listpost2.add(listbanpost.get(l).getPostID());
                            }

                        }
                    }

                    if (listpost2.size() > 0) {
                        listview2 = PostController.SetupViewData(listpost2);
                        if (listview2 != null) {
                            userdata.setListpostBaned(listview2);
                        }
                    }

                    List<Postchecked> listpostChecked = StubPostChecked.FindAllPostCheckedByMember(list.get(i).getMemberId());
                    List<Post> listpost1 = new ArrayList<>();
                    List<ViewData> listview1 = new ArrayList<>();
                    if (listpostChecked != null) {
                        if (listpostChecked.size() > 0) {
                            for (int k = 0; k < listpostChecked.size(); k++) {
                                listpost1.add(listpostChecked.get(k).getPostID());
                            }
                        }
                    }

                    if (listpost1.size() > 0) {
                        listview1 = PostController.SetupViewData(listpost1);
                        if (listview1 != null) {
                            userdata.setListpostChecked(listview1);
                        }
                    }

                    List<Postdenied> listPostdenied = StubDeniedPost.FindAllPostDeniedByMember(list.get(i).getMemberId());
                    List<Post> listpost4 = new ArrayList<>();
                    List<ViewData> listview4 = new ArrayList<>();
                    if (listPostdenied != null) {
                        if (listPostdenied.size() > 0) {
                            for (int k = 0; k < listPostdenied.size(); k++) {
                                listpost4.add(listPostdenied.get(k).getPostID());
                            }
                        }
                    }

                    if (listpost4.size() > 0) {
                        listview4 = PostController.SetupViewData(listpost4);
                        if (listview1 != null) {
                            userdata.setListpostDenied(listview4);
                        }
                    }

                    ListData.add(userdata);
                }
            }
            return ListData;

        } catch (Exception e) {

        }
        return null;
    }

    //----------------------------------------------------------------------------------
    public String SetupViewAllSearchUserForManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String id = params.get("id");
        String userid = params.get("userid");
        String Name = params.get("Name");
        String Email = params.get("Email");
        String checkrole = params.get("roleform:role");
        String sendmail = params.get("blockform:sendmail");
        String cityuser = params.get("searchall:cityuser");
        String rolesearch = params.get("searchall:rolesearch");
        String typesearch = params.get("searchall:typesearch");
        String np = params.get("np");
        String Action = params.get("Action");
        Stub = new StubCustomer();
        PostController= new PostController();
        try {
            if (Action == null) {
                list = new ArrayList<>();
                SearchName = "";
                SearchEmail = "";
                SearchCity = "";
                SearchRole = "";
                TypeSearch = "";
                tongrecord = Stub.countFindAllMembers(SearchName, SearchEmail, SearchCity, SearchRole, TypeSearch);
                if (tongrecord > 0) {
                    taotrang(3, tongrecord);
                    tranghientai = 1;
                    list = Stub.FindAllMembers(SearchName, SearchEmail, SearchCity, SearchRole, TypeSearch, 3, tranghientai);

                    ListData = SetupView(list);
                    TypeForm = "";
                    return "managers";
                } else {
                    return "blank1";
                }
            } else if (Action.equals("numberpage")) {
                list = new ArrayList<>();
                tongrecord = Stub.countFindAllMembers(SearchName, SearchEmail, SearchCity, SearchRole, TypeSearch);
                if (tongrecord > 0) {
                    taotrang(3, tongrecord);
                    tranghientai = Integer.parseInt(id);
                    list = Stub.FindAllMembers(SearchName, SearchEmail, SearchCity, SearchRole, TypeSearch, 3, tranghientai);

                    ListData = SetupView(list);
                    TypeForm = "";
                    return "managers";
                } else {
                    return "blank1";
                }
            } else if (Action.equals("nextprv")) {
                list = new ArrayList<>();
                tongrecord = Stub.countFindAllMembers(SearchName, SearchEmail, SearchCity, SearchRole, TypeSearch);
                if (tongrecord > 0) {
                    taotrang(3, tongrecord);
                    NextPrvPage(np);
                    list = Stub.FindAllMembers(SearchName, SearchEmail, SearchCity, SearchRole, TypeSearch, 3, tranghientai);

                    ListData = SetupView(list);
                    TypeForm = "";
                    return "managers";
                } else {
                    return "blank1";
                }
            } else if (Action.equals("search")) {
                list = new ArrayList<>();
                tongrecord = Stub.countFindAllMembers(Name, Email, cityuser, rolesearch, typesearch);
                if (tongrecord > 0) {
                    tranghientai = 1;
                    TypeSearch = typesearch;
                    SearchName = Name;
                    SearchEmail = Email;
                    SearchCity = cityuser;
                    SearchRole = rolesearch;
                    taotrang(3, tongrecord);
                    list = Stub.FindAllMembers(Name, Email, cityuser, rolesearch, typesearch, 3, tranghientai);

                    ListData = SetupView(list);
                    TypeForm = "";

                    return "managers";
                } else {
                    return "blank1";
                }
            } else if (Action.equals("unblockuser")) {
                list = new ArrayList<>();
                UnBlockUser(Integer.parseInt(userid));
                tongrecord = Stub.countFindAllMembers(SearchName, SearchEmail, SearchCity, SearchRole, TypeSearch);
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    list = Stub.FindAllMembers(SearchName, SearchEmail, SearchCity, SearchRole, TypeSearch, 3, tranghientai);

                    ListData = SetupView(list);
                    TypeForm = "";
                    return "managers";
                } else {
                    return "blank1";
                }
            } else if (Action.equals("blockuser")) {
                list = new ArrayList<>();
                BlockUser();
                if (sendmail.equals("Có")) {
                    sendMail();
                }
                tongrecord = Stub.countFindAllMembers(SearchName, SearchEmail, SearchCity, SearchRole, TypeSearch);
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    list = Stub.FindAllMembers(SearchName, SearchEmail, SearchCity, SearchRole, TypeSearch, 3, tranghientai);

                    ListData = SetupView(list);
                    TypeForm = "";
                    return "managers";
                } else {
                    return "blank1";
                }
            } else if (Action.equals("checkrole")) {
                list = new ArrayList<>();
                SetRoleforUser(checkrole);

                tongrecord = Stub.countFindAllMembers(SearchName, SearchEmail, SearchCity, SearchRole, TypeSearch);
                if (tongrecord > 0) {

                    taotrang(3, tongrecord);
                    list = Stub.FindAllMembers(SearchName, SearchEmail, SearchCity, SearchRole, TypeSearch, 3, tranghientai);

                    ListData = SetupView(list);
                    TypeForm = "";
                    return "managers";
                } else {
                    return "blank1";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "trangloi";
        }
        return "trangloi";
    }

    public String SetupViewReportForManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String id = params.get("id");

        String np = params.get("np");
        String Action = params.get("Action");
        Stub = new StubCustomer();
        StubPost = new StubPost();
        try {
            if (Action == null) {
                List<Post> list = new ArrayList<Post>();
                List<Members> list2 = new ArrayList<Members>();
                int x = StubPost.countFindAllPost("", "", null, null, "", "", "","","");

                if (x > 0) {

                    tranghientai = 1;
                    list = StubPost.FindAllPost("", "", null, null, "", "", "","","", 40, tranghientai);
                    for (int i = 0; i < list.size(); i++) {
                        list2.add(list.get(i).getMemberId());

                    }
                    for (int j = 0; j < list2.size() - 1; j++) {
                        for (int k = j + 1; k < list2.size(); k++) {
                            if (list2.get(j).getEmail().equals(list2.get(k).getEmail())) {
                                list2.remove(k);
                                k--;
                            }
                        }

                    }
                    tongrecord = list2.size();
                    taotrang(3, tongrecord);
                    ListData = SetupView(list2);
                    TypeForm = "";
                    return "index";
                } else {
                    return "blank";
                }
            } else if (Action.equals("numberpage")) {
                List<Post> list = new ArrayList<Post>();
                List<Members> list2 = new ArrayList<Members>();
                int x = StubPost.countFindAllPost("", "", BeforeDate1, AfterDate1, "", "", "","","");
                if (x > 0) {

                    tranghientai = Integer.parseInt(id);
                    list = StubPost.FindAllPost("", "", BeforeDate1, AfterDate1, "", "", "","","", 40, tranghientai);
                    for (int i = 0; i < list.size(); i++) {
                        list2.add(list.get(i).getMemberId());

                    }
                    for (int j = 0; j < list2.size() - 1; j++) {
                        for (int k = j + 1; k < list2.size(); k++) {
                            if (list2.get(j).getEmail().equals(list2.get(k).getEmail())) {
                                list2.remove(k);
                                k--;
                            }
                        }

                    }
                    tongrecord = list2.size();
                    taotrang(3, tongrecord);
                    ListData = SetupView(list2);
                    return "index";
                } else {
                    return "blank";
                }
            } else if (Action.equals("nextprv")) {
                List<Post> list = new ArrayList<Post>();
                List<Members> list2 = new ArrayList<Members>();
                int x = StubPost.countFindAllPost("", "", BeforeDate1, AfterDate1, "", "", "","","");
                if (x > 0) {

                    NextPrvPage(np);
                    list = StubPost.FindAllPost("", "", BeforeDate1, AfterDate1, "", "", "","","", 40, tranghientai);
                    for (int i = 0; i < list.size(); i++) {
                        list2.add(list.get(i).getMemberId());

                    }
                    for (int j = 0; j < list2.size() - 1; j++) {
                        for (int k = j + 1; k < list2.size() - 1; k++) {
                            if (list2.get(j).getEmail().equals(list2.get(k).getEmail())) {
                                list2.remove(k);
                            }
                        }

                    }
                    tongrecord = list2.size();
                    taotrang(3, tongrecord);
                    ListData = SetupView(list2);
                    return "index";
                } else {
                    return "blank";
                }
            } else if (Action.equals("search")) {
                List<Post> list = new ArrayList<Post>();
                List<Members> list2 = new ArrayList<Members>();
                int x = StubPost.countFindAllPost("", "", BeforeDate, AfterDate, "", "", "","","");
                if (x > 0) {
                    tranghientai = 1;
                    BeforeDate1 = BeforeDate;
                    AfterDate1 = AfterDate;

                    list = StubPost.FindAllPost("", "", BeforeDate, AfterDate, "", "", "","","", 40, tranghientai);
                    for (int i = 0; i < list.size(); i++) {
                        list2.add(list.get(i).getMemberId());

                    }
                    for (int j = 0; j < list2.size() - 1; j++) {
                        for (int k = j + 1; k < list2.size(); k++) {
                            if (list2.get(j).getEmail().equals(list2.get(k).getEmail())) {
                                list2.remove(k);
                                k--;
                            }
                        }

                    }
                    tongrecord = list2.size();
                    taotrang(3, tongrecord);
                    ListData = SetupView(list2);
                    Chart = new Chart(ListData);
                    TypeForm = "show";
                    return "index";
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

    public String SetupUser() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String Action = params.get("Action");
        String index = params.get("index");
        try {
            UserData = ListData.get(Integer.parseInt(index));
            TypeForm = "userdetail";
        } catch (Exception e) {
            e.getMessage();

        }
        return "UserInformation";

    }

    public String ViewUserInfo() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();

        String Action = params.get("Action");

        if (Action != null) {

            if (Action.equals("viewuserinfo")) {
                TypeForm = "userdetail";
            }
        }

        try {

        } catch (Exception e) {
            e.printStackTrace();
            return "trangloi";

        }
        return null;
    }

    public String SetupViewUserListPost() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String Action = params.get("Action");

        try {
            if (Action.equals("viewlistpost")) {
                if (UserData.getListpost() != null) {
                    if (UserData.getListpost().size() <= 0) {
                        TypeForm = "khoitao";
                    } else {
                        paginator = new RepeatPaginator(UserData.getListpost());
                        TypeForm = "listpost";
                    }
                }
                return "UserInformation";
            }

        } catch (Exception e) {
        }
        return "trangloi";
    }

    public String SetupViewUserPostChecked() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String Action = params.get("Action");

        try {
            if (Action.equals("viewlistpost")) {
                if (UserData.getListpostChecked() != null) {
                    if (UserData.getListpostChecked().size() <= 0) {
                        TypeForm = "khoitao";
                    } else {
                        paginator = new RepeatPaginator(UserData.getListpostChecked());
                        TypeForm = "listpostchecked";
                    }
                }
                return "UserInformation";
            }

        } catch (Exception e) {
        }
        return "trangloi";
    }

    public String SetupViewUserPostDenied() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String Action = params.get("Action");

        try {
            if (Action.equals("viewlistpost")) {
                if (UserData.getListpostDenied() != null) {
                    if (UserData.getListpostDenied().size() <= 0) {
                        TypeForm = "khoitao";
                    } else {
                        paginator = new RepeatPaginator(UserData.getListpostDenied());
                        TypeForm = "listpostdenied";
                    }
                }
                return "UserInformation";
            }

        } catch (Exception e) {
        }
        return "trangloi";
    }

    public String SetupViewUserPostBaned() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String Action = params.get("Action");

        try {
            if (Action.equals("viewlistpost")) {
                if (UserData.getListpostBaned() != null) {
                    if (UserData.getListpostBaned().size() <= 0) {
                        TypeForm = "khoitao";
                    } else {
                        paginator = new RepeatPaginator(UserData.getListpostBaned());
                        TypeForm = "listpostbaned";

                    }
                }
                return "UserInformation";
            }

        } catch (Exception e) {
        }
        return "trangloi";
    }

    public String ViewUserPost() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String Action = params.get("Action");
        String indexrow = params.get("indexrow");
        try {
            if (Action.equals("viewpost")) {
                if (UserData.getListpost() != null) {
                    if (UserData.getListpost().size() <= 0) {
                        TypeForm = "khoitao";
                    } else {
                        paginator = new RepeatPaginator(UserData.getListpost());
                        index = Integer.parseInt(indexrow);
                        TypeForm = "postdetail";
                    }
                }
                return "UserInformation";
            }

        } catch (Exception e) {
        }
        return "trangloi";
    }

    public String SetupUserRole() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String Action = params.get("Action");
        String indexrow = params.get("indexrow");
        try {
            UserData = ListData.get(Integer.parseInt(indexrow));
            if (Action.equals("setuprole")) {
                if (UserData.getRole() != null) {
                    if (UserData.getRole().size() <= 0) {
                        TypeForm = "khoitao";
                    } else {

                        TypeForm = "role";
                    }
                }
                return null;
            }

        } catch (Exception e) {
        }
        return "trangloi";
    }

    public String ViewUserPostChecked() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String Action = params.get("Action");
        String indexrow = params.get("indexrow");
        try {
            if (Action.equals("viewpost")) {
                if (UserData.getListpostChecked() != null) {
                    if (UserData.getListpostChecked().size() <= 0) {
                        TypeForm = "khoitao";
                    } else {
                        paginator = new RepeatPaginator(UserData.getListpostChecked());
                        index = Integer.parseInt(indexrow);
                        TypeForm = "postdetail";
                    }
                }
                return "UserInformation";
            }

        } catch (Exception e) {
        }
        return "trangloi";
    }

    public String ViewUserPostBaned() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String Action = params.get("Action");
        String indexrow = params.get("indexrow");
        try {
            if (Action.equals("viewpost")) {
                if (UserData.getListpostBaned() != null) {
                    if (UserData.getListpostBaned().size() <= 0) {
                        TypeForm = "khoitao";
                    } else {
                        paginator = new RepeatPaginator(UserData.getListpostBaned());
                        index = Integer.parseInt(indexrow);
                        TypeForm = "postdetail";
                    }
                }
                return "UserInformation";
            }

        } catch (Exception e) {
        }
        return "trangloi";
    }

    public String ViewUserPostDenied() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String Action = params.get("Action");
        String indexrow = params.get("indexrow");
        try {
            if (Action.equals("viewpost")) {
                if (UserData.getListpostDenied() != null) {
                    if (UserData.getListpostDenied().size() <= 0) {
                        TypeForm = "khoitao";
                    } else {
                        paginator = new RepeatPaginator(UserData.getListpostDenied());
                        index = Integer.parseInt(indexrow);
                        TypeForm = "postdetail";
                    }
                }
                return "UserInformation";
            }

        } catch (Exception e) {
        }
        return "trangloi";
    }

    public String SetupBlockUser() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String Action = params.get("Action");
        String index = params.get("index");
        try {
            UserData = ListData.get(Integer.parseInt(index));
            TypeForm = "block";
        } catch (Exception e) {
            e.getMessage();

        }
        return "managers";
    }

    //setup list
    //-----------------------------------------------------
    //get set
    public Integer getMemberId() {
        return entity.getMemberId();
    }

    public void setMemberId(Integer memberId) {
        entity.setMemberId(memberId);
    }

    public String getUsername() {
        return entity.getUsername();
    }

    public void setUsername(String username) {
        entity.setUsername(username);
    }

    public String getPassword() {
        return entity.getPassword();
    }

    public void setPassword(String password) {
        entity.setPassword(password);
    }

    public String getName() {
        return entity.getName();
    }

    public void setName(String name) {
        entity.setName(name);
    }

    public String getEmail() {
        return entity.getEmail();
    }

    public void setEmail(String email) {
        entity.setEmail(email);
    }

    public Date getDateOfBirh() {
        return entity.getDateOfBirh();
    }

    public void setDateOfBirh(Date dateOfBirh) {
        entity.setDateOfBirh(dateOfBirh);
    }

    public String getPhone() {
        return entity.getPhone();
    }

    public String getGmail() {
        return entity.getGmail();
    }

    public void setGmail(String gmail) {
        entity.setGmail(gmail);
    }

    public String getFacebook() {
        return entity.getFacebook();
    }

    public void setFacebook(String facebook) {
        entity.setFacebook(facebook);
    }

    public String getGender() {
        return entity.getGender();
    }

    public void setGender(String gender) {
        entity.setGender(gender);
    }

    public String getStatus() {
        return entity.getStatus();
    }

    public void setStatus(String status) {
        entity.setStatus(status);
    }

    public Date getDateCreated() {
        return entity.getDateCreated();
    }

    public void setDateCreated(Date dateCreated) {
        entity.setDateCreated(dateCreated);
    }

    public Date getLastAcceptDate() {
        return entity.getLastAcceptDate();
    }

    public void setLastAcceptDate(Date lastAcceptDate) {
        entity.setLastAcceptDate(lastAcceptDate);
    }

    public Boolean getIsLockOut() {
        return entity.getIsLockOut();
    }

    public void setIsLockOut(Boolean isLockOut) {
        entity.setIsLockOut(isLockOut);
    }

    public String getDetailsAddress() {
        return entity.getDetailsAddress();
    }

    public void setDetailsAddress(String detailsAddress) {
        entity.setDetailsAddress(detailsAddress);
    }

    public Integer getActivityCode() {
        return entity.getActivityCode();
    }

    public void setActivityCode(Integer activityCode) {
        entity.setActivityCode(activityCode);
    }

    public Boolean getIsActivity() {
        return entity.getIsActivity();
    }

    public void setIsActivity(Boolean isActivity) {
        entity.setIsActivity(isActivity);
    }

    public Collection<Rolesmanager> getRolesmanagerCollection() {
        return entity.getRolesmanagerCollection();
    }

    public void setRolesmanagerCollection(Collection<Rolesmanager> rolesmanagerCollection) {
        entity.setRolesmanagerCollection(rolesmanagerCollection);
    }

    public Collection<Bookmark> getBookmarkCollection() {
        return entity.getBookmarkCollection();
    }

    public void setBookmarkCollection(Collection<Bookmark> bookmarkCollection) {
        entity.setBookmarkCollection(bookmarkCollection);
    }

    public Collection<Banlistpost> getBanlistpostCollection() {
        return entity.getBanlistpostCollection();
    }

    public void setBanlistpostCollection(Collection<Banlistpost> banlistpostCollection) {
        entity.setBanlistpostCollection(banlistpostCollection);
    }

    public Banlistuser getBanlistuser() {
        return Banlistuser;
    }

    public void setBanlistuser(Banlistuser Banlistuser) {
        this.Banlistuser = Banlistuser;
    }

    public Collection<Post> getPostCollection() {
        return entity.getPostCollection();
    }

    public void setPostCollection(Collection<Post> postCollection) {
        entity.setPostCollection(postCollection);
    }

    public Province getProvinceId() {
        return entity.getProvinceId();
    }

    public void setProvinceId(Province provinceId) {
        entity.setProvinceId(provinceId);
    }

    public Members getEntity() {
        return entity;
    }

    public void setEntity(Members entity) {
        this.entity = entity;
    }

    public String getMessager() {
        return messager;
    }

    public void setMessager(String messager) {
        this.messager = messager;
    }

    public StubCustomer getStub() {
        return Stub;
    }

    public void setStub(StubCustomer Stub) {
        this.Stub = Stub;
    }

    public StubRolesmanager getStubRolesmanager() {
        return StubRolesmanager;
    }

    public void setStubRolesmanager(StubRolesmanager StubRolesmanager) {
        this.StubRolesmanager = StubRolesmanager;
    }

    public PostController getPostController() {
        return PostController;
    }

    public void setPostController(PostController PostController) {
        this.PostController = PostController;
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

    public String getSearchEmail() {
        return SearchEmail;
    }

    public void setSearchEmail(String SearchEmail) {
        this.SearchEmail = SearchEmail;
    }

    public String getSearchCity() {
        return SearchCity;
    }

    public void setSearchCity(String SearchCity) {
        this.SearchCity = SearchCity;
    }

    public String getSearchRole() {
        return SearchRole;
    }

    public void setSearchRole(String SearchRole) {
        this.SearchRole = SearchRole;
    }

    public String getTypeSearch() {
        return TypeSearch;
    }

    public void setTypeSearch(String TypeSearch) {
        this.TypeSearch = TypeSearch;
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

    public List<Members> getList() {
        return list;
    }

    public void setList(List<Members> list) {
        this.list = list;
    }

    public UserData getUserData() {
        return UserData;
    }

    public void setUserData(UserData UserData) {
        this.UserData = UserData;
    }

    public List<UserData> getListData() {
        return ListData;
    }

    public void setListData(List<UserData> ListData) {
        this.ListData = ListData;
    }

    public RepeatPaginator getPaginator() {
        return paginator;
    }

    public void setPaginator(RepeatPaginator paginator) {
        this.paginator = paginator;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public StubBanlistUser getStubBanlistUser() {
        return StubBanlistUser;
    }

    public void setStubBanlistUser(StubBanlistUser StubBanlistUser) {
        this.StubBanlistUser = StubBanlistUser;
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

    public Chart getChart() {
        return Chart;
    }

    public void setChart(Chart Chart) {
        this.Chart = Chart;
    }

}
