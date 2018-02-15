package com.dimas.ymlparser.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Offer {

    private String id;
    private String groupId;
    private Boolean isAvailable;
    private String price;
    private String currency;
    private String category;
    private String name;
    private String picture;
    private String description;
    private Map<String, String> params = new HashMap<>();

}
