package com.tm.orm.util;

import java.sql.*;

public class Attribute {

	private String name;
	private String datatype;
	private String size;
	private String decimalDigits;
	private boolean nullable;
	private boolean autoIncrement;
  private boolean primaryKey;
  private boolean foreignKey;
  private String foreignKeyRefTable;
  private String foreignKeyRefCol;
  private boolean uniqueKey;


 	public void setName(String name) {
 		this.name = name;
 	}

 	public String getName() {
  		return this.name;
 	}

 	public void setDatatype(String datatype){
  		this.datatype=datatype;
 	}

 	public String getDatatype(){
  		return this.datatype;
 	}

 	public void setSize(String size){
  		this.size=size;
 	}

 	public String getSize(){
  		return this.size;
 	}

 	public void setDecimalDigits(String decimalDigits){
  		this.decimalDigits=decimalDigits;
 	}

	public String getDecimalDigits(){
  		return this.decimalDigits;
 	}

 	public void setNullable(boolean nullable){
  		this.nullable = nullable;
 	}

 	public boolean isNullable(){
  		return this.nullable;
 	}

 	public void setAutoIncrement(boolean autoIncrement){
  		this.autoIncrement = autoIncrement;
 	}

 	public boolean isAutoIncrement(){
  		return this.autoIncrement;
 	}

  public void setPrimaryKey(boolean primaryKey) {
    this.primaryKey = primaryKey;
  }

  public boolean isPrimaryKey() {
    return this.primaryKey;
  }

  public void setForeignKey(boolean foreignKey) {
    this.foreignKey = foreignKey;
  }

  public boolean isForeignKey() {
    return this.foreignKey;
  }

  public void setUniqueKey(boolean uniqueKey) {
    this.uniqueKey = uniqueKey;
  }

  public boolean isUniqueKey() {
    return this.uniqueKey;
  }

  public void setForeignKeyRefTable(String name) {
    this.foreignKeyRefTable = name;
  }

  public String getForeignKeyRefTable() {
    return this.foreignKeyRefTable;
  }

  public void setForeignKeyRefCol(String name) {
    this.foreignKeyRefCol = name;
  }

  public String getForeignKeyRefCol() {
    return this.foreignKeyRefCol;
  }

 	public String getValue(ResultSet resultSet) {
 	  try {

 		 if(datatype.equalsIgnoreCase("INT"))
 			  return resultSet.getInt(name) + "";
 		 if(datatype.equalsIgnoreCase("CHAR"))
 		   	return resultSet.getString(name);
 		 if(datatype.equalsIgnoreCase("VARCHAR"))
 		   	return resultSet.getString(name);
 		 if(datatype.equalsIgnoreCase("DATE"))
 		   	return resultSet.getDate(name) + "";
 		 if(datatype.equalsIgnoreCase("SMALLINT UNSIGNED"))
 		   	return resultSet.getInt(name) + "";
 		 if(datatype.equalsIgnoreCase("TINYINT UNSIGNED"))
 		   	return resultSet.getInt(name) + "";
 		 if(datatype.equalsIgnoreCase("TIMESTAMP"))
 		   	return resultSet.getTime(name) + "";
 		 if(datatype.equalsIgnoreCase("FLOAT"))
 		   	return resultSet.getFloat(name) + "";
 		 if(datatype.equalsIgnoreCase("DECIMAL"))
 		   	return resultSet.getDouble(name) + "";
 		 if(datatype.equalsIgnoreCase("BIT"))
 		   	return resultSet.getInt(name) + "";

 	   }catch(Exception e) {
 	   	 	e.printStackTrace();
 	   }

 		return "";
 	}

  /**
   * toString is overriden for debugging purpose.
   */

 	@Override
 	public String toString() {

 		StringBuffer sb = new StringBuffer();
 		sb.append("Name: " + name + ", ");
 		sb.append("DataType: " + datatype + ", ");
 		sb.append("Size: " + size + ", ");
 		sb.append("DD: " + decimalDigits + ", ");
 		sb.append("isNullable: " + nullable + ", ");
 		sb.append("isAuIcr: " + autoIncrement + ", ");
    sb.append("isPrimary: " + primaryKey + ", ");
    sb.append("isForeign: " + foreignKey + ", ");
    sb.append("foreingkey tablename: " + foreignKeyRefTable + ", ");
    sb.append("foreingkey Column: " + foreignKeyRefCol + ", ");
    sb.append("isUniqueKey: " + uniqueKey + "\r\n");

 		return sb.toString();
 	}
}