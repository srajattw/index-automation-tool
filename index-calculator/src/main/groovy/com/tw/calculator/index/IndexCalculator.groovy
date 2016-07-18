package com.tw.calculator.index

import groovy.json.JsonSlurper
/**
 * Created by vohray on 7/14/16.
 */
class IndexCalculator {

    List instruments
    Object config

    IndexCalculator(instruments, config) {
        this.instruments = instruments
        this.config = config
    }

    Object generateIndex() {

        def jsonSlurper = new JsonSlurper()
        return jsonSlurper.parseText('{ "index":23}')

    }


}
