//package me.afsd.domain;
//
//import me.afsd.domain.base.DomainName;
//import org.springframework.data.jpa.domain.AbstractPersistable;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.*;
//
///**
// * User: afsd
// * Date: 2016/4/7
// * Time: 21:47
// * Brief:
// */
//@Entity
//@Table(name="employee")
//@DomainName("员工")
//@EntityListeners({AuditingEntityListener.class})
//public class Employee extends AbstractPersistable<Long>{
//    @Column
//    private String name;
//
//    @ManyToOne
//    private Company company;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
