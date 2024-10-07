package com.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class FlowMapping {
    @Id
    private long id;
    private int inputDataId;
    private String inputDatasetName;
    private String outputDatasetId;
    private String outputDatasetName;
    private String datasetType;


}
