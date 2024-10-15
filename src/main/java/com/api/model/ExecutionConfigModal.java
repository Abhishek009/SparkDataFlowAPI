package com.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@ToString
public class ExecutionConfigModal {
    @Id
    private long id;
    private String inputDatasetId;
    private String inputDatasetName;
    private String inputDatasetType;
    private String inputDatasetSchemaName;
    private String inputDatasetTableName;
    private String inputSourceType;
    private String inputSourceFormat;
    private String computeDatasetId;
    private String computeDatasetName;
    private String outputDatasetId;
    private String outputDataset;
    private String outputSchemaName;
    private String outputTableName;


}
