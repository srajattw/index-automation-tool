package com.tw.index.calculation;

import com.mongodb.DBObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by vohray on 7/20/16.
 */
public interface IndexCalculator {

  public String calculateIndex(List<DBObject> instruments, DBObject indexConfig) throws IOException;

}
