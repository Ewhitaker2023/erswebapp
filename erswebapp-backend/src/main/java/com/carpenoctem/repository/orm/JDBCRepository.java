package com.carpenoctem.repository.orm;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class JDBCRepository<T, ID> {

    public T save(T object) {

        try (Connection connection = ConnectionUtil.getConnection()) {
            System.out.println("HERE2");
            String schema = connection.getSchema();
            System.out.println(schema);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
