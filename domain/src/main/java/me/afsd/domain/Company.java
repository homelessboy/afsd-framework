package me.afsd.domain;

import me.afsd.domain.base.DomainName;
import me.afsd.domain.base.StringRemarkEnmu;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * User: afsd
 * Date: 2016/1/26
 * Time: 17:39
 */
@Entity
@Table(name="company")
@DomainName("公司")
@EntityListeners({AuditingEntityListener.class})
public class Company extends AbstractAuditable<User,Long> {
    @Column
    private String name;

    @Column()
    private String address;

    public Company(){
        super();
    }

    public void setId(Long id){
        super.setId(id);
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
