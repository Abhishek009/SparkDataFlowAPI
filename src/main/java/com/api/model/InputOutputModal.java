package com.api.model;

import com.api.converter.StringListConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "inputoutput")
public class InputOutputModal {
    @Id
    private long id;
    @Convert(converter = StringListConverter.class)
    private List<String> selectedInputNames;
    @Column(name = "outputDataset")
    private String outputDataset;
    @Column(name = "outputDatasetFormat")
    private String format;
    @Column(name = "outputSchemaName")
    private String schemaName;
    @Column(name = "outputTableName")
    private String tableName;

    @ManyToOne
    @JoinColumn(name = "dataSetName")
    private InputModal inputModal;


}
