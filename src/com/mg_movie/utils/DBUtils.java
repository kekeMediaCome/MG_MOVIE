package com.mg_movie.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mg_movie.AppLog;
import com.mg_movie.db.DBHelper;
import com.mg_movie.type.Type_v_qq_com;

public class DBUtils {

	private static DBHelper mDBHelper;

	public DBUtils(Context paramContext) {
		mDBHelper = new DBHelper(paramContext);
	}

	public void close() {
		if (mDBHelper != null) {
			mDBHelper.close();
		}
	}
	
	/**
	 * 插入一条tencent视频
	 */
	public void insertMovie(Type_v_qq_com video){
		SQLiteDatabase db = null;
		try {
			db = mDBHelper.getWritableDatabase();
			Type_v_qq_com type = video;
			ContentValues contentValues = new ContentValues();
			contentValues.put("video_name", type.getVideo_name());
			contentValues.put("video_urlstite", type.getVideo_urlstite());
			contentValues.put("video_url", type.getVideo_url());
			contentValues.put("video_img", type.getVideo_img());
			contentValues.put("video_source", type.getVideo_source());
			contentValues.put("video_mark", type.getVideo_mark());
			db.insertOrThrow("movie", null, contentValues);
			AppLog.e(type.getVideo_name()+"  :插入");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取所有的movie的視頻的个数
	 * @return
	 */
	public int getMovieCount(){
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT count(*) FROM movie", null);
		cursor.moveToNext();
		int coutn = cursor.getInt(0);
		cursor.close();
		db.close();
		return coutn;
	}
	/**
	 * 获取所有的视频
	 * @return
	 */
	public List<Type_v_qq_com> getAllMovies(){
		List<Type_v_qq_com> videos = new ArrayList<Type_v_qq_com>();
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT count(*) FROM movie", null);
		while (cursor.moveToNext()) {
			Type_v_qq_com video = new Type_v_qq_com();
			video.setVideo_id(cursor.getInt(0));
			video.setVideo_name(cursor.getString(1));
			video.setVideo_urlstite(cursor.getString(2));
			video.setVideo_url(cursor.getString(3));
			video.setVideo_img(cursor.getString(4));
			video.setVideo_source(cursor.getString(5));
			video.setVideo_mark(cursor.getString(6));
			videos.add(video);
		}
		return videos;
	}
}
