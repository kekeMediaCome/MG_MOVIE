package com.mg_movie.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
	static SimpleDateFormat sdf4 = new SimpleDateFormat("MM月dd日时mm分");
	public static SimpleDateFormat sdf5 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdf7 = new SimpleDateFormat(
			"yyyyMMdd HH:mm:ss");

	public static String date2sdf4(String paramString) {
		Date localDate = str2Date(paramString);
		return sdf4.format(localDate);
	}
	
	public static String getYear(String paramString){
		String year = null;
		try {
			Date date = sdf.parse(paramString);
			year = sdf2.format(date);
		} catch (Exception e) {
		}
		return year;
	}
	
	public static String sdf2Str(Date paramDate){
		return sdf.format(paramDate);
	}
	
	public static Date str2Date(String paramString){
		Date date = null;
		try {
			date = sdf.parse(paramString);
		} catch (Exception e) {
		}
		return date;
	}
	
}
