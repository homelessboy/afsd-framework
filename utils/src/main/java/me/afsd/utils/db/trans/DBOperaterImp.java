package me.afsd.utils.db.trans;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: afsd
 * Date: 2016/3/29
 * Time: 13:30
 */
public class DBOperaterImp implements IDBOperater {
    DBInfo dbInfo;
    Connection connection;
    int pitchCount=800;

    public DBOperaterImp(Connection connection) throws SQLException {
        this("%", connection);
    }

    public DBOperaterImp(String schemaPattern, Connection connection) throws SQLException {
        this(schemaPattern, "%", connection);
    }

    public DBOperaterImp(String schemaPattern, String tableNamePattern, Connection connection) throws SQLException {
        this.connection=connection;
        dbInfo = new DBInfo(schemaPattern, tableNamePattern, connection);
    }


    @Override
    public List<DataFields> getTableData(String tableName) throws SQLException {
        String sql = "select * from " + tableName;

        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(sql);

        ResultSetMetaData rsm = rs.getMetaData();
        List<DataFields> res = new ArrayList<>();
        while (rs.next()) {
            DataFields dfs = new DataFields();
            for (int i = 1; i <= rsm.getColumnCount(); ++i) {
                dfs.putValue(rsm.getColumnName(i), rs.getObject(i));
            }
            res.add(dfs);
        }
        return res;
    }

    @Override
    public void insertTableData(String tableName, List<DataFields> dataFieldses) throws SQLException {
        if (dataFieldses == null || dataFieldses.isEmpty()) {
            System.out.println("[WARN] table " + tableName + " data size is 0!");
            return;
        }

        TableInfo tableInfo = dbInfo.getTableInfo(tableName);

        String sql = "insert into " + tableName + "(";

        Set<String> fields = tableInfo.getColumns().keySet();
        List<String> quotesFields = new ArrayList<>();
        fields.forEach(field -> quotesFields.add("\"" + field + "\""));
        String fieldsString = quotesFields.toString();
        sql += fieldsString.substring(1, fieldsString.length() - 1) + ") values";
        StringBuilder ts = new StringBuilder(sql + "");

        try {
            int count = 0;
            int pitch = 0;
            for (DataFields dataFields : dataFieldses) {
                ts.append(" (");
                for (String field : fields) {
                    Object value = dataFields.getInsertValue(field);
                    ts.append(value + ",");
                }
                ts.deleteCharAt(ts.length()-1).append("),");
                count++;

                if (count > pitchCount) {
                    ts.deleteCharAt(ts.length()-1);
                    System.out.println(tableName + ":" + (pitch * pitchCount + count));
                    pitch++;
                    Statement stat = connection.createStatement();
                    stat.executeUpdate(ts.toString());
                    ts.setLength(0);
                    ts.append(sql + "");
                    count = 0;
                }
            }

            if (count > 0) {
                ts.deleteCharAt(ts.length()-1);
                System.out.println(tableName + ":" + (pitch * pitchCount + count));
                Statement stat = connection.createStatement();
                stat.executeUpdate(ts.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ts.toString());
        }
    }

    @Override
    public void deleteFK(String table, String fkTableName) throws SQLException {
        String fkName = dbInfo.getTableInfo(table).getFkName(fkTableName);
        if (fkName == null || fkName.isEmpty()) return;
        String[] names = table.split("\\.");
        String schemaName = "\"" + names[0] + "\"";
        String tableName = "\"" + names[1] + "\"";
        String sql = "ALTER TABLE  " + schemaName + "." + tableName + " DROP CONSTRAINT \"" + fkName + "\"";
        Statement stat = connection.createStatement();
        stat.execute(sql);
    }

    @Override
    public boolean isTableHasField(String tableName, String field) {
        return dbInfo.getTableInfo(tableName).getColumns().containsKey(field);
    }

    @Override
    public void updateAllTable() throws SQLException {
        Map tableInfosMap = dbInfo.getTableInfos();
        Set<String> tableInfosSet = tableInfosMap.keySet();

        for (String tableName : tableInfosSet) {
            TableInfo tableInfo = dbInfo.getTableInfo(tableName);
            updateTableSeqValue(tableInfo);
        }
    }

    @Override
    public DBInfo getDbInfo() throws SQLException {
        return this.dbInfo;
    }

    public int getPitchCount() {
        return pitchCount;
    }

    public void setPitchCount(int pitchCount) {
        this.pitchCount = pitchCount;
    }

    //更新所有表的id为最大值
    private void updateTableSeqValue(TableInfo tableInfo) throws SQLException {
        if (!isTableHasField(tableInfo.getTableName(), "id"))
            return;

        Statement state = connection.createStatement();
        ResultSet res = state.executeQuery("select max(id) from " + tableInfo.getTableName());
        if (res.next()) {
            Long maxVal = res.getLong(1);
            if (maxVal <= 0)
                return;
            Statement state1 = connection.createStatement();
            String sql = "select setval('" + tableInfo.getTableName() + "_id_seq', " + maxVal + ")";
            System.out.println(sql);
            try {
                state1.executeQuery(sql);
            } catch (SQLException e) {
            }
        }
    }
}
