package com.tm.orm.util;

public class ForeignKey {

	private String name;
	private String refTable;
	private String refColumn;

	public ForeignKey() {
		this.name = "";
		this.refTable = "";
		this.refColumn = "";
	}

	public ForeignKey(String name, String refTable, String refColumn) {
		this.name = name;
		this.refTable = refTable;
		this.refColumn = refColumn;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setRefTable(String refTable) {
		this.refTable = refTable;
	}

	public String getRefTable() {
		return this.refTable;
	}

	public void setRefColumn(String refColumn) {
		this.refColumn = refColumn;
	}

	public String getRefColumn() {
		return this.refColumn;
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
 		sb.append("Name: " + name + ", ");
 		sb.append("Ref Table: " + refTable + ", ");
 		sb.append("Ref Col: " + refColumn + "  ");

 		return sb.toString();
	}

}