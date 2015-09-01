package dao.process;

import model.process.Decision;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Wojciech on 2015-06-25.
 */
public class DecisionDAO {

    public static List<Decision> getAll() {
        List decision=null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();


            decision = session.createQuery("from Decision").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return decision;
    }
    public static Decision getById(Long id) {

        Decision decision = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            decision = (Decision) session.get(Decision.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return decision;


    }

    public static Decision save(Decision decision) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            Long id = (Long) session.save(decision);
            decision.setId(id);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return decision;
    }

    public static Decision update(Decision decision) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            decision=(Decision) session.merge(decision);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return decision;

    }

    public static void delete(Decision decision) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.delete(decision);

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
