package com.api.model;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/*
– @Entity annotation indicates that the class is a persistent Java class.
– @Table annotation provides the table that maps this entity.
– @Id annotation is for the primary key.
– @GeneratedValue annotation is used to define generation strategy for the primary key. GenerationType.AUTO means Auto Increment field.
– @Column annotation is used to define the column in database that maps annotated field.
*/

@Entity
@Table(name = "users")
public class User {
	
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

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", dataSetName='" + dataSetName + '\'' +
				", sourceType='" + sourceType + '\'' +
				", schemaName='" + schemaName + '\'' +
				", tableName='" + tableName + '\'' +
				", directoryFileLocation='" + directoryFileLocation + '\'' +
				'}';
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDataSetName() {
		return dataSetName;
	}

	public void setDataSetName(String dataSetName) {
		this.dataSetName = dataSetName;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
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

	public String getDirectoryFileLocation() {
		return directoryFileLocation;
	}

	public void setDirectoryFileLocation(String directoryFileLocation) {
		this.directoryFileLocation = directoryFileLocation;
	}
}
