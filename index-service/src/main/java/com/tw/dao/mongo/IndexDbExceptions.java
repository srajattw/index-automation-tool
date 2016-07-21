package com.tw.dao.mongo;

/**
 * Created by vohray on 7/19/16.
 */
public class IndexDbExceptions {

    protected static RuntimeException objectNotfound(long objectId) {
        return new IndexException("Object with Id "+objectId+"not found");
    }

    protected static RuntimeException objectNotfound(String objectId) {
        return new IndexException("Object with Id "+objectId+"not found");
    }

    private static class IndexException extends RuntimeException {

        public IndexException(String message) {
            super(message);

        }

    }
}
