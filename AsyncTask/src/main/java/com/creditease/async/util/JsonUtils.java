package com.creditease.async.util;

import java.util.Map;

import com.google.gson.Gson;

public class JsonUtils {
	public static String toJsonString(Object object){
		Gson gson = new Gson();
		return gson.toJson(object);
	}
	
	public static String toJsonString(Object object,Class<?> classObj){
		Gson gson = new Gson();
		return gson.toJson(object,classObj);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMapObject(String values){
		Gson gson = new Gson();
		return gson.fromJson(values, Map.class);
	}
	
	
	public static void main(String[] args) {
		System.out.println();
	}
}
