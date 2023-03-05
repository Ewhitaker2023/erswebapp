package com.carpenoctem.repository.orm;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SQLDataTypes {
    public static final int ARRAY = 2003;
    public static final int BIG_INT = -5;
    public static final int BINARY = -2;
    public static final int BIT = -7;
    public static final int BLOB = 2004;
    public static final int BOOLEAN = 16;
    public static final int CHAR = 1;
    public static final int CLOB = 2005;
    public static final int DATE = 91;
    public static final int DATALINK = 70;
    public static final int DECIMAL = 3;
    public static final int DISTINCT = 2001;
    public static final int DOUBLE = 8;
    public static final int FLOAT = 6;
    public static final int INTEGER = 4;
    public static final int JAVAOBJECT = 2000;
    public static final int LONG_VARCHAR = -16;
    public static final int NCHAR = -15;
    public static final int NCLOB = 2011;
    public static final int VARCHAR = 12;
    public static final int VARBINARY = -3;
    public static final int TINY_INT = -6;
    public static final int TIMESTAMP_WITH_TIMEZONE = 2014;
    public static final int TIMESTAMP = 93;
    public static final int TIME = 92;
    public static final int STRUCT = 2002;
    public static final int SQLXML = 2009;
    public static final int SMALL_INT = 5;
    public static final int ROW_ID = -8;
    public static final int REFCURSOR = 2012;
    public static final int REF = 2006;
    public static final int REAL = 7;
    public static final int NVARCHAR = -9;
    public static final int NUMERIC = 2;
    public static final int NULL = 0;
    public static final int UUID = 1111;

    public static Map<Integer, Class<?>> mapping = new HashMap<Integer, Class<?>>() {
        {
            put(UUID, UUID.class);
            put(VARCHAR, String.class);
        }
    };

    private SQLDataTypes(Map<Integer, Class<?>> mapping) {
        super();
    }

    public void updateMapping(Integer key, Class<?> value) {
        mapping.put(key, value);
    }

}
