package com.mg_movie.server;

import java.util.List;

import com.mg_movie.json.JsonParseTogic_1;
import com.mg_movie.type.Type_live_togic_1;
import com.mg_movie.utils.DBUtils;

import android.app.Activity;
import android.os.AsyncTask;

public class ServerTogicLive {
	private Activity instance;

	public ServerTogicLive(Activity instance) {
		this.instance = instance;
		new InitData().execute();
	}

	class InitData extends AsyncTask<Void, Void, Void> {
		InitData() {

		}

		@Override
		protected Void doInBackground(Void... params) {
			DBUtils dbUtils = new DBUtils(instance);
			JsonParseTogic_1 parse = new JsonParseTogic_1();
			List<Type_live_togic_1> listPages = parse.getTvChannel();
			for (Type_live_togic_1 live : listPages) {
				dbUtils.insertLive(live);
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
