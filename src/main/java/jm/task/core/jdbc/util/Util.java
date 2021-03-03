package jm.task.core.jdbc.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/users_base?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Util util;
    private Connection connection;

    private Util() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Util getUtil() {
        if (util == null) {
            return util = new Util();
        } else {
            return util;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
