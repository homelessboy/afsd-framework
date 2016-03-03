package me.afsd.service.base;

import me.afsd.dao.base.BaseRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * User: afsd
 * Date: 2016/3/3
 * Time: 16:49
 */
@NoRepositoryBean
public abstract class BaseService<T,ID extends Serializable> {

    public List<T> findAll(){
        return getRepository().findAll();
    }

    protected abstract BaseRepository<T,ID> getRepository();
}
