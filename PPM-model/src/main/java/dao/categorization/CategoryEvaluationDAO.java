package dao.categorization;

import model.categorization.CategoryEvaluation;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 * Created by Wojciech on 2015-06-29.
 */
public class CategoryEvaluationDAO {
    /*public static List<CategoryEvaluation> getAll() {
        List categoryEvaluations=null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();


            categoryEvaluations = session.createQuery("from CategoryEvaluation").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return categoryEvaluations;
    }
    public static CategoryEvaluation getById(Long id) {

        CategoryEvaluation categoryEvaluation = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            categoryEvaluation = (CategoryEvaluation) session.get(CategoryEvaluation.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return categoryEvaluation;


    }*/

    public static CategoryEvaluation save(CategoryEvaluation categoryEvaluation) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.save(categoryEvaluation);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return categoryEvaluation;
    }

    public static CategoryEvaluation update(CategoryEvaluation categoryEvaluation) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            categoryEvaluation=(CategoryEvaluation) session.merge(categoryEvaluation);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return categoryEvaluation;

    }

    public static void delete(CategoryEvaluation categoryEvaluation) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.delete(categoryEvaluation);

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
