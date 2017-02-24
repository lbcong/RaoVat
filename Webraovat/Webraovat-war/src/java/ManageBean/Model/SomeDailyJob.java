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
import ManageBean.CatagoriesController;
import ManageBean.CustomerController;
import ManageBean.ProductsentityController;
import ManageBean.ProvineController;
import Stub.StubBanlistPost;
import Stub.StubCustomer;
import Stub.StubDeniedPost;
import Stub.StubPost;
import Stub.StubPostChecked;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
public class SomeDailyJob implements Runnable {

    @Override
    public void run() {

       //System.out.println("he thong tu dong chay");
        //BlockPost();
        

    }

    public List<ViewData> SetupViewData(List<Post> list) {
        StubPost Stub = new StubPost();

        List<ViewData> ListView1 = new ArrayList<ViewData>();
        StubCustomer StubCustomer = new StubCustomer();
        StubBanlistPost StubBanlistPost = new StubBanlistPost();
        ProductsentityController ProductsentityController = new ProductsentityController();
        CatagoriesController CatagoriesController = new CatagoriesController();
        ProvineController ProvineController = new ProvineController();
        CustomerController CustomerController = new CustomerController();
        StubPostChecked StubPostChecked = new StubPostChecked();
        StubDeniedPost StubDeniedPost = new StubDeniedPost();
        try {
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {

                    Productsentity productentity = new Productsentity();
                    Catagories Catagoriesentity = new Catagories();
                    Province provinceentity = new Province();
                    Members memberentity = new Members();
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

   

    public void BlockPost() {

        StubBanlistPost StubBanlistPost = new StubBanlistPost();
        StubPostChecked StubPostChecked = new StubPostChecked();
        StubDeniedPost StubDeniedPost = new StubDeniedPost();
        StubCustomer StubCustomer = new StubCustomer();
        ViewData Viewdata = new ViewData();
        Post entity = new Post();

        StubPost Stub = new StubPost();

        Members memberentity = StubCustomer.findByEmailMember("admincong@gmail.com");
        try {

            List<ViewData> ListView = new ArrayList<>();
            List<Post> list = new ArrayList<Post>();
            int tongrecord = Stub.FindAllPostExperiDate().size();

            if (tongrecord > 0) {
                System.out.println("tests1");
                list = Stub.FindExpPostByPage(1000, 1);

                ListView = SetupViewData(list);
                for (int i = 0; i < ListView.size(); i++) {

                    Viewdata = ListView.get(i);
                    entity = Viewdata.getPostentity();
                    Banlistpost Banlistpostentity1 = StubBanlistPost.FindAllPostBanedByID(entity.getPostId());

                    if (Banlistpostentity1 == null) {
                        Banlistpost Banlistpostentity = new Banlistpost();
                        Date dateCreate = new Date();
                        Banlistpostentity.setDateBaned(dateCreate);
                        Banlistpostentity.setMemberID(memberentity);
                        Banlistpostentity.setReason("Hết hạn");
                        if (Viewdata.getPostChecked() != null) {
                            entity.setIsCheck(false);
                            StubPostChecked.remove(Viewdata.getPostChecked());
                        } else if (Viewdata.getPostdenied() != null) {
                            StubDeniedPost.remove(Viewdata.getPostdenied());

                        }

                        Banlistpostentity.setPostID(entity);

                        Stub.edit(entity);
                        StubBanlistPost.create(Banlistpostentity);
                    }
                }
                //return true;
            }
        } catch (Exception e) {
            e.getMessage();
            // return false;
        }
        // return false;
    }
}
