package dao.organization;

import model.organization.Organization;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Wojciech on 2015-08-15.
 */
public class OrganizationDAO {
    public static List<Organization> getAll() {
        List organizations=null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();


            organizations = session.createQuery("from Organization").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return organizations;
    }
    public static Organization getById(Long id) {

        Organization organization = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            organization = (Organization) session.get(Organization.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return organization;


    }

    public static Organization save(Organization organization) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            Long id = (Long) session.save(organization);
            organization.setId(id);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return organization;
    }

    public static Organization update(Organization organization) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            organization=(Organization) session.merge(organization);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return organization;

    }

    public static void delete(Organization organization) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.delete(organization);

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
