package com.tm.orm.util;

import java.sql.*;

public class Attribute {

	private String name;
	private String datatype;
	private String size;
	private String decimalDigits;
	private boolean isNullable;
	private boolean isAutoIncrement;


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

 	public void setIsNullable(boolean isNullable){
  		this.isNullable=isNullable;
 	}

 	public boolean getIsNullable(){
  		return this.isNullable;
 	}

 	public void setIsAutoIncrement(boolean isAutoIncrement){
  		this.isAutoIncrement=isAutoIncrement;
 	}

 	public boolean getIsAutoIncrement(){
  		return this.isAutoIncrement;
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

 	@Override
 	public String toString() {

 		StringBuffer sb = new StringBuffer();
 		sb.append("Name: " + name + ", ");
 		sb.append("DataType: " + datatype + ", ");
 		sb.append("Size: " + size + ", ");
 		sb.append("DD: " + decimalDigits + ", ");
 		sb.append("isNullable: " + isNullable + ", ");
 		sb.append("isAuIcr: " + isAutoIncrement);

 		return sb.toString();
 	}
}