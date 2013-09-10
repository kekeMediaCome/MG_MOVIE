package com.mg_movie.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mg_movie.KSetting;
import com.mg_movie.type.Type_live_togic_1;
import com.mg_movie.utils.HttpApiImpl;

public class JsonParseTogic_1 {

	public List<Type_live_togic_1> getTvChannel() {
		List<Type_live_togic_1> list = new ArrayList<Type_live_togic_1>();
		String json = new HttpApiImpl().doHttpGet(KSetting.live_togic_1_url);
		try {
			JSONArray result = new JSONArray(json);
			int count = result.length();
			TogicChannelParse parse = new TogicChannelParse();
			for (int i = 0; i < count; i++) {
				JSONObject object = result.getJSONObject(i);
				list.add(parse.parse(object));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}

	public class TogicChannelParse {
		public Type_live_togic_1 parse(JSONObject JObject) throws JSONException {
			Type_live_togic_1 type = new Type_live_togic_1();
			if (JObject.has("_id")) {
				type.set_id(JObject.getString("_id"));
			}
			if (JObject.has("category")) {
				JSONArray category = JObject.getJSONArray("category");
				int count = category.length();
				StringBuffer buffer = new StringBuffer();
				for (int i = 0; i < count; i++) {
					if (i == (count - 1)) {
						buffer.append((String) category.get(i));
					} else {
						buffer.append((String) category.get(i)).append(",");
					}
				}
				type.setCategory(buffer.toString());
			}
			if (JObject.has("icon")) {
				String icon = JObject.getString("icon");
				if (icon.startsWith("/upload")) {
					icon = "http://tv.togic.com" + icon;
				}
				type.setIcon(icon);
			}
			if (JObject.has("province")) {
				type.setProvince(JObject.getString("province"));
			}
			if (JObject.has("resolution")) {
				type.setResolution(JObject.getString("resolution"));
			}
			if (JObject.has("title")) {
				type.setTitle(JObject.getString("title"));
			}
			if (JObject.has("urls")) {
				JSONArray urls = JObject.getJSONArray("urls");
				int count = urls.length();
				String url = "";
				for (int i = 0; i < count; i++) {
					if (i == (count - 1)) {
						url += (String) urls.get(i);
					} else {
						url += (String) urls.get(i) + ",";
					}
				}
				type.setUrls(url);
			}
			if (JObject.has("num")) {
				type.setNum(JObject.getInt("num"));
			}
			return type;
		}
	}
}
