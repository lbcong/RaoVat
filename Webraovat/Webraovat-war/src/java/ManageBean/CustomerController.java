/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import Entity.Bookmark;
import Entity.Members;
import Entity.Post;
import Entity.Province;
import Entity.Roles;
import Entity.Rolesmanager;
import ManageBean.Model.MailUtilGmail;
import ManageBean.Model.UserData;
import Stub.StubCustomer;
import Stub.StubProvine;
import Stub.StubRolesmanager;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Asus
 */
@Named(value = "customerController")
@SessionScoped
public class CustomerController implements Serializable {

    private Members entity = null;
    private StubCustomer Stub = null;
    private StubRolesmanager StubRolesmanager = null;
    private String message = null;
    String chuoi = null;
    private List<Members> list = null;
    UserData UserData = null;
    private Province provinceentity = null;
    private int tongsotrang = 0;
    private int tranghientai = 0;
    private int tongrecord = 0;
    @ManagedProperty(value = "#{managerController}")
    ManagerController ManagerController = null;
    private Date date = null;
//tam ------------------------------------------
    private String passChuaMH = null;
    private String passMoi = null;
    private int code = 0;
    private String passNhaplai = null;
    private Part part = null;
//---------------------------------------------

    /**
     * Creates a new instance of CustomerController
     */
    public CustomerController() {
        entity = new Members();
        date = new Date();
    }

    @PostConstruct
    public void init() {
        entity = new Members();
        provinceentity = new Province();

    }

//phuong----------------------------------------------------------------
    public String sendMail() {

        String to = entity.getEmail();
        String from = "lisatthu35@gmail.com";
        String subject = "Gửi mã kích hoạt";

        String body = " kích vào đây để kích hoạt accout :" + " <a href=\"http://localhost:8080/Webraovat-war/faces/user/ActivityAccount.xhtml?activitycode="
                + entity.getActivityCode() + "\">http://localhost:8080/Webraovat-war/faces/user/ActivityAccount.xhtml </a>";
        // active?code=123
        boolean bodyIsHTML = true;

        try {

            MailUtilGmail.sendMail(to, from, subject, body, bodyIsHTML);
            message = "Đăng kí thành công, để đăng nhập bạn vui lòng vào email để xác nhận";
            return "register";

        } catch (Exception e) {

            message = "Gửi email không thành công,vui lòng kiểm tra kết nối mạng";
            remoAccount();
            return "register";

        }

    }

    public void remoAccount() {
        entity.setEmail(null);
        entity.setUsername(null);
        entity.setPhone(null);

    }

