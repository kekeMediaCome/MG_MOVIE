package com.mg_movie.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mg_movie.MG_App;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class MG_Utils {

	@SuppressLint("SimpleDateFormat")
	static SimpleDateFormat mSdfDate = new SimpleDateFormat("yyyyMMdd");

	public static NetworkInfo getAvailableNetWorkInfo(Context paramContext) {
		NetworkInfo networkInfo = ((ConnectivityManager) paramContext
				.getSystemService(Context.CONNECTIVITY_SERVICE))
				.getActiveNetworkInfo();
		return networkInfo;
	}

	public static String getCurDate() {
		Date localDate = (Date) Calendar.getInstance().getTime();
		return mSdfDate.format(localDate);
	}

	/**
	 * 获取今天的周几
	 * 
	 * @return
	 */
	public static int getTodayWeek() {
		Calendar calendar = Calendar.getInstance();
		Date date = MG_App.getNow();
		if (date != null) {
			calendar.setTime(date);
		}
		int i = calendar.get(Calendar.DAY_OF_WEEK);
		int j = 1;
		if (i == 1) {
			j = 7;
		} else {
			j = i - 1;
		}
		return j;
	}

	/**
	 * 得到当前日期内的一周时间
	 * 
	 * @param paramInt
	 * @return
	 */
	public static List<String> getCurWeekOfDate(int paramInt) {
		List<String> arrayList = new ArrayList<String>();
		Date date = MG_App.getNow();
		String str1 = null;
		if (date != null) {
			str1 = mSdfDate.format(date);
		} else {
			mSdfDate.format(Calendar.getInstance().getTime());
		}
		Date date2;
		try {
			int i = 1;
			while (i <= 7) {
				Calendar calendar = Calendar.getInstance();
				date2 = mSdfDate.parse(str1);
				calendar.setTime(date2);
				int diff = i - paramInt;
				calendar.add(Calendar.DATE, diff);
				date2 = calendar.getTime();
				String str2 = mSdfDate.format(date2);
				arrayList.add(str2);
				i++;
			}

		} catch (Exception exception) {
		}
		return arrayList;
	}

	public static void showToast(Context paramContext, int paramInt) {
		Toast.makeText(paramContext, paramInt, Toast.LENGTH_SHORT).show();
	}

	public static void showToast(Context paramContext, String paramString) {
		Toast.makeText(paramContext, paramString, Toast.LENGTH_SHORT).show();
	}
}