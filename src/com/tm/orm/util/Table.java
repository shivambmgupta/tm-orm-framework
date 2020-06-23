package com.tm.orm.util;

import java.util.*;
import java.sql.*;

public class Table {

	private String name;
	private String parentDB;
	private Map<String, Attribute> attributes;
	private List<String> primaryKeys;
	private List<String> foreignKeys;

	private Connection connection;

	public Table() {
		name = "";
		parentDB = "";
		connection = null;
		attributes = new HashMap<>();
		primaryKeys = new LinkedList<>();
		foreignKeys = new LinkedList<>();
	}
	
	public Table(String name, String parentDB, Connection connection) {
		this.name = name;
		this.connection = connection;
		this.parentDB = parentDB;
		this.attributes = new HashMap<>();
		this.primaryKeys = new LinkedList<>();
		this.foreignKeys = new LinkedList<>();
	}

 	public void setName(String name) {
  		this.name=name;
 	}

 	public String getName() {
		return this.name;
 	}

 	public void setConnection(Connection conn) {
 		this.connection = connection;
 	}

 	public Connection getConnection() {
 		return this.connection;
 	}

 	public void setParentDB(String parentDB) {
  		this.parentDB=parentDB;
 	}

 	public String getParentDB() {
 		return this.parentDB;
 	}

 	public void addAttribute(Attribute attribute) {
 		this.attributes.put(attribute.getName(), attribute);
 	}

 	public Map<String, Attribute> getAttributes() {
 		return this.attributes;
 	}

 	public Attribute getAttribute(int index) {
 		return this.attributes.get(index);
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

 	public void addForeignKey(String key) {
 		this.foreignKeys.add(key);
 	}

 	public List<String> getForeignKeys() {
 		return this.foreignKeys;
 	}

 	public String getForeignKey(int index) {
 		return this.foreignKeys.get(index);
 	}


 	@Override
 	public String toString() {
 		StringBuffer sb = new StringBuffer();

 		sb.append("Name: " + name + "\n\r");
 		sb.append("Parent Db: " + parentDB + "\n\r");
 		sb.append("Attributes: " + attributes.toString() + "\n\r");

 		sb.append("Primary keys: " + primaryKeys);
 		sb.append("Foreign keys: " + foreignKeys);
 		
 		return sb.toString();
 	}

}