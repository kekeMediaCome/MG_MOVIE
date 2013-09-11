package com.mg_movie.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mg_movie.json.JsonParseCntvChannel;
import com.mg_movie.type.Type_Cntv_Channel;
import com.mg_movie.utils.HttpApiImpl;
import com.mg_movie.utils.JSONUtils;

public class CntvChannelDAO {
	public List<Type_Cntv_Channel> getTvChannel(String paramString){
		List<Type_Cntv_Channel> list = new ArrayList<Type_Cntv_Channel>();
		StringBuilder localBuilder = new StringBuilder();
		localBuilder.append("http://api.cbox.cntv.cn/api/").append("channel_list").append("?pid=").append("GC3ueQnxE8PrWVRkmaUvsJHZqNiXpgdz").append("&mediatype=").append(paramString);
		String url = localBuilder.toString();
		String result = new HttpApiImpl().doHttpGet(url);
		try {
			
			JSONObject resultJson = JSONUtils.getBodyAndCheckHeader(url, result);
			JsonParseCntvChannel localTvChannelParse = new JsonParseCntvChannel();
			JSONArray localJSONArray = resultJson.getJSONArray("channels");
			int size = localJSONArray.length();
			for (int i = 0; i < size; i++) {
				JSONObject jObject = localJSONArray.getJSONObject(i);
				list.add(localTvChannelParse.parse(jObject));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}

}
