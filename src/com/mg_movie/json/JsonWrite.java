package com.mg_movie.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import com.mg_movie.type.Type_v_qq_com;

public class JsonWrite {
	
	ArrayList<Type_v_qq_com> qqVidoes;
	File saveFile;
	public JsonWrite(ArrayList<Type_v_qq_com> qqVidoes){
		this.qqVidoes = qqVidoes;
	}
	
	public void setFilePath(String path){
		saveFile = new File(path);
		try {
			if (saveFile.exists()) {
				saveFile.delete();
			}
			saveFile.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getJsonData(){
		String jsonData = null;
		try {
//			StringBuilder builder = new StringBuilder();
			ArrayList<String> qqVideosData = new ArrayList<String>();
			JSONArray array = new JSONArray();
			int size = qqVidoes.size();
			for (int i = 0; i < size; i++) {
				Type_v_qq_com qqVideo = qqVidoes.get(i);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("video_title", qqVideo.getVideo_name());
				jsonObject.put("video_url", qqVideo.getVideo_url());
				jsonObject.put("video_img", qqVideo.getVideo_img());
				jsonObject.put("video_mark", qqVideo.getVideo_mark());
				qqVideosData.add(jsonObject.toString());
				array.put(jsonObject);
			}
//			int len = array.length();
			jsonData = new JSONStringer().object().key("video").value(array).endObject().toString();
			writeData(jsonData);
		} catch (Exception e) {
		}
		return jsonData;
	}
	
	private void writeData(String jsonData) {  
		try {  
			BufferedReader reader=new BufferedReader(new StringReader(jsonData));  
			BufferedWriter writer=new BufferedWriter(new FileWriter(saveFile));  
			int len=0;
			char[] buffer=new char[1024];
			while((len=reader.read(buffer))!=-1){
				writer.write(buffer, 0, len);
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

}
