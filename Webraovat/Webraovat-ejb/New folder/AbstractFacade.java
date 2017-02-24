/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import Entity.Bookmark;
import Entity.Catagories;
import Entity.Manager;
import Entity.Members;
import Entity.Post;
import Entity.Productsentity;
import Entity.Province;
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

    public Members findByPhoneMember(int phone) {

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

            String JPQL = "SELECT prd FROM Post p JOIN p.productsentityCollection prd WHERE p.postId=:id";
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

            //String JPQL = " SELECT p FROM Province pr,Post p WHERE p.provinceId = pr.provinceid ";
            //String JPQL = " SELECT p FROM Province pr , Post p WHERE pr.provinceid=:provinceid";
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

            String JPQL = "SELECT p FROM Province pr join fetch pr.postCollection p "
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
            String JPQL = "SELECT p FROM Members m join fetch m.postCollection p WHERE m.memberId=:id";
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
            String JPQL = "SELECT p FROM Post p  WHERE p.isCheck=0";
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
            String JPQL = "SELECT p FROM Post p ";
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

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
