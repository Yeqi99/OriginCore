package cn.originmc.plugins.origincore.util.data.database.mysql.fun;

public class Condition {
    private String fieldName;
    private String operator;
    private String value;

    public Condition(String fieldName, String operator, String value) {
        this.fieldName = fieldName;
        this.operator = operator;
        this.value = value;
    }

    public boolean evaluate(Record record) {
        Field field = record.getField(fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Field " + fieldName + " does not exist.");
        }

        String recordValue = field.getValue();

        switch (operator) {
            case "=":
                return recordValue.equals(value);
            case "!=":
                return !recordValue.equals(value);
            case ">":
                return Double.parseDouble(recordValue) > Double.parseDouble(value);
            case "<":
                return Double.parseDouble(recordValue) < Double.parseDouble(value);
            case ">=":
                return Double.parseDouble(recordValue) >= Double.parseDouble(value);
            case "<=":
                return Double.parseDouble(recordValue) <= Double.parseDouble(value);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
