package com.api.model;

import javax.persistence.*;

@Entity
@Table(name = "tableinfo")
public class TableInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "schemaName")
    private String schemaName;
    @Column(name = "tableName")
    private String tableName;

    @Override
    public String toString() {
        return "TableInfo{" +
                "id=" + id +
                ", schemaName='" + schemaName + '\'' +
                ", tableName='" + tableName + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
