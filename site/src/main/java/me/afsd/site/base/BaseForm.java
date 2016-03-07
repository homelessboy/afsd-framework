package me.afsd.site.base;

import java.io.Serializable;

/**
 * User: afsd
 * Date: 2016/3/7
 * Time: 11:08
 */
public abstract class BaseForm<T,ID extends Serializable> {
    protected String model;
    protected ID id;

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
}
