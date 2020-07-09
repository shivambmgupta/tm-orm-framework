package com.tm.orm.handler;

import java.io.*;
import java.util.*;
import java.sql.*;
import java.text.*;
import java.lang.reflect.*;
import com.tm.orm.session.*;
import com.tm.orm.util.*;
import com.tm.orm.annotation.*;
import com.tm.orm.exception.*;
import com.tm.orm.validator.*;

@Deprecated
public class TMORMapper {

	public static Map<String, Table> tables;
	private static TMORMapper instance = null;
	private static Validate validate;

	static {
		tables = new HashMap<>();
		TMSession database = null;
		try {
			database = new TMSession("C:/TMORM-Framework/conf/session.txt");
			tables = DBAnalyser.analyseDatabase(database);
			validate = Validate.getInstance(tables);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	private TMORMapper() {}

	public static TMORMapper getInstance() {
		if(instance == null)
			instance = new TMORMapper();

		return instance;
	}

	public void save(Object object) {
		try {
			Class<?> clazz = object.getClass();
			com.tm.orm.annotation.MapTable mTable = (com.tm.orm.annotation.MapTable) clazz.getAnnotation(com.tm.orm.annotation.MapTable.class);

			if(mTable == null) return; //User didn't mention table name.

			String tableName = mTable.value().trim();
			Table table = tables.get(tableName);
			if(table == null) return; //the table user mentioned does not exists.
			Field[] fields = clazz.getDeclaredFields();

			String mappedColumn;
			Field aiField = null;

			Map<String, Field> map = new TreeMap<>(); //because order is important for the implement.
			for(Field field : fields) {
				mappedColumn = getMappedColumn(field);
				if(mappedColumn != null) {
					if(field.getAnnotation(AutoGenerate.class) == null) {
						map.put(mappedColumn, field);
					}
					else {
						aiField = field;
					}
				}
			}

			
			try {
				validate.forInsertion(object, table, map);
			} catch(ORMException exception) {
				System.err.printf("\n\tFollowing is exception occured\n\n");
				System.out.println(exception.getMessage());
				return;
			} 

		    PreparedStatement preparedStatement = prepareInsertStatement(object, table, map);
		    preparedStatement.executeUpdate();

		    if(aiField != null) {
		    	ResultSet rs = preparedStatement.getGeneratedKeys();
		    	if(rs != null && rs.next()) {
		    		aiField.setAccessible(true);
		    		aiField.set(object, (int)(rs.getLong(1)));
		    	}
		    }

		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	public void remove(Object object) {
		try {
			Class<?> clazz = object.getClass();
			com.tm.orm.annotation.MapTable mTable = (com.tm.orm.annotation.MapTable) clazz.getAnnotation(com.tm.orm.annotation.MapTable.class);

			if(mTable == null) return; //User didn't mention table name.

			String tableName = mTable.value().trim();
			Table table = tables.get(tableName);

			if(table == null) return; //the table user mentioned does not exists.

			Field[] fields = clazz.getDeclaredFields();

			String query = "DELETE FROM " + table.getName() + " where ";

			String mappedCol;
			String getterMethod;
			Object temp;
			String fieldName;
			Method method;
			for(Field field : fields) {
				if(field.getAnnotation(PrimaryKey.class) == null) continue;
				mappedCol = getMappedColumn(field);
				field.setAccessible(true);
				if(field.getType() == boolean.class || field.getType() == Boolean.class)
					query += (mappedCol + " = " + field.get(object) + " and ");
				else if(field.getType() == java.util.Date.class) {
					String pattern = "yyyy-MM-dd";
					SimpleDateFormat formatter = new SimpleDateFormat(pattern);
					String t = formatter.format(field.get(object));
					query += (mappedCol + " = " + "\'" + t + "\' and ");
				}
				else 
					query += (mappedCol + " = " + "\'" + field.get(object) + "\' and ");					 
			}

			query = query.substring(0, query.length() - 4);

			PreparedStatement ps = ORMConnection.prepareStatement(query);
			ps.executeUpdate();

		} catch(Exception exception) {
			exception.printStackTrace();
		} 
	}

	public void commit() {
		ORMConnection.commit();
	}

	public void begin() {
		ORMConnection.setAutoCommit(false);
	}

	private String getMappedColumn(Field field) {
		com.tm.orm.annotation.MapColumn mCol = field.getAnnotation(com.tm.orm.annotation.MapColumn.class);
		return mCol == null ? null : mCol.value();
	}

	private PreparedStatement prepareInsertStatement(Object object, Table table, Map<String, Field> map) throws Exception {

		String query = "INSERT INTO " + table.getName() + "(";

		Set<String> keys = map.keySet();
		for(String key : keys) {
			query += (key + ",");
		}

		query = query.substring(0, query.length() - 1);
		query += ") values(";

		Collection<Field> fields = map.values();

		int modifiers;
		String getterMethod;
		Object temp;
		String fieldName;
		Method method;
		for(Field field : fields) {
			field.setAccessible(true);
			if(field.getType() == boolean.class || field.getType() == Boolean.class)
				query += (field.get(object) + ",");
			else if(field.getType() == java.util.Date.class) {
				String pattern = "yyyy-MM-dd";
				SimpleDateFormat formatter = new SimpleDateFormat(pattern);
				String t = formatter.format((java.util.Date)field.get(object));
				query += ("\'" + t + "\',");
			}
			else 
				query += ("\'" + field.get(object) + "\',");
		}

		query = query.substring(0, query.length() - 1);
		query += ")";

		return ORMConnection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

	}

	public Select select(Class<?> clazz) {
 		return new Select(clazz);
 	}

}