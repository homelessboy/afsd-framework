package me.afsd.site.base;

import java.io.Serializable;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
public abstract class BaseForm<T,ID extends Serializable> {
    protected String model;
    protected ID id;
    protected String name;

    public BaseForm(){
    }

    public BaseForm(T t){
        this.setEntity(t);
    }

    public abstract void setEntity(T t);

    public abstract T as();

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
