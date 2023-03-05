package com.carpenoctem.repository.orm;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Database {

    private static Connection connection;

    private Database() {
        connection = null;
    }

    private static Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Dotenv dotenv = null;
        try {
            dotenv = Dotenv.configure().load();
        } catch (DotenvException e) {
            e.printStackTrace();
        }
        if (dotenv.get("DB_HOSTNAME") != null) {
            try {
                Class.forName(dotenv.get("DB_DRIVER"));
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
                connection = DriverManager.getConnection(jdbcUrl);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    public static ObjectRelationMapper mapRelation(Class<?> type) {
        ObjectRelationMapper orm = new ObjectRelationMapper();
        orm.setType(type);
        try (Connection connection = getConnection()) {
            DatabaseMetaData dbMetaData = connection.getMetaData();

            String[] fullclassname = type.getName().toLowerCase().split("\\.");
            String classname = fullclassname[fullclassname.length - 1];
            orm.setClassname(classname);

            ResultSet primaryKeys = dbMetaData.getPrimaryKeys(null, null, classname);
            Map<String, Short> pkMap = new HashMap<String, Short>();
            while (primaryKeys.next()) {
                pkMap.put(primaryKeys.getString("COLUMN_NAME"), primaryKeys.getShort("KEY_SEQ"));
            }
            Set<String> pkNames = pkMap.keySet();

            ResultSet columns = dbMetaData.getColumns(null, null, classname, null);

            Map<Attribute, Accessor> mapping = new HashMap<Attribute, Accessor>();
            List<Attribute> attributes = new ArrayList<Attribute>();
            List<PrimaryKey> pkList = new ArrayList<PrimaryKey>();
            while (columns.next()) {
                Attribute attribute = null;
                int position = columns.getInt("ORDINAL_POSITION");
                String name = columns.getString("COLUMN_NAME");
                Class<?> datatype = SQLDataTypes.mapping.get(columns.getInt("DATA_TYPE"));
                Boolean isAutoIncrement = columns.getString("IS_AUTOINCREMENT").equals("YES") ? true : false;
                Boolean isNullable = columns.getString("IS_NULLABLE").equals("YES") ? true : false;

                if (pkNames.contains(name)) {
                    attribute = new PrimaryKey(pkMap.get(name), position, name, datatype, isAutoIncrement, isNullable);
                    pkList.add((PrimaryKey) attribute);
                } else {
                    attribute = new Attribute(position, name, datatype, isAutoIncrement, isNullable);
                }
                attributes.add(attribute);

                StringBuilder changeCase = new StringBuilder();
                CharacterIterator nameIterator = new StringCharacterIterator(name.toLowerCase());

                changeCase.append(Character.toUpperCase(nameIterator.current()));
                nameIterator.next();
                while (nameIterator.current() != CharacterIterator.DONE) {
                    if (nameIterator.current() == '_') {
                        nameIterator.next();
                        changeCase.append(Character.toUpperCase(nameIterator.current()));
                    } else
                        changeCase.append(nameIterator.current());
                    nameIterator.next();
                }
                String getter = "get" + changeCase.toString();
                String setter = "set" + changeCase.toString();

                Method[] methods = type.getMethods();
                Accessor accessor = new Accessor();
                for (Method m : methods) {
                    if (m.getName().equals(getter)) {
                        accessor.setGetter(m);
                    } else if (m.getName().equals(setter)) {
                        accessor.setSetter(m);
                    }
                }
                mapping.put(attribute, accessor);
            }
            orm.setRelation(new Relation(attributes, pkList));
            orm.setMapping(new Mapping(mapping));

            return orm;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet executeQuery(String sql) {
        try (Connection connection = getConnection()) {
            System.out.println(sql);
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
