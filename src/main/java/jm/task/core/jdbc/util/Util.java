package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    public static Statement getConnection(String url, String user, String psw) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, psw);
        //System.out.println("Connected to " + url);
        Statement stmt = connection.createStatement();
        return stmt;
    }

//    public static void closeConnection(Connection connection) {
//        if (connection != null) {
//            connection.close();
//            System.out.println("Connection closed");
//        } else {
//            System.out.println("DB null link during closure");
//        }
//    }
}


