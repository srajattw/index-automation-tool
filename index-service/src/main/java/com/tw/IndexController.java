package com.tw;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    public static String PING_REPLY = "reply from ping service";

    @RequestMapping("/ping")
    public String ping() {
        return PING_REPLY;
    }


    @RequestMapping()
    public boolean saveIndex(){

        return true;
    }
}

