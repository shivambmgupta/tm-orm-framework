package com.tm.orm.util;

import java.io.*;
import java.util.*;

public class Record {

	private String tableName;
	private Set<String> primaryKeys;

	/**
	 *
	 * String -> key name
	 * Set -> records for this key
	 *
	 */

	private Map<String,Set<String>> uniqueKeys;
	private Map<String,Set<String>> foreignKeys;


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setPrimaryKeys(Set<String> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public void addPrimaryKey(String key) {
		this.primaryKeys.add(key);
	}

	public Set<String> getPrimaryKeys() {
		return this.primaryKeys;
	}

	public void setUniqueKeys(Map<String,Set<String>> uniqueKeys) {
		this.uniqueKeys = uniqueKeys;
	}

	public void addUniqueKey(String key, String value) {
		Set<String> temp = uniqueKeys.get(key);
		if(temp == null) return;
		temp.add(value);
	}

	public Set<String> getUniqueKeyValues(String key) {
		return this.uniqueKeys.get(key);
	}	

	public Map<String,Set<String>> getUniqueKeys() {
		return this.uniqueKeys;
	}

	public void setForeignKeys(Map<String,Set<String>> foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

	public void addForeignKey(String key, String value) {
		Set<String> temp = foreignKeys.get(key);
		if(temp == null) return;
		temp.add(value);
	}

	public Set<String> getForeignKeyValues(String key) {
		return this.foreignKeys.get(key);
	}

	public Map<String,Set<String>> getForeignKeys() {
		return this.foreignKeys;
	}
	
	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Table Name: " + tableName);
		stringBuffer.append("PK Values: " + primaryKeys);
		stringBuffer.append("FK values: " + foreignKeys);
		stringBuffer.append("UK values: " + uniqueKeys);

		return stringBuffer.toString();
	}
}