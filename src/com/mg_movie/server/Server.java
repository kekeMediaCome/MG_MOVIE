package com.mg_movie.server;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;

import com.mg_movie.json.JsonParseTogic_1;
import com.mg_movie.server.SeverTencentMovie;
public class Server extends Activity{

	public SeverTencentMovie severTencentMovie;
	public Server56Movie server56Movie;
	public ServerTencentTV serverTencentTV;
	public ServerPPTVCartoon serverPPTVCartoon;
	public ServerTogicLive serverTogicLive; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		File dbFile = new File("/mnt/sdcard/zs.sqlite");
		if (dbFile.exists()) {
			dbFile.delete();
		}
//		severTencentMovie = new SeverTencentMovie(this);
//		server56Movie = new Server56Movie(this);
//		serverTencentTV = new ServerTencentTV(this);
//		serverPPTVCartoon = new ServerPPTVCartoon(this);
		serverTogicLive = new ServerTogicLive(this);
	}
}
