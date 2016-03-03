package me.afsd.service.base;

import me.afsd.dao.base.BaseRepository;
import me.afsd.dao.base.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    public <S extends T> S save(S entity) {
        return getRepository().save(entity);
    }

    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        return getRepository().save(entities);
    }

    public <S extends T> S saveAndFlush(S entity) {
        return getRepository().saveAndFlush(entity);
    }

    public void flush(){
        getRepository().flush();
    }

    public T getOne(ID id) {
        return getRepository().getOne(id);
    }

    public void delete(ID id){
        getRepository().delete(id);
    }

    public void delete(T entity) {
        getRepository().delete(entity);
    }

    public void delete(Iterable<? extends T> entities){
        getRepository().delete(entities);
    }

    public List<T> findAll(){
        return getRepository().findAll();
    }

    public List<T> findAll(Specification<T> spec){
        return getRepository().findAll(spec);
    }

    public List<T> findAll(Specification<T> spec,Sort sort){
        return getRepository().findAll(spec,sort);
    }

    public Page<T> findAll(Specification spec,Pageable pageable){
        return getRepository().findAll(spec,pageable);
    }

    public Page<T> findAll(Query<T> query){
        return findAll(query.asSpecification(),query.getPageable());
    }

    public String getDomainName(){
        return getRepository().getDomainName();
    }

    protected abstract BaseRepository<T,ID> getRepository();
}
