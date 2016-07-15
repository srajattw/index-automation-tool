package com.tw;

import com.tw.dao.mongo.IndexDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;

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
            indexDao.saveMethodologyDefinition(methodologyDefinition);
        } catch (UnknownHostException e) {
            saved=false;
            e.printStackTrace();
        }
        return saved;
    }

}

