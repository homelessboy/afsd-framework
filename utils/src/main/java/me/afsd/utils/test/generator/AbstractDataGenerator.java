package me.afsd.utils.test.generator;

import org.springframework.data.domain.Persistable;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * User: afsd
 * Date: 2016/3/31
 * Time: 15:18
 */
public abstract class AbstractDataGenerator implements IDataGenerator{
    protected List<Persistable> domains = new ArrayList<>();
    List<IDataGenerator> dataGenerators = new ArrayList<>();

    public final List<Persistable> createData() {
        if(dataGenerators!=null&&!dataGenerators.isEmpty()){
            for (IDataGenerator dataGenerator:dataGenerators){
                dataGenerator.setDomains(domains);
                List<Persistable> addDomains=dataGenerator.generator();
                domains.addAll(addDomains);
            }
        }
        domains=this.generator();
        return domains;
    }

    public void addDataGenerators(IDataGenerator dataGenerator){
        dataGenerators.add(dataGenerator);
    }

    @Override
    public void setDomains(List<Persistable> domains) {
        this.domains=domains;
    }

    @Override
    public List<Persistable> getDomains() {
        return domains;
    }

    @Override
    public List<Persistable> getDomains(Class type) {
        return domains.stream().filter(tmp-> tmp.getClass().isAssignableFrom(type)).collect(toList());
    }
}
