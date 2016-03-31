package me.afsd.utils.test.generator;

import org.springframework.data.domain.Persistable;

import java.util.List;

/**
 * User: afsd
 * Date: 2016/3/31
 * Time: 15:16
 */
public interface IDataGenerator{
    List<Persistable> generator();

    void setDomains(List<Persistable> domains);

    List<Persistable> getDomains();

    List<Persistable> getDomains(Class type);
}
