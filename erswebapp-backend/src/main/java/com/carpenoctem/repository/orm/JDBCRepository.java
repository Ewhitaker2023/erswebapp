package com.carpenoctem.repository.orm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class JDBCRepository<T> {
    private final Class<T> type;
    private final String classname;
    private final List<PrimaryKey> primaryKeys;
    private final Map<Attribute, Method> getMapping;
    private final Map<Attribute, Method> setMapping;

    public JDBCRepository(Class<T> type) {
        ObjectRelationMapper orm = Database.mapRelation(type);
        this.type = type;
        this.classname = orm.getClassname();
        this.primaryKeys = orm.getRelation().getPrimaryKeys();
        this.getMapping = orm.getMapping().getGetMapping();
        this.setMapping = orm.getMapping().getSetMapping();
    }

    public Optional<T> selectByPk(T object) {
        QueryBuilder query = new QueryBuilder();
        try {
            Attribute attribute = primaryKeys.get(0);
            Method method = getMapping.get(attribute);
            query = new QueryBuilder().buildSelect(classname).addWhereClause().addCondition(attribute,
                    method.invoke(object));
            if (primaryKeys.size() > 1) {
                for (int i = 1; i < primaryKeys.size(); i++) {
                    attribute = primaryKeys.get(i);
                    method = getMapping.get(attribute);
                    query = query.addAnd().addCondition(attribute, method.invoke(object));
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        ResultSet rs = Database.executeQuery(query.toString());

        int rowCount = 0;
        try {
            if (rs.last()) {
                rowCount = rs.getRow();
                rs.beforeFirst();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rowCount == 1) {
            try {
                T newObject = type.getDeclaredConstructor().newInstance();

                ResultSetMetaData metaData = rs.getMetaData();

                while (rs.next()) {
                    for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                        Method m = setMapping.get(new Attribute(metaData.getColumnName(i),
                                SQLDataTypes.mapping.get(metaData.getColumnType(i))));

                        m.invoke(newObject, rs.getObject(i));
                    }
                }

                return Optional.of(newObject);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Optional.empty();
    }

    public boolean existsByPk(T object) {
        Optional<T> foundObject = selectByPk(object);
        if (foundObject.isPresent())
            return true;
        else
            return false;
    }

    public List<T> selectAll() {
        QueryBuilder query = new QueryBuilder().buildSelect(classname);

        ResultSet rs = Database.executeQuery(query.toString());
        List<T> newObjects = new ArrayList<T>();
        try {
            ResultSetMetaData metaData = rs.getMetaData();

            while (rs.next()) {
                T newObject = type.getDeclaredConstructor().newInstance();

                for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                    Method m = setMapping.get(new Attribute(metaData.getColumnName(i),
                            SQLDataTypes.mapping.get(metaData.getColumnType(i))));
                    m.invoke(newObject, rs.getObject(i));
                }
                newObjects.add(newObject);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newObjects;
    }

    public List<T> selectAllByPk(Iterable<T> objects) {
        QueryBuilder query = new QueryBuilder().buildSelect(classname);
        try {
            Iterator<T> objectsIterator = objects.iterator();
            T object = objectsIterator.next();

            Attribute attribute = primaryKeys.get(0);
            Method method = getMapping.get(attribute);
            query = query.addWhereClause().addOpenParentheses().addCondition(attribute, method.invoke(object));
            if (primaryKeys.size() > 1) {
                for (int i = 1; i < primaryKeys.size(); i++) {
                    attribute = primaryKeys.get(i);
                    method = getMapping.get(attribute);
                    query = query.addAnd().addCondition(attribute, method.invoke(object));
                }
            }

            while (objectsIterator.hasNext()) {
                query = query.addCloseParentheses().addOr();

                object = objectsIterator.next();

                attribute = primaryKeys.get(0);
                method = getMapping.get(attribute);
                query = query.addOpenParentheses().addCondition(attribute, method.invoke(object));
                if (primaryKeys.size() > 1) {
                    for (int i = 1; i < primaryKeys.size(); i++) {
                        attribute = primaryKeys.get(i);
                        method = getMapping.get(attribute);
                        query = query.addAnd().addCondition(attribute, method.invoke(object));
                    }
                }
            }
            query = query.addCloseParentheses();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        ResultSet rs = Database.executeQuery(query.toString());
        List<T> newObjects = new ArrayList<T>();
        try {
            ResultSetMetaData metaData = rs.getMetaData();

            while (rs.next()) {
                T newObject = type.getDeclaredConstructor().newInstance();

                for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                    Method m = setMapping.get(new Attribute(metaData.getColumnName(i),
                            SQLDataTypes.mapping.get(metaData.getColumnType(i))));
                    m.invoke(newObject, rs.getObject(i));
                }
                newObjects.add(newObject);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newObjects;
    }
}
