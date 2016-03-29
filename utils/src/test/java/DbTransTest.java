import com.alibaba.druid.pool.DruidDataSource;
import me.afsd.utils.db.trans.DBOperaterImp;
import me.afsd.utils.db.trans.IDBOperater;
import me.afsd.utils.db.trans.TransferHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

/**
 * User: afsd
 * Date: 2016/3/29
 * Time: 13:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class DbTransTest {
    @Qualifier("dataSource_1")
    @Autowired
    DruidDataSource fromDataSource;

    @Qualifier("dataSource_2")
    @Autowired
    DruidDataSource toDataSource;

    @Test
    public void test() throws SQLException {
        IDBOperater toDBOperater = new DBOperaterImp("public", fromDataSource.getConnection());
        IDBOperater fromDBOperater = new DBOperaterImp("public", toDataSource.getConnection());
        TransferHandler handler = new TransferHandler(fromDBOperater, toDBOperater);
        handler.handler("public.test", "public.test", (dataFieldses -> null));
    }
}