    public String CheckActivityCode() {

        Stub = new StubCustomer();
        try {
            entity = Stub.findByActivityMember(Integer.parseInt(chuoi));
            entity.setIsActivity(true);
            Stub.edit(entity);
            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            return "trangloi";
        }
    }

//-----------------------------------------------------------------------
    //--------------------------------------------------------------
//pt ho tro
    public boolean CheckEmail(String email) {

        Stub = new StubCustomer();
        try {

            Members entity1 = Stub.findByEmailMember(email);
            //if(list!=null){System.out.println("khac null");}
            //System.out.println(list.size());
            //System.out.println(list);
            if (entity1 == null) {

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    public boolean CheckPhone(String Phone) {

        Stub = new StubCustomer();
        try {

            Members entity1 = Stub.findByPhoneMember(Phone);

            if (entity1 == null) {

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public boolean CheckUsername(String Username) {

        Stub = new StubCustomer();
        try {

            Members entity1 = Stub.findByUsername(Username);

            if (entity1 == null) {

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public boolean CheckActivity(String email) {

        Stub = new StubCustomer();
        try {

            return Stub.findByIsActivity(email);

        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }

    }

    public boolean CheckRole(Members entity) {

        ManagerController = new ManagerController();
        try {
            List<Members> list = new ArrayList();
            list.add(entity);
            List<UserData> UserData1 = new ArrayList<>();
            UserData1 = ManagerController.SetupView(list);
            if (UserData1 != null) {
                if (UserData1.get(0).getRole().get(0).equals("member")) {
                    return true;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public String CheckActivityCode1() {

        Stub = new StubCustomer();
        try {
//
            if (entity == null) {
                System.out.println("enity null");
            }

            int code = entity.getActivityCode();

            System.out.println(code + "code");

            entity = Stub.findByEmailMember(chuoi);

            if (entity == null) {
                System.out.println("enity null");
            }

            if (code == entity.getActivityCode()) {
                entity.setIsActivity(true);
                Stub.edit(entity);

                return "login";

            } else {
                return "ActivityAccount";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "trangloi";
        }
    }

    public String Register() {
        StubRolesmanager = new StubRolesmanager();
        Stub = new StubCustomer();
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String passForm = MD5Encyption.encryptMD5(entity.getPassword());
        try {

            boolean check1 = CheckEmail(entity.getEmail());
            boolean check3 = CheckUsername(entity.getUsername());
            boolean check2 = CheckPhone(entity.getPhone());

            System.out.println(check1);
            System.out.println(check2);
            System.out.println(check3);

            if (check1 && check2 && check3) {

                Date date = new Date();

                entity.setDateCreated(date);
                entity.setActivityCode(rand(100, 10000));
                entity.setPassword(passForm);

                Rolesmanager rl = new Rolesmanager();
                Roles r = new Roles();
                r.setRoleID(1);
                r.setRoleName("member");
                rl.setRoleID(r);
                rl.setUserID(entity);
                Stub.create(entity);
                StubRolesmanager.create(rl);

                request.setAttribute("messeger", "Đăng kí thành công để đăng nhập bạn vui lòng vào email để xác nhận");
                return sendMail();
            } else {
                remoAccount();
                entity = new Members();
                request.setAttribute("messeger", "Email hoặc Số điện thoại đã tồn tại");

            }

        } catch (Exception e) {
            e.getMessage();
            request.setAttribute("messeger", "Đăng kí thất bại");

        }
        return "register";
    }

    public static int rand(int min, int max) {
        try {
            Random rn = new Random();
            int range = max - min + 1;
            int randomNum = min + rn.nextInt(range);
            return randomNum;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
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

    public String Login() {

        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();

        Stub = new StubCustomer();
        Members entity1 = Stub.findByEmailMember(entity.getEmail());
        String passForm = MD5Encyption.encryptMD5(passChuaMH);

        try {
            if (entity1 != null) {
                entity1.setPassword(passForm);
                boolean check2 = CheckRole(entity1);
                boolean check = CheckLogin(entity1.getEmail(), entity1.getPassword());
                boolean check3 = CheckActivity(entity1.getEmail());

                if (check && check3 && check2) {

                    // System.out.println(entity.getDateOfBirh());
                    Date lastAccessTime = new Date();
                    entity1.setLastAcceptDate(lastAccessTime);

                    entity1.setIsLockOut(false);
                    Stub.edit(entity1);
                    //sua code
                    //List<Post> test = (List<Post>) entity1.getPostCollection();
                    session.setAttribute("customer", entity1);

                    return "trangchu";
                } else if (check == false || check2 == false) {
                    request.setAttribute("messeger", "Đăng Nhập Thất Bại");
                    message = "Đăng Nhập Thất Bại";
                    return "login";
                } else if (check3 == false) {
                    request.setAttribute("messeger", "Bạn vui lòng kiểm tra hộp thư để kích hoạt mã xác nhận");

                    return "login";
                }
            }
            request.setAttribute("messeger", "Đăng Nhập Thất Bại");
            message = "Đăng Nhập Thất Bại";
            return "login";
        } catch (Exception e) {
            e.getMessage();

        }
        return "trangloi";
    }

    public String DeleteMember() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        Stub = new StubCustomer();
        String id = params.get("id");
        System.out.println("id" + entity.getMemberId());
        try {
            Stub.remove(entity);
            return "managerUser";

        } catch (Exception e) {

        }
        return "trangloi";
    }

    public String Logout() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();

        try {
            Stub = new StubCustomer();
            if (GetMemberFromSession() != null) {

                session.removeAttribute("customer");
            } else {

                return "trangloi";
            }
        } catch (Exception e) {
            e.getMessage();

        }
        message = null;
        return "trangchu";
    }

    //cong
    public String EditInformation() throws ParseException {
        ProductsentityController ProductsentityController = new ProductsentityController();
        //CalendarView ca = new CalendarView();
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        // myDate is the java.util.Date in yyyy-MM-dd format
        // Converting it into String using formatter
        //Converting the String back to java.util.Date
        Date dt = null;
        if (entity.getDateOfBirh() != null) {
            String x = sm.format(entity.getDateOfBirh());
            dt = sm.parse(x);
        }

//        entity.setDateOfBirh(dt);
        Stub = new StubCustomer();
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        try {

            if (entity != null) {
                Members ent1 = new Members();
                ent1 = Stub.findByEmailMember(entity.getEmail());

                if (part != null) {
                    String imgname = ProductsentityController.UploadFile(part);
                    ent1.setAvatar(imgname);
                }
                ent1.setPhone(entity.getPhone());

                ent1.setName(entity.getUsername());
                ent1.setFacebook(entity.getFacebook());
                ent1.setGender(entity.getGender());

                ent1.setDetailsAddress(entity.getDetailsAddress());
                //ent1.setDateOfBirh(dt);
                Stub.edit(ent1);

                session.setAttribute("customer", ent1);
                request.setAttribute("messeger", "Cập Nhật Thành Công");

            } else {
                return "trangloi";
            }

        } catch (Exception e) {
            e.getMessage();
            request.setAttribute("messeger", "Cập Nhật Thất Bại");
            return "trangloi";

        }

        return "editcustomer";

    }

//tam
    public String changePassword() {
        Stub = new StubCustomer();
        String pass = MD5Encyption.encryptMD5(passNhaplai);
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (passMoi != null && passMoi.equalsIgnoreCase(passNhaplai)) {
            try {
                Members a = GetMemberFromSession();

                a.setPassword(pass);
                Stub.edit(a);
                request.setAttribute("messeger", "Cập nhật thành công!");
                return "changePassword";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("messeger", "Mật khẩu nhập lại không dúng, xin thử lại!!!");
        return "changePassword";
    }

    public String getEmailforget() {
        Stub = new StubCustomer();
        System.out.println("email :" + entity.getEmail());
        String to = entity.getEmail();
        System.out.println(to);
        try {
            Members entity1 = Stub.findByEmailMember(entity.getEmail());
            System.out.println("-----------------");

            if (entity1 != null) {
                entity.setForgetPasswordCode(rand(1000, 10000));
                Stub.edit(entity);
                System.out.println("Ma xac nhận :" + entity.getForgetPasswordCode());
                String from = "leminhtam3311@gmail.com";
                String subject = "Bạn dã yêu cầu lấy lại mật khẩu?? ";

                String body = "Vui lòng nhập mã xác nhận dể cập nhật mật khẩu:  " + entity.getForgetPasswordCode() + " ";
                // active?code=123
                boolean bodyIsHTML = true;
                System.out.println("Chay toi day chua???");

                try {

                    MailUtilGmail.sendMail(to, from, subject, body, bodyIsHTML);
                } catch (MessagingException ex) {

                }
                System.out.println("Chay dc khong????");
                message = "Vui lòng vào mail dể xác nhận mật khẩu!!!";
                return "updatePassword";

            } else {
                message = "Mail dang nhập không dúng, vui lòng nhập chính xác!!!";
                return "forgetpass";

            }

        } catch (Exception e) {
            e.getMessage();
            message = "Mail dang nhập không dúng, vui lòng nhập chính xác!!!";
        }
        return "updatePassword";
    }

    public String updatePassword() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Stub = new StubCustomer();
        String mail = entity.getEmail();
        Members mb = Stub.findByEmailMember(mail);
        int code1 = code;
        String passchuaMH = passChuaMH;
        String passMH = MD5Encyption.encryptMD5(passchuaMH);

        if (mb != null && mb.getForgetPasswordCode() == code1) {
            try {
                mb.setPassword(passMH);
                mb.setForgetPasswordCode(null);
                Stub.edit(mb);
            } catch (Exception e) {
                e.getStackTrace();
            }
            request.setAttribute("messeger", "Cập nhật thành công,vui lòng dang nhập dể tiếp tục!!");
            message = "Thay đổi mật khẩu thành công,vui lòng dang nhập để tiếp tục!";
        } else {
            request.setAttribute("messeger", "Update không thành công, vui lòng ki?m tra l?i thông tin!!");
        }
        message = "Update không thành công, vui lòng kiểm tra lại thông tin!!";
        return message;
    }

    public String SetupEdit() {

        message = null;
        try {
            if (GetMemberFromSession() != null) {
                entity = GetMemberFromSession();
            } else {

                return "trangloi";
            }

        } catch (Exception e) {
            e.getMessage();
            return "trangloi";
        }
        return "editInformation";
    }

    public List<Members> getAllSupplier() {
        List<Members> list = new ArrayList<>();
        Stub = new StubCustomer();
        try {
            
            list = Stub.findAll();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSupplier() == null) {
                    list.remove(i);
                    i--;
                }
            }

        } catch (Exception e) {
        }
        return list;
    }
//-------------------------------------------------------------------
//pt tim kiem

    public String FindMemberByEmail() {
        Stub = new StubCustomer();
        System.out.println("email" + entity.getEmail());

        entity = Stub.findByEmailMember(entity.getEmail());

        list.add(entity);
        return "managerUser";
    }

//--------------------------------------------------------------------
//---------------------------------------------------------------
//get /set
    public Members GetMemberFromSession() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        if (session.getAttribute("customer") != null) {
            Members entity = (Members) session.getAttribute("customer");
            System.out.println(entity.getMemberId() + entity.getEmail());
            return entity;
        }
        return null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

    public String getChuoi() {
        return chuoi;
    }

    public void setChuoi(String chuoi) {
        this.chuoi = chuoi;
    }

    public List<Members> getList() {
        return list;
    }

    public void setList(List<Members> list) {
        this.list = list;
    }

    public Province getProvinceentity() {
        return provinceentity;
    }

    public void setProvinceentity(Province provinceentity) {
        this.provinceentity = provinceentity;
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

    public String getPhone() {
        return entity.getPhone();
    }

    public void setPhone(String phone) {
        entity.setPhone(phone);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserData getUserData() {
        return UserData;
    }

    public void setUserData(UserData UserData) {
        this.UserData = UserData;
    }

    public String getPassChuaMH() {
        return passChuaMH;
    }

    public void setPassChuaMH(String passChuaMH) {
        this.passChuaMH = passChuaMH;
    }

    public String getPassMoi() {
        return passMoi;
    }

    public void setPassMoi(String passMoi) {
        this.passMoi = passMoi;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPassNhaplai() {
        return passNhaplai;
    }

    public void setPassNhaplai(String passNhaplai) {
        this.passNhaplai = passNhaplai;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

}
