package com.tm.orm.util;

import java.sql.*;

public class ORMConnection {

	private static Connection connection;

	private ORMConnection() {}

	public static void setConnection(Connection value) {
		connection = value;
	}

	public static void commit() {
		try {
			connection.commit();
		} catch(Exception exception) {
			exception.printStackTrace();
			try {
				connection.rollback();
			} catch(Exception e) {e.printStackTrace();}
		}
	}

	public static void setAutoCommit(boolean value) {
		try {
			connection.setAutoCommit(value);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	public static PreparedStatement prepareStatement(String query) throws Exception {
		return connection.prepareStatement(query);
	}

	public static PreparedStatement prepareStatement(String query, int option) throws Exception {
		return connection.prepareStatement(query, option);
	}

	public static DatabaseMetaData getMetaData() {
		try {
			return connection.getMetaData();
		} catch(Exception exception) {
			exception.printStackTrace();
		}

		return null;
	}

	public static void closeConnection() {
		try {
			connection.close();
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
}