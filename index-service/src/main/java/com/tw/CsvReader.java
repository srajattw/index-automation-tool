package com.tw;

import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rajats on 7/21/16.
 */
public class CsvReader {

   public static List<String>  read(String path){
        FileReader fis = null;
        BufferedReader bis = null;
        String line="";
        List<String> instruments = new ArrayList<String>();

        File f = new File(path);

        String name = f.getName();

        String parts[] = name.split("_");
        try {
             fis = new FileReader(path);
             bis = new BufferedReader(fis);
            line = bis.readLine();
            while ((line = bis.readLine()) != null) {

                // use comma as separator
                String[] data = line.split("\t");

                Map<String,Object> map = new HashMap<String,Object>();

                map.put("instrumentType","BSE_CORPORATIONS");
                map.put("company",data[0]);

                map.put("floatingCapitalization",Double.parseDouble(data[1]));
                map.put("fixedCapitalization",Double.parseDouble(data[2]));
                map.put("asof",parts[0]);

                String json = JSONObject.toJSONString(map);

                instruments.add(json);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                bis.close();
            } catch (IOException e) {

            }
        }

        return instruments;
    }
}
