package com.mg_movie.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mg_movie.json.JsonParserCntvPlayBackInfo;
import com.mg_movie.type.Type_Cntv_PlayBackInfo;
import com.mg_movie.utils.HttpApiImpl;

public class CntvPlayBackDAO {
	public List<Type_Cntv_PlayBackInfo> getPlayBackList(String url){
		List<Type_Cntv_PlayBackInfo> list = new ArrayList<Type_Cntv_PlayBackInfo>();
		try {
			String result_json = new HttpApiImpl().doHttpGet(url);
			 JsonParserCntvPlayBackInfo backInfoParse = new JsonParserCntvPlayBackInfo();
			JSONObject result_Obj = new JSONObject(result_json);
			JSONArray chapters = result_Obj.getJSONObject("video").getJSONArray("chapters");
			int size = chapters.length();
			for (int i = 0; i < size; i++) {
				list.add(backInfoParse.parse(chapters.getJSONObject(i)));
			}
		} catch (Exception e) {
		}
		return list;
	}
}














