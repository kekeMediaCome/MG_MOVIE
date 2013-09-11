package com.mg_movie.server;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.mg_movie.json.JsonParseTogic_1;
import com.mg_movie.server.SeverTencentMovie;
public class Server extends Activity{

	public SeverTencentMovie severTencentMovie;
	public Server56Movie server56Movie;
	public ServerTencentTV serverTencentTV;
	public ServerPPTVCartoon serverPPTVCartoon;
	public ServerTogicLive serverTogicLive; 
	public static Server instance;
	public static int index = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
//		File dbFile = new File("/mnt/sdcard/zs.sqlite");
//		if (dbFile.exists()) {
//			dbFile.delete();
//		}
//		severTencentMovie = new SeverTencentMovie(this);
//		server56Movie = new Server56Movie(this);
//		serverTencentTV = new ServerTencentTV(this);
//		serverPPTVCartoon = new ServerPPTVCartoon(this);
		serverTogicLive = new ServerTogicLive(this);
	}
	
	public Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				severTencentMovie = new SeverTencentMovie(instance);
				break;
			case 2:
				server56Movie = new Server56Movie(instance);
				break;
			case 3:
				serverTencentTV = new ServerTencentTV(instance);
				break;
			case 4:
				serverPPTVCartoon = new ServerPPTVCartoon(instance);
				break;
			case 5:
				serverTogicLive = new ServerTogicLive(instance);
				break;
			}
			super.handleMessage(msg);
		}
	};
}
