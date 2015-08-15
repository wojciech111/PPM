package dao.organization;

import model.organization.Stakeholder;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Wojciech on 2015-08-15.
 */
public class StakeholderDAO {
    /*public static List<Stakeholder> getAll() {
        List stakeholders=null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();


            stakeholders = session.createQuery("from Stakeholder").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return stakeholders;
    }
    public static Stakeholder getById(Long id) {

        Stakeholder stakeholder = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            stakeholder = (Stakeholder) session.get(Stakeholder.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return stakeholder;


    }*/

    public static Stakeholder save(Stakeholder stakeholder) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            session.save(stakeholder);


            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return stakeholder;
    }

    public static Stakeholder update(Stakeholder stakeholder) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            stakeholder=(Stakeholder) session.merge(stakeholder);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return stakeholder;

    }

    public static void delete(Stakeholder stakeholder) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.delete(stakeholder);

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
