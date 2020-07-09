package com.tm.orm.util;

import java.util.*;
import java.sql.*;

public class Table {

	private String name;
	
	private Map<String, Attribute> attributes;

	/**
	 *
	 * Though each attributes is already contained with the 
	 * information if it's a primary key, or a foreign key, or uniuqe but
	 * still keeping DSs explicitly. Just keeping these DS will
	 * avoid future calculations. A bit redundancy can avoid lot of calculations.
	 *
	 */

	private List<String> primaryKeys;
	private List<ForeignKey> foreignKeys;
	private List<String> uniqueKeys;

	private boolean autoIncrementPrimaryKey;

	public Table() {

		this.name = "";
		this.attributes = new HashMap<>();
		this.primaryKeys = new LinkedList<>();
		this.foreignKeys = new LinkedList<>();
		this.uniqueKeys = new LinkedList<>();

	}
	
	public Table(String name, String parentDB, Connection connection) {

		this.name = name;
		this.attributes = new HashMap<>();
		this.primaryKeys = new LinkedList<>();
		this.foreignKeys = new LinkedList<>();
		this.uniqueKeys = new LinkedList<>();

	}

 	public void setName(String name) {
  		this.name=name;
 	}

 	public String getName() {
		return this.name;
 	}

 	public void addAttribute(Attribute attribute) {
 		this.attributes.put(attribute.getName(), attribute);
 	}

 	public Map<String, Attribute> getAttributes() {
 		return this.attributes;
 	}

 	public Attribute getAttribute(String fieldName) {
 		return this.attributes.get(fieldName);
 	}

 	public void addPrimaryKey(String key) {
 		this.primaryKeys.add(key);
 	}

 	public List<String> getPrimaryKeys() {
 		return this.primaryKeys;
 	}

 	public String getPrimaryKey(int index) {
 		return this.primaryKeys.get(index);
 	}

 	public void addForeignKey(ForeignKey key) {
 		this.foreignKeys.add(key);
 	}

 	public List<ForeignKey> getForeignKeys() {
 		return this.foreignKeys;
 	}

 	public ForeignKey getForeignKey(int index) {
 		return this.foreignKeys.get(index);
 	}

 	public void addUniqueKey(String key) {
 		this.uniqueKeys.add(key);
 	}

 	public List<String> getUniqueKeys() {
 		return this.uniqueKeys;
 	}

 	public String getUniqueKey(int index) {
 		return this.uniqueKeys.get(index);
 	}

 	public void setAutoIncrementPrimaryKey(boolean value) {
 		this.autoIncrementPrimaryKey = value;
 	}

    public boolean hasAutoIncrementPrimaryKey() {
    	return this.autoIncrementPrimaryKey;
    }


 	@Override
 	public String toString() {
 		StringBuffer sb = new StringBuffer();

 		sb.append("  Name: " + name + "\n\r");
 		sb.append("  Attributes: " + attributes.toString() + "\n\r");

 		sb.append("  Primary keys: " + primaryKeys + "\n\r");
 		sb.append("  Foreign keys: " + foreignKeys + "\n\r");
 		sb.append("  Unique keys: " + uniqueKeys + "\n\r");
 		
 		return sb.toString();
 	}

}