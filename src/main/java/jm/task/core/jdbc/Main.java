package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Program had been started");
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Petrov", (byte) 5);
        userService.saveUser("Ivan2", "Petrov2", (byte) 10);
        userService.saveUser("Василий", "Иванов", (byte) 15);
        userService.saveUser("Елена", "Прекрасная", (byte) 20);
        List<User> usersList= userService.getAllUsers();
        for (User u : usersList) {
            System.out.println(u);
        }
        userService.removeUserById((long) 2);
        userService.cleanUsersTable();
    }
}




