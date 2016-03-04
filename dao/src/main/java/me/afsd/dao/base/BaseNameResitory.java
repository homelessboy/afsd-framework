package me.afsd.dao.base;

import me.afsd.domain.Company;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * User: XuHui
 * Date: 2016/3/4
 * Time: 10:34
 */
@NoRepositoryBean
public interface BaseNameResitory<T,ID extends Serializable> extends  BaseRepository<T,ID>{
    List<T> findByName(String name);
}
