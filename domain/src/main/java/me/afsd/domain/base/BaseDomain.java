package me.afsd.domain.base;

import org.joda.time.DateTime;
import org.springframework.data.domain.Persistable;
import org.springframework.util.ClassUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
@MappedSuperclass
public class BaseDomain<PK extends Serializable> implements Persistable<PK> {

    private static final long serialVersionUID = -5554308939380869754L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private PK id;

    @Version
    private int version;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public PK getId() {
        return id;
    }

    public void setId(final PK id) {
        this.id = id;
    }

    @PrePersist
    public void prePersist(){
        this.updateDate = new Date();
        this.createdDate = this.updateDate;
    }

    @PreUpdate
    public void preUpdate(){
        this.updateDate = new Date();
    }

    public DateTime getCreatedDate() {
        return null == createdDate ? null : new DateTime(createdDate);
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = null == createdDate ? null : createdDate.toDate();
    }

    public DateTime getUpdateDate() {
        return null == updateDate ? null : new DateTime(updateDate);
    }

    public void setUpdateDate(DateTime updateDate) {
        this.updateDate = null == updateDate ? null : updateDate.toDate();
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Transient
    public boolean isNew() {
        return null == getId();
    }

    @Override
    public String toString() {
        return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
    }

    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(ClassUtils.getUserClass(obj))) {
            return false;
        }

        BaseDomain<?> that = (BaseDomain<?>) obj;

        return null != this.getId() && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode += null == getId() ? 0 : getId().hashCode() * 31;

        return hashCode;
    }
}
