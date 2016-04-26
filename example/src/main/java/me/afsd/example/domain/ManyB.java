package me.afsd.example.domain;

import me.afsd.domain.base.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
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
