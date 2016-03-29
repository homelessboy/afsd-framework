package me.afsd.utils.db.trans;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: afsd
 * Date: 2016/3/29
 * Time: 14:41
 */
public class AutomTrans {
    private String[] tableNames;
    private Map<String, DataTransfer> transferMap = new HashMap<>();
    private Map<String, String> changeNameMap = new HashMap<>();

    private IDBOperater fromDbOperater;
    private IDBOperater toDbOperater;
    private TransferHandler transferHandler;

    public AutomTrans(IDBOperater fromDbOperater,IDBOperater toDbOperater){
        this.fromDbOperater=fromDbOperater;
        this.toDbOperater=toDbOperater;
        transferHandler=new TransferHandler(fromDbOperater,toDbOperater);
    }

    public void startTrans() throws SQLException {
        for(String tableName: tableNames){
            String toName=changeNameMap.get(tableName);
            if(toName==null)
                toName=tableName;
            transferHandler.handler(tableName,toName,transferMap.get(tableName));
        }
    }

    public String[] getTableNames() {
        return tableNames;
    }

    public void setTableNames(String[] tableNames) {
        this.tableNames = tableNames;
    }

    public Map<String, DataTransfer> getTransferMap() {
        return transferMap;
    }

    public void setTransferMap(Map<String, DataTransfer> transferMap) {
        this.transferMap = transferMap;
    }

    public Map<String, String> getChangeNameMap() {
        return changeNameMap;
    }

    public void setChangeNameMap(Map<String, String> changeNameMap) {
        this.changeNameMap = changeNameMap;
    }

    public IDBOperater getFromDbOperater() {
        return fromDbOperater;
    }

    public void setFromDbOperater(IDBOperater fromDbOperater) {
        this.fromDbOperater = fromDbOperater;
    }

    public IDBOperater getToDbOperater() {
        return toDbOperater;
    }

    public void setToDbOperater(IDBOperater toDbOperater) {
        this.toDbOperater = toDbOperater;
    }
}
