package com.tw.index.calculation;

import com.mongodb.DBObject;
import groovy.lang.GroovyShell;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by vohray on 7/20/16.
 */
public class IndexCalculatorFactory {

  public static IndexCalculator getIndexCalculator(String type) {
    switch (type) {
      case "Groovy":
        return new GroovyIndexCalculator();
      default:
        return null;
    }

  }

  private static class GroovyIndexCalculator implements IndexCalculator {
    @Override
    public String calculateIndex(List<DBObject> instruments, DBObject indexConfig) throws IOException {
      String calculationScript = (String) ((DBObject) indexConfig.get("methodology")).get("calculationScript");
      GroovyShell shell = new GroovyShell();
      Object indexCalMethodArguments[] = {instruments, indexConfig};
      String receivedJSON = (String) shell.parse(calculationScript).invokeMethod("generateIndex", indexCalMethodArguments);
      return receivedJSON;


    }
  }
}
