package com.tm.orm.util;

public class DBMSMeta {

	private String dbms;
	private String header;
	private String connectionString;
	private int defaultPort;
	private String defaultServerAddress;

	public DBMSMeta(String dbms, String header, String connectionString, int defaultPort) {
		this.dbms = dbms;
		this.header = header;
		this.connectionString = connectionString;
		this.defaultPort = defaultPort;
	}


	public void setDbms(String dbms){
  		this.dbms=dbms;
 	}

 	public String getDbms(){
  		return this.dbms;
 	}

 	public void setHeader(String header){
  		this.header=header;
 	}

 	public String getHeader(){
  		return this.header;
 	}

 	public void setConnectionString(String connectionString){
  		this.connectionString=connectionString;
 	}

 	public String getConnectionString(){
  		return this.connectionString;
 	}

 	public void setDefaultPort(int defaultPort){
  		this.defaultPort=defaultPort;
 	}

 	public int getDefaultPort(){
  		return this.defaultPort;
 	}

 	public void setDefaultServerAddress(String defaultServerAddress){
  		this.defaultServerAddress=defaultServerAddress;
 	}

 	public String getDefaultServerddress(){
  		return this.defaultServerAddress;
 	}

}