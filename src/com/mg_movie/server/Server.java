package com.mg_movie.server;

import android.app.Activity;
import android.os.Bundle;
import com.mg_movie.server.SeverTencentMovie;

public class Server extends Activity {

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
		// File dbFile = new File("/mnt/sdcard/zs.sqlite");
		// if (dbFile.exists()) {
		// dbFile.delete();
		// }
		// severTencentMovie = new SeverTencentMovie(this);
		// server56Movie = new Server56Movie(this);
		// serverTencentTV = new ServerTencentTV(this);
		// serverPPTVCartoon = new ServerPPTVCartoon(this);
		serverTogicLive = new ServerTogicLive(this);
	}
}
