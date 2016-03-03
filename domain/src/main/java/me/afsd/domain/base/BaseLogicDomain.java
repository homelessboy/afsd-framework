package me.afsd.domain.base;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * User: XuHui
 * Date: 2016/2/12
 * Time: 16:11
 */
@MappedSuperclass
public class BaseLogicDomain<FK extends Serializable> extends AbstractPersistable<FK> implements CanLogicDomain{

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
