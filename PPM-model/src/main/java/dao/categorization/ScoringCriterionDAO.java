package dao.categorization;

import model.categorization.Category;
import model.categorization.ScoringCriterion;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Wojciech on 2015-06-29.
 */
public class ScoringCriterionDAO {
    public static List<ScoringCriterion> getAll() {
        List scoringCriterions=null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();


            scoringCriterions = session.createQuery("from ScoringCriterion").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return scoringCriterions;
    }
    public static ScoringCriterion getById(Long id) {

        ScoringCriterion scoringCriterion = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            scoringCriterion = (ScoringCriterion) session.get(ScoringCriterion.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return scoringCriterion;


    }

    public static ScoringCriterion save(ScoringCriterion scoringCriterion) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            Long id = (Long) session.save(scoringCriterion);
            scoringCriterion.setId(id);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return scoringCriterion;
    }

    public static ScoringCriterion update(ScoringCriterion scoringCriterion) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            scoringCriterion=(ScoringCriterion) session.merge(scoringCriterion);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return scoringCriterion;

    }

    public static void delete(ScoringCriterion scoringCriterion) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.delete(scoringCriterion);

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
