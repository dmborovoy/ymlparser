package com.dimas.ymlparser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.*;

@Setter
@Getter
@Entity
@Table(name = "model")
public class Model {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "model_id")
    private String id;//group_id

    private String picture;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "model")
    private Set<Variation> variations;

    public Model() {
    }

    public Model(String id, String picture, String description) {
        this.id = id;
        this.picture = picture;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id='" + id + '\'' +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
