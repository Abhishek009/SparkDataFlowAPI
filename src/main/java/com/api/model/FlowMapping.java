package com.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FlowMapping {
    @Id
    private long id;
    private int inputDataId;
    private String inputDatasetName;
    private String outputDatasetId;
    private String outputDatasetName;

    @Override
    public String toString() {
        return "FlowMapping{" +
                "id=" + id +
                ", inputDataId=" + inputDataId +
                ", inputDatasetName='" + inputDatasetName + '\'' +
                ", outputDatasetId='" + outputDatasetId + '\'' +
                ", outputDatasetName='" + outputDatasetName + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getInputDataId() {
        return inputDataId;
    }

    public void setInputDataId(int inputDataId) {
        this.inputDataId = inputDataId;
    }

    public String getInputDatasetName() {
        return inputDatasetName;
    }

    public void setInputDatasetName(String inputDatasetName) {
        this.inputDatasetName = inputDatasetName;
    }

    public String getOutputDatasetId() {
        return outputDatasetId;
    }

    public void setOutputDatasetId(String outputDatasetId) {
        this.outputDatasetId = outputDatasetId;
    }

    public String getOutputDatasetName() {
        return outputDatasetName;
    }

    public void setOutputDatasetName(String outputDatasetName) {
        this.outputDatasetName = outputDatasetName;
    }
}
