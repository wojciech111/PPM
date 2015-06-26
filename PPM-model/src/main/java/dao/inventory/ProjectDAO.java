package dao.inventory;

import model.inventory.Project;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Wojciech on 2015-06-25.
 */
public class ProjectDAO {

    public static List<Project> getAll() {
        List projects=null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();


            projects = session.createQuery("from Project").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return projects;
    }
    public static Project getById(Long id) {

        Project project = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            project = (Project) session.get(Project.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return project;


    }

    public static Project save(Project project) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            Long id = (Long) session.save(project);
            project.setId(id);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return project;
    }

    public static Project update(Project project) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            project=(Project) session.merge(project);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return project;

    }

    public static void delete(Project project) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.delete(project);

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
