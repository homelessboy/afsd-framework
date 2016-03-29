package me.afsd.utils.db.trans;

import java.util.List;

/**
 * User: afsd
 * Date: 2016/3/29
 * Time: 13:44
 */
@FunctionalInterface
public interface DataTransfer {
    List<DataFields> trans(List<DataFields> dataFieldses);
}
