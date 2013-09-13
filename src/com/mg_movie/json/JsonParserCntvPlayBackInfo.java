package com.mg_movie.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.mg_movie.type.Type_Cntv_PlayBackInfo;

public class JsonParserCntvPlayBackInfo {
	public Type_Cntv_PlayBackInfo parse(JSONObject paramJsonObject) throws JSONException {
		Type_Cntv_PlayBackInfo backInfo = new Type_Cntv_PlayBackInfo();
		if (paramJsonObject.has("url")) {
			String url = paramJsonObject.getString("url");
			backInfo.setUrl(url);
		}
		if (paramJsonObject.has("duration")) {
			String duration = paramJsonObject.getString("duration");
			backInfo.setDuration(duration);
		}
		return backInfo;
	}
}
