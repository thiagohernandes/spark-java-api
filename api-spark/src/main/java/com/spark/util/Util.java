package com.spark.util;

import com.google.gson.Gson;

import spark.Request;

public class Util<T> {
	
	Gson gson = new Gson();

	@SuppressWarnings("hiding")
	public <T> T objFromJson(Request req, Class<T> clazz) {
        return gson.fromJson(req.body(), clazz);
	}
	
	public String objToJson(Object obj) {
		return gson.toJson(obj);
	}
}
