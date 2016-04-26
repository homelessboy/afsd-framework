package me.afsd.domain.base;

import org.springframework.data.domain.Persistable;

import java.io.Serializable;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
public interface CanLogicDomain<FK extends Serializable> extends Persistable<FK> {
    Boolean getDel();
    void setDel(Boolean del);
}
