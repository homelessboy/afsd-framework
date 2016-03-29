package me.afsd.utils.db.trans;

import org.postgresql.jdbc42.Jdbc42Array;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * User: afsd
 * Date: 2016/3/29
 * Time: 11:46
 */
public class DataFields {
    Map<String, Object> fieldValues = new HashMap<>();

    public Map<String, Object> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(Map<String, Object> fieldValues) {
        this.fieldValues = fieldValues;
    }

    public Object getInsertValue(String field) throws SQLException {
        Object val = fieldValues.get(field);
        if (val instanceof String) {
            return "'" + ((String) val).replaceAll("'", "''") + "'";
        }
        if (val instanceof Integer)
            return Long.valueOf((Integer) val);
        if (val instanceof Date)
            return "'" + val.toString() + "'";
        if (val instanceof Timestamp)
            return "'" + val.toString() + "'";
        if (val instanceof Arrays)
            return "'" + val.toString() + "'";
        if (val instanceof Jdbc42Array)
            return "'" + val.toString() + "'";
        return val;
    }

    public Object getValue(String field) {
        Object val = fieldValues.get(field);
        if (val instanceof Integer)
            return Long.valueOf((Integer) val);
        return val;
    }

    public void putValue(String field, Object value) {
        this.fieldValues.put(field, value);
    }

    public void changeKey(String fromField, String toField) {
        this.fieldValues.put(toField, this.fieldValues.get(fromField));
        this.fieldValues.remove(fromField);
    }

    public void removeKey(String fieldName){
        this.fieldValues.remove(fieldName);
    }
}
