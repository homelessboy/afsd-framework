package me.afsd.utils.db.trans;

import java.sql.SQLException;
import java.util.List;

/**
 * User: afsd
 * Date: 2016/3/29
 * Time: 13:39
 */
public class TransferHandler {
    IDBOperater fromDBOperater;
    IDBOperater toDBOperater;

    public TransferHandler(IDBOperater fromDBOperater, IDBOperater toDBOperater) {
        this.fromDBOperater = fromDBOperater;
        this.toDBOperater = toDBOperater;
    }

    public void handler(String tableName) throws SQLException {
        handler(tableName,tableName);
    }

    public void handler(String tableName, DataTransfer dataTransfer) throws SQLException {
        handler(tableName,tableName,dataTransfer);
    }

    public void handler(String fromTableName, String toTableName) throws SQLException {
        handler(fromTableName,toTableName,null);
    }

    public void handler(String fromTableName, String toTableName, DataTransfer transfer) throws SQLException {
        List<DataFields> dataFieldses = fromDBOperater.getTableData(fromTableName);
        if (transfer != null) {
            dataFieldses = transfer.trans(dataFieldses);
        }
        toDBOperater.deleteFK(toTableName, toTableName);
        toDBOperater.insertTableData(toTableName, dataFieldses);
    }

}
