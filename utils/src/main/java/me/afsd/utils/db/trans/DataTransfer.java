package me.afsd.utils.db.trans;

import java.util.List;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
@FunctionalInterface
public interface DataTransfer {
    List<DataFields> trans(List<DataFields> dataFieldses);
}
