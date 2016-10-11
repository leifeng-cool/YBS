package com.ybs.model;

public class Block {
	private String ID;
	private String Type_Name;
	private boolean enable;
	private String tableName;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getType_Name() {
		return Type_Name;
	}
	public void setType_Name(String type_Name) {
		Type_Name = type_Name;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
