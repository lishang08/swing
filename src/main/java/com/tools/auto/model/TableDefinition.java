package com.tools.auto.model;
/**
* Author: fulishang
* Create Time  : 2017年5月14日,下午9:39:28
* Modify Time :
* Desc  : 
* Blog : https://lishang08.github.io/
*/

public class TableDefinition {

	//column name which get from source
	private String logicalName;
	//flag to indicate whether need to onboard
	private String onboard;
	//column name which match with the pyshical table
	private String physicalName;
    //data type which can be date? number? varchar
	private String dataType;
	//table name
	private String tableName;
	//the position of the field in the source file
	private String position;
	//flag to indicate whether the column is allow to null
	private String nullable;
	//maxlength
	private String maxlength;
	//data length
	private String dataLength;
	
	
	
	public String getDataLength() {
		return dataLength;
	}
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}
	public String getMaxlength() {
		return maxlength;
	}
	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}
	public String getLogicalName() {
		return logicalName;
	}
	public void setLogicalName(String logicalName) {
		this.logicalName = logicalName;
	}
	public String getOnboard() {
		return onboard;
	}
	public void setOnboard(String onboard) {
		this.onboard = onboard;
	}
	public String getPhysicalName() {
		return physicalName;
	}
	public void setPhysicalName(String physicalName) {
		this.physicalName = physicalName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getNullable() {
		return nullable;
	}
	public void setNullable(String nullable) {
		this.nullable = nullable;
	}
	@Override
	public String toString() {
		return "StagingTable [logicalName=" + logicalName + ", onboard=" + onboard + ", physicalName=" + physicalName
				+ ", dataType=" + dataType + ", tableName=" + tableName + ", position=" + position + ", nullable="
				+ nullable + ", maxlength=" + maxlength + ", dataLength=" + dataLength + "]";
	}
	

}
