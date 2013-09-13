package com.mg_movie.activity;

import java.util.List;

import com.mg_movie.R;
import com.mg_movie.adapter.MG_Live_Cntv_ProgramAdapter;
import com.mg_movie.dao.CntvProgramsDAO;
import com.mg_movie.type.Type_Cntv_Channel;
import com.mg_movie.type.Type_Cntv_Programs;
import com.mg_movie.utils.MG_Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MG_CNTV_Channel extends Activity implements OnItemClickListener,
		OnCheckedChangeListener, OnClickListener {
	public static Type_Cntv_Channel mCurTvChannel;
	public static int monclicweekday = 1;
	protected final int FIRST_ID = 436;
	private List<Type_Cntv_Programs> mCurProgramList;
	private List<String> mCurWeeKDate;
	private int mCurWeek;
	public static ListView mTvDetailListView;
	private MG_Live_Cntv_ProgramAdapter mTvProgramAdapt;
	private RadioGroup week_group;
	public static Handler handler;
	public ProgressDialog progressDialog;

	public MG_CNTV_Channel() {
		mCurWeek = 1;
	}

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_tv_detail_activity);
		progressDialog = new ProgressDialog(MG_CNTV_Channel.this);
		progressDialog.setMessage("加载中...");
		initTime();
		initLayout();
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case 0:
					mTvDetailListView.setSelection(msg.arg1);
					mTvDetailListView.setSelected(true);
					break;
				}
			}
		};
	}

	public void initLayout() {
		Bundle bundle = getIntent().getExtras();
		mCurTvChannel = (Type_Cntv_Channel) bundle.getParcelable("TvChannel");
		mTvDetailListView = (ListView) findViewById(R.id.listview);
		mTvProgramAdapt = new MG_Live_Cntv_ProgramAdapter(this, mCurTvChannel);
		mTvDetailListView.setAdapter(mTvProgramAdapt);
		mTvDetailListView.setOnItemClickListener(this);
		week_group = (RadioGroup) findViewById(R.id.week_group);
		week_group.setOnCheckedChangeListener(this);
		int i = MG_Utils.getTodayWeek() - 1;
		((RadioButton) week_group.getChildAt(i)).setChecked(true);
		findViewById(R.id.back_btn).setOnClickListener(this);

	}

	public void initTime() {
		mCurWeek = MG_Utils.getTodayWeek();
		mCurWeeKDate = MG_Utils.getCurWeekOfDate(mCurWeek);
	}

	private void setProgram(String paramString1, String paramString2) {
		InitData localInitData = new InitData(paramString1, paramString2);
		Void[] arrayOfVoid = new Void[0];
		localInitData.execute(arrayOfVoid);
	}

	class InitData extends AsyncTask<Void, Void, Void> {
		private String channelID;
		private String dateTime;

		public InitData(String channelID, String dateTime) {
			super();
			this.channelID = channelID;
			this.dateTime = dateTime;
		}

		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			try {
				CntvProgramsDAO programsDAO = new CntvProgramsDAO();
				mCurProgramList = programsDAO.getPrograms(channelID, dateTime);
			} catch (Exception e) {

			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog.show();
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			mTvProgramAdapt.notifyDataSetChanged(mCurProgramList);
			progressDialog.cancel();
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		mTvProgramAdapt.playBack(this, mTvProgramAdapt.mPrograms.get(position));
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (mCurWeeKDate == null) {
			return;
		}
		String dateTime = "";
		switch (checkedId) {
		case R.id.play_time_one:
			dateTime = mCurWeeKDate.get(0);
			monclicweekday = 1;
			break;
		case R.id.play_time_two:
			dateTime = mCurWeeKDate.get(1);
			monclicweekday = 2;
			break;
		case R.id.play_time_three:
			dateTime = mCurWeeKDate.get(2);
			monclicweekday = 3;
			break;
		case R.id.play_time_four:
			dateTime = mCurWeeKDate.get(3);
			monclicweekday = 4;
			break;
		case R.id.play_time_five:
			dateTime = mCurWeeKDate.get(4);
			monclicweekday = 5;
			break;
		case R.id.play_time_six:
			dateTime = mCurWeeKDate.get(5);
			monclicweekday = 6;
			break;
		case R.id.play_time_seven:
			dateTime = mCurWeeKDate.get(6);
			monclicweekday = 7;
			break;
		}
		String channelId = mCurTvChannel.getChannelid();
		setProgram(channelId, dateTime);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_btn:
			this.finish();
			break;
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		Log.e("Config", ":  "+newConfig.orientation);
		super.onConfigurationChanged(newConfig);
	}

	
}
