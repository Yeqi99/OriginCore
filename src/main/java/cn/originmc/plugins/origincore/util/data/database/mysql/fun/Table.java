package cn.originmc.plugins.origincore.util.data.database.mysql.fun;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private String name;
    private List<Field> fields;
    private List<Record> records;

    public Table(String name, List<Field> fields) {
        this.name = name;
        this.fields = fields;
        this.records = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Field> getFields() {
        return fields;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void addRecord(Record record) {
        records.add(record);
    }
}
