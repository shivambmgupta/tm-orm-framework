package com.tm.orm.session;

import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.tm.orm.util.*;

/**
 *
 * Since it's a session, it must have
 * all the details requrired to establish a
 * connection.
 *
 */


public class TMSession {

	private String dbmsType;

	private String header;
	private String serverAddress;
	private int    portNumber;
	private String database;
	private String properties;

	private String username;
	private String password;
	
	static {
		TMDataHouse.configureHeaders();
	}

	public TMSession() {
		this.dbmsType = "MySQL";

		this.header = TMDataHouse.getHeader(dbmsType);
		this.serverAddress = "localhost";
		this.portNumber = 3306;
		this.database = "";
		this.properties = "";

		this.username = "";
		this.password = "";

	}
	
	public TMSession(String fileName) throws Exception {
		File file = new File(fileName);
		if(!file.exists()) throw new Exception("File not found");

		Gson gson = new Gson();

		StringBuffer stringBuffer = new StringBuffer();
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		while(raf.getFilePointer() < raf.length())
			stringBuffer.append(raf.readLine());

		TMSession session = (TMSession)gson.fromJson(stringBuffer.toString(), com.tm.orm.session.TMSession.class);

		this.setDbmsType(session.getDbmsType());  //this will automatically set header
		this.setServerAddress(session.getServerAddress());
		this.setPortNumber(session.getPortNumber());
		this.setDatabase(session.getDatabase());
		this.setProperties(session.getProperties());

		this.setUsername(session.getUsername());
		this.setPassword(session.getPassword());

	}

	public TMSession(String dbmsType, String serverAddress, int portNumber, String database, String username) {
		this.dbmsType = dbmsType;

		this.header = TMDataHouse.getHeader(dbmsType);
		this.serverAddress = this.serverAddress;
		this.portNumber = portNumber;
		this.database = database;
		this.properties = "";

		this.username = username;
		this.password = "";
	}

	public TMSession(String dbmsType, String serverAddress, int portNumber, String database, String username, String password) {
		this.dbmsType = dbmsType;

		this.header = TMDataHouse.getHeader(dbmsType);
		this.serverAddress = this.serverAddress;
		this.portNumber = portNumber;
		this.database = database;
		this.properties = "";

		this.username = username;
		this.password = password;	
	}

	public String getQueryString() {

		String queryString = header + serverAddress + ":" + portNumber + '/' + database;
		if(properties != "")
			queryString += "?" + properties;

		return queryString;  

    }
	//Modifiers/Setters
	public void setDbmsType(String dbmsType) {
		this.dbmsType = dbmsType;
		this.header = TMDataHouse.getHeader(dbmsType);
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}


	//Getters
	public String getDbmsType() {
		return this.dbmsType;
	}

	public String getServerAddress() {
		return this.serverAddress;
	}

	public int getPortNumber() {
		return this.portNumber;
	}

	public String getDatabase() {
		return this.database;
	}

	public String getProperties() {
		return this.properties;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}


	//Utility
	public void save() {

		Gson gson = new Gson();
		String toSave = gson.toJson(this);

		String fileName = "session";
		File file = new File(fileName + ".txt");
		int i = 1;
		while(file.exists()) {
			fileName = "session(" + i + ")";
			file = new File(fileName + ".txt");
			++i;
		}

		try {
			FileWriter writer = new FileWriter(file);
			writer.write(toSave);
			writer.close();
	    } catch(Exception exception) {
	    	exception.printStackTrace();
	    	return;
	    }
	}

	public void save(String fileName) {
		Gson gson = new Gson();
		String toSave = gson.toJson(this);

		File file = new File(fileName);
		if(file.exists()) file.delete();

		file = new File(fileName);
		
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(toSave);
			writer.close();
	    } catch(Exception exception) {
	    	exception.printStackTrace();
	    	return;
	    }
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("Database MS : " + this.dbmsType + "\n\r");
		buffer.append("Server      : " + this.serverAddress + "\n\r");
		buffer.append("Port        : " + this.portNumber + "\n\r");
		buffer.append("Database    : " + this.database + "\n\r");
		buffer.append("Properties  : " + this.properties + "\n\r");
		buffer.append("Username    : " + this.username + "\n\r");
		buffer.append("Password    : " + this.password + "\n\r");

		return buffer.toString();
	}

	@Override
	public boolean equals(Object object) {
		if(!(object instanceof com.tm.orm.session.TMSession)) return false;
		TMSession session = (TMSession) object;
		return this.toString().equals(session.toString());
	}

}