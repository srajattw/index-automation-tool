import ch.qos.logback.core.net.SyslogOutputStream;
import com.mongodb.DBObject;
import com.tw.dao.mongo.IndexDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by rajats on 7/12/16.
 */
public class IndexDaoTest {

    private final String indexJson = "{'indexConfigId':1,'indexName':'test','baseCurrency':'USD','baseIndex':100}";
    private final String RBSInstrumentJson = "{'instrumentId':'RBS001','name':'RBS','floatingCapitalization':100.5,'fixedCapitalization':100,'asOfDate':'2016-07-21'}";
    private final String ABNInstrumentJson = "{'instrumentId':'ABN001','name':'ABN','floatingCapitalization':200.5,'fixedCapitalization':200,'asOfDate':'2016-07-21'}";
    private final IndexDao indexDao = new IndexDao();



    @Before
    public void setUp() throws UnknownHostException {
        indexDao.saveIndex(indexJson);
        indexDao.saveInstrument(RBSInstrumentJson);
        indexDao.saveInstrument(ABNInstrumentJson);

    }



    @Test
    public void shouldFetchAllIndex() throws UnknownHostException {
        List<DBObject> indexConfigs = indexDao.fetchAllIndexConfig();
        Assert.assertTrue(1 <= indexConfigs.size());
    }

    @Test
    public void shouldFetchAllInstruments() throws UnknownHostException {
        List<DBObject> instruments = indexDao.fetchAllInstruments();
        Assert.assertTrue(2 == instruments.size());
    }


    @Test
    public void shouldFetchIndexConfigById() throws UnknownHostException {

        DBObject indexConfigs = indexDao.fetchIndexConfigById(1);

        assertEquals("test", indexConfigs.get("indexName"));
        assertEquals("USD", indexConfigs.get("baseCurrency"));
        assertEquals(100, indexConfigs.get("baseIndex"));

    }

    @Test
    public void shouldDeleteIndexConfigById() throws UnknownHostException {

        DBObject indexConfigs = indexDao.fetchIndexConfigById(1);
        assertEquals("test", indexConfigs.get("indexName"));
        assertEquals("USD", indexConfigs.get("baseCurrency"));
        assertEquals(100, indexConfigs.get("baseIndex"));


    }

    @Test
    public void shoulFetchInstrumentById() throws UnknownHostException {

        DBObject indexConfigs = indexDao.fetchInstrumentById("RBS001");
        assertEquals("RBS", indexConfigs.get("name"));
        assertEquals(100.5, indexConfigs.get("floatingCapitalization"));
        assertEquals(100, indexConfigs.get("fixedCapitalization"));


    }

    @After
    public void tearDown() throws UnknownHostException {
        indexDao.deleteIndexConfigById(1);
        indexDao.deleteInstrumentById("RBS001");
        indexDao.deleteInstrumentById("ABN001");

    }

    @Test
    public void shouldFetchAllMethodologies() throws UnknownHostException {

        IndexDao indexDao = new IndexDao();

        List<DBObject> indexConfigs = indexDao.fetchAllMethodologies();
        System.out.println(indexConfigs);
        Assert.assertTrue(1<=indexConfigs.size());
    }
}
