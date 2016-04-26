package me.afsd.dao.base;

import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
@NoRepositoryBean
public interface BaseNameResitory<T,ID extends Serializable> extends  BaseRepository<T,ID>{
    List<T> findByName(String name);
}
