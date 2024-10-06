package com.api.model;

import com.api.converter.StringListConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
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


}
