package com.mg_movie.server;

import java.util.ArrayList;
import java.util.List;

import com.mg_movie.AppLog;
import com.mg_movie.KSetting;
import com.mg_movie.parser.Parser_music_yinyuetai_com;
import com.mg_movie.type.Type_YinYueTai;
import com.mg_movie.utils.DBUtils;

import android.app.Activity;
import android.os.AsyncTask;

public class ServerYinYueTaiMusic {

	private Activity instance;
	private ArrayList<String> listPages = null;
	
	public ServerYinYueTaiMusic(Activity instance) {
		this.instance = instance;
		new InitData().execute();
	}
	
	class InitData extends AsyncTask<Void, Void, Void> {
		InitData() {
		}

		@Override
		protected Void doInBackground(Void... params) {
			DBUtils dbUtils = new DBUtils(instance);
			try {
				Parser_music_yinyuetai_com parse = new Parser_music_yinyuetai_com();
				listPages = parse.ParsePage(KSetting.yinyuetai_url);
				int count = listPages.size();
				AppLog.e(count+"  count");
				for (int i = 0; i < count; i++) {
					List<Type_YinYueTai> musics = parse.ParseMusic(listPages.get(i));
					for (Type_YinYueTai music : musics) {
						dbUtils.insertMusci(music);
						AppLog.e("   当前插入页： " + i);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbUtils.close();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		}
	}
}
