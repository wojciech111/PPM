package dao.inventory;

import model.inventory.Component;
import model.inventory.Project;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Wojciech on 2015-06-25.
 */
public class ComponentDAO {

    public static List<Component> getAll() {
        List components=null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();


            components = session.createQuery("from Component").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return components;
    }
    public static Component getById(Long id) {

        Component component = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            component = (Component) session.get(Component.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return component;


    }

    public static Component save(Component component) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            Long id = (Long) session.save(component);
            component.setId(id);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return component;
    }

    public static Component update(Component component) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            component=(Component) session.merge(component);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return component;

    }

    public static void delete(Component component) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.delete(component);

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
