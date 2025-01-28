package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        String createTable = "CREATE TABLE IF NOT EXISTS `USERS` (" +
                "  `Id` BIGINT NOT NULL AUTO_INCREMENT," +
                "  `Name` VARCHAR(45) NOT NULL," +
                "  `LastName` VARCHAR(45) NOT NULL," +
                "  `Age` TINYINT NOT NULL," +
                "  PRIMARY KEY (`Id`))";
        try (Statement stmt = Util.getConnection().createStatement()) {
            stmt.executeUpdate(createTable);
            System.out.println("Table has been created");
        } catch (SQLException e) {
            System.out.println("Table has been not created");
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String tableDelete = "DROP TABLE IF EXISTS USERS";
        try (Statement stmt = Util.getConnection().createStatement()) {
            stmt.executeUpdate(tableDelete);
            System.out.println("Table has been deleted");
        } catch (SQLException e) {
            System.out.println("Table has been not deleted");
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String userAdd = "INSERT INTO USERS (name,LastName,age) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = Util.getConnection().prepareStatement(userAdd)) {
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.executeUpdate();
            System.out.println("Line has been added");
        } catch (SQLException e) {
            System.out.println("Exception during line add process");
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long idToRemove) {
        String deleteUser = "DELETE FROM USERS WHERE Id = ?";
        try (PreparedStatement stmt = Util.getConnection().prepareStatement(deleteUser)) {
            stmt.setString(1, String.valueOf(idToRemove));
            stmt.executeUpdate();
            System.out.println("Line has been deleted");
        } catch (SQLException e) {
            System.out.println("Exceptiong during line delete process");
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String getUsers = "SELECT * FROM USERS";
        List<User> userList = new ArrayList<User>();
        User userTemp = new User();
        try (Statement stmt = Util.getConnection().createStatement()) {
            ResultSet out = stmt.executeQuery(getUsers);
            System.out.println("ResultSet from DB is found");
            while (out.next()) {
                userTemp = new User((long) out.getLong("id"), out.getString("name"), out.getString("LastName"), out.getByte("Age"));
                userList.add(userTemp);
                System.out.println("User added to list for upload " + userTemp);
            }
        } catch (SQLException e) {
            System.out.println("Exception at geting users list process");
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        String deleteUser = "TRUNCATE TABLE USERS";
        try (Statement stmt = Util.getConnection().createStatement()) {
            stmt.executeUpdate(deleteUser);
        } catch (SQLException e) {
            System.out.println("Exception during table clear process");
            System.out.println(e.getMessage());
        }
    }
}
