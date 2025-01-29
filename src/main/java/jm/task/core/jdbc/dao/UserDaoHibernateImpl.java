package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String createTable = "CREATE TABLE IF NOT EXISTS `USERS` (" +
                    "  `Id` BIGINT NOT NULL AUTO_INCREMENT," +
                    "  `Name` VARCHAR(45) NOT NULL," +
                    "  `LastName` VARCHAR(45) NOT NULL," +
                    "  `Age` TINYINT NOT NULL," +
                    "  PRIMARY KEY (`Id`))";
           session.createSQLQuery(createTable).addEntity(User.class).executeUpdate();

            transaction.commit();
            System.out.println("Table has been created by Hibernate");
        } catch (Exception e) {
            System.out.println("Exception occurred while Hibernate creating a table");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String tableDelete = "DROP TABLE IF EXISTS USERS";
            session.createSQLQuery(tableDelete).addEntity(User.class).executeUpdate();
            transaction.commit();
            System.out.println("Table has been dropped by Hibernate");
        } catch (Exception e) {
            System.out.println("Exception occurred while Hibernate dropping a table");
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User " + name + " has been saved by Hibernate");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Exception occurred while Hibernate saving user " + name);
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User userToDelite = (User) session.load(User.class, id);
            session.delete(userToDelite);
            transaction.commit();
            System.out.println("User " + userToDelite.getName() + " has been removed by Hibernate");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Exception occured while removing user by Hibernate, ID = " + id);
        }
    }


    @Override
    public List<User> getAllUsers() {
        List <User> users = new ArrayList<User>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            users = session.createCriteria(User.class).list();
            transaction.commit();
            System.out.println("User list created by Hibernate");

        } catch (Exception e) {
            System.out.println("Exception occurred while getting User list by Hibernate");
            System.out.println(e.getMessage());
        }
        return users;
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE User").executeUpdate();
            transaction.commit();
            System.out.println("The table cleared by Hibernate");
        } catch (Exception e) {
            System.out.println("Exception occurred during Table clear by Hibernate");
            System.out.println(e.getMessage());
        }
    }
}
