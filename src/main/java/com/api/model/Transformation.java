package com.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "transformation")
public class Transformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "transDataSetName")
    private String transDataSetName;
    @Column(name = "transSourceNames")
    private String transSourceNames;
    @Column(name = "transOutputName")
    private String transOutputName;
    @Column(name = "transQuery")
    private String transQuery;


}
