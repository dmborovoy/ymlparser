package com.dimas.ymlparser.service;

import com.dimas.ymlparser.model.Model;
import com.dimas.ymlparser.model.Offer;
import com.dimas.ymlparser.model.Variation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class ParserTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void name() {
        String fileName = "/home/dimas/ymlparser/src/main/resources/yml_test_new.xml";
        List<Offer> empList = Parser.parseXML(fileName);
        for(Offer emp : empList){
            log.info(emp.toString());
        }

        List<Model> models = Parser.getModels(empList);

        for(Model emp : models){
            log.info(emp.toString());
            for (Variation variation : emp.getVariations()) {
                log.info(variation.toString());
            }
        }

    }
}