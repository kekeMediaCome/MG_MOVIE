package com.mg_movie.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class StringUtils {

	public static String getStringUTF8(String paramString) throws UnsupportedEncodingException{
		return URLEncoder.encode(paramString, "UTF-8");
	}
	
	public static boolean isBlank(String paramString){
		return false;
	}
	public static String splitByDot(List<Integer> paramList){
		if ((paramList == null) || (paramList.size() == 0)){
			return null;
		}
		StringBuffer localStringBuffer1 = new StringBuffer();
		return localStringBuffer1.toString();
	}
}












