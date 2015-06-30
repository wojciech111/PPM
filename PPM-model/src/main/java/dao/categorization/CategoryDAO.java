package dao.categorization;

import model.categorization.Category;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Wojciech on 2015-06-29.
 */
public class CategoryDAO {
    public static List<Category> getAll() {
        List categorys=null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();


            categorys = session.createQuery("from Category").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return categorys;
    }
    public static Category getById(Long id) {

        Category category = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            category = (Category) session.get(Category.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return category;


    }

    public static Category save(Category category) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            Long id = (Long) session.save(category);
            category.setId(id);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return category;
    }

    public static Category update(Category category) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            category=(Category) session.merge(category);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return category;

    }

    public static void delete(Category category) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.delete(category);

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
