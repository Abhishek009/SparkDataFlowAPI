package com.api.model;

import javax.persistence.*;

@Entity
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

    @Override
    public String toString() {
        return "Transformation{" +
                "id=" + id +
                ", transDataSetName='" + transDataSetName + '\'' +
                ", transSourceNames='" + transSourceNames + '\'' +
                ", transOutputName='" + transOutputName + '\'' +
                ", transQuery='" + transQuery + '\'' +
                '}';
    }

    public String getTransQuery() {
        return transQuery;
    }

    public void setTransQuery(String transQuery) {
        this.transQuery = transQuery;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransDataSetName() {
        return transDataSetName;
    }

    public void setTransDataSetName(String transDataSetName) {
        this.transDataSetName = transDataSetName;
    }

    public String getTransSourceNames() {
        return transSourceNames;
    }

    public void setTransSourceNames(String transSourceNames) {
        this.transSourceNames = transSourceNames;
    }

    public String getTransOutputName() {
        return transOutputName;
    }

    public void setTransOutputName(String transOutputName) {
        this.transOutputName = transOutputName;
    }
}
