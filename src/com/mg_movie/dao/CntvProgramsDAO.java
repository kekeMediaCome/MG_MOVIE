package com.mg_movie.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mg_movie.KSetting;
import com.mg_movie.json.JsonParserCntvPrograms;
import com.mg_movie.type.Type_Cntv_Programs;
import com.mg_movie.utils.HttpApiImpl;
import com.mg_movie.utils.JSONUtils;

public class CntvProgramsDAO{
	
	public List<Type_Cntv_Programs> getPrograms(String channelid, String currentdate){
		List<Type_Cntv_Programs> list = new ArrayList<Type_Cntv_Programs>();
		StringBuffer buffer = new StringBuffer();
		buffer.append(KSetting.cntv_url).append("program_list").append("?pid=").append("GC3ueQnxE8PrWVRkmaUvsJHZqNiXpgdz").append("&version=");
		buffer.append(KSetting.CNTV_VERSION).append("&channelid=").append(channelid).append("&currentdate=").append(currentdate);
		String url = buffer.toString();
		try {
			String json_result = new HttpApiImpl().doHttpGet(url);
			JSONObject result = JSONUtils.getBodyAndCheckHeader(url, json_result);
			JSONArray pragramsArray = result.getJSONArray("programs");
			JsonParserCntvPrograms programsParse = new JsonParserCntvPrograms(currentdate);
			int size = pragramsArray.length();
			for (int i = 0; i < size; i++) {
				list.add(programsParse.parse(pragramsArray.getJSONObject(i)));
			}
		} catch (Exception e) {
		}
		return list;
	}
}













