package dao.categorization;

import model.categorization.Score;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 * Created by Wojciech on 2015-06-29.
 */
public class ScoreDAO {
    /*public static List<Score> getAll() {
        List scores=null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();


            scores = session.createQuery("from Score").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return scores;
    }
    public static Score getById(Long id) {

        Score score = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            score = (Score) session.get(Score.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return score;


    }*/

    public static Score save(Score score) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.save(score);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return score;
    }

    public static Score update(Score score) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            score=(Score) session.merge(score);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return score;

    }

    public static void delete(Score score) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.delete(score);

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
