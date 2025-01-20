package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    private final String url = "jdbc:mysql://localhost:3306/kn_db";
    private final String user = "root";
    private final String password = "RootPassword1";

    public void createUsersTable() {

        String createTable = "CREATE TABLE IF NOT EXISTS `USERS` (" +
                "  `Id` INT NOT NULL AUTO_INCREMENT," +
                "  `Name` VARCHAR(45) NOT NULL," +
                "  `LastName` VARCHAR(45) NOT NULL," +
                "  `Age` INT NOT NULL," +
                "  PRIMARY KEY (`Id`))";
        try (Statement stmt = Util.getConnection(url, user, password)) {
            stmt.executeUpdate(createTable);
            System.out.println("Table has been created");
        } catch (SQLException e) {
            System.out.println("Table has been not created");
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String tableDelete = "DROP TABLE IF EXISTS USERS";
        try (Statement stmt = Util.getConnection(url, user, password)) {
            stmt.executeUpdate(tableDelete);
            System.out.println("Table has been deleted");
        } catch (SQLException e) {
            System.out.println("Table has been not deleted");
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String userAdd = "INSERT INTO USERS (name,LastName,age) VALUES ('" + name + "','" + lastName + "'," + age + ")";
        try (Statement stmt = Util.getConnection(url, user, password)) {

            stmt.executeUpdate(userAdd);
            System.out.println("Line has been added");
        }
        catch (SQLException e) {
            System.out.println("Exception during line add process");
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String deleteUser = "DELETE FROM USERS WHERE Id = " + id;
        try (Statement stmt = Util.getConnection(url, user, password)) {
            int res = stmt.executeUpdate(deleteUser);
            System.out.println("Line has been deleted");
            if (res != 0) {
                System.out.println("Deleted " + res + " lines");
            }
            else {
                System.out.println("Line with id " + id + " not found for delete");
            }
        }
        catch (SQLException e) {
            System.out.println("Eceptiong during line delete process");
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String getUsers = "SELECT * FROM USERS";
        List<User> userList = new ArrayList<User>();
        User userTemp = new User();
        try (Statement stmt = Util.getConnection(url, user, password)) {
            ResultSet out = stmt.executeQuery(getUsers);
            System.out.println("ResultSet from DB is found");
            while (out.next()) {
                userTemp.setId((long) out.getInt("id"));
                userTemp.setName(out.getString("name"));
                userTemp.setLastName(out.getString("LastName"));
                userTemp.setAge((byte) out.getInt("Age"));
                userList.add(userTemp);
                System.out.println("User id =" + userTemp.getId() + "added to list for upload");
            }
        }
        catch (SQLException e) {
            System.out.println("Exception at geting users list process");
            System.out.println(e.getMessage());
        }

        return userList;
    }

    public void cleanUsersTable() {
        String deleteUser = "DELETE FROM USERS";
        try (Statement stmt = Util.getConnection(url, user, password)) {
            int res = stmt.executeUpdate(deleteUser);
            System.out.println("Table has been cleared. Deleted " + res + " lines");
        }
        catch (SQLException e) {
            System.out.println("Exception during table clear process");
            System.out.println(e.getMessage());
        }
    }
}
