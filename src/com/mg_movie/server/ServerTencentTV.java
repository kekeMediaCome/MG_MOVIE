package com.mg_movie.server;

import java.util.ArrayList;
import java.util.List;

import com.mg_movie.AppLog;
import com.mg_movie.KSetting;
import com.mg_movie.parser.Parser_tv_qq_com;
import com.mg_movie.type.Type_tv;
import com.mg_movie.utils.DBUtils;

import android.app.Activity;
import android.os.AsyncTask;

public class ServerTencentTV {

	private Activity instance;
	private ArrayList<String> listPages = null;

	public ServerTencentTV(Activity instance) {
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
				Parser_tv_qq_com parse = new Parser_tv_qq_com();
				listPages = parse.ParsePage(KSetting.tv_q_com_url);
				int count = listPages.size();
				for (int i = 0; i < count; i++) {
					List<Type_tv> videos = parse.ParseTV(listPages.get(i));
					for (Type_tv video: videos) {
						AppLog.e("   TV当前插入页： "+i);
						dbUtils.insertTV(video);
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
