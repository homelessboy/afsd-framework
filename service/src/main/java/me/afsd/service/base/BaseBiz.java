package me.afsd.service.base;

import me.afsd.dao.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * User: XuHui
 * Date: 2016/3/4
 * Time: 10:16
 */
@NoRepositoryBean
public class BaseBiz <T,ID extends Serializable> {

    @Autowired
    protected BaseRepository<T,ID> repository;

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
