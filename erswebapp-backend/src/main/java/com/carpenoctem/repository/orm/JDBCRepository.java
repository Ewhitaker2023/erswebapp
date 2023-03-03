package com.carpenoctem.repository.orm;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class JDBCRepository<T, ID> {

    public T save(T object) {

        Connection connection = ConnectionUtil.getConnection();
        try {
            String schema = connection.getSchema();
            System.out.println(schema);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
