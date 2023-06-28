package cn.originmc.plugins.origincore.util.data.database.mysql;

import cn.originmc.plugins.origincore.util.data.database.mysql.fun.*;
import cn.originmc.plugins.origincore.util.data.database.mysql.fun.Record;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void test(){
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "password";

        Database database = new Database(url, username, password);

        try {
            database.connect();
            // 创建表
            if (!database.tableExists("mytable")) {
                List<Field> fields = new ArrayList<>();
                fields.add(new Field("id", "INT"));
                fields.add(new Field("name", "VARCHAR(50)"));
                fields.add(new Field("age", "INT"));
                database.createTable("mytable", fields);
            }

            // 获取表
            Table table = database.getTable("mytable");

            // 添加记录
            Record record1 = new Record();
            record1.addField("id", "1");
            record1.addField("name", "John");
            record1.addField("age", "25");
            table.addRecord(record1);

            Record record2 = new Record();
            record2.addField("id", "2");
            record2.addField("name", "Jane");
            record2.addField("age", "30");
            table.addRecord(record2);

            // 查询记录
            Condition condition = new Condition("age", "=", "25");
            List<Record> result = database.query("mytable", condition);

            for (Record record : result) {
                System.out.println(record.getField("name").getValue());
            }

            // 插入记录
            Record record3 = new Record();
            record3.addField("id", "3");
            record3.addField("name", "Bob");
            record3.addField("age", "40");
            database.insert("mytable", record3);

            // 查询所有记录
            List<Record> allRecords = table.getRecords();
            for (Record record : allRecords) {
                System.out.println(record.getField("name").getValue());
            }
            database.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
