package com.mg_movie.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.mg_movie.type.PlayBackInfo;

public class PlayBackInfoParse {
	public PlayBackInfo parse(JSONObject paramJsonObject) throws JSONException {
		PlayBackInfo backInfo = new PlayBackInfo();
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
