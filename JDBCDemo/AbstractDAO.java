package JDBCDemo;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T> {
    private final Connection connection;
    private final String table;
    private Class<T> tClass;

    public AbstractDAO(Connection connection, String table, Class<T> tClass) {
        this.connection = connection;
        this.table = table;
        this.tClass = tClass;
    }

    public void add(T tInstance) {
        String query = addQuery(tInstance);
        execQuery(query, "Added.");
    }

    private String addQuery(T tInstance) {
        // Result: INSERT INTO table (col1,col2..colN) VALUES (v1,v2..vN)
        // Монструозный метод..
        // Alexander Ts, если вам будет нетрудно, напишите, можно ли было по другому сделать?
        StringBuilder query = new StringBuilder();
        StringBuilder values = new StringBuilder();
        query.append("INSERT INTO ").append(table).append(" (");
        values.append("VALUES (");
        Class<?> cls = tInstance.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                query.append(field.getName()).append(",");
                values.append("'" + field.get(tInstance).toString() + "',");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        query.deleteCharAt(query.length() - 1);
        values.deleteCharAt(values.length() - 1);
        query.append(") ");
        query.append(values).append(")");
        return query.toString();
    }


    public void update(T tInstance) {
        String query = updateQuery(tInstance);
        execQuery(query, "Updated.");
    }

    private String updateQuery(T tInstance) {
        // UPDATE Clients set col1='val1',col2='val2'..colN='valN' where PKColumnName=PKFieldValue;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ")
                .append(table)
                .append(" SET ");
        String primaryKeyField = getTablePrimaryKeyField();
        Class<?> cls = tInstance.getClass();
        Field[] fields = cls.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals(primaryKeyField)) {
                    continue;
                }
                query.append(field.getName())
                        .append("='")
                        .append(field.get(tInstance).toString())
                        .append("'")
                        .append(",");
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        query.deleteCharAt(query.length() - 1);
        return primaryKeyFieldQuery(tInstance, query);
    }

    private String getTablePrimaryKeyField() {
        try {
            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet rs = dbmd.getPrimaryKeys(null, null, table);
            while (rs.next()) {
                return rs.getString("COLUMN_NAME");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public void delete(T tInstance) {
        String query = delQuery(tInstance);
        execQuery(query, "Deleted.");
    }

    private String delQuery(T tInstance) {
        //DELETE FROM table where PKColumnName=PKFieldValue
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ").append(table);
        return primaryKeyFieldQuery(tInstance, query);
    }

    private String primaryKeyFieldQuery(T tInstance, StringBuilder query) {
        String primaryKeyFieldName = getTablePrimaryKeyField();
        Class<?> cls = tInstance.getClass();
        query.append(" WHERE ")
                .append(primaryKeyFieldName)
                .append("=");
        try {
            Field primaryKeyValue = cls.getDeclaredField(primaryKeyFieldName);
            primaryKeyValue.setAccessible(true);
            query.append(primaryKeyValue.get(tInstance).toString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return query.toString();
    }

    public List<T> getAll() {
        List<T> result = new ArrayList<>();

        try {
            try (Statement st = connection.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM " + table)) {
                    ResultSetMetaData md = rs.getMetaData();

                    while (rs.next()) {
                        //T instance = (T) new Object();
                        T tInstance = getInstance();
                        Class<?> cls = tInstance.getClass();

                        for (int i = 1; i <= md.getColumnCount(); i++) {
                            String columnName = md.getColumnName(i);
                            try {
                                Field field = cls.getDeclaredField(columnName);
                                field.setAccessible(true);
                                field.set(tInstance, rs.getObject(columnName));
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        result.add(tInstance);
                    }
                }
            }
            return result;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private T getInstance() {
        try {
            return tClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void execQuery(String query, String message) {
        System.out.println(query);
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println(message);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
