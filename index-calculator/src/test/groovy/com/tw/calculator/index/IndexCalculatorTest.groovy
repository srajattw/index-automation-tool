package com.tw.calculator.index

import groovy.json.JsonSlurper

/**
 * Created by vohray on 7/14/16.
 */
class IndexCalculatorTest extends GroovyTestCase {
    List instrumentJSON = ['{"name":"RBS","value":100}']
    List instruments=[]
    String configJSON = '{ "simpleConfig":"simple"}'
    Object config


    protected void setUp() {
        def jsonSlurper = new JsonSlurper();
        Object instrument = jsonSlurper.parseText(instrumentJSON[0])
        instruments.add(instrument)
        config = jsonSlurper.parseText(configJSON);

    }


    void testIndexCalculation() {
        String expectedndexJson = '{"index":23}';
        IndexCalculator indexCalculator = new IndexCalculator(instruments, config)

        Object generatedIndex = indexCalculator.generateIndex();
        assert generatedIndex instanceof Map
        assert 23 == generatedIndex.index

    }
}
