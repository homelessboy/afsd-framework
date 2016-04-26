package me.afsd.utils.test.generator;

import org.springframework.data.domain.Persistable;

import java.util.List;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
public interface IDataGenerator{
    List<Persistable> generator();

    void setDomains(List<Persistable> domains);

    List<Persistable> getDomains();

    List<Persistable> getDomains(Class type);
}
