package com.mg_movie.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.mg_movie.type.Type_Cntv_Channel;

public class JsonParseCntvChannel {

	public Type_Cntv_Channel parse(JSONObject paramJSONObject) throws JSONException {
		Type_Cntv_Channel localTvChannel = new Type_Cntv_Channel();
		if (paramJSONObject.has("viewback")) {
			int i = paramJSONObject.getInt("viewback");
			localTvChannel.setViewback(i);
		}
		if (paramJSONObject.has("channelname")) {
			String str1 = paramJSONObject.getString("channelname");
			localTvChannel.setChannelname(str1);
		}
		if (paramJSONObject.has("channellogo")) {
			String str2 = paramJSONObject.getString("channellogo");
			localTvChannel.setChannellogo(str2);
		}
		if (paramJSONObject.has("channelurl")) {
			String str3 = paramJSONObject.getString("channelurl");
			localTvChannel.setChannelurl(str3);
		}
		if (paramJSONObject.has("channelid")) {
			String str4 = paramJSONObject.getString("channelid");
			localTvChannel.setChannelid(str4);
		}
		if (paramJSONObject.has("program_name")) {
			String str5 = paramJSONObject.getString("program_name");
			localTvChannel.setProgram_name(str5);
		}
		System.out.println(localTvChannel.toString());
		return localTvChannel;
	}

}
