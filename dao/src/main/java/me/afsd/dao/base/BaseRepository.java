package me.afsd.dao.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * User: XuHui
 * Date: 2016/1/28
 * Time: 20:12
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID> {

    Page<T> findAll(Specification<T> spec,Pageable pageable);

    List<T> findAll(Specification<T> spec, Sort sort);

    List<T> findAll(Specification<T> spec);

}
