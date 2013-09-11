package com.mg_movie.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.mg_movie.type.Type_Cntv_Programs;

public class ProgramsParse {
	String realTime;

	public ProgramsParse(String paramString) {
		this.realTime = paramString;
	}

	public Type_Cntv_Programs parse(JSONObject paramJSONObject) throws JSONException {
		Type_Cntv_Programs localPrograms = new Type_Cntv_Programs();
		if (paramJSONObject.has("title")) {
			String str1 = paramJSONObject.getString("title");
			localPrograms.setTitle(str1);
		}
		if (paramJSONObject.has("showtime")) {
			String str2 = paramJSONObject.getString("showtime");
			localPrograms.setShowtime(str2);
		}
		if (paramJSONObject.has("playback")) {
			String str3 = paramJSONObject.getString("playback");
			localPrograms.setPlayback(str3);
		}
		String str4 = this.realTime;
		localPrograms.setRealTime(str4);
		return localPrograms;
	}
}