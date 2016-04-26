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
import java.util.Optional;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
@NoRepositoryBean
public abstract class BaseService<T,ID extends Serializable> {
    @Autowired
    protected BaseRepository<T,ID> dao;
    @Autowired(required = false)
    protected BaseBiz<T,ID> biz;

    public <S extends T> Optional<S> save(S entity) {
        System.out.println(entity.getClass().getName());
        if(biz!=null) biz.save(entity);
        return Optional.ofNullable(dao.save(entity));
    }

    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        if(biz!=null) biz.save(entities);
        return dao.save(entities);
    }

    public <S extends T> Optional<S> saveAndFlush(S entity) {
        if(biz!=null) biz.saveAndFlush(entity);
        return Optional.ofNullable(dao.saveAndFlush(entity));
    }

    public void flush(){
        dao.flush();
    }

    public Optional<T> getOne(ID id) {
        return Optional.ofNullable(dao.getOne(id));
    }

    public void delete(ID id){
        if(biz!=null) biz.delete(id);
        dao.delete(id);
    }

    public void delete(T entity) {
        if(biz!=null) biz.delete(entity);
        dao.delete(entity);
    }

    public void delete(Iterable<? extends T> entities){
        if(biz!=null) biz.delete(entities);
        dao.delete(entities);
    }

    public List<T> findAll(){
        return dao.findAll();
    }

    public List<T> findAll(Specification<T> spec){
        return dao.findAll(spec);
    }

    public List<T> findAll(Specification<T> spec,Sort sort){
        return dao.findAll(spec, sort);
    }

    public Page<T> findAll(Specification spec,Pageable pageable){
        return dao.findAll(spec, pageable);
    }

    public Page<T> findAll(Query<T> query){
        return findAll(query.asSpecification(),query.getPageable());
    }

    public String getDomainName(){
        return dao.getDomainName();
    }

}
