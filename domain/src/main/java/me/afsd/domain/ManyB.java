package me.afsd.domain;

import me.afsd.domain.base.BaseDomain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: afsd
 * Date: 2016/4/11
 * Time: 20:45
 * Brif:
 */
@Entity()
@Table(name="many_b")
public class ManyB extends BaseDomain<Long> {
    @Column
    private String name;

    @ManyToMany(mappedBy = "manyBs")//单向配置
/*  双向配置
    @ManyToMany
    @JoinTable(name = "a_b",joinColumns=@JoinColumn(name="b_id",referencedColumnName="id")
            ,inverseJoinColumns=@JoinColumn(name="a_id",referencedColumnName = "id"))*/
    private List<ManyA> manyAs=new ArrayList<>();

    public ManyB(){}

    public ManyB(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ManyA> getManyAs() {
        return manyAs;
    }

    public void setManyAs(List<ManyA> manyAs) {
        this.manyAs = manyAs;
    }
}
