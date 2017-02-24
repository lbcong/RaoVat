/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.*;
import Entity.Banlistuser;
import Entity.Bookmark;
import Entity.Catagories;
import Entity.Makeorder;
import Entity.Manager;
import Entity.Members;
import Entity.Post;
import Entity.Postchecked;
import Entity.Postdenied;
import Entity.Productsentity;
import Entity.Province;
import Entity.Report;
import Entity.Roles;
import Entity.Rolesmanager;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Asus
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {

        getEntityManager().persist(entity);
        System.out.println("cre");

    }

    public void edit(T entity) {

        getEntityManager().merge(entity);

        System.out.println("edit");

    }

    public void edit1(Members admin) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            admin = em.merge(admin);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = admin.getMemberId();
                if (find(id) == null) {
//                    throw new NonexistentEntityException("The admin with id " + id + " no longer exists.");

                }
            }
            throw ex;
        } finally {
            if (em != null) {
//                em.close();
            }
        }
    }

    public Catagories test(Productsentity pr){
         try {

            String JPQL = "select c from Catagories  ";
            TypedQuery<Catagories> query = getEntityManager().createQuery(JPQL, Catagories.class);
            query.setParameter("pr", pr);

            return query.getSingleResult();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }
    
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        System.out.println("delete");
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public Members CheckLogin(String email, String password) {
        try {

            String JPQL = "SELECT c FROM Members c WHERE c.email = :email AND  c.password = :password";
            TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            return query.getSingleResult();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }

    }

    public Members findByEmailMember(String email) {

        try {

            String JPQL = "SELECT c FROM Members c WHERE c.email = :email ";
            //TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            query.setParameter("email", email);

            return query.getSingleResult();
        } catch (Exception e) {
            e.getMessage();

        } finally {
            if (getEntityManager() != null) {

            }
        }
        return null;
    }

    public Members findByUsername(String username) {

        try {

            String JPQL = "SELECT c FROM Members c WHERE c.username = :username ";
            TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            query.setParameter("username", username);

            return query.getSingleResult();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }

    }

    public boolean findByIsActivity(String email) {

        try {

            String JPQL = "SELECT c FROM Members c WHERE c.email = :email ";
            TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            query.setParameter("email", email);

            Members mem = query.getSingleResult();
            if (mem.getIsActivity() != null) {
                return mem.getIsActivity();
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (getEntityManager() != null) {

            }
        }
        return false;
    }

    public List<Members> findMemberOnline(Date date) {

        try {

            String JPQL = "SELECT d FROM Members d WHERE d.lastAcceptDate >= :date ";
            TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            query.setParameter("date", date);

            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }

    }

    public Members findByPhoneMember(String phone) {

        try {

            String JPQL = "SELECT d FROM Members d WHERE d.phone = :phone ";
            TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            query.setParameter("phone", phone);

            return query.getSingleResult();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }

    }

    public Post FindPostByID(int id) {
        try {

            String JPQL = "SELECT p FROM Post p WHERE p.postId=:id";
            //Query query = getEntityManager().createQuery(JPQL);
            TypedQuery<Post> query = getEntityManager().createQuery(JPQL, Post.class);

            query.setParameter("id", id);
            return query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public Bookmark FindBookmarkByID(int id) {
        try {

            String JPQL = "SELECT b FROM Post p JOIN p.bookmarkCollection b WHERE p.postId=:id";
            //Query query = getEntityManager().createQuery(JPQL);
            TypedQuery<Bookmark> query = getEntityManager().createQuery(JPQL, Bookmark.class);

            query.setParameter("id", id);
            return query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public Productsentity FindProductentityByID(int id) {
        try {

            String JPQL = "SELECT prd FROM Post p JOIN p.productsentity prd WHERE p.postId=:id";
            //Query query = getEntityManager().createQuery(JPQL);
            TypedQuery<Productsentity> query = getEntityManager().createQuery(JPQL, Productsentity.class);

            query.setParameter("id", id);
            return query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public Catagories FindCatagoriesByID(int id) {
        try {

            String JPQL = "SELECT ca FROM Catagories ca JOIN ca.productsentityCollection prd WHERE prd.postId.postId=:id";
            //Query query = getEntityManager().createQuery(JPQL);
            TypedQuery<Catagories> query = getEntityManager().createQuery(JPQL, Catagories.class);

            query.setParameter("id", id);
            return query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public Province FindProvinceByID(int id) {
        try {

            String JPQL = "SELECT pr FROM Province pr JOIN pr.postCollection p WHERE p.postId=:id";
            //Query query = getEntityManager().createQuery(JPQL);
            TypedQuery<Province> query = getEntityManager().createQuery(JPQL, Province.class);

            query.setParameter("id", id);
            return query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public Manager CheckLoginManager(String username, String password) {
        try {

            String JPQL = "SELECT c FROM Manager c WHERE c.username = :username AND  c.password = :password";
            TypedQuery<Manager> query = getEntityManager().createQuery(JPQL, Manager.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            return query.getSingleResult();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }

    }

    public Manager findByUsernameManager(String username) {

        try {

            String JPQL = "SELECT c FROM Manager c WHERE c.username = :username ";
            TypedQuery<Manager> query = getEntityManager().createQuery(JPQL, Manager.class);
            query.setParameter("username", username);

            return query.getSingleResult();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }

    }

    public Banlistpost FindAllPostBanedByID(int postID) {
        try {

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
            String JPQL = "SELECT b FROM Banlistpost b WHERE b.postID.postId = :postID";
            Query query = getEntityManager().createQuery(JPQL);

            query.setParameter("postID", postID);
            return (Banlistpost) query.getSingleResult();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public Postdenied FindAllPostDeniedByID(int postID) {
        try {

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
            String JPQL = "SELECT b FROM Postdenied b WHERE b.postID.postId = :postID";
            Query query = getEntityManager().createQuery(JPQL);

            query.setParameter("postID", postID);
            return (Postdenied) query.getSingleResult();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public List<Report> FindAllPostReportedByID(int PostID) {
        try {

            String JPQL = "SELECT rp FROM Report rp WHERE rp.postId.postId = :PostID";
            Query query = getEntityManager().createQuery(JPQL);

            query.setParameter("PostID", PostID);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public List<Makeorder> FindAllMakeorderByID(int MemberID) {
        try {

            String JPQL = "SELECT mo FROM Makeorder mo WHERE mo.memberID.memberId = :MemberID order by mo.createDated desc ";
            Query query = getEntityManager().createQuery(JPQL);

            query.setParameter("MemberID", MemberID);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public List<Orderdetails> FindAllOrderdetailsByID(int makeorderID) {

        try {

            String JPQL = "SELECT od FROM Orderdetails od WHERE od.orderID.idOrder = :makeorderID";
            Query query = getEntityManager().createQuery(JPQL);

            query.setParameter("makeorderID", makeorderID);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public List<Postdenied> FindAllPostDeniedByMember(int MemberID) {
        try {

            String JPQL = "SELECT b FROM Postdenied b WHERE b.memberID.memberId = :MemberID";
            Query query = getEntityManager().createQuery(JPQL);

            query.setParameter("MemberID", MemberID);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public List<Banlistpost> FindAllPostBanedByMember(int MemberID) {
        try {

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
            String JPQL = "SELECT b FROM Banlistpost b WHERE b.memberID.memberId = :MemberID";
            Query query = getEntityManager().createQuery(JPQL);

            query.setParameter("MemberID", MemberID);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public List<Postchecked> FindAllPostCheckedByMember(int MemberID) {
        try {

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
            String JPQL = "SELECT b FROM Postchecked b WHERE b.memberID.memberId = :MemberID";
            Query query = getEntityManager().createQuery(JPQL);

            query.setParameter("MemberID", MemberID);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public Postchecked FindAllPostCheckeddByID(int postID) {
        try {

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
            String JPQL = "SELECT b FROM Postchecked b WHERE b.postID.postId = :postID";
            Query query = getEntityManager().createQuery(JPQL);

            query.setParameter("postID", postID);
            return (Postchecked) query.getSingleResult();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public Banlistuser FindAllUSerBanedByID(int UserID) {
        try {

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
            String JPQL = "SELECT b FROM Banlistuser b WHERE b.memberID.memberId = :UserID";
            Query query = getEntityManager().createQuery(JPQL);

            query.setParameter("UserID", UserID);
            return (Banlistuser) query.getSingleResult();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public List<Post> FindPostByCity(String name) {
        try {

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
            String JPQL = "SELECT p FROM Province pr join fetch pr.postCollection p WHERE pr.name=:name";
            Query query = getEntityManager().createQuery(JPQL);

            //TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            query.setParameter("name", name);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public List<Post> FindAllPostExperiDate() {
        try {

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
            String JPQL = "select p from Post p where p.experiDate <=:date";
            Query query = getEntityManager().createQuery(JPQL);
            Date date = new Date();
            //TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            query.setParameter("date", date);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public List<Post> FindPostByIDCity(int id) {
        try {

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
            String JPQL = "SELECT p FROM Province pr join fetch pr.postCollection p WHERE pr.provinceid=:id";
            Query query = getEntityManager().createQuery(JPQL);

            //TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            query.setParameter("id", id);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public List<Post> FindPostByCatagoriesName(String name) {

        try {

            String JPQL = "SELECT p FROM Catagories ca JOIN ca.productsentityCollection pr JOIN Post p WHERE pr.postId.postId = p.postId AND ca.name=:name";
            Query query = getEntityManager().createQuery(JPQL);

            //TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            query.setParameter("name", name);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }

    }

    public List<Post> FindPostByCatagoriesAndCity(String catagories, String city) {

        try {

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
            String JPQL = "SELECT p FROM Catagories ca JOIN ca.productsentityCollection "
                    + "pr JOIN Post p JOIN Province prv WHERE pr.postId.postId = p.postId "
                    + "AND p.provinceId.provinceid = prv.provinceid"
                    + " AND ca.name=:catagories AND prv.name=:city";

            Query query = getEntityManager().createQuery(JPQL);

            //TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            query.setParameter("catagories", catagories);
            query.setParameter("city", city);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }

    }

    public List<Post> FindPostByTittleAndCity(String tittle, String city) {

        try {

            String JPQL = "SELECT p "
                    + "FROM Province pr join fetch pr.postCollection p "
                    + "WHERE pr.name=:city "
                    + "and p.title LIKE CONCAT('%', :tittle, '%') ";

            //String JPQL = "SELECT p FROM Province pr join fetch pr.postCollection p "
            //       + "WHERE pr.name=:city and p.title like :tittle ";
            Query query = getEntityManager().createQuery(JPQL);

            //TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            //query.setParameter("tittle","%"+tittle+"%");
            query.setParameter("tittle", tittle);
            query.setParameter("city", city);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }

    }

    public List<Post> FindPostByTittleAndCityAndCatagories(String tittle, String city, String catagories) {

        try {

            String JPQL = " SELECT p FROM Catagories ca JOIN ca.productsentityCollection "
                    + " pr JOIN Post p JOIN Province prv "
                    + "  WHERE pr.postId.postId = p.postId "
                    + " AND p.provinceId.provinceid = prv.provinceid "
                    + " AND ca.name=:catagories "
                    + " AND prv.name=:city "
                    + " and p.title LIKE CONCAT('%', :tittle, '%') ";

            Query query = getEntityManager().createQuery(JPQL);

            //TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            query.setParameter("tittle", tittle);
            query.setParameter("catagories", catagories);
            query.setParameter("city", city);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }

    }

    public List<Post> FindPostByMember(int id) {
        try {

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
            String JPQL = "SELECT p FROM Members m join fetch m.postCollection p WHERE m.memberId=:id order by p.postDate desc";
            Query query = getEntityManager().createQuery(JPQL);

            //TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            query.setParameter("id", id);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public List<Post> FindAllPostByCheck(int check) {
        try {

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
            String JPQL = "SELECT p FROM Post p  WHERE p.isCheck=0 order by p.postDate desc";
            Query query = getEntityManager().createQuery(JPQL);

            //TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            //query.setParameter("check", check);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public List<Post> FindAllPostByReport(int report) {
        try {

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
            String JPQL = "SELECT p FROM Post p  WHERE p.numberOfReport>=:report";
            Query query = getEntityManager().createQuery(JPQL);

            //TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            query.setParameter("report", report);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public List<Post> FindAllPostByPage(int limit, int tranghientai) {
        try {

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
            String JPQL = "SELECT p FROM Post p order by p.postDate desc";
            Query query = getEntityManager().createQuery(JPQL);
            List<Post> list = getEntityManager().createQuery(JPQL)
                    .setMaxResults(limit)
                    .setFirstResult((tranghientai - 1) * limit).getResultList();
            //TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);

            return list;

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public List<Post> FindNoCheckPostByPage(int limit, int tranghientai) {
        try {

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
            String JPQL = "SELECT p FROM Post p  WHERE p.isCheck=0 order by p.postDate desc";
            Query query = getEntityManager().createQuery(JPQL);
            List<Post> list = getEntityManager().createQuery(JPQL)
                    .setMaxResults(limit)
                    .setFirstResult((tranghientai - 1) * limit).getResultList();
            //TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);

            return list;

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public List<Post> FindExpPostByPage(int limit, int tranghientai) {
        try {

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
            String JPQL = "select p from Post p where p.experiDate <=:date order by p.postDate desc";
            Date date = new Date();

            List<Post> list = getEntityManager().createQuery(JPQL)
                    .setParameter("date", date)
                    .setMaxResults(limit)
                    .setFirstResult((tranghientai - 1) * limit).getResultList();
            //TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);

            return list;

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public List<Banlistpost> FindAllPostBanedByPage(int limit, int tranghientai) {
        try {

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
            String JPQL = "SELECT p FROM Banlistpost p order by p.dateBaned desc";
            Query query = getEntityManager().createQuery(JPQL);

            List<Banlistpost> list = getEntityManager().createQuery(JPQL)
                    .setMaxResults(limit)
                    .setFirstResult((tranghientai - 1) * limit).getResultList();

            return list;

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }
//-----------------------------------

    public List<Post> FindAllPost(String Tittle, String Name, Date beforeDate, Date afterDate, String CatagoriesName, String ProvinceName, String ProductName, String SupplierName, String Type, int limit, int tranghientai) {

          try {
            //s1 from , s2 where , s3 order
            String JPQL = "";
            String S1 = " select  p From Post p ";
            String S2 = "";
            String S3 = "";

            String S4 = " order by p.postDate desc ";

            System.out.println("tt" + Tittle + "-----" + CatagoriesName + "----" + ProvinceName + Type);

//-------------------------------------------------------------------------------
            if (!Tittle.equals("")) {
                S3 = S3 + " (p.title LIKE CONCAT('%', :Tittle, '%')) ";

            }

            if (!CatagoriesName.equals("")) {
                S2 = S2 + " Join Catagories ca "
                        + " join Productsentity pr ";

                if (!S3.equals("")) {
                    S3 = S3 + " and  pr.postId.postId = p.postId "
                            + " and  ca.catagoriesId=pr.categoriesId.catagoriesId "
                            + " and  (ca.name=:CatagoriesName) ";
                } else {
                    S3 = S3 + " pr.postId.postId = p.postId "
                            + " and  ca.catagoriesId=pr.categoriesId.catagoriesId "
                            + " and  (ca.name=:CatagoriesName) ";

                }
            }

            if (!ProvinceName.equals("")) {
                S2 = S2 + " JOIN Province prv ";
                if (!S3.equals("")) {
                    S3 = S3 + " and  p.provinceId.provinceid = prv.provinceid "
                            + " and  (prv.name=:ProvinceName) ";
                } else {
                    S3 = S3 + "   p.provinceId.provinceid = prv.provinceid "
                            + " and  (prv.name=:ProvinceName) ";
                }
            }

            if (!Name.equals("")) {
                S2 = S2 + " JOIN Members m ";
                if (!S3.equals("")) {
                    S3 = S3 + " and  p.memberId.memberId = m.memberId "
                            + " and (m.username LIKE CONCAT('%', :Name, '%')) ";
                } else {
                    S3 = S3 + "   p.memberId.memberId = m.memberId "
                            + " and (m.username LIKE CONCAT('%', :Name, '%')) ";
                }
            }

            if (beforeDate != null && afterDate != null) {

                if (!S3.equals("")) {
                    S3 = S3 + " and  p.postDate between :beforeDate and :afterDate ";

                } else {
                    S3 = S3 + "   p.postDate between :beforeDate and :afterDate ";
                }
            }

            if (!ProductName.equals("")) {
                if (ProductName.equals("Tất cả")) {
                    S2 = S2 + " join Productsentity pr ";
                    if (!S3.equals("")) {
                        S3 = S3 + " and  pr.postId.postId = p.postId "
                                + " and pr.totalProduct>0 ";
                    } else {
                        S3 = S3 + "   pr.postId.postId = p.postId "
                                + " and pr.totalProduct>0 ";
                    }
                } else {
                    S2 = S2 + " join Productsentity pr ";
                    if (!S3.equals("")) {
                        S3 = S3 + " and  pr.postId.postId = p.postId "
                                + " and pr.totalProduct>0 "
                                + " and (pr.name LIKE CONCAT('%', :ProductName, '%')) ";
                    } else {
                        S3 = S3 + "   pr.postId.postId = p.postId "
                                + " and pr.totalProduct>0 "
                                + " and (pr.name LIKE CONCAT('%', :ProductName, '%')) ";
                    }

                }
            }

            

//-------------------------------------------
            if (Type.equals("Check")) {

                if (!S3.equals("")) {
                    S3 = S3
                            + " and  p.isCheck=false "
                            + " and  NOT EXISTS (select b From Banlistpost b where b.postID.postId=p.postId ) "
                            + " and  NOT EXISTS (select pc From Postchecked pc where pc.postID.postId=p.postId ) "
                            + " and  NOT EXISTS (select pd From Postdenied pd where pd.postID.postId=p.postId ) ";
                } else {
                    S3 = S3 + "   p.isCheck=false "
                            + " and  NOT EXISTS (select b From Banlistpost b where b.postID.postId=p.postId ) "
                            + " and  NOT EXISTS (select pc From Postchecked pc where pc.postID.postId=p.postId ) "
                            + " and  NOT EXISTS (select pd From Postdenied pd where pd.postID.postId=p.postId ) ";
                }
            } else if (Type.equals("Block")) {

                S2 = S2 + " JOIN Banlistpost b   ";
                if (!S3.equals("")) {
                    S3 = S3 + " and  b.postID.postId=p.postId ";
                } else {
                    S3 = S3 + "   b.postID.postId=p.postId ";
                }
            } else if (Type.equals("CheckNotBlock")) {
                S2 = S2 + " JOIN Postchecked pc  ";
                if (!S3.equals("")) {
                    S3 = S3 + " and  NOT EXISTS (select b From Banlistpost b where b.postID.postId=p.postId ) "
                            + " and  p.isCheck=true "
                            + " and  pc.postID.postId=p.postId ";
                } else {
                    S3 = S3 + "   NOT EXISTS (select b From Banlistpost b where b.postID.postId=p.postId ) "
                            + " and  p.isCheck=true "
                            + " and  NOT EXISTS (select pd From Postdenied pd where pd.postID.postId=p.postId ) "
                            + " and  pc.postID.postId=p.postId ";
                }
            } else if (Type.equals("Denied")) {
                S2 = S2 + "Join Postdenied pd ";
                if (!S3.equals("")) {
                    S3 = S3 + " and  pd.postID.postId=p.postId  ";
                } else {
                    S3 = S3 + "   pd.postID.postId=p.postId  ";
                }
            }

            if (ProductName.equals("")&&Tittle.equals("") && CatagoriesName.equals("") && ProvinceName.equals("") && Type.equals("") && Name.equals("") && beforeDate == null && afterDate == null) {
                JPQL = "select p from Post p order by p.postDate desc ";
            } else {
                JPQL = S1 + S2 + " where " + S3 + S4;
            }

            if (Type.equals("Report")) {

                JPQL = " select distinct p from Post p JOIN Report rp where rp.postId.postId=p.postId order by rp.reportDate desc";
            }

            System.out.println("jpql" + JPQL);

            Query query = getEntityManager().createQuery(JPQL);

            if (!Tittle.equals("")) {
                query.setParameter("Tittle", Tittle);

            }
            if (!CatagoriesName.equals("")) {
                query.setParameter("CatagoriesName", CatagoriesName);
            }
            if (!ProvinceName.equals("")) {
                query.setParameter("ProvinceName", ProvinceName);
            }

            if (!Name.equals("")) {
                query.setParameter("Name", Name);
            }

            if(!ProductName.equals("")&&!ProductName.equals("Tất cả")){
                query.setParameter("ProductName", ProductName);
            }
            
            if (beforeDate != null && afterDate != null) {
                query.setParameter("beforeDate", beforeDate);
                query.setParameter("afterDate", afterDate);
            }

            List<Post> list = query
                    .setMaxResults(limit)
                    .setFirstResult((tranghientai - 1) * limit).getResultList();

            return list;

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }

    }

    public int countFindAllPost(String Tittle, String Name, Date beforeDate, Date afterDate, String CatagoriesName, String ProvinceName, String ProductName, String SupplierName, String Type) {

        try {
            //s1 from , s2 where , s3 order
            String JPQL = "";
            String S1 = " select  p From Post p ";
            String S2 = "";
            String S3 = "";

            String S4 = " order by p.postDate desc ";

            System.out.println("tt" + Tittle + "-----" + CatagoriesName + "----" + ProvinceName + Type);

//-------------------------------------------------------------------------------
            if (!Tittle.equals("")) {
                S3 = S3 + " (p.title LIKE CONCAT('%', :Tittle, '%')) ";

            }

            if (!CatagoriesName.equals("")) {
                S2 = S2 + " Join Catagories ca "
                        + " join Productsentity pr ";

                if (!S3.equals("")) {
                    S3 = S3 + " and  pr.postId.postId = p.postId "
                            + " and  ca.catagoriesId=pr.categoriesId.catagoriesId "
                            + " and  (ca.name=:CatagoriesName) ";
                } else {
                    S3 = S3 + " pr.postId.postId = p.postId "
                            + " and  ca.catagoriesId=pr.categoriesId.catagoriesId "
                            + " and  (ca.name=:CatagoriesName) ";

                }
            }

            if (!ProvinceName.equals("")) {
                S2 = S2 + " JOIN Province prv ";
                if (!S3.equals("")) {
                    S3 = S3 + " and  p.provinceId.provinceid = prv.provinceid "
                            + " and  (prv.name=:ProvinceName) ";
                } else {
                    S3 = S3 + "   p.provinceId.provinceid = prv.provinceid "
                            + " and  (prv.name=:ProvinceName) ";
                }
            }

            if (!Name.equals("")) {
                S2 = S2 + " JOIN Members m ";
                if (!S3.equals("")) {
                    S3 = S3 + " and  p.memberId.memberId = m.memberId "
                            + " and (m.username LIKE CONCAT('%', :Name, '%')) ";
                } else {
                    S3 = S3 + "   p.memberId.memberId = m.memberId "
                            + " and (m.username LIKE CONCAT('%', :Name, '%')) ";
                }
            }

            if (beforeDate != null && afterDate != null) {

                if (!S3.equals("")) {
                    S3 = S3 + " and  p.postDate between :beforeDate and :afterDate ";

                } else {
                    S3 = S3 + "   p.postDate between :beforeDate and :afterDate ";
                }
            }

            if (!ProductName.equals("")) {
                if (ProductName.equals("Tất cả")) {
                    S2 = S2 + " join Productsentity pr ";
                    if (!S3.equals("")) {
                        S3 = S3 + " and  pr.postId.postId = p.postId "
                                + " and pr.totalProduct>0 ";
                    } else {
                        S3 = S3 + "   pr.postId.postId = p.postId "
                                + " and pr.totalProduct>0 ";
                    }
                } else {
                    S2 = S2 + " join Productsentity pr ";
                    if (!S3.equals("")) {
                        S3 = S3 + " and  pr.postId.postId = p.postId "
                                + " and pr.totalProduct>0 "
                                + " and (pr.name LIKE CONCAT('%', :ProductName, '%')) ";
                    } else {
                        S3 = S3 + "   pr.postId.postId = p.postId "
                                + " and pr.totalProduct>0 "
                                + " and (pr.name LIKE CONCAT('%', :ProductName, '%')) ";
                    }

                }
            }

            

//-------------------------------------------
            if (Type.equals("Check")) {

                if (!S3.equals("")) {
                    S3 = S3
                            + " and  p.isCheck=false "
                            + " and  NOT EXISTS (select b From Banlistpost b where b.postID.postId=p.postId ) "
                            + " and  NOT EXISTS (select pc From Postchecked pc where pc.postID.postId=p.postId ) "
                            + " and  NOT EXISTS (select pd From Postdenied pd where pd.postID.postId=p.postId ) ";
                } else {
                    S3 = S3 + "   p.isCheck=false "
                            + " and  NOT EXISTS (select b From Banlistpost b where b.postID.postId=p.postId ) "
                            + " and  NOT EXISTS (select pc From Postchecked pc where pc.postID.postId=p.postId ) "
                            + " and  NOT EXISTS (select pd From Postdenied pd where pd.postID.postId=p.postId ) ";
                }
            } else if (Type.equals("Block")) {

                S2 = S2 + " JOIN Banlistpost b   ";
                if (!S3.equals("")) {
                    S3 = S3 + " and  b.postID.postId=p.postId ";
                } else {
                    S3 = S3 + "   b.postID.postId=p.postId ";
                }
            } else if (Type.equals("CheckNotBlock")) {
                S2 = S2 + " JOIN Postchecked pc  ";
                if (!S3.equals("")) {
                    S3 = S3 + " and  NOT EXISTS (select b From Banlistpost b where b.postID.postId=p.postId ) "
                            + " and  p.isCheck=true "
                            + " and  pc.postID.postId=p.postId ";
                } else {
                    S3 = S3 + "   NOT EXISTS (select b From Banlistpost b where b.postID.postId=p.postId ) "
                            + " and  p.isCheck=true "
                            + " and  NOT EXISTS (select pd From Postdenied pd where pd.postID.postId=p.postId ) "
                            + " and  pc.postID.postId=p.postId ";
                }
            } else if (Type.equals("Denied")) {
                S2 = S2 + "Join Postdenied pd ";
                if (!S3.equals("")) {
                    S3 = S3 + " and  pd.postID.postId=p.postId  ";
                } else {
                    S3 = S3 + "   pd.postID.postId=p.postId  ";
                }
            }

            if (ProductName.equals("")&&Tittle.equals("") && CatagoriesName.equals("") && ProvinceName.equals("") && Type.equals("") && Name.equals("") && beforeDate == null && afterDate == null) {
                JPQL = "select p from Post p order by p.postDate desc ";
            } else {
                JPQL = S1 + S2 + " where " + S3 + S4;
            }

            if (Type.equals("Report")) {

                JPQL = " select distinct p from Post p JOIN Report rp where rp.postId.postId=p.postId order by rp.reportDate desc";
            }

            System.out.println("jpql" + JPQL);

            Query query = getEntityManager().createQuery(JPQL);

            if (!Tittle.equals("")) {
                query.setParameter("Tittle", Tittle);

            }
            if (!CatagoriesName.equals("")) {
                query.setParameter("CatagoriesName", CatagoriesName);
            }
            if (!ProvinceName.equals("")) {
                query.setParameter("ProvinceName", ProvinceName);
            }

            if (!Name.equals("")) {
                query.setParameter("Name", Name);
            }

            if(!ProductName.equals("")&&!ProductName.equals("Tất cả")){
                query.setParameter("ProductName", ProductName);
            }
            
            if (beforeDate != null && afterDate != null) {
                query.setParameter("beforeDate", beforeDate);
                query.setParameter("afterDate", afterDate);
            }

            List<Post> list = query
                    .getResultList();

            return list.size();

        } catch (Exception e) {
            e.getMessage();
            return -1;
        } finally {
            if (getEntityManager() != null) {

            }
        }

    }
//-----------------------------------

    public List<Makeorder> FindAllMakeorder(int MemberID, String Name, int limit, int tranghientai) {
        String JPQL = "";

        String S1 = " select  mo From Makeorder mo ";
        String S2 = "";
        String S3 = "";

        String S4 = " order by mo.createDated desc ";

        try {

            //---------------------tao cau lenh sql
           

            if (!Name.equals("")) {
                S2 = S2 + " Join Members m ";
                
                if (!S3.equals("")) {
                    S3 = S3 + " and  mo.memberID.memberId = m.memberId "
                            + "  and (m.username LIKE CONCAT('%', :Name, '%'))  ";

                    //S3 = S3+"  and mo.memberID.username LIKE CONCAT('%', :Name, '%') ";
                } else {
                    S3 = S3 + "   mo.memberID.memberId = m.memberId "
                            + "  and (m.username LIKE CONCAT('%', :Name, '%'))  ";
                   // S3 = S3+"   mo.memberID.username LIKE CONCAT('%', :Name, '%') ";
                }
            }

            //----------------------------------tao JPQL
            if (MemberID == 0 && Name.equals("")) {
                JPQL = " select  mo From Makeorder mo order by mo.createDated desc ";
            } else {
                JPQL = S1 + S2 + " where " + S3 + S4;
            }
            System.out.println(JPQL);
            //-----------------------------tao querry + set gia tri
            Query query = getEntityManager().createQuery(JPQL);

            //-----------------------------
            if (MemberID != 0) {
                query.setParameter("MemberID", MemberID);
            }
            if (!Name.equals("")) {
                query.setParameter("Name", Name);
            }

            //-----------------------------------
            List<Makeorder> list = query
                    .setMaxResults(limit)
                    .setFirstResult((tranghientai - 1) * limit).getResultList();

            return list;

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public int countAllMakeorder(int MemberID, String Name) {
        String JPQL = "";

        String S1 = " select  mo From Makeorder mo ";
        String S2 = "";
        String S3 = "";

        String S4 = " order by mo.createDated desc ";

        try {

           if (!Name.equals("")) {
                //S2 = S2 + " Join Members m ";
                
                if (!S3.equals("")) {
//                    S3 = S3 + " and  mo.memberID.memberId = m.memberId "
//                            + "  and (m.username LIKE CONCAT('%', :Name, '%'))  ";

                    S3 = S3+"  and mo.memberID.username LIKE CONCAT('%', :Name, '%') ";
                } else {
//                    S3 = S3 + "   mo.memberID.memberId = m.memberId "
//                            + "  and (m.username LIKE CONCAT('%', :Name, '%'))  ";
                    S3 = S3+"   mo.memberID.username LIKE CONCAT('%', :Name, '%') ";
                }
            }

            //----------------------------------tao JPQL
            if (MemberID == 0 && Name.equals("")) {
                JPQL = " select  mo From Makeorder mo order by mo.createDated desc ";
            } else {
                JPQL = S1 + S2 + " where " + S3 + S4;
            }
            System.out.println(JPQL);
            //-----------------------------tao querry + set gia tri
            Query query = getEntityManager().createQuery(JPQL);

            //-----------------------------
            if (MemberID != 0) {
                query.setParameter("MemberID", MemberID);
            }
            if (!Name.equals("")) {
                query.setParameter("Name", Name);
            }

            //-----------------------------------
            List<Makeorder> list = query
                    .getResultList();

            return list.size();

        } catch (Exception e) {
            return -1;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

//--------------------------------
    public List<Members> FindAllMembers(String Name, String Email, String ProvinceName, String Role, String Type, int limit, int tranghientai) {

        try {
            //s1 from , s2 where , s3 order

            String JPQL = "";
            String S1 = " select  p From Members p ";
            String S2 = "";
            String S3 = "";

            String S4 = " order by p.dateCreated desc ";

            System.out.println("tt" + Name + "-----" + Email + "----" + ProvinceName);

            if (!Name.equals("")) {
                S3 = S3 + " p.username LIKE CONCAT('%', :Name , '%') ";

            }

            if (!Email.equals("")) {
                if (!S3.equals("")) {
                    S3 = S3 + " and p.email LIKE CONCAT('%', :Email , '%') ";
                } else {
                    S3 = S3 + " p.email LIKE CONCAT('%', :Email , '%') ";
                }
            }

            if (!ProvinceName.equals("")) {
                S2 = S2 + " join Province prv ";

                if (!S3.equals("")) {
                    S3 = S3 + " and p.provinceId.provinceid=prv.provinceid "
                            + " and prv.name=:ProvinceName ";
                } else {
                    S3 = S3 + "  p.provinceId.provinceid=prv.provinceid "
                            + " and prv.name=:ProvinceName ";
                }
            }

            if (!Role.equals("")) {
                S2 = S2 + " join Roles r join Rolesmanager rm ";

                if (!S3.equals("")) {
                    S3 = S3 + " and rm.userID.memberId=p.memberId  "
                            + " and r.roleID = rm.roleID.roleID "
                            + " and r.roleName=:Role ";
                } else {
                    S3 = S3 + "  rm.userID.memberId=p.memberId  "
                            + " and r.roleID = rm.roleID.roleID "
                            + " and r.roleName=:Role ";
                }
            }

            if (Type.equals("Block")) {
                S2 = S2 + " JOIN Banlistuser b ";

                if (!S3.equals("")) {
                    S3 = S3 + "  and b.memberID.memberId=p.memberId ";
                } else {
                    S3 = S3 + "  b.memberID.memberId=p.memberId ";
                }
            }

            if (Type.equals("Activity")) {

                if (!S3.equals("")) {
                    S3 = S3 + " and p.isActivity=true ";
                } else {
                    S3 = S3 + " p.isActivity=true ";
                }
            }

            if (Type.equals("Unactivity")) {

                if (!S3.equals("")) {
                    S3 = S3 + " and p.isActivity=false ";
                } else {
                    S3 = S3 + " p.isActivity=false ";
                }
            }

            //---------------------------
            if (Name.equals("") && Email.equals("") && ProvinceName.equals("") && Type.equals("") && Role.equals("")) {
                JPQL = "select p from Members p";
            } else {
                JPQL = S1 + S2 + " where " + S3 + S4;
            }

            System.out.println("jpql" + JPQL);

            Query query = getEntityManager().createQuery(JPQL);

            if (!Name.equals("")) {
                query.setParameter("Name", Name);
            }
            if (!Email.equals("")) {
                query.setParameter("Email", Email);
            }
            if (!ProvinceName.equals("")) {
                query.setParameter("ProvinceName", ProvinceName);
            }
            if (!Role.equals("")) {
                query.setParameter("Role", Role);
            }

            List<Members> list = query
                    .setMaxResults(limit)
                    .setFirstResult((tranghientai - 1) * limit).getResultList();

            return list;

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }

    }

    public int countFindAllMembers(String Name, String Email, String ProvinceName, String Role, String Type) {

        try {
            //s1 from , s2 where , s3 order

            String JPQL = "";
            String S1 = " select  p From Members p ";
            String S2 = "";
            String S3 = "";

            String S4 = " order by p.dateCreated desc ";

            System.out.println("tt" + Name + "-----" + Email + "----" + ProvinceName);

            if (!Name.equals("")) {
                S3 = S3 + " p.username LIKE CONCAT('%', :Name , '%') ";

            }

            if (!Email.equals("")) {
                if (!S3.equals("")) {
                    S3 = S3 + " and p.email LIKE CONCAT('%', :Email , '%') ";
                } else {
                    S3 = S3 + " p.email LIKE CONCAT('%', :Email , '%')";
                }
            }

            if (!ProvinceName.equals("")) {
                S2 = S2 + " join Province prv ";

                if (!S3.equals("")) {
                    S3 = S3 + " and p.provinceId.provinceid=prv.provinceid "
                            + " and prv.name=:ProvinceName ";
                } else {
                    S3 = S3 + "  p.provinceId.provinceid=prv.provinceid "
                            + " and prv.name=:ProvinceName ";
                }
            }

            if (!Role.equals("")) {
                S2 = S2 + " join Roles r join Rolesmanager rm ";

                if (!S3.equals("")) {
                    S3 = S3 + " and rm.userID.memberId=p.memberId  "
                            + " and r.roleID = rm.roleID.roleID "
                            + " and r.roleName=:Role ";
                } else {
                    S3 = S3 + "  rm.userID.memberId=p.memberId  "
                            + " and r.roleID = rm.roleID.roleID "
                            + " and r.roleName=:Role ";
                }
            }

            if (Type.equals("Block")) {
                S2 = S2 + " JOIN Banlistuser b ";

                if (!S3.equals("")) {
                    S3 = S3 + "  and b.memberID.memberId=p.memberId ";
                } else {
                    S3 = S3 + "  b.memberID.memberId=p.memberId ";
                }
            }

            if (Type.equals("Activity")) {

                if (!S3.equals("")) {
                    S3 = S3 + " and p.isActivity=true ";
                } else {
                    S3 = S3 + " p.isActivity=true ";
                }
            }

            if (Type.equals("Unactivity")) {

                if (!S3.equals("")) {
                    S3 = S3 + " and p.isActivity=false ";
                } else {
                    S3 = S3 + " p.isActivity=false ";
                }
            }

            //---------------------------
            if (Name.equals("") && Email.equals("") && ProvinceName.equals("") && Type.equals("") && Role.equals("")) {
                JPQL = "select p from Members p";
            } else {
                JPQL = S1 + S2 + " where " + S3 + S4;
            }

            System.out.println("jpql" + JPQL);

            Query query = getEntityManager().createQuery(JPQL);

            if (!Name.equals("")) {
                query.setParameter("Name", Name);
            }
            if (!Email.equals("")) {
                query.setParameter("Email", Email);
            }
            if (!ProvinceName.equals("")) {
                query.setParameter("ProvinceName", ProvinceName);
            }
            if (!Role.equals("")) {
                query.setParameter("Role", Role);
            }

            List<Members> list = query
                    .getResultList();

            return list.size();

        } catch (Exception e) {
            return -1;
        } finally {
            if (getEntityManager() != null) {

            }
        }

    }
//---------------------------------

    public List<Bookmark> FindBookmarkByMember(int id) {
        try {

            //String JPQL = " SELECT b FROM Members m JOIN m.bookmarkCollection b WHERE m.memberId=:id ";
            String JPQL = " SELECT b FROM Bookmark b WHERE b.memberId.memberId=:id ";
            Query query = getEntityManager().createQuery(JPQL);

            //TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            query.setParameter("id", id);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }
    }

    public List<Rolesmanager> findByUserID(int id) {
        try {

            String JPQL = "SELECT c FROM Rolesmanager c WHERE c.userID.memberId =:memberId";
            TypedQuery<Rolesmanager> query = getEntityManager().createQuery(JPQL, Rolesmanager.class);
            query.setParameter("memberId", id);

            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }

    }

    public Roles findRoleByUserName(String Name) {
        try {

            String JPQL = "SELECT r FROM Roles r WHERE r.roleName = :Name";
            TypedQuery<Roles> query = getEntityManager().createQuery(JPQL, Roles.class);
            query.setParameter("Name", Name);

            return query.getSingleResult();

        } catch (Exception e) {
            return null;
        } finally {
            if (getEntityManager() != null) {

            }
        }

    }

    //phuong-------------------------------------------------------------
    public Members findByActivityMember(Integer activityCode) {

        try {

            String JPQL = "SELECT c FROM Members c WHERE c.activityCode = :activityCode";
            //TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            TypedQuery<Members> query = getEntityManager().createQuery(JPQL, Members.class);
            query.setParameter("activityCode", activityCode);

            return query.getSingleResult();
        } catch (Exception e) {
            e.getMessage();

        } finally {
            if (getEntityManager() != null) {

            }
        }
        return null;
    }

//------------------------------------------------------------------
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
