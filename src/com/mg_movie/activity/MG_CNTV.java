package com.mg_movie.activity;

import java.util.ArrayList;
import java.util.List;

import com.mg_movie.KSetting;
import com.mg_movie.MG_Exit;
import com.mg_movie.R;
import com.mg_movie.adapter.MG_Lvie_CntvChannelAdapter;
import com.mg_movie.dao.CntvChannelDAO;
import com.mg_movie.dao.CntvMediaTypeDAO;
import com.mg_movie.imageloader.AbsListViewBaseActivity;
import com.mg_movie.type.Type_CntvMediaType;
import com.mg_movie.type.Type_Cntv_Channel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MG_CNTV extends AbsListViewBaseActivity implements
		OnCheckedChangeListener, OnClickListener {

	private List<Type_CntvMediaType> mMediaTypesList;
	private LayoutInflater mLayoutInflater;
	private MG_Lvie_CntvChannelAdapter mTvAdapter;
	private List<List<Type_Cntv_Channel>> mTvChanenList;
	private ArrayList<Integer> radioList;
	private RadioGroup tv_all;
	private int mCurRadioIndex;
	public ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mg_live_cntv);
		MG_Exit.getInstance().addActivity(this);
		mLayoutInflater = (LayoutInflater) getSystemService("layout_inflater");
		initLayout();
		progressDialog = new ProgressDialog(MG_CNTV.this);
		progressDialog.setMessage("加载中...");
		new InitData().execute();
	}

	public void initLayout() {
		ImageView home_top_img = (ImageView) findViewById(R.id.home_top_menudraw);
		home_top_img.setOnClickListener(this);
		home_top_img.setBackgroundResource(R.drawable.btn_back_normal);
		TextView home_top_name = (TextView) findViewById(R.id.home_top_name);
		home_top_name.setText("CNTV");
		mTvChanenList = new ArrayList<List<Type_Cntv_Channel>>();
		radioList = new ArrayList<Integer>();
		mCurRadioIndex = -1;
		listView = (ListView) findViewById(R.id.listview);
		mTvAdapter = new MG_Lvie_CntvChannelAdapter(this, imageLoader);
		((ListView) listView).setAdapter(mTvAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				Type_Cntv_Channel tvChannel = mTvChanenList.get(mCurRadioIndex)
						.get(position);
				bundle.putParcelable("TvChannel", tvChannel);
				intent.putExtras(bundle);
				intent.setClass(MG_CNTV.this, MG_CNTV_Channel.class);
				startActivity(intent);
			}
		});
		tv_all = (RadioGroup) findViewById(R.id.live_cntv_tv_all);
		tv_all.setOnCheckedChangeListener(this);
	}

	private void addButtonByNet() {
		if (mMediaTypesList == null) {
			return;
		}
		try {
			int i = mMediaTypesList.size();
			for (int j = 0; j < i; j++) {
				RadioButton localRadioButton = (RadioButton) this.mLayoutInflater
						.inflate(R.layout.mg_cntv_page_tv_radio_btn, null);
				String name = mMediaTypesList.get(j).getName();
				if (name.equals("中央电视台")) {
					localRadioButton.setText("央视");
				} else if (name.equals("省市电视台")) {
					localRadioButton.setText("卫视");
				} else {
					localRadioButton.setText(name);
				}

				int k = j + 436;
				localRadioButton.setId(k);
				radioList.add(k);
				tv_all.addView(localRadioButton);
				((ViewGroup.MarginLayoutParams) localRadioButton
						.getLayoutParams()).rightMargin = 10;
			}
		} catch (Exception e) {
		}

	}

	class InitData extends AsyncTask<Void, Void, Void> {
		InitData() {
		}

		@Override
		protected Void doInBackground(Void... paramArrayOfVoid) {
			try {
				CntvMediaTypeDAO mediaTypeDAO = new CntvMediaTypeDAO();
				mMediaTypesList = mediaTypeDAO.getMediaTypes(KSetting.cntv_url,
						"?pid=");
				CntvChannelDAO localTvChannelDAO = new CntvChannelDAO();
				int size = mMediaTypesList.size();
				for (int i = 0; i < size; i++) {
					String stringType = ((Type_CntvMediaType) mMediaTypesList
							.get(i)).getMediatype();
					List<Type_Cntv_Channel> localList2 = localTvChannelDAO
							.getTvChannel(stringType);
					mTvChanenList.add(localList2);
				}
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
			addButtonByNet();
			if (tv_all.getChildCount() > 0) {
				((RadioButton) tv_all.getChildAt(0)).setChecked(true);
			}
			progressDialog.cancel();
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		Integer newInteger = Integer.valueOf(checkedId);
		int i = radioList.indexOf(newInteger);
		if (i != mCurRadioIndex) {
			mCurRadioIndex = i;
			List<Type_Cntv_Channel> loList = (List<Type_Cntv_Channel>) mTvChanenList
					.get(i);
			mTvAdapter.notifyDataSetChanged(loList);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.home_top_menudraw:
			finish();
			break;
		}
	}
}