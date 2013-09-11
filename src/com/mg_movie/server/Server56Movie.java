package com.mg_movie.server;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;

import com.mg_movie.AppLog;
import com.mg_movie.KSetting;
import com.mg_movie.parser.Parser_movielist_56_com;
import com.mg_movie.type.Type_v_qq_com;
import com.mg_movie.utils.DBUtils;

public class Server56Movie {

	private Activity instance;
	private ArrayList<String> listPages = null;

	public Server56Movie(Activity instance){
		this.instance = instance;
		new InitData().execute();
	}
	
	class InitData extends AsyncTask<Void, Void, Void> {
		InitData() {
			
		}
		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			DBUtils dbUtils = new DBUtils(instance);
			try {
				Parser_movielist_56_com parse = new Parser_movielist_56_com();
				listPages = parse.ParsePage(KSetting.movielist_56_com);
				int count = listPages.size();
				for (int i = 0; i < count; i++) {
					List<Type_v_qq_com> videos = parse.ParseVideo(listPages.get(i));
					for (Type_v_qq_com video: videos) {
						AppLog.e("当前插入页： "+i);
						dbUtils.insertMovie(video);
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
