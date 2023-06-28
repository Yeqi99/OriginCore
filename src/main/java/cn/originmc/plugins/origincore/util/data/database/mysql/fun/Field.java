package cn.originmc.plugins.origincore.util.data.database.mysql.fun;

public class Field {
    private String name;
    private String type;
    private String value;

    public Field(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
