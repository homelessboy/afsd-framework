package me.afsd.utils.db.trans;

import java.util.HashMap;
import java.util.Map;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
public class TableInfo {
    Map<String, String> columns = new HashMap<>();//<column_name,type>
    Map<String, String> fks = new HashMap<>();//<FK_TABLE_NAME,FK_NAME>
    String tableName;

    public TableInfo(String tableName) {
        this.tableName=tableName;
    }

    void addColumn(String columnName, String type) {
        columns.put(columnName,type);
    }

    void addFK(String fkTableName,String fkName){
        fks.put(fkTableName,fkName);
    }

    String getFkName(String fkTableName){
        return fks.get(fkTableName);
    }

    public Map<String, String> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, String> columns) {
        this.columns = columns;
    }

    public Map<String, String> getFks() {
        return fks;
    }

    public void setFks(Map<String, String> fks) {
        this.fks = fks;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
