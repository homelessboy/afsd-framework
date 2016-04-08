package me.afsd.domain;

import me.afsd.domain.base.BaseDomain;
import me.afsd.domain.base.DomainName;
import me.afsd.domain.base.StringRemarkEnmu;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * User: afsd
 * Date: 2016/1/26
 * Time: 17:39
 */
@Entity
@Table(name="company")
@DomainName("公司")
@EntityListeners({AuditingEntityListener.class})
public class Company extends BaseDomain<Long> {

    @Column
    private String name;

    @Column()
    private String address;

    @OneToMany(cascade = CascadeType.ALL,mappedBy="company",orphanRemoval = true)
    public List<Employee> employeeList;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status=Status.online;

    public Company(){
        super();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
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

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

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
}
