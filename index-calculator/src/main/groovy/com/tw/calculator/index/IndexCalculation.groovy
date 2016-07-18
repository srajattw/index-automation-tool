package com.tw.calculator.index

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

/**
 * Created by vohray on 7/15/16.
 */

String generateIndex(instruments, config) {

    double indexCaluclated = 0
    for (instrument in instruments) {
        indexCaluclated = indexCaluclated + instrument.price + config.baseValue
    }
    def json = new JsonBuilder()
    def root = json "index": indexCaluclated
    return json.toString()
}

void testGenerateIndex() {
    List instrumentJSON = ['[{"name":"RBS","price":100},{"name":"ABN","price":100}]']
    String configJSON = '{ "simpleConfig":"simple","baseValue":100}'
    Object config

    def jsonSlurper = new JsonSlurper();
    Object instrument = jsonSlurper.parseText(instrumentJSON[0])
    config = jsonSlurper.parseText(configJSON);

    String generatedIndex = generateIndex(instrument, config)
    System.out.println(generatedIndex);

}

def var = testGenerateIndex();