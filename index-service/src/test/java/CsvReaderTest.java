import com.tw.Application;
import com.tw.CsvReader;
import com.tw.dao.mongo.IndexDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by rajats on 7/21/16.
 */


public class CsvReaderTest {





    @Test
    public void shouldReadCsv() throws UnknownHostException {
        List<String> instruments =  CsvReader.read("/Users/rajats/index-automation-tool/index-service/src/test/resources/21072016_company_data.tsv");

        System.out.println(instruments);

        IndexDao indexDao = new IndexDao();

        for(String i : instruments) {
            indexDao.saveInstrument(i);
        }
        Assert.assertEquals(30,instruments.size());
    }
}
