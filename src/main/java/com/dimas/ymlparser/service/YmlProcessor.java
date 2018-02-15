package com.dimas.ymlparser.service;

import com.dimas.ymlparser.model.Model;
import com.dimas.ymlparser.model.Offer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.*;

@Slf4j
public class YmlProcessor implements ItemProcessor<Offer, Model> {


    @Override
    public Model process(Offer offer) throws Exception {
        return null;
    }
}
