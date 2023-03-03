package com.carpenoctem.repository.orm;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private ConnectionUtil() {
        super();
    }

    public static Connection getConnection() {
        Dotenv dotenv = null;
        try {
            dotenv = Dotenv.configure().load();
        } catch (DotenvException e) {
            e.printStackTrace();
        }
        if (dotenv.get("DB_HOSTNAME") != null) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            String dbname = dotenv.get("DB_NAME");
            String username = dotenv.get("DB_USERNAME");
            String password = dotenv.get("DB_PASSWORD");
            String hostname = dotenv.get("DB_HOSTNAME");
            String port = dotenv.get("DB_PORT");
            String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbname + "?user=" + username
                    + "&password=" + password;

            try {
                Connection connection = DriverManager.getConnection(jdbcUrl);

                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
