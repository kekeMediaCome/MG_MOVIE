package com.mg_movie.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String NAME = "zs.sqlite";
	private static final String DB_PATH = "/mnt/sdcard/";
	private static final int DB_VERSION = 1;
	public static final String TABLE_NAME_CHANNLE_MOVIE = "movie";
	public static final String MOVIE_SQL = "CREATE TABLE movie ('video_id' INTEGER PRIMARY KEY  AUTOINCREMENT, 'video_name' VARCHAR, 'video_urlstite' VARCHAR, 'video_url' VARCHAR, 'video_img' VARCHAR, 'video_source' VARCHAR, 'video_mark' VARCHAR)";

	public DBHelper(Context context) {
		super(context, DB_PATH+NAME, null, DB_VERSION);
	}

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(MOVIE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF movie");
		onCreate(db);
	}
}
