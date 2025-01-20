package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    public void createUsersTable();
    public void dropUsersTable();
    public void saveUser(String name, String lastName, byte age);
    public void removeUserById(long id);
    public List<User> getAllUsers();
    public void cleanUsersTable();
}

