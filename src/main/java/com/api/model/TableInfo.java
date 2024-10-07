package com.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "tableinfo")
public class TableInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "schemaName")
    private String schemaName;
    @Column(name = "tableName")
    private String tableName;


}
