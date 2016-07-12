import ch.qos.logback.core.net.SyslogOutputStream;
import com.mongodb.DBObject;
import com.tw.dao.mongo.IndexDao;
import org.junit.Assert;
import org.junit.Test;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by rajats on 7/12/16.
 */
public class IndexDaoTest {

    @Test
    public void shouldSaveIndex() throws UnknownHostException {

        IndexDao indexDao = new IndexDao();
        String indexJson = "{'indexName':'test','baseCurrency':'USD','baseIndex':100}";
        indexDao.saveIndex(indexJson);

    }

    @Test
    public void shouldFetchAllIndex() throws UnknownHostException {

        IndexDao indexDao = new IndexDao();
        String indexJson = "{'indexName':'test','baseCurrency':'USD','baseIndex':100}";
        indexDao.saveIndex(indexJson);

        List<DBObject> indexConfigs = indexDao.fetchAllIndexConfig();
        Assert.assertTrue(1<=indexConfigs.size());
    }
}
