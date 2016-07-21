import com.tw.CsvReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by rajats on 7/21/16.
 */
public class CsvReaderTest {

    @Test
    public void shouldReadCsv(){
        List<String> instruments =  CsvReader.read("/Users/rajats/index-automation-tool/index-service/src/test/resources/21072016_company_data.tsv");

        System.out.println(instruments);

        Assert.assertEquals(30,instruments.size());
    }
}
