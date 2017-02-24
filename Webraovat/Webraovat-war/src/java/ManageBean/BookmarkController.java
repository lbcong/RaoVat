/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import Entity.Bookmark;
import Entity.Members;
import Entity.Post;
import Stub.StubBookmark;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
@Named(value = "bookmarkController")
@SessionScoped
public class BookmarkController implements Serializable {

    public BookmarkController() {
        entity = new Bookmark();
        Membersentity = new Members();
        Postentity = new Post();
    }

    private Bookmark entity = null;

    private StubBookmark Stub = null;
    private List<Bookmark> list = null;
    Post Postentity = null;
    Members Membersentity = null;

    public String DeleteBookmark(int id) {

        System.out.println(id + "bookmark member id");
        Stub = new StubBookmark();
        try {

            entity = Stub.FindBookmarkByID(id);
            if (entity == null) {
                System.out.println("entity null");
            }

            Stub.remove(entity);

        } catch (Exception e) {
            e.printStackTrace();
            return "trangloi";
        }
        return "BookmarkList";

    }

    public String BookmarkPost() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();

        Stub = new StubBookmark();
        try {
            List<Bookmark> bm = Stub.FindBookmarkByMember(Membersentity.getMemberId());
            if (bm.size() > 0) {
                for (int i = 0; i < bm.size(); i++) {
                    if (bm.get(i).getIdofPost().getPostId() == Postentity.getPostId()) {
                        request.setAttribute("messeger", "Bài đã được lưu");
                        return "ViewPost";
                    }
                }
            }
            entity.setIdofPost(Postentity);
            entity.setMemberId(Membersentity);
            Stub.create(entity);
            request.setAttribute("messeger", "Lưu thành công");

        } catch (Exception e) {
            e.printStackTrace();
            return "trangloi";
        }
        return "ViewPost";

    }

    public List<Bookmark> FindBookmarkByMember(int id) {

        System.out.println(id + "bookmark member id");
        Stub = new StubBookmark();
        try {

            List<Bookmark> list = Stub.FindBookmarkByMember(id);
            //System.out.println(list.size() + "size bookmark");
            //System.out.println(list.get(0).getIdofPost());
            if (list.size() != 0) {

                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;

    }

    public Bookmark getEntity() {
        return entity;
    }

    public void setEntity(Bookmark entity) {
        this.entity = entity;
    }

    public List<Bookmark> getList() {
        return list;
    }

    public void setList(List<Bookmark> list) {
        this.list = list;
    }

    public Post getPostentity() {
        return Postentity;
    }

    public void setPostentity(Post Postentity) {
        this.Postentity = Postentity;
    }

    public Members getMembersentity() {
        return Membersentity;
    }

    public void setMembersentity(Members Membersentity) {
        this.Membersentity = Membersentity;
    }

}
