package me.afsd.example.domain;

import me.afsd.domain.base.BaseDomain;

import javax.persistence.*;
import java.util.List;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
@Entity
@Table(name="many_a")
public class ManyA extends BaseDomain<Long> {
    @Column
    private String name;

    @ManyToMany
    @JoinTable(name = "a_b",joinColumns=@JoinColumn(name="a_id",referencedColumnName="id")
            ,inverseJoinColumns=@JoinColumn(name="b_id",referencedColumnName = "id"))
    private List<ManyB> manyBs;

    public ManyA(){}

    public ManyA(String name){
        this.name=name;
    }

    public List<ManyB> getManyBs() {
        return manyBs;
    }

    public void setManyBs(List<ManyB> manyBs) {
        this.manyBs = manyBs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
