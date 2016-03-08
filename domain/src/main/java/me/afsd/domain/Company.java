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

    @Column
    @Enumerated(EnumType.STRING)
    private Status status=Status.online;

    enum Status implements StringRemarkEnmu{
        online("上线"),offline("下线");

        private String remark;
        Status(String remark){
            this.remark=remark;
        }

        @Override
        public String getRemark() {
            return null;
        }
        public boolean equal(Status status){
            return this.name().equals(status.name());
        }
    }

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
