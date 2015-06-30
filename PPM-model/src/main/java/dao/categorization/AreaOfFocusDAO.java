package dao.categorization;

import model.categorization.AreaOfFocus;
import model.inventory.Portfolio;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Wojciech on 2015-06-29.
 */
public class AreaOfFocusDAO {
    /*public static List<AreaOfFocus> getAll() {
        List areaOfFocuss=null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();


            areaOfFocuss = session.createQuery("from AreaOfFocus").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return areaOfFocuss;
    }
    public static AreaOfFocus getById(Long id) {

        AreaOfFocus areaOfFocus = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            areaOfFocus = (AreaOfFocus) session.get(AreaOfFocus.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return areaOfFocus;


    }*/

    public static AreaOfFocus save(AreaOfFocus areaOfFocus) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.save(areaOfFocus);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return areaOfFocus;
    }

    public static AreaOfFocus update(AreaOfFocus areaOfFocus) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            areaOfFocus=(AreaOfFocus) session.merge(areaOfFocus);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return areaOfFocus;

    }

    public static void delete(AreaOfFocus areaOfFocus) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.delete(areaOfFocus);

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
