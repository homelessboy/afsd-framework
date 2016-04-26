package me.afsd.service.base;

import me.afsd.dao.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
@NoRepositoryBean
public class BaseBiz <T,ID extends Serializable> {

    @Autowired
    protected BaseRepository<T,ID> dao;

    public <S extends T> void save(S entity) {
    }

    public <S extends T> void save(Iterable<S> entities) {
    }

    public <S extends T> void saveAndFlush(S entity) {
    }

    public void delete(ID id){
    }

    public void delete(T entity) {
    }

    public void delete(Iterable<? extends T> entities){
    }

}
