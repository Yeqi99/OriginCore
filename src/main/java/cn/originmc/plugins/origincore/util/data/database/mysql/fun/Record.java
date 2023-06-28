package cn.originmc.plugins.origincore.util.data.database.mysql.fun;

import java.util.ArrayList;
import java.util.List;

public class Record {
    private List<Field> fields;

    public Record() {
        this.fields = new ArrayList<>();
    }

    public List<Field> getFields() {
        return fields;
    }

    public void addField(String name, String value) {
        fields.add(new Field(name, null));
        getField(name).setValue(value);
    }

    public Field getField(String name) {
        for (Field field : fields) {
            if (field.getName().equals(name)) {
                return field;
            }
        }
        return null;
    }
}
