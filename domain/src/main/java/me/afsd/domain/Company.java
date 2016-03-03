package me.afsd.domain;

import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * User: XuHui
 * Date: 2016/1/26
 * Time: 17:39
 */
@Entity
@Table(name="company")
@EntityListeners({AuditingEntityListener.class})
public class Company extends AbstractAuditable<User,Long> {
    @Column
    private String name;

    @Column()
    private String address;

    public Company(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
