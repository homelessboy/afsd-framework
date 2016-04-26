package me.afsd.utils.db.trans;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
public class DBInfo {
    Map<String, TableInfo> tableInfos = new HashMap<>();

    public DBInfo(Connection connection) throws SQLException {
        this("%", connection);
    }

    public DBInfo(String schemaPattern, Connection connection) throws SQLException {
        this(schemaPattern, "%", connection);
    }

    public DBInfo(String schemaPattern, String tableNamePattern, Connection connection) throws SQLException {
        DatabaseMetaData md = connection.getMetaData();
        ResultSet rs = md.getColumns(connection.getCatalog(), schemaPattern, tableNamePattern, null);

        while (rs.next()) {
            String schema = rs.getString("table_schem");
            String table = rs.getString("table_name");
            String tableName=schema+"."+table;

            TableInfo tableInfo = tableInfos.get(tableName);
            if(tableInfo==null){
                tableInfo=new TableInfo(tableName);
                tableInfos.put(tableName,tableInfo);
                ResultSet rsFK=md.getImportedKeys(connection.getCatalog(),schema,table);
                while (rsFK.next()){
                    tableInfo.addColumn(schema+rsFK.getString("PKTABLE_NAME"),rsFK.getString("FK_NAME"));
                }
            }
            tableInfo.addColumn(rs.getString("column_name"),rs.getString("type_name"));
        }
    }

    public Map<String, TableInfo> getTableInfos() {
        return tableInfos;
    }

    public void setTableInfos(Map<String, TableInfo> tableInfos) {
        this.tableInfos = tableInfos;
    }

    public TableInfo getTableInfo(String talbeName){
        return tableInfos.get(talbeName);
    }
}
