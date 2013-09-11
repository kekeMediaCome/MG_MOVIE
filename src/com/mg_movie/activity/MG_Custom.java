package com.mg_movie.activity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mg_movie.KSetting;
import com.mg_movie.MG_Exit;
import com.mg_movie.R;
import com.mg_movie.adapter.MG_CustomAdapter;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MG_Custom extends MG_BaseActivity implements OnClickListener, OnChildClickListener{
	public ExpandableListView listview;
	public MG_CustomAdapter adapter;
	private List<String> groupArray;
	private List<List<String[]>> childArray;
	public ProgressDialog progress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mg_official_custom);
		MG_Exit.getInstance().addActivity(this);
		ImageView home_top_img = (ImageView) findViewById(R.id.home_top_menudraw);
		home_top_img.setOnClickListener(this);
		home_top_img.setBackgroundResource(R.drawable.btn_back_normal);
		TextView home_top_name = (TextView) findViewById(R.id.home_top_name);
		home_top_name.setText("自定义");
		progress = new ProgressDialog(MG_Custom.this);
		progress.setMessage("解析中...");
		listview = (ExpandableListView)findViewById(R.id.listview);
		int flag = ReadSelfChannel("three_tvlist.txt");
		if (flag == 1) {
			new InitData().execute();
		}
		adapter = new MG_CustomAdapter(this, groupArray, childArray);
		listview.setAdapter(adapter);
		listview.setOnChildClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.home_top_menudraw:
			finish();
			break;
		}
	}
	class InitData extends AsyncTask<Void, Void, Void> {
		String filePathString = Environment.getExternalStorageDirectory() + "/"+"three_tvlist.txt";
		InitData() {
		}
		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			try {
				PareChannel(KSetting.rushplayer, KSetting.rushplayer_head, filePathString);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			progress.show();
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			ReadSelfChannel("three_tvlist.txt");
			adapter.setLists(groupArray, childArray);
			progress.cancel();
		}
	}
	public void PareChannel(String urlString, String headString, String path) {
		try {
			Document doc = Jsoup.connect(urlString).get();
			Element divGroup = doc.getElementById("divGroup");
			if (divGroup != null) {
				File file = new File(path);
				if (!file.exists()) {
				}else {
					file.delete();
				}
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(file), "GB2312"));
				PrintWriter out = new PrintWriter(writer);
				Elements videos = divGroup.getElementsByTag("a");
				for (Element video : videos) {
					String url = video.attr("href");
					if (url.startsWith("http://")) {
					} else {
						url = headString + url;
					}
					String name = video.text();

					ParseChild(name, url, out);
				}
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean isFist = true;
	public void ParseChild(String name1, String urlString, PrintWriter fout) {
		try {
			Document doc = Jsoup.connect(urlString).get();
			Element contentwrapper = doc.getElementById("contentwrapper");
			if (contentwrapper != null) {
				Elements radios = contentwrapper.getElementsByTag("a");
				for (Element radio : radios) {
					if (!isFist) {
						String url = radio.attr("href");
						String name = name1 + "," + radio.text() + "," + url
								+ "\r\n";
						fout.write(name);
					}else {
						isFist = false;
						fout.write("\r\n");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int ReadSelfChannel(String filename) {
		String path = null;
		String code = "UTF-8";
		File file = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		if (sdCardExist) {
			path = Environment.getExternalStorageDirectory() + "/" + filename;
			file = new File(path);
			if (!file.exists()) {
				Toast.makeText(this, "第一次进行更新中", Toast.LENGTH_LONG).show();
				// finish();
				return 1;
			}
		} else {
			Toast.makeText(this, "请检查SD卡是否存在", Toast.LENGTH_LONG).show();
			finish();
			return -1;
		}
		try {
			// 探测txt文件的编码格式
			code = codeString(path);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			InputStream inStream = new FileInputStream(file);
			if (inStream != null) {
				InputStreamReader inputreader = new InputStreamReader(inStream,
						code);
				BufferedReader buffreader = new BufferedReader(inputreader);
				String line;
				String[] splitArray;
				int lastGroup = 0;
				while ((line = buffreader.readLine()) != null) {
					splitArray = line.split(",");
					if (splitArray != null && splitArray.length == 3) {
						lastGroup = groupArray.size() - 1;
						if (lastGroup == -1) {
							lastGroup = 0;
							groupArray.add(splitArray[0]);
						} else if (groupArray.get(lastGroup).equals(
								splitArray[0])) {
							// 首先检测最后一个类型
						} else {
							lastGroup = CheckGroupName(splitArray[0]);
							if (lastGroup == -1) {
								groupArray.add(splitArray[0]);
								lastGroup = groupArray.size() - 1;
							}
						}
						String[] tempArray = new String[2];
						tempArray[0] = splitArray[1];
						tempArray[1] = splitArray[2];
						List<String[]> temlist;
						int size = childArray.size();
						if (size == 0 || (size - 1) < lastGroup) {
							temlist = new ArrayList<String[]>();
						} else {
							temlist = childArray.get(lastGroup);
							childArray.remove(lastGroup);
						}
						temlist.add(tempArray);
						childArray.add(lastGroup, temlist);
					}
				}
				buffreader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int CheckGroupName(String name) {
		int count = groupArray.size();
		for (int i = 0; i < count; i++) {
			if (name.equals(groupArray.get(i))) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 判断文件的编码格式
	 * 
	 * @param fileName
	 *            :file
	 * @return 文件编码格式
	 * @throws Exception
	 */
	private static String codeString(String fileName) throws Exception {
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(
				fileName));
		int p = (bin.read() << 8) + bin.read();
		String code = null;

		switch (p) {
		case 0xefbb:
			code = "UTF-8";
			break;
		case 0xfffe:
			code = "Unicode";
			break;
		case 0xfeff:
			code = "UTF-16BE";
			break;
		default:
			code = "GBK";
		}

		bin.close();

		Log.d("Parseutil", "find text code ===>" + code);

		return code;
	}

	@Override
	public boolean onChildClick(ExpandableListView arg0, View arg1, int arg2,
			int arg3, long arg4) {
		return false;
	}
}
