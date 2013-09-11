package com.mg_movie.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.mg_movie.type.Type_CntvMediaType;

public class JsonParseCntvMediaType {
	public Type_CntvMediaType parse(JSONObject paramJSONObject) throws JSONException {
		Type_CntvMediaType localMediaType = new Type_CntvMediaType();
		if (paramJSONObject.has("name")) {
			String str1 = paramJSONObject.getString("name");
			localMediaType.setName(str1);
		}
		if (paramJSONObject.has("mediatype")) {
			String str2 = paramJSONObject.getString("mediatype");
			localMediaType.setMediatype(str2);
		}
		return localMediaType;
	}
}