package com.dimas.ymlparser.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Entity
@Table(name = "variation")
public class Variation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

    @Column(name = "variation_id")
    private String variationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = true)
    private Model model;

    @ElementCollection
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    @CollectionTable(name = "attributes", joinColumns = @JoinColumn(name = "attribute_id"))
    private Map<String, String> params = new HashMap<>();

    @Override
    public String toString() {
        return "Variation{" +
                "id='" + id + '\'' +
                ", model_id='" + (model == null ? "" : model.getId()) + '\'' +
                ", variationId='" + variationId + '\'' +
                ", params=" + params +
                '}';
    }
}
