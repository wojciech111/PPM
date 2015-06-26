package dao.inventory;

import model.inventory.Portfolio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Wojciech on 2015-06-25.
 */
public class PortfolioDAO {

    public static List<Portfolio> getAll() {
        List portfolios=null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();


            portfolios = session.createQuery("from Portfolio").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return portfolios;
    }
    public static Portfolio getById(Long id) {

        Portfolio portfolio = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            portfolio = (Portfolio) session.get(Portfolio.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return portfolio;


    }

    public static Portfolio save(Portfolio portfolio) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            Long id = (Long) session.save(portfolio);
            portfolio.setId(id);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return portfolio;
    }

    public static Portfolio update(Portfolio portfolio) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            portfolio=(Portfolio) session.merge(portfolio);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return portfolio;

    }

    public static void delete(Portfolio portfolio) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.delete(portfolio);

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
