package dao.categorization;

import model.categorization.CategoryMembership;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 * Created by Wojciech on 2015-06-29.
 */
public class CategoryMembershipDAO {
    /*public static List<CategoryMembership> getAll() {
        List categoryMemberships=null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();


            categoryMemberships = session.createQuery("from CategoryMembership").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return categoryMemberships;
    }
    public static CategoryMembership getById(Long id) {

        CategoryMembership categoryMembership = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            categoryMembership = (CategoryMembership) session.get(CategoryMembership.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return categoryMembership;


    }*/

    public static CategoryMembership save(CategoryMembership categoryMembership) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.save(categoryMembership);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return categoryMembership;
    }

    public static CategoryMembership update(CategoryMembership categoryMembership) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            categoryMembership=(CategoryMembership) session.merge(categoryMembership);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return categoryMembership;

    }

    public static void delete(CategoryMembership categoryMembership) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.delete(categoryMembership);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
