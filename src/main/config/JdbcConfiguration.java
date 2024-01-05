package main.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfiguration {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/expense";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "1234";

    public static Connection getConnection() {
        Connection connection = null;
        try {

            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            System.out.println("Connection to the database successful.");


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        return connection;
    }



}

