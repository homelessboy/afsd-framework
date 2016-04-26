package me.afsd.example.domain;

import me.afsd.domain.base.BaseDomain;
import me.afsd.domain.base.DomainName;

import javax.persistence.*;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
@Entity
@Table(name="employee")
@DomainName("员工")
//@EntityListeners({AuditingEntityListener.class})
public class Employee extends BaseDomain<Long> {
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="company_id", nullable=false, updatable=false)
    private Company company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
