package com.dimas.ymlparser.dao;

import com.dimas.ymlparser.model.Model;
import com.dimas.ymlparser.model.Variation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VariationRepository extends CrudRepository<Variation, String> {

}