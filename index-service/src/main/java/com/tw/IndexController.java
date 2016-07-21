package com.tw;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.tw.dao.mongo.IndexDao;

import org.json.simple.JSONObject;

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
            System.out.println("methodology save request:"+methodologyDefinition);
            indexDao.saveMethodologyDefinition(methodologyDefinition);
        } catch (UnknownHostException e) {
            saved=false;
            e.printStackTrace();
        }
        return saved;
  }


  @RequestMapping(value = "/calculateIndices", method = RequestMethod.POST)
  public String calculateIndices(@RequestBody String request) throws IOException {


    DBObject requestObj = (DBObject) JSON.parse(request);
    BasicDBList indexConfigJSON = (BasicDBList) requestObj.get("indicesToCalculate");
    System.out.println(indexConfigJSON);
    String startDate = (String) requestObj.get("startDate");
    String endDate = (String) requestObj.get("endDate");

    String typeOfScript = "groovy";
    String calculatedIndex = "";
    for(Object index  : indexConfigJSON) {

        IndexCalculator indexCalculator = IndexCalculatorFactory.getIndexCalculator(typeOfScript);
        List<DBObject> instruments = indexDao.fetchAllInstruments();

        calculatedIndex = indexCalculator.calculateIndex(instruments, (DBObject) index);
        indexDao.saveCalculatedIndex(calculatedIndex);
    }
    return calculatedIndex;
  }


    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/getMethodologies",method = RequestMethod.GET)
    public List fetchMethodlogies(){
        List methodlogies = null;

        try {
             methodlogies = indexDao.fetchAllMethodologies();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return methodlogies;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/searchIndices",method = RequestMethod.GET)
    public List fetchIndices(){
        List indexConfigs = null;

        try {
            indexConfigs = indexDao.fetchAllIndexConfig();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return indexConfigs;
    }

}

