package me.afsd.utils.db.trans;

import java.sql.SQLException;
import java.util.List;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
public interface IDBOperater {
    List<DataFields> getTableData(String tableName) throws SQLException;

    void insertTableData(String tableName,List<DataFields> dataFieldses) throws SQLException;

    void deleteFK(String table,String fkTableName) throws SQLException;

    boolean isTableHasField(String tableName,String field);

    void updateAllTable() throws SQLException;

    DBInfo getDbInfo() throws SQLException;
}
