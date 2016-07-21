
import groovy.lang.GroovyShell;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * Created by vohray on 7/20/16.
 */
public class IndexCalculationTest {


  /**
   * Created by vohray on 7/15/16.
   */
  @Test
  public void testIndexCalculation() throws IOException, JSONException {
    String script = "import groovy.json.JsonBuilder\n" +
            "import groovy.json.JsonSlurper\n" +
            "\n" +
            "String generateIndex(instruments, config) {\n" +
            "\n" +
            "    double indexCaluclated = 0\n" +
            "    for (instrument in instruments) {\n" +
            "        indexCaluclated = indexCaluclated + instrument.price + config.baseValue\n" +
            "    }\n" +
            "    def json = new JsonBuilder()\n" +
            "    def root = json \"index\": indexCaluclated\n" +
            "    return json.toString()\n" +
            "}";
    String instrumentsJSON = "{\"name\":\"RBS\",\"price\":100}";
    String configJSON = "{ \"simpleConfig\":\"simple\" , \"baseValue\":100}";
    JSONObject instruments = new JSONObject(instrumentsJSON);
    JSONObject config = new JSONObject(configJSON);
    Object indexCalMethodArguments[] = {instruments, config};
    GroovyShell shell = new GroovyShell();
    String expectedJson = "{\"index\":200.0}";

    //String receivedJSON = (String) shell.parse(script).invokeMethod("generateIndex", indexCalMethodArguments);
    String receivedJSON = (String) shell.parse(new StringReader(script)).invokeMethod("generateIndex", indexCalMethodArguments);
    System.out.println(receivedJSON);
    assertThat(expectedJson, is(equalTo(receivedJSON)));
  }
}

