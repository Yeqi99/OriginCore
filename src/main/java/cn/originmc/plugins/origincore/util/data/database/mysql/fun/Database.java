package cn.originmc.plugins.origincore.util.data.database.mysql.fun;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private String url;
    private String username;
    private String password;
    private Connection connection;

    public Database(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public boolean tableExists(String tableName) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet resultSet = meta.getTables(null, null, tableName, null);
        return resultSet.next();
    }

    public void createTable(String tableName, List<Field> fields) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE ").append(tableName).append(" (");

        for (Field field : fields) {
            query.append(field.getName()).append(" ").append(field.getType()).append(", ");
        }

        query.setLength(query.length() - 2);
        query.append(")");

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query.toString());
        }
    }

    public Table getTable(String tableName) throws SQLException {
        if (!tableExists(tableName)) {
            throw new IllegalArgumentException("Table " + tableName + " does not exist.");
        }

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData metaData = resultSet.getMetaData();

        List<Field> fields = new ArrayList<>();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            String columnType = metaData.getColumnTypeName(i);
            fields.add(new Field(columnName, columnType));
        }

        Table table = new Table(tableName, fields);

        while (resultSet.next()) {
            Record record = new Record();
            for (Field field : fields) {
                String columnName = field.getName();
                String columnValue = resultSet.getString(columnName);
                record.addField(columnName, columnValue);
            }
            table.addRecord(record);
        }

        resultSet.close();
        statement.close();

        return table;
    }

    public List<Record> query(String tableName, Condition condition) throws SQLException {
        if (!tableExists(tableName)) {
            throw new IllegalArgumentException("Table " + tableName + " does not exist.");
        }

        Table table = getTable(tableName);
        List<Record> result = new ArrayList<>();

        for (Record record : table.getRecords()) {
            if (condition.evaluate(record)) {
                result.add(record);
            }
        }

        return result;
    }

    public void insert(String tableName, Record record) throws SQLException {
        if (!tableExists(tableName)) {
            throw new IllegalArgumentException("Table " + tableName + " does not exist.");
        }

        List<Field> fields = record.getFields();

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ").append(tableName).append(" (");

        for (Field field : fields) {
            query.append(field.getName()).append(", ");
        }

        query.setLength(query.length() - 2);
        query.append(") VALUES (");

        for (Field field : fields) {
            query.append("'").append(field.getValue()).append("', ");
        }

        query.setLength(query.length() - 2);
        query.append(")");

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query.toString());
        }
    }
}