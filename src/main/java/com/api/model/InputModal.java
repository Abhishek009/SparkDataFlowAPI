package com.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/*
– @Entity annotation indicates that the class is a persistent Java class.
– @Table annotation provides the table that maps this entity.
– @Id annotation is for the primary key.
– @GeneratedValue annotation is used to define generation strategy for the primary key. GenerationType.AUTO means Auto Increment field.
– @Column annotation is used to define the column in database that maps annotated field.
*/
@Getter
@Setter
@Entity
@Table(name = "InputModal")
public class InputModal {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	@Column(name = "dataSetName")
	private String dataSetName;
	@Column(name = "sourceType")
    private String sourceType;
	@Column(name = "schemaName")
    private String schemaName;
	@Column(name = "tableName")
	private String tableName;
	@Column(name = "directoryFileLocation")
	private String directoryFileLocation;
	@Column(name = "datasetType")
	private String datasettype;

}
