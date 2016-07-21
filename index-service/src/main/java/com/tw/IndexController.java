package com.tw;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.tw.dao.mongo.IndexDao;
import com.tw.index.calculation.IndexCalculator;
import com.tw.index.calculation.IndexCalculatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class IndexController {

  public static String PING_REPLY = "reply from ping service";

  @Autowired
  protected IndexDao indexDao;

  @RequestMapping("/ping")
  public String ping() {
    return PING_REPLY;
  }


  @RequestMapping(value = "/saveIndexConfig", method = RequestMethod.POST)
  public boolean saveIndex(@RequestBody String indexJson) {
    boolean saved = true;

    try {
      indexDao.saveIndex(indexJson);
    } catch (UnknownHostException e) {
      saved = false;
      e.printStackTrace();
    }
    return saved;
  }


  @RequestMapping(value = "/saveMethodologyDefinition", method = RequestMethod.POST)
  public boolean saveMethodologyDefinition(@RequestBody String methodologyDefinition) {
    boolean saved = true;

    try {
      indexDao.saveMethodologyDefinition(methodologyDefinition);
    } catch (UnknownHostException e) {
      saved = false;
      e.printStackTrace();
    }
    return saved;
  }


  @RequestMapping(value = "/calculateIndices", method = RequestMethod.POST)
  public String calculateIndices(@RequestBody String request) throws IOException {


    DBObject requestObj = (DBObject) JSON.parse(request);
    DBObject indexConfigJSON = (DBObject) requestObj.get("indicesToCalculate");
    String startDate = (String) requestObj.get("startDate");
    String endDate = (String) requestObj.get("endDate");
    String typeOfScript = (String) requestObj.get("typeOfScript");

    IndexCalculator indexCalculator = IndexCalculatorFactory.getIndexCalculator(typeOfScript);
    List<DBObject> instruments = indexDao.fetchAllInstruments();

    String calculatedIndex = indexCalculator.calculateIndex(instruments, requestObj);
    indexDao.saveCalculatedIndex(calculatedIndex);
    return calculatedIndex;
  }

}

