package me.afsd.utils.db.trans.transfer;

import me.afsd.utils.db.trans.DataFields;
import me.afsd.utils.db.trans.DataTransfer;

import java.util.List;

/**
 * Author：XuHui/xuhui@simpletour.com
 * Brief：
 * Date: 2016/3/29
 * Time: 14:59
 */
public class IdInc implements DataTransfer {
    private String idColumn="id";
    private Long step=10000L;

    @Override
    public List<DataFields> trans(List<DataFields> dataFieldses) {
        dataFieldses.forEach(tmp->{
            Long id= (Long) tmp.getValue(idColumn);
            id=id.longValue()+step;
            tmp.putValue("idColumn",id);
        });
        return dataFieldses;
    }
}
