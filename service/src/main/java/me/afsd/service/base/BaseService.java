package me.afsd.service.base;

import me.afsd.dao.base.BaseRepository;
import me.afsd.dao.base.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    protected BaseRepository<T,ID> repository;
    @Autowired(required = false)
    protected BaseBiz<T,ID> biz;

    public <S extends T> S save(S entity) {
        System.out.println(entity.getClass().getName());
        if(biz!=null) biz.save(entity);
        return repository.save(entity);
    }

    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        if(biz!=null) biz.save(entities);
        return repository.save(entities);
    }

    public <S extends T> S saveAndFlush(S entity) {
        if(biz!=null) biz.saveAndFlush(entity);
        return repository.saveAndFlush(entity);
    }

    public void flush(){
        repository.flush();
    }

    public T getOne(ID id) {
        return repository.getOne(id);
    }

    public void delete(ID id){
        if(biz!=null) biz.delete(id);
        repository.delete(id);
    }

    public void delete(T entity) {
        if(biz!=null) biz.delete(entity);
        repository.delete(entity);
    }

    public void delete(Iterable<? extends T> entities){
        if(biz!=null) biz.delete(entities);
        repository.delete(entities);
    }

    public List<T> findAll(){
        return repository.findAll();
    }

    public List<T> findAll(Specification<T> spec){
        return repository.findAll(spec);
    }

    public List<T> findAll(Specification<T> spec,Sort sort){
        return repository.findAll(spec, sort);
    }

    public Page<T> findAll(Specification spec,Pageable pageable){
        return repository.findAll(spec, pageable);
    }

    public Page<T> findAll(Query<T> query){
        return findAll(query.asSpecification(),query.getPageable());
    }

    public String getDomainName(){
        return repository.getDomainName();
    }

}
