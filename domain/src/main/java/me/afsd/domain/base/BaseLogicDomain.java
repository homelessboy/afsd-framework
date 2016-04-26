package me.afsd.domain.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
@MappedSuperclass
public class BaseLogicDomain<FK extends Serializable> extends BaseDomain<FK> implements CanLogicDomain<FK>{

    @Column
    private Boolean del=Boolean.FALSE;

    @Override
    public Boolean getDel() {
        return this.del;
    }

    @Override
    public void setDel(Boolean del) {
        this.del=del;
    }
}
