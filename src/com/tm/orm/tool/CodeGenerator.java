package com.tm.orm.tool;

import java.io.*;
import java.util.*;

import com.tm.orm.util.*;

public class CodeGenerator {

	private static String tableName;
	private static String clazzName;
	private static Map<Attribute, String> columnToFieldMap;
	private static String path;

	private static final String FILE_EXTENSION = ".java";

	private CodeGenerator() {}

	public static void init(String table, String clazz, Map<Attribute, String> map, String pathToSave) {
		tableName = table;
		clazzName = clazz;
		columnToFieldMap = map;
		path = pathToSave;
	}

	public static void generate() {
		if(tableName == null || clazzName == null || columnToFieldMap == null || path == null) return;
		if(tableName.isEmpty() || clazzName.isEmpty() || columnToFieldMap.isEmpty() || path.isEmpty()) return;

		if(!path.endsWith(File.separator))
			path += File.separator;

		try {

			File file = new File(path + clazzName + FILE_EXTENSION);
			if(file.exists()) file.delete();
			file = new File(path + clazzName + FILE_EXTENSION);
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

			writeClass(randomAccessFile);
			writeFields(randomAccessFile);
			writeMethods(randomAccessFile);

			randomAccessFile.close();
		} catch(Exception exception) {
			exception.printStackTrace(); 
		}
	}

	private static void writeClass(RandomAccessFile raf) throws Exception {
		raf.writeBytes("import com.tm.orm.annotation.*;\r\n");
		addEmptyLines(raf, 1);
		raf.writeBytes("@MapTable(" + tableName + ")\r\n");
		raf.writeBytes("public class " + clazzName + " {\r\n");
		addEmptyLines(raf, 1);
	}

	private static void writeFields(RandomAccessFile raf) throws Exception {

		for(Map.Entry<Attribute, String> entry : columnToFieldMap.entrySet()) {
			Attribute attribute = entry.getKey();
			String fieldName = entry.getValue();

			addEmptyLines(raf, 1);
			raf.writeBytes("\t@MapColumn(" + attribute.getName() + ")\r\n");

			if(attribute.isPrimaryKey()) {
				raf.writeBytes("\t@PrimaryKey\r\n");
			}

			if(attribute.isForeignKey()) {
				raf.writeBytes("\t@ForeignKey\r\n");
			}

			if(attribute.isNullable()) {
				raf.writeBytes("\t@Nullable\r\n");
			}

			if(attribute.isAutoIncrement()) {
				raf.writeBytes("\t@AutoIncrement\r\n");
			}

			raf.writeBytes("\tprivate " + getDataType(attribute) + " " + fieldName + ";\r\n");

		}
	}

	private static void writeMethods(RandomAccessFile raf) throws Exception {

		StringBuilder stringBuilder = new StringBuilder();

		for(Map.Entry<Attribute, String> entry : columnToFieldMap.entrySet()) {
			Attribute attribute = entry.getKey();
			String identifier = entry.getValue();
			String t = new String(identifier);
			String dataType = getDataType(attribute);
			identifier = identifier.substring(0, 1).toUpperCase() + identifier.substring(1);
			stringBuilder.append( "\r\n\tpublic void set" + identifier + "(" + dataType + " " + t + ") {\r\n" );
          	stringBuilder.append( "\t\tthis." + t + " = " + t + ";\r\n\t}\r\n" );
          	stringBuilder.append( "\r\n\tpublic "+ dataType +" get" + identifier + "() {\r\n");
          	stringBuilder.append( "\t\treturn this." + t + ";\r\n\t}\r\n" );
		}

		raf.writeBytes(stringBuilder.toString() + "}\r\n");

	}

	private static void addEmptyLines(RandomAccessFile raf, int n) throws Exception {
		for(int i = 0; i < n; ++i)	raf.writeBytes("\r\n");
	}

	private static String getDataType(Attribute attribute) {
		
		String datatype = attribute.getDatatype();

		if(datatype.equalsIgnoreCase("INT"))
 			return "int";
 		 if(datatype.equalsIgnoreCase("CHAR"))
 		   	return "String";
 		 if(datatype.equalsIgnoreCase("VARCHAR"))
 		   	return "String";
 		 if(datatype.equalsIgnoreCase("DATE"))
 		    return "Date";
 		 if(datatype.equalsIgnoreCase("SMALLINT UNSIGNED"))
 		  	return "int";
 		 if(datatype.equalsIgnoreCase("TINYINT UNSIGNED"))
 		    return "int";
 		 if(datatype.equalsIgnoreCase("TIMESTAMP"))
 		   	return "";
 		 if(datatype.equalsIgnoreCase("FLOAT"))
 		   	return "float";
 		 if(datatype.equalsIgnoreCase("DECIMAL"))
 		   	return "double";
 		 if(datatype.equalsIgnoreCase("BIT"))
 		   	return "boolean";  

 		 return "";
	}

}