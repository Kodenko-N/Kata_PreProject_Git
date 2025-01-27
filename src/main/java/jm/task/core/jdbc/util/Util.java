package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/kn_db";
    private static final String USER = "root";
    private static final String PASSWORD = "RootPassword1";
    public static Connection conn;

    public static Connection getConnection() throws SQLException {
        try {
            System.out.println("Connecting to " + URL);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (SQLException e) {
            System.out.println("DB connection failed");
            System.out.println("Please choose an option: ");
            System.out.println(" 1: Close program");
            System.out.println(" Any other key: try again connect to:" + URL);
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            while (!input.equals("1")) {
                getConnection();
                input = sc.nextLine();
            }
            System.out.println("Program finished");
            System.exit(0);
            return null;
        }
    }
}


