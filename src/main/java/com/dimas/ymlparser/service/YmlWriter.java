package com.dimas.ymlparser.service;

import com.dimas.ymlparser.dao.ModelRepository;
import com.dimas.ymlparser.model.Model;
import com.dimas.ymlparser.model.Offer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebParam;
import java.util.List;

@Slf4j
public class YmlWriter implements ItemWriter<Model> {

    @Autowired
    ModelRepository modelRepository;

    @Override
    public void write(List<? extends Model> list) throws Exception {
        modelRepository.save(list);
    }
}
