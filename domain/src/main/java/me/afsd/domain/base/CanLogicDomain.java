package me.afsd.domain.base;

import org.springframework.data.domain.Persistable;

import java.io.Serializable;

/**
 * User: afsd
 * Date: 2016/2/12
 * Time: 16:10
 */
public interface CanLogicDomain<FK extends Serializable> extends Persistable<FK> {
    Boolean getDel();
    void setDel(Boolean del);
}
