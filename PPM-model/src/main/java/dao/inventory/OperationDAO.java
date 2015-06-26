package dao.inventory;

import model.inventory.Operation;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Wojciech on 2015-06-25.
 */
public class OperationDAO {

    public static List<Operation> getAll() {
        List operations=null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();


            operations = session.createQuery("from Operation").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return operations;
    }
    public static Operation getById(Long id) {

        Operation operation = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            operation = (Operation) session.get(Operation.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return operation;


    }

    public static Operation save(Operation operation) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            Long id = (Long) session.save(operation);
            operation.setId(id);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return operation;
    }

    public static Operation update(Operation operation) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            operation=(Operation) session.merge(operation);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return operation;

    }

    public static void delete(Operation operation) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.delete(operation);

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
