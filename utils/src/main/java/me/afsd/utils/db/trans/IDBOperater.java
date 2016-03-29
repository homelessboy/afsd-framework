package me.afsd.utils.db.trans;

import java.sql.SQLException;
import java.util.List;

/**
 * User: afsd
 * Date: 2016/3/29
 * Time: 13:26
 */
public interface IDBOperater {
    List<DataFields> getTableData(String tableName) throws SQLException;

    void insertTableData(String tableName,List<DataFields> dataFieldses) throws SQLException;

    void deleteFK(String table,String fkTableName) throws SQLException;

    boolean isTableHasField(String tableName,String field);

    void updateAllTable() throws SQLException;

    DBInfo getDbInfo() throws SQLException;
}
