import com.tw.Application;
import com.tw.dao.mongo.IndexDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;

/**
 * Created by rajats on 7/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:8081")
@WebAppConfiguration
public class IndexControllerTest {

  @Value("${local.server.port}")
  int port = 8081;

  private String baseUrl = "http://localhost:" + port + "/";
  private String indexConfig;
  private String calculationScript = "double generateIndex(instruments, config) {\n" +
          "    double totalMarketCapitalization = 0\n" +
          "    for (instrument in instruments) {\n" +
          "        totalMarketCapitalization = totalMarketCapitalization + instrument.floatingCapitalization\n" +
          "    }\n" +
          "    double indexCalculated = totalMarketCapitalization * config.baseIndex / config.baseCapitalization\n" +
          "    return indexCalculated\n" +
          "}";


  final RestTemplate template = new RestTemplate();

  @Autowired
  protected IndexDao indexDao;
  private final String RBSInstrumentJson = "{'instrumentId':'RBS001','name':'RBS','floatingCapitalization':100.5,'fixedCapitalization':100,'asOfDate':'2016-07-21'}";
  private final String ABNInstrumentJson = "{'instrumentId':'ABN001','name':'ABN','floatingCapitalization':200.5,'fixedCapitalization':200,'asOfDate':'2016-07-21'}";


  @Before
  public void setUp() throws UnknownHostException {
    StringBuilder builder = new StringBuilder();
    builder.append("{'startDate':'2016-07-20','endDate':'2016-07-20','typeOfScript':'Groovy','methodology':{");
    builder.append("'calculationScript':'");
    builder.append(calculationScript);
    builder.append("',");
    builder.append("},");
    builder.append("'baseIndex':100");
    builder.append(",'baseCapitalization':300");
    builder.append(",'asOfDate':'2016-07-20'");
    builder.append("}");
    indexConfig = builder.toString();

    /*indexConfig = "{'startDate':'2016-07-20','endDate':'2016-07-20','typeOfScript':'Groovy','methodology':{" +
            "'calculationScript':'String generateIndex(instruments, config) {\n" +
            "\n" +
            "    double indexCaluclated = 0\n" +
            "    for (instrument in instruments) {\n" +
            "        indexCaluclated = indexCaluclated + instrument.price + config.baseValue\n" +
            "    }\n" +
            "    def json = new JsonBuilder()\n" +
            "    def root = json \"index\": indexCaluclated\n" +
            "    return json.toString()\n" +
            "}'" +
            "}" +
            "}";*/
    indexDao.saveInstrument(RBSInstrumentJson);
    indexDao.saveInstrument(ABNInstrumentJson);

  }

  @Test

  public void shouldReplyIndexService() {

  }

  @Test
  public void shouldCalculateIndex() {

    String calculateIndexUrl = baseUrl + "calculateIndices";
   /* MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
    map.add("indicesToCalculate", indexConfig);
    map.add("startDate", "2016-07-20");
    map.add("endDate", "2016-07-20");
    map.add("typeOfScript", "Groovy");*/
    String returnedJason = template.postForObject(calculateIndexUrl, indexConfig, String.class);
    System.out.println(returnedJason);


  }

  @After
  public void tearDown() throws UnknownHostException {
    indexDao.deleteInstrumentById("RBS001");
    indexDao.deleteInstrumentById("ABN001");
  }
}
