package com.dimas.ymlparser.dao;

import com.dimas.ymlparser.model.Model;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ModelRepository extends CrudRepository<Model, String> {

//    List<Model> findByLastName(String lastName);
}