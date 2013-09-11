package com.mg_movie.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mg_movie.KSetting;
import com.mg_movie.json.JsonParseCntvMediaType;
import com.mg_movie.type.Type_CntvMediaType;
import com.mg_movie.utils.HttpApiImpl;
import com.mg_movie.utils.JSONUtils;

public class CntvMediaTypeDAO {

	public List<Type_CntvMediaType> getMediaTypes(String uri,String type) throws JSONException {
		List<Type_CntvMediaType> list = new ArrayList<Type_CntvMediaType>();
		StringBuffer buffer = new StringBuffer();
		buffer.append(uri).append(KSetting.IFACE_GET_MEDIA_TYPE)
				.append(type).append(KSetting.PID)
				.append("&version=");
		buffer.append(KSetting.CNTV_VERSION);
		String url = buffer.toString();
		String json = new HttpApiImpl().doHttpGet(url);
		JSONObject result = JSONUtils.getBodyAndCheckHeader(url, json);
		JSONArray mediatypesArray = result.getJSONArray("mediatypes");
		JsonParseCntvMediaType mediaTypeParse = new JsonParseCntvMediaType();
		int size = mediatypesArray.length();
		for (int i = 0; i < size; i++) {
			list.add(mediaTypeParse.parse(mediatypesArray.getJSONObject(i)));
		}
		return list;
	}

}
