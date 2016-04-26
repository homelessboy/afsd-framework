package me.afsd.example.domain;

import me.afsd.domain.base.BaseDomain;
import me.afsd.domain.base.DomainName;
import me.afsd.domain.base.StringRemarkEnmu;

import javax.persistence.*;
import java.util.List;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
@Entity
@Table(name="company")
@DomainName("公司")
//@EntityListeners({AuditingEntityListener.class})
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
