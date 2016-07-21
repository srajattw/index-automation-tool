package com.tw;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.tw.dao.mongo.IndexDao;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/saveIndexConfig",method = RequestMethod.POST)
    public boolean saveIndex(@RequestBody String indexJson){
        boolean saved = true;

        try {
            indexDao.saveIndex(indexJson);
        } catch (UnknownHostException e) {
            saved=false;
            e.printStackTrace();
        }
        return saved;
    }


    @RequestMapping(value = "/saveMethodologyDefinition",method = RequestMethod.POST)
    public boolean saveMethodologyDefinition(@RequestBody String methodologyDefinition){
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



    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/calculateIndices",method = RequestMethod.POST)
    public Boolean calculateIndices(@RequestBody String request){

        DBObject requestObj = (DBObject)JSON.parse(request);
        System.out.println(request);
        System.out.println(requestObj.get("indicesToCalculate"));
        System.out.println(requestObj.get("startDate"));
        System.out.println(requestObj.get("endDate"));


        return true;
    }
}

