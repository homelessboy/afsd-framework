package me.afsd.domain;

import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * User: XuHui
 * Date: 2016/2/13
 * Time: 11:37
 */
@Entity
@Table(name="web_user")
@EntityListeners({AuditingEntityListener.class})
public class User extends AbstractAuditable<User,Long> {
    @Column
    private String name;

    @Column
    private String pwd;

    @PreUpdate
    public void checkUpdate(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
