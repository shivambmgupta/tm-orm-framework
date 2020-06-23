package com.tm.orm.util;

import java.io.*;
import java.util.*;
import com.google.gson.*;

public class TMDataHouse {

	private static Map<String, String> headers = new TreeMap<>();

	private TMDataHouse(){}

	public static void configureHeaders() {

		Gson gson = new Gson();

		File file = new File("c:/TMORM-Framework/conf/header.conf");

		if(!file.exists()) return;

		StringBuffer sb = new StringBuffer();

		try {
			RandomAccessFile raf = new RandomAccessFile(file, "r");
			while(raf.getFilePointer() < raf.length())
				sb.append(raf.readLine());
		} catch(Exception exception) {
			exception.printStackTrace();
			return;
		}

		headers = (Map<String, String>) gson.fromJson(sb.toString(), java.util.Map.class);

	}

	public static void addHeader(String dbms, String header) {
    	headers.put(dbms, header);
		updateHeaders();
	}

	private static void updateHeaders() {

		Gson gson = new Gson();

		String toWrite = gson.toJson(headers);

		File file = new File("c:/TMORM-Framework/conf/header.conf");

		if(file.exists()) file.delete();

		file = new File("c:/TMORM-Framework/conf/header.conf");

		try{
			FileWriter writer = new FileWriter(file);
			writer.write(toWrite);
			writer.close();
		} catch(Exception exception) {
			exception.printStackTrace();
			return;
		}

	}

	public static String getHeader(String key) {

		return headers.get(key);
		
	}

}