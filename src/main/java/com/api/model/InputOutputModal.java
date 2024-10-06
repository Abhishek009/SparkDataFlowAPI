package com.api.model;

import com.api.converter.StringListConverter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "inputoutput")
public class InputOutputModal {
    @Convert(converter = StringListConverter.class)
    private List<String> selectedInputNames;
    @Column(name = "outputDataset")
    private String outputDataset;
    @Column(name = "outputDatasetFormat")
    private String format;
    @Column(name = "outputTableName")
    private String tableName;
    @Column(name = "outputSchemaName")
    private String schemaName;
    @Id
    private long id;
    @ManyToOne
    @JoinColumn(name = "dataSetName")
    private InputModal inputModal;

    @Override
    public String toString() {
        return "InputOutputModal{" +
                "selectedInputNames='" + selectedInputNames + '\'' +
                ", outputDataset='" + outputDataset + '\'' +
                ", format='" + format + '\'' +
                ", tableName='" + tableName + '\'' +
                ", schemaName='" + schemaName + '\'' +
                ", id=" + id +
                ", inputModal=" + inputModal +
                '}';
    }

    public List<String> getSelectedInputNames() {
        return selectedInputNames;
    }

    public void setSelectedInputNames(List<String> selectedInputNames) {
        this.selectedInputNames = selectedInputNames;
    }

    public String getOutputDataset() {
        return outputDataset;
    }

    public void setOutputDataset(String outputDataset) {
        this.outputDataset = outputDataset;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public InputModal getInputModal() {
        return inputModal;
    }

    public void setInputModal(InputModal inputModal) {
        this.inputModal = inputModal;
    }
}
