package com.tw.dao.mongo;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajats on 7/12/16.
 */

@Component
public class IndexDao {

    public static final String INDEX_DB = "IndexDB";
    public static final String INDEX_CONFIG_COLLECTION = "IndexConfig";

    public MongoClient getMongoClient() throws UnknownHostException {
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        return mongo;
    }

    public void saveIndex(String indexJson) throws UnknownHostException {
        DBObject index = (DBObject) JSON.parse(indexJson);
        MongoClient mongo = getMongoClient();
        DB db = mongo.getDB(INDEX_DB);
        DBCollection collection = db.getCollection(INDEX_CONFIG_COLLECTION);
        WriteResult writeResult = collection.insert(index);
        System.out.print(writeResult.getN());
    }

    public void saveMethodologyDefinition(String methodologyDefinition) throws UnknownHostException {
        DBObject index = (DBObject) JSON.parse(methodologyDefinition);
        MongoClient mongo = getMongoClient();
        DB db = mongo.getDB(INDEX_DB);
        DBCollection collection = db.getCollection("MethodologyDefinition");
        WriteResult writeResult = collection.insert(index);
        System.out.print(writeResult.getN());
    }


    public List<DBObject> fetchAllIndexConfig() throws UnknownHostException {
        MongoClient mongo = getMongoClient();
        DB db = mongo.getDB(INDEX_DB);
        DBCollection table = db.getCollection(INDEX_CONFIG_COLLECTION);

        /*"_id" : ObjectId("578dc1b9bee83a3307e85504"),
                "indexName" : "Sensex",
                "indexCode" : "Sensex",
                "baseIndex" : "100",
                "baseIndexLevel" : "70000",
                "indexStartDate" : "2016-07-01",*/

        String query = "{ }, { indexName: 1, indexCode: 1 }";


        BasicDBObject fields = new BasicDBObject();
        //fields.put("_id", 0);
        fields.put("indexName", 1);
        fields.put("indexCode",1);
        fields.put("baseIndexLevel",1);
        fields.put("indexStartDate",1);
        fields.put("baseIndex",1);

        DBCursor cursor = table.find(new BasicDBObject(),fields);
        List<DBObject> indexConfigs = new ArrayList<DBObject>();
        while (cursor.hasNext()) {
            indexConfigs.add(cursor.next());
        }

        return indexConfigs;
    }

    public List fetchAllMethodologies() throws UnknownHostException {
        MongoClient mongo = getMongoClient();
        DB db = mongo.getDB(INDEX_DB);
        DBCollection table = db.getCollection("MethodologyDefinition");
        DBCursor cursor = table.find();
        List methodlogies = new ArrayList<String>();
        while (cursor.hasNext()) {
            DBObject result= cursor.next();
            //String output = JSONObject.toJSONString(result.toMap());
            methodlogies.add(result);
        }

        return methodlogies;
    }
}
