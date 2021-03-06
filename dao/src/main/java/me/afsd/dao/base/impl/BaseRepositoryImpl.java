package me.afsd.dao.base.impl;

import me.afsd.dao.base.BaseRepository;
import me.afsd.domain.base.CanLogicDomain;
import me.afsd.domain.base.DomainName;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
@NoRepositoryBean
public class BaseRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private EntityManager em;
    private CrudMethodMetadata metadata;

    public BaseRepositoryImpl(JpaEntityInformation entityInformation,
                              EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.em = entityManager;
    }

    public void setRepositoryMethodMetadata(CrudMethodMetadata crudMethodMetadata) {
        super.setRepositoryMethodMetadata(crudMethodMetadata);
        this.metadata = crudMethodMetadata;
    }

    @Override
    @Transactional
    public void delete(T entity) {
        Assert.notNull(entity, "The entity must not be null!");
        if (CanLogicDomain.class.isAssignableFrom(entity.getClass())) {
            ((CanLogicDomain) entity).setDel(true);
            save(entity);
        } else {
            em.remove(em.contains(entity) ? entity : em.merge(entity));
        }
    }

    @Override
    protected TypedQuery<T> getQuery(Specification<T> spec, Sort sort) {
        return super.getQuery(getSpecification(spec), sort);
    }

    @Override
    protected TypedQuery<Long> getCountQuery(Specification<T> spec) {
        return super.getCountQuery(getSpecification(spec));
    }

    protected Specification<T> getSpecification(Specification<T> spec) {
        LogicSpecification logicSpecification = new LogicSpecification();
        spec = Specifications.where(spec).and(logicSpecification);
        return spec;
    }

    @Override
    public String getDomainName(){
        DomainName name=getDomainClass().getAnnotation(DomainName.class);
        if(name!=null&&name.value()!=null&&!name.value().isEmpty())
            return name.value();
        return null;
    }

    class LogicSpecification implements Specification<T> {
        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            if (CanLogicDomain.class.isAssignableFrom(root.getModel().getJavaType())) {
                Predicate isNotDel = cb.equal(root.get("del").as(Boolean.class), Boolean.FALSE);
                query.where(isNotDel);
            }
            return query.getRestriction();
        }
    }

}