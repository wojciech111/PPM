package dao.finance;

import model.finance.Cost;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Wojciech on 2015-06-29.
 */
public class CostDAO {
    public static List<Cost> getAll() {
        List costs=null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();


            costs = session.createQuery("from Cost").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return costs;
    }
    public static Cost getById(Long id) {

        Cost cost = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            cost = (Cost) session.get(Cost.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cost;


    }

    public static Cost save(Cost cost) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            Long id = (Long) session.save(cost);
            cost.setId(id);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cost;
    }

    public static Cost update(Cost cost) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            cost=(Cost) session.merge(cost);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cost;

    }

    public static void delete(Cost cost) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.delete(cost);

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
