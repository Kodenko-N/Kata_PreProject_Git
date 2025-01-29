package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDaoJDBC = new UserDaoJDBCImpl();
    UserDaoHibernateImpl userDaoHibernateImpl = new UserDaoHibernateImpl();

    public void createUsersTable() {
        //userDaoJDBC.createUsersTable();
        userDaoHibernateImpl.createUsersTable();
    }

    public void dropUsersTable() {
        //userDaoJDBC.dropUsersTable();
        userDaoHibernateImpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        //userDaoJDBC.saveUser(name, lastName, age);
        userDaoHibernateImpl.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        //userDaoJDBC.removeUserById(id);
        userDaoHibernateImpl.removeUserById(id);
    }

    public List<User> getAllUsers() {
        //return userDaoJDBC.getAllUsers();
        return userDaoHibernateImpl.getAllUsers();
    }

    public void cleanUsersTable() {
        //userDaoJDBC.cleanUsersTable();
        userDaoHibernateImpl.cleanUsersTable();
    }
}
