package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        System.out.println("Programm had been started");
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Petrov", (byte) 5);
        userService.saveUser("Ivan2", "Petrov2", (byte) 10);
        userService.saveUser("Василий", "Иванов", (byte) 15);
        userService.saveUser("Елена", "Прекрасная", (byte) 20);
        userService.getAllUsers();
        userService.removeUserById((long) 2);
        userService.cleanUsersTable();
    }
}
