package com.carpenoctem.repository.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private ConnectionUtil() {
        super();
    }

    public static Connection getConnection() {
        if (System.getenv("HOSTNAME") != null) {
            try {
                Class.forName("org.postgresql.Driver");
                String dbname = System.getenv("DB_NAME");
                String username = System.getenv("USERNAME");
                String password = System.getenv("PASSWORD");
                String hostname = System.getenv("HOSTNAME");
                String port = System.getenv("PORT");
                String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbname + "?user=" + username
                        + "&password=" + password;

                Connection connection = DriverManager.getConnection(jdbcUrl);

                return connection;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
