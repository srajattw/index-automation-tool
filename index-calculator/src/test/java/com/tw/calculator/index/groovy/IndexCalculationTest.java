package com.tw.calculator.index.groovy;

import groovy.lang.GroovyShell;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by vohray on 7/15/16.
 */
public class IndexCalculationTest {
  @Test
  public void testIndexCalculation() throws IOException, JSONException {
    String instrumentsJSON = "{\"name\":\"RBS\",\"price\":100}";
    String configJSON = "{ \"simpleConfig\":\"simple\" , \"baseValue\":100}";
    JSONObject instruments = new JSONObject(instrumentsJSON);
    JSONObject config = new JSONObject(configJSON);
    Object indexCalMethodArguments[] = {instruments, config};
    GroovyShell shell = new GroovyShell();
    String expectedJson = "{\"index\":200.0}";

    String receivedJSON = (String) shell.parse(new File("/Users/vohray/yug_ws/index-automation-tool/index-calculator/src/main/groovy/com/tw/calculator/index/IndexCalculation.groovy")).invokeMethod("generateIndex", indexCalMethodArguments);

    assertThat(expectedJson, is(equalTo(receivedJSON)));
  }
}
