package dao.organization;

import model.organization.Employee;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Wojciech on 2015-08-15.
 */
public class EmployeeDAO {
    public static List<Employee> getAll() {
        List employees=null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();


            employees = session.createQuery("from Employee").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return employees;
    }
    public static Employee getById(Long id) {

        Employee employee = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            employee = (Employee) session.get(Employee.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return employee;


    }

    public static Employee save(Employee employee) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            Long id = (Long) session.save(employee);
            employee.setId(id);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return employee;
    }

    public static Employee update(Employee employee) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            employee=(Employee) session.merge(employee);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return employee;

    }

    public static void delete(Employee employee) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.delete(employee);

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
