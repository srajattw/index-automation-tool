package com.tw.calculator.index

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

/**
 * Created by vohray on 7/15/16.
 */


double generateIndex(instruments, config) {
    double totalMarketCapitalization = 0
    for (instrument in instruments) {
        totalMarketCapitalization = totalMarketCapitalization + instrument.floatingCapitalization
    }
    double indexCalculated = totalMarketCapitalization * config.baseIndex / config.baseCapitalization
    return indexCalculated
}

void testGenerateIndex() {
    List instrumentJSON = ['[{"name":"RBS","fixedCapitalization":100},{"name":"ABN","fixedCapitalization":100}]']
    String configJSON = '{ "simpleConfig":"simple","baseIndex":100,"baseCapitalization":300}'
    Object config

    def jsonSlurper = new JsonSlurper();
    Object instrument = jsonSlurper.parseText(instrumentJSON[0])
    config = jsonSlurper.parseText(configJSON);

    double generatedIndex = generateIndex(instrument, config)
    System.out.println(generatedIndex);

}

def var = testGenerateIndex();