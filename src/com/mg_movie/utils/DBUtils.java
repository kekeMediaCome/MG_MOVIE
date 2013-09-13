package com.mg_movie.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mg_movie.AppLog;
import com.mg_movie.db.DBHelper;
import com.mg_movie.type.Type_Cntv_Remind;
import com.mg_movie.type.Type_YinYueTai;
import com.mg_movie.type.Type_cartoon;
import com.mg_movie.type.Type_live_togic_1;
import com.mg_movie.type.Type_tv;
import com.mg_movie.type.Type_v_qq_com;

public class DBUtils implements Serializable {

	private static final long serialVersionUID = 1L;
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
	public void insertMovie(Type_v_qq_com video) {
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
			AppLog.e(type.getVideo_name() + "  :插入");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入一个电视台
	 * 
	 * @param tv
	 */
	public void insertTV(Type_tv tv) {
		SQLiteDatabase db = null;
		try {
			db = mDBHelper.getWritableDatabase();
			Type_tv type = tv;
			ContentValues contentValues = new ContentValues();
			contentValues.put("tv_name", type.getTv_name());
			contentValues.put("tv_urlstite", type.getTv_urlstite());
			contentValues.put("tv_url", type.getTv_url());
			contentValues.put("tv_img", type.getTv_img());
			contentValues.put("tv_source", type.getTv_source());
			contentValues.put("tv_mark_sd", type.getTv_mark_sd());
			contentValues.put("tv_mark_txt", type.getTv_mark_txt());
			db.insertOrThrow("tv", null, contentValues);
			AppLog.e(type.getTv_name() + "  :插入");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入一个卡通节目
	 * 
	 * @param ct
	 */
	public void insertCT(Type_cartoon cartoon) {
		SQLiteDatabase db = null;
		try {
			db = mDBHelper.getWritableDatabase();
			Type_cartoon ct = cartoon;
			ContentValues contentValues = new ContentValues();
			contentValues.put("ct_name", ct.getCt_name());
			contentValues.put("ct_urlstite", ct.getCt_urlstite());
			contentValues.put("ct_url", ct.getCt_url());
			contentValues.put("ct_img", ct.getCt_img());
			contentValues.put("ct_source", ct.getCt_source());
			contentValues.put("ct_mark_sd", ct.getCt_mark_sd());
			contentValues.put("ct_mark_txt", ct.getCt_mark_txt());
			db.insertOrThrow("ct", null, contentValues);
			AppLog.e(ct.getCt_name() + "  :插入");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入一条togic的直播
	 * 
	 * @param live
	 */
	public void insertLive(Type_live_togic_1 live) {
		SQLiteDatabase db = null;
		try {
			db = mDBHelper.getWritableDatabase();
			Type_live_togic_1 live_togic = live;
			ContentValues contentValues = new ContentValues();
			contentValues.put("_id", live_togic.get_id());
			contentValues.put("category", live_togic.getCategory());
			contentValues.put("icon", live_togic.getIcon());
			contentValues.put("province", live_togic.getProvince());
			contentValues.put("resolution", live_togic.getResolution());
			contentValues.put("title", live_togic.getTitle());
			contentValues.put("urls", live_togic.getUrls());
			contentValues.put("num", live.getNum());
			db.insertOrThrow("live_togic_1", null, contentValues);
			AppLog.e(live_togic.getTitle() + "  :插入");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 插入一首音乐
	 * @param music
	 */
	public void insertMusci(Type_YinYueTai music){
		SQLiteDatabase db = null;
		try {
			db = mDBHelper.getWritableDatabase();
			Type_YinYueTai musicTai = music;
			ContentValues contentValues = new ContentValues();
			contentValues.put("mc_title", musicTai.getMusic_title());
			contentValues.put("mc_url", musicTai.getMusic_url());
			contentValues.put("mc_urlstite", musicTai.getMusic_urlstite());
			contentValues.put("mc_img", musicTai.getMusic_img());
			contentValues.put("mc_shdIco", musicTai.getMusic_shdIco());
			contentValues.put("mc_time", musicTai.getMusic_time());
			contentValues.put("mc_player", musicTai.getMusic_player());
			db.insertOrThrow("music", null, contentValues);
			AppLog.e(musicTai.getMusic_title() + "  :插入");
		} catch (Exception e) {
		}
	}

	/**
	 * 获取所有的movie的視頻的个数
	 * 
	 * @return
	 */
	public int getMovieCount() {
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT count(*) FROM movie", null);
		cursor.moveToNext();
		int coutn = cursor.getInt(0);
		cursor.close();
		db.close();
		return coutn;
	}

	/**
	 * 获取所有的tv的电视台节目的个数
	 * 
	 * @return
	 */
	public int getTVCount() {
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT count(*) FROM tv", null);
		cursor.moveToNext();
		int coutn = cursor.getInt(0);
		cursor.close();
		db.close();
		return coutn;
	}

	/**
	 * 获取所有的ct的电视台节目的个数
	 * 
	 * @return
	 */
	public int getCTCount() {
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT count(*) FROM ct", null);
		cursor.moveToNext();
		int coutn = cursor.getInt(0);
		cursor.close();
		db.close();
		return coutn;
	}
	/**
	 * 获取所有的音乐的个数
	 * 
	 * @return
	 */
	public int getMusicCount(){
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT count(*) FROM music", null);
		cursor.moveToNext();
		int coutn = cursor.getInt(0);
		cursor.close();
		db.close();
		return coutn;
	}

	/**
	 * 获取togic所有的直播节目
	 * 
	 * @return
	 */
	public int getLiveTogicCount() {
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT count(*) FROM live_togic_1", null);
		cursor.moveToNext();
		int coutn = cursor.getInt(0);
		cursor.close();
		db.close();
		return coutn;
	}

	/**
	 * 获取所有的视频
	 * 
	 * @return
	 */
	public List<Type_v_qq_com> getAllMovies() {
		List<Type_v_qq_com> videos = new ArrayList<Type_v_qq_com>();
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM movie", null);
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

	/**
	 * 获取指定范围内的电影
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Type_v_qq_com> getRoundMovies(int start, int end) {
		List<Type_v_qq_com> videos = new ArrayList<Type_v_qq_com>();
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		String[] pars = { String.valueOf(start), String.valueOf(end) };
		Cursor cursor = db.rawQuery(
				"SELECT * FROM movie where video_id >= ? and video_id < ?",
				pars);
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

	/**
	 * 获取所有的电视台的节目
	 * 
	 * @return
	 */
	public List<Type_tv> getAllTVs() {
		List<Type_tv> tvs = new ArrayList<Type_tv>();
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM tv", null);
		while (cursor.moveToNext()) {
			Type_tv tv = new Type_tv();
			tv.setTv_id(cursor.getInt(0));
			tv.setTv_name(cursor.getString(1));
			tv.setTv_urlstite(cursor.getString(2));
			tv.setTv_url(cursor.getString(3));
			tv.setTv_img(cursor.getString(4));
			tv.setTv_source(cursor.getString(5));
			tv.setTv_mark_sd(cursor.getString(6));
			tv.setTv_mark_txt(cursor.getString(7));
			tvs.add(tv);
		}
		return tvs;
	}

	/**
	 * 获取所有的卡通节目
	 * 
	 * @return
	 */
	public List<Type_cartoon> getAllCTs() {
		List<Type_cartoon> cts = new ArrayList<Type_cartoon>();
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM ct", null);
		while (cursor.moveToNext()) {
			Type_cartoon ct = new Type_cartoon();
			ct.setCt_id(cursor.getInt(0));
			ct.setCt_name(cursor.getString(1));
			ct.setCt_urlstite(cursor.getString(2));
			ct.setCt_url(cursor.getString(3));
			ct.setCt_img(cursor.getString(4));
			ct.setCt_source(cursor.getString(5));
			ct.setCt_mark_sd(cursor.getString(6));
			ct.setCt_mark_txt(cursor.getString(7));
			cts.add(ct);
		}
		return cts;
	}

	/**
	 * 获取所有的togic的视频
	 * 
	 * @return
	 */
	public List<Type_live_togic_1> getAllLiveTogics() {
		List<Type_live_togic_1> lives = new ArrayList<Type_live_togic_1>();
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM live_togic_1", null);
		while (cursor.moveToNext()) {
			Type_live_togic_1 live = new Type_live_togic_1();
			live.set_id(cursor.getString(0));
			live.setCategory(cursor.getString(1));
			live.setIcon(cursor.getString(2));
			live.setProvince(cursor.getString(3));
			live.setResolution(cursor.getString(4));
			live.setTitle(cursor.getString(5));
			live.setUrls(cursor.getString(6));
			live.setNum(cursor.getInt(7));
			lives.add(live);
		}
		return lives;
	}

	public ArrayList<Type_live_togic_1> getRoundLives(String[] parms) {
		ArrayList<Type_live_togic_1> lives = new ArrayList<Type_live_togic_1>();
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		String sql = "SELECT * FROM live_togic_1 where " + parms[0]
				+ " like '%" + parms[1] + "%'";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			Type_live_togic_1 live = new Type_live_togic_1();
			live.set_id(cursor.getString(0));
			live.setCategory(cursor.getString(1));
			live.setIcon(cursor.getString(2));
			live.setProvince(cursor.getString(3));
			live.setResolution(cursor.getString(4));
			live.setTitle(cursor.getString(5));
			live.setUrls(cursor.getString(6));
			live.setNum(cursor.getInt(7));
			lives.add(live);
		}
		return lives;
	}
	
	/**
	 * 得到所有的音樂頻道
	 * @return
	 */
	public ArrayList<Type_YinYueTai> getAllMusics(){
		ArrayList<Type_YinYueTai> lists = new ArrayList<Type_YinYueTai>();
		try {
			SQLiteDatabase db = mDBHelper.getReadableDatabase();
			Cursor cursor = db.rawQuery("SELECT * FROM music", null);
			while (cursor.moveToNext()) {
				Type_YinYueTai music = new Type_YinYueTai();
				music.setMusic_id(cursor.getInt(0));
				music.setMusic_title(cursor.getString(1));
				music.setMusic_url(cursor.getString(2));
				music.setMusic_urlstite(cursor.getString(3));
				music.setMusic_img(cursor.getString(4));
				music.setMusic_shdIco(cursor.getString(5));
				music.setMusic_time(cursor.getString(6));
				music.setMusic_player(cursor.getString(7));
				lists.add(music);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}

	public void deleteAllCollects() {
		SQLiteDatabase localSQLiteDatabase = mDBHelper.getWritableDatabase();
		localSQLiteDatabase.delete("collect", "", null);
		localSQLiteDatabase.close();
		close();
	}

	public void deleteAllHistory() {
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		db.delete("history", "", null);
		db.close();
		close();
	}

	public void deleteAllRemind() {
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		db.delete("remind", "", null);
		db.close();
		close();
	}

	public void deleteOneCollect(int paramInt) {
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		try {
			String sql = "delete from collect where _id = " + paramInt;
			db.execSQL(sql);
		} catch (Exception e) {
		} finally {
			db.close();
		}
	}

	public void deleteOneHistory(int paramInt) {
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		try {
			String sql = "delete from history where _id = " + paramInt;
			db.execSQL(sql);
		} catch (Exception e) {
		} finally {
			db.close();
		}
	}

	public void deleteOneRemind(String paramInt) {
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		try {
			String sql = "delete from remind where _id = " + paramInt;
			db.execSQL(sql);
		} catch (Exception e) {
		} finally {
			db.close();
		}
	}

	public void deleteSomeCollects(List<Integer> paramList) {
		// if (paramList == null || paramList.size() == 0) {
		// return;
		// }
		// SQLiteDatabase db = mDBHelper.getWritableDatabase();
		// try {
		//
		// } catch (Exception e) {
		// }
	}

	public int getAllRemindsCount() {
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT count(*) FROM remind", null);
		cursor.moveToNext();
		int i = cursor.getInt(0);
		cursor.close();
		db.close();
		return i;
	}

	public void insertRemind(Type_Cntv_Remind paramRemind) {
		SQLiteDatabase db = null;
		try {
			if (!StringUtils.isBlank(paramRemind.getTitle())) {
				db = mDBHelper.getWritableDatabase();
				ContentValues contentValues = new ContentValues();
				contentValues.put("title", paramRemind.getTitle());
				contentValues
						.put("channel_name", paramRemind.getChannel_name());
				contentValues.put("remind_time", paramRemind.getRemind_time());
				contentValues.put("is_new",
						Integer.valueOf(paramRemind.getIs_new()));
				contentValues.put("pic", paramRemind.getPic());
				db.insertOrThrow("remind", null, contentValues);
			}
		} catch (Exception e) {
		} finally {
			db.close();
		}
	}

	public int getRemindStatus(String title, String remind_time) {
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		String[] arrayOfString = new String[2];
		arrayOfString[0] = title;
		arrayOfString[1] = remind_time;
		Cursor cursor = db.rawQuery(
				"SELECT count(*) FROM remind where title=? and remind_time=?",
				arrayOfString);
		cursor.moveToNext();
		int i = cursor.getInt(0);
		cursor.close();
		db.close();
		return i;
	}
}
