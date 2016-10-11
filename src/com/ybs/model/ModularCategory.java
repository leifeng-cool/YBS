package com.ybs.model;

public class ModularCategory {
	private String ID;
	private String parent_ID;
	private String Name;
	private String TableName;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getParent_ID() {
		return parent_ID;
	}
	public void setParent_ID(String parent_ID) {
		this.parent_ID = parent_ID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getTableName() {
		return TableName;
	}
	public void setTableName(String tableName) {
		TableName = tableName;
	}
	
}
