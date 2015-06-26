package dao.inventory;

import model.inventory.Program;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Wojciech on 2015-06-25.
 */
public class ProgramDAO {

    public static List<Program> getAll() {
        List programs=null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();


            programs = session.createQuery("from Program").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return programs;
    }
    public static Program getById(Long id) {

        Program program = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            program = (Program) session.get(Program.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return program;


    }

    public static Program save(Program program) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            Long id = (Long) session.save(program);
            program.setId(id);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return program;
    }

    public static Program update(Program program) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            program=(Program) session.merge(program);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return program;

    }

    public static void delete(Program program) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.delete(program);

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
