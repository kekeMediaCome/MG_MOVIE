package com.mg_movie.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DBHelper extends SQLiteOpenHelper {

	private static final String NAME = "zs.sqlite";
	private static final String DB_PATH = Environment.getExternalStorageDirectory() + "/";
	private static final int DB_VERSION = 1;
	public static final String TABLE_NAME_CHANNLE_MOVIE = "movie";
	public static final String MOVIE_SQL = "CREATE TABLE movie ('video_id' INTEGER PRIMARY KEY  AUTOINCREMENT, 'video_name' VARCHAR, 'video_urlstite' VARCHAR, 'video_url' VARCHAR, 'video_img' VARCHAR, 'video_source' VARCHAR, 'video_mark' VARCHAR)";
	public static final String TV_SQL = "CREATE TABLE tv ('tv_id' INTEGER PRIMARY KEY  AUTOINCREMENT, 'tv_name' VARCHAR, 'tv_urlstite' VARCHAR, 'tv_url' VARCHAR, 'tv_img' VARCHAR, 'tv_source' VARCHAR, 'tv_mark_sd' VARCHAR, 'tv_mark_txt' VARCHAR)";
	public static final String CT_SQL = "CREATE TABLE ct ('ct_id' INTEGER PRIMARY KEY  AUTOINCREMENT, 'ct_name' VARCHAR, 'ct_urlstite' VARCHAR, 'ct_url' VARCHAR, 'ct_img' VARCHAR, 'ct_source' VARCHAR, 'ct_mark_sd' VARCHAR, 'ct_mark_txt' VARCHAR)";
	public static final String Live_SQL = "CREATE TABLE live_togic_1 ('_id' VARCHAR, 'category' VARCHAR, 'icon' VARCHAR, 'province' VARCHAR, 'resolution' VARCHAR, 'title' VARCHAR, 'urls' VARCHAR, 'num' INTEGER)";
	public static final String Music_SQL = "CREATE TABLE music ('mc_id' INTEGER PRIMARY KEY  AUTOINCREMENT, 'mc_title' VARCHAR, 'mc_url' VARCHAR, 'mc_urlstite' VARCHAR, 'mc_img' VARCHAR, 'mc_shdIco' VARCHAR, 'mc_time' VARCHAR, 'mc_player' VARCHAR)";
	
	
	public static final String REMIND_IDX = "CREATE UNIQUE INDEX 'remind_idx' ON 'remind' ('title' , 'remind_time' );";
	public static final String REMIND_SQL = "CREATE  TABLE 'remind' ('_id' INTEGER PRIMARY KEY  AUTOINCREMENT , 'title' VARCHAR, 'channel_name' VARCHAR, 'remind_time' datetime default (datetime('now', 'localtime')) ,'is_new' INTEGER DEFAULT 0, 'pic' VARCHAR)";
	public static final String SRC_URL = "srcUrl";
	public static final String SUBSCIBE_IDX = "CREATE UNIQUE INDEX 'subscibe_idx' ON 'subscibe' ('content_id' , 'content_type_id' );";
	public static final String SUBSCIBE_SQL = "CREATE  TABLE 'subscibe' ('_id' INTEGER PRIMARY KEY  AUTOINCREMENT , 'title' VARCHAR, 'part' VARCHAR, 'total' VARCHAR, 'content_id' VARCHAR, 'content_type_id' VARCHAR, 'is_new' INTEGER DEFAULT 0, 'pic' VARCHAR)";
	public static final String TABLE_NAME_COLLECT = "collect";
	public static final String TABLE_NAME_HISTORY = "history";
	public static final String TABLE_NAME_REMIND = "remind";
	public static final String TABLE_NAME_SUBSCIBE = "subscibe";
	public static final String VIDEO_ID = "videoId";
	public static final String VIDEO_SIZE = "videoSize";
	public static final String VIDEO_TITLE = "videoTitle";
	public static final String WHICH_SECTION = "whichSection";

	public DBHelper(Context context) {
		super(context, DB_PATH + NAME, null, DB_VERSION);
	}

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(MOVIE_SQL);
		db.execSQL(TV_SQL);
		db.execSQL(CT_SQL);
		db.execSQL(Live_SQL);
		db.execSQL(Music_SQL);
		
		
		db.execSQL("CREATE  TABLE 'history' ('_id' INTEGER PRIMARY KEY  AUTOINCREMENT , 'title' VARCHAR, 'total' VARCHAR, 'content_id' VARCHAR, 'content_type_id' VARCHAR, 'series_num' VARCHAR,'position' INTEGER DEFAULT 0, 'pic' VARCHAR,'update_time' TIMESTAMP default (datetime('now', 'localtime')))");
		db.execSQL("CREATE UNIQUE INDEX 'history_idx' ON 'history' ('content_id' , 'content_type_id' );");
		db.execSQL("CREATE  TABLE 'collect' ('_id' INTEGER PRIMARY KEY  AUTOINCREMENT , 'title' VARCHAR, 'total' VARCHAR, 'content_id' VARCHAR, 'content_type_id' VARCHAR, 'intro' VARCHAR, 'pic' VARCHAR ,'liveurl' VARCHAR)");
		db.execSQL("CREATE UNIQUE INDEX 'collect_idx' ON 'collect' ('content_id' , 'content_type_id' );");
		db.execSQL("CREATE  TABLE 'remind' ('_id' INTEGER PRIMARY KEY  AUTOINCREMENT , 'title' VARCHAR, 'channel_name' VARCHAR, 'remind_time' datetime default (datetime('now', 'localtime')) ,'is_new' INTEGER DEFAULT 0, 'pic' VARCHAR)");
		db.execSQL("CREATE UNIQUE INDEX 'remind_idx' ON 'remind' ('title' , 'remind_time' );");
		db.execSQL("CREATE  TABLE 'subscibe' ('_id' INTEGER PRIMARY KEY  AUTOINCREMENT , 'title' VARCHAR, 'part' VARCHAR, 'total' VARCHAR, 'content_id' VARCHAR, 'content_type_id' VARCHAR, 'is_new' INTEGER DEFAULT 0, 'pic' VARCHAR)");
		db.execSQL("CREATE UNIQUE INDEX 'subscibe_idx' ON 'subscibe' ('content_id' , 'content_type_id' );");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF movie");
		db.execSQL("DROP TABLE IF tv");
		db.execSQL("DROP TABLE IF ct");
		db.execSQL("DROP TABLE IF live_togic_1");
		db.execSQL("DROP TABLE IF music");
		
		db.execSQL("DROP TABLE IF EXISTS subscibe");
		db.execSQL("DROP TABLE IF EXISTS collect");
		db.execSQL("DROP TABLE IF EXISTS history");
		db.execSQL("DROP TABLE IF EXISTS remind");
		onCreate(db);
	}
}
