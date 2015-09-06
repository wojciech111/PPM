package dao.finance;

import model.finance.Budget;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 * Created by Wojciech on 2015-06-29.
 */
public class BudgetDAO {
    /*public static List<Budget> getAll() {
        List budgets=null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();


            budgets = session.createQuery("from Budget").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return budgets;
    }
    public static Budget getById(Long id) {

        Budget budget = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            budget = (Budget) session.get(Budget.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return budget;


    }*/

    public static Budget save(Budget budget) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.save(budget);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return budget;
    }

    public static Budget update(Budget budget) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            budget=(Budget) session.merge(budget);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return budget;

    }

    public static void delete(Budget budget) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.delete(budget);

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
