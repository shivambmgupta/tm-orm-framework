package com.tm.orm.util;

import java.util.*;
import java.sql.*;
import com.tm.orm.session.*;
import com.tm.orm.exception.*;

public class DBAnalyser {

	private DBAnalyser() {}

	public static Map<String, Table> analyseDatabase(TMSession database) throws ORMException {

		Map<String, Table> tables = new HashMap<>();

		try {

			Class.forName(database.getDriverString());
			Connection conn = DriverManager.getConnection(database.getConnectionString(), database.getUsername(), database.getPassword());

			ORMConnection.setConnection(conn);

			DatabaseMetaData databaseMetaData = ORMConnection.getMetaData();

			ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});

			Table table;
			Attribute attribute;
			String tempKey;

			while(resultSet.next()) {	

				table = new Table();
				table.setName(resultSet.getString("TABLE_NAME"));

    			ResultSet columns = databaseMetaData.getColumns(null,null, table.getName(), null);

				while(columns.next()) {

		 			attribute = new Attribute();
		 			attribute.setName(columns.getString("COLUMN_NAME"));
		 			attribute.setDatatype(columns.getString("TYPE_NAME"));
		 			attribute.setSize(columns.getString("COLUMN_SIZE"));
		 			attribute.setDecimalDigits(columns.getString("DECIMAL_DIGITS"));
		 			boolean value = columns.getString("IS_NULLABLE").equalsIgnoreCase("No") ? false : true;
		 			attribute.setNullable(value);
		 			if(columns.getString("IS_AUTOINCREMENT").equalsIgnoreCase("Yes")) {
		 				attribute.setAutoIncrement(true);
		 				table.setAutoIncrementPrimaryKey(true); //If it's auto increment then it's a primary key.
		 			}

                    
		 			table.addAttribute(attribute);

				}

				ResultSet rsPk = databaseMetaData.getPrimaryKeys(null, null, table.getName());

				while(rsPk.next()) {
					tempKey = rsPk.getString("COLUMN_NAME");
		 			table.addPrimaryKey(tempKey);

		 			attribute = table.getAttribute(tempKey);
		 			attribute.setPrimaryKey(true);
				}

				ResultSet rsFk = databaseMetaData.getImportedKeys(null, null, table.getName());

				while(rsFk.next()) {
					
					tempKey = rsFk.getString("FKCOLUMN_NAME");
					String fktName = rsFk.getString("PKTABLE_NAME");
					String pkCol = rsFk.getString("PKCOLUMN_NAME");
					table.addForeignKey(new com.tm.orm.util.ForeignKey(tempKey, fktName, pkCol));

					attribute = table.getAttribute(tempKey);
		 			attribute.setForeignKey(true);
		 			attribute.setForeignKeyRefTable(fktName);
		 			attribute.setForeignKeyRefCol(pkCol);
				}

				ResultSet uk = databaseMetaData.getIndexInfo(null, null, table.getName(), true, true);
				
				while(uk.next()) {
					tempKey = uk.getString("COLUMN_NAME");
					table.addUniqueKey(tempKey);

					attribute = table.getAttribute(tempKey);
		 			attribute.setUniqueKey(true);		
				}

				tables.put(table.getName(), table);

			}

			return tables;

		} catch(Exception exception) {
			throw new ORMException(exception.getMessage());
		} 

	}

}