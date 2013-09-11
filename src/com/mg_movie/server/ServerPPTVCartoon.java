package com.mg_movie.server;

import java.util.ArrayList;
import java.util.List;

import com.mg_movie.AppLog;
import com.mg_movie.KSetting;
import com.mg_movie.parser.Parser_cartoon_pptv_com;
import com.mg_movie.type.Type_cartoon;
import com.mg_movie.utils.DBUtils;

import android.app.Activity;
import android.os.AsyncTask;

public class ServerPPTVCartoon {
	private Activity instance;
	private ArrayList<String> listPages = null;

	public ServerPPTVCartoon(Activity instance) {
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
				Parser_cartoon_pptv_com parse = new Parser_cartoon_pptv_com();
				listPages = parse.ParsePage(KSetting.cartoon_pptv_com_url);
				int count = listPages.size();
				for (int i = 0; i < count; i++) {
					List<Type_cartoon> cartoons = parse.ParseCartoon(listPages
							.get(i));
					for (Type_cartoon cartoon : cartoons) {
						dbUtils.insertCT(cartoon);
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
