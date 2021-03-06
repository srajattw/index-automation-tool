package com.tw.index.calculation;

import com.mongodb.DBObject;
import groovy.lang.GroovyShell;

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
        return new GroovyIndexCalculator();
    }

  }

  private static class GroovyIndexCalculator implements IndexCalculator {
    @Override



    public Double calculateIndex(List<DBObject> instruments, DBObject indexConfig) throws IOException {
      String calculationScript = (String) ((DBObject) indexConfig.get("methodology")).get("calculatorScript");
      GroovyShell shell = new GroovyShell();
      Object indexCalMethodArguments[] = {instruments, indexConfig};
      Double calculatedIndex = (Double) shell.parse(calculationScript).invokeMethod("generateIndex", indexCalMethodArguments);
      return calculatedIndex;
    }
  }
}
