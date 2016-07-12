package com.tw.dao.mongo;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajats on 7/12/16.
 */

@Component
public class IndexDao {

    public MongoClient getMongoClient() throws UnknownHostException {
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        return mongo;
    }

    public void saveIndex(String indexJson) throws UnknownHostException {
        DBObject index = (DBObject) JSON.parse(indexJson);
        MongoClient mongo = getMongoClient();
        DB db = mongo.getDB("IndexDB");
        DBCollection collection = db.getCollection("IndexConfig");
        WriteResult writeResult = collection.insert(index);
        System.out.print(writeResult.getN());
    }


    public List<DBObject> fetchAllIndexConfig() throws UnknownHostException {
        MongoClient mongo = getMongoClient();
        DB db = mongo.getDB("IndexDB");
        DBCollection table = db.getCollection("IndexConfig");
        DBCursor cursor = table.find();
        List<DBObject> indexConfigs = new ArrayList<DBObject>();
        while (cursor.hasNext()) {
            indexConfigs.add(cursor.next());
        }

        return indexConfigs;
    }
}
