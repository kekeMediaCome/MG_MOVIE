package com.mg_movie.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;
import com.mg_movie.KSetting;
import com.mg_movie.MG_Exit;
import com.mg_movie.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MG_BaseActivity extends Activity {

	public static Toast infoToast = null;
	public ConnectivityManager con = null;
	public ActionBar actionBar;
	public boolean isBack = false;
	Timer BackTimer = null;
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	/**
	 * 程序顶部的控件
	 * @param layout_title
	 */
	public void initActionBar(int layout_title) {
		actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setTitle(getResources().getString(layout_title));
	}

	/**
	 * 程序顶部的控件的组件
	 * @param cls
	 */
	public void initActionBarCompont(Class<?> cls) {
		Action shareAction = new IntentAction(this, createShareIntent(),
				R.drawable.ic_title_share_default);
		actionBar.addAction(shareAction);
		Action otherAction = new IntentAction(this, new Intent(this,
				cls), R.drawable.ic_title_export_default);
		actionBar.addAction(otherAction);
	}

	private Intent createShareIntent() {
		final Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "Shared from the ActionBar widget.");
		return Intent.createChooser(intent, "Share");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_clear_memory_cache:
			imageLoader.clearMemoryCache();
			return true;
		case R.id.item_clear_disc_cache:
			imageLoader.clearDiscCache();
			return true;
		default:
			return false;
		}
	}

	public void showInfo(int info) {
		if (infoToast == null) {
			infoToast = Toast.makeText(this, info, Toast.LENGTH_SHORT);
		} else {
			infoToast.setText(info);
		}
		infoToast.show();
	}

	public void showInfo(String info) {
		if (infoToast == null) {
			infoToast = Toast.makeText(this, info, Toast.LENGTH_SHORT);
		} else {
			infoToast.setText(info);
		}
		infoToast.show();
	}

	/**
	 * 添加网络检查
	 * 
	 * @return
	 */
	public boolean checkNetwork() {
		if (con == null) {
			con = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		}
		boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.isConnectedOrConnecting();
		boolean internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.isConnectedOrConnecting();
		if (!wifi && !internet) {
			showInfo(R.string.network_check_error);
			return false;
		} else {
			return true;
		}
	}

	// 定时双击back
	class backTimerTask extends TimerTask {
		@Override
		public void run() {
			isBack = false;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if (!isBack) {
				isBack = true;
				showInfo(R.string.double_back_exit);
				BackTimer = new Timer();
				BackTimer.schedule(new backTimerTask(), 1000);
			} else {
				MG_Exit.getInstance().exit();
			}
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	public boolean isFirstIn() {
		SharedPreferences preferences = getSharedPreferences(
				KSetting.ISFIRSTLOAD, MODE_PRIVATE);
		boolean isFirst = preferences.getBoolean("isFirstIn", true);
		return isFirst;
	}

	public void writeFirstParm() {
		SharedPreferences preferences = getSharedPreferences(
				KSetting.ISFIRSTLOAD, MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putBoolean("isFirstIn", false);
		editor.commit();
	}
}
