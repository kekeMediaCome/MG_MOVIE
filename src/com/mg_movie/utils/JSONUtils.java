package com.mg_movie.utils;

import org.json.JSONObject;

import com.mg_movie.MG_App;

public class JSONUtils {

	public static JSONObject getBodyAndCheckHeader(String url,
			String returnResutl) {
		JSONObject result = null;
		try {
			JSONObject main = new JSONObject(returnResutl);
			JSONObject status = main.getJSONObject("status");
			String date = status.getString("now");
			MG_App.setNow(DateUtils.str2Date(date));
			result = main.getJSONObject("result");
		} catch (Exception e) {
		}
		return result;
	}

}
