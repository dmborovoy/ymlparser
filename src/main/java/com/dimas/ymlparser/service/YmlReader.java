package com.dimas.ymlparser.service;

import com.dimas.ymlparser.model.Model;
import com.dimas.ymlparser.model.Offer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class YmlReader implements ItemReader<Model> {

    private String fileName;
    private Iterator<Model> iterator;

    public YmlReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Model read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (iterator.hasNext()) {
            Model next = iterator.next();
            log.info("");
            return next;
        } else {
            return null;
        }
    }


    @BeforeStep
    public void open() throws FileNotFoundException {
        List<Offer> offers = Parser.parseXML(fileName);
        List<Model> models = Parser.getModels(offers);
        this.iterator = models.iterator();
    }

    @AfterStep
    public void destroy() {
    }

}
