package com.mg_movie.adapter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mg_movie.MG_App;
import com.mg_movie.R;
import com.mg_movie.activity.TvDetailActivity;
import com.mg_movie.dao.PlayBackDAO;
import com.mg_movie.player.JieLiveVideoPlayer;
import com.mg_movie.type.PlayBackInfo;
import com.mg_movie.type.Remind;
import com.mg_movie.type.Type_Cntv_Channel;
import com.mg_movie.type.Type_Cntv_Programs;
import com.mg_movie.utils.DBUtils;
import com.mg_movie.utils.DateUtils;
import com.mg_movie.utils.MG_Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MG_Live_Cntv_ProgramAdapter extends BaseAdapter {

	private Activity mContext;
	private Type_Cntv_Channel mCurTvChannel;
	public SimpleDateFormat mFormat;
	private LayoutInflater mLayoutInflater;
	public List<Type_Cntv_Programs> mPrograms;

	@SuppressLint("SimpleDateFormat")
	public MG_Live_Cntv_ProgramAdapter(Activity paramActivity,
			Type_Cntv_Channel paramTvChannel) {
		mFormat = new SimpleDateFormat("HH:mm");
		mContext = paramActivity;
		mCurTvChannel = paramTvChannel;
		mLayoutInflater = (LayoutInflater) mContext
				.getSystemService("layout_inflater");
	}

	/**
	 * 
	 * @param paramString
	 *            视频的播放时间(不是长度)
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	private Boolean nowIsAfterBoolean(String paramString) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH:mm");
		Boolean localBoolean = false;
		try {
			Date localDate1 = localSimpleDateFormat.parse(paramString);
			Date localDate2 = MG_App.getNow();
			String str = localSimpleDateFormat.format(localDate2);
			localBoolean = Boolean.valueOf(localSimpleDateFormat.parse(str)
					.after(localDate1));
		} catch (Exception e) {
		}
		return localBoolean;
	}

	// 直播的播放状态
	private void playingLive(ViewHolder viewHolder) {
		viewHolder.program_status.setText(R.string.tv_time_playnow);
		viewHolder.program_status
				.setBackgroundResource(R.drawable.transparent_background);
		viewHolder.program_time.setTextColor(mContext.getResources().getColor(
				R.color.solid_red));
		viewHolder.program_name.setTextColor(mContext.getResources().getColor(
				R.color.solid_red));
		viewHolder.program_status.setTextColor(mContext.getResources()
				.getColor(R.color.solid_red));
	}

	private String switchDate(String parmString) {
		String string = null;
		try {
			Date date = DateUtils.sdf7.parse(parmString);
			string = DateUtils.sdf5.format(date);
		} catch (Exception e) {
		}
		return string;
	}

	public boolean addRemind(Type_Cntv_Programs paramPrograms, View paramView) {
		try {
			String channel_name = TvDetailActivity.mCurTvChannel
					.getChannelname();
			// String real_time = String.valueOf(paramPrograms.getRealTime());
			// StringBuffer buffer = new StringBuffer(real_time).append(" ");
			String show_time = paramPrograms.getShowtime() + ":00";
			String remind_time = switchDate(show_time);
			Remind localRemind = new Remind();
			localRemind.setTitle(paramPrograms.getTitle());
			localRemind.setChannel_name(channel_name);
			localRemind.setRemind_time(remind_time);
			localRemind.setIs_new(1);
			DBUtils localDbUtils = new DBUtils(mContext);
			paramView.setEnabled(false);
			int j = localDbUtils.getAllRemindsCount();
			if (j < 15) {// 最多15条提醒
				localDbUtils.insertRemind(localRemind);
				((TextView) paramView).setText("已提醒");
				return true;
			} else {
				paramView.setEnabled(true);
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public void playBack(final Activity activity,
			final Type_Cntv_Programs programs) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String playBack = programs.getPlayback();
				// String title = programs.getTitle();
				try {
					PlayBackDAO localPlayBackDAO = new PlayBackDAO();
					List<PlayBackInfo> list = localPlayBackDAO
							.getPlayBackList(playBack);
					Intent intent = new Intent(activity,
							JieLiveVideoPlayer.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("PlayBackInfo", (Serializable) list);
					intent.putExtras(bundle);
					activity.startActivity(intent);
				} catch (Exception e) {
				}
			}
		}).start();
	}

	public class PlayLiveClick implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// String channelName =
		}

	};

	@Override
	public int getCount() {
		if (mPrograms != null) {
			return mPrograms.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return mPrograms.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public int getPlayingPos() {
		int i = mPrograms.size();
		int k = 0;
		for (k = 0; k < i; k++) {
			String str1 = ((Type_Cntv_Programs) mPrograms.get(k)).getShowtime();
			if (!nowIsAfterBoolean(str1).booleanValue()) {// 现在的时间是否在播放列表的后面
				break;
			}
		}
		return k;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Type_Cntv_Programs programs = (Type_Cntv_Programs) mPrograms
				.get(position);
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mLayoutInflater.inflate(
					R.layout.tv_play_program_item, null);
			viewHolder.program_name = (TextView) convertView
					.findViewById(R.id.program_name);
			viewHolder.program_time = (TextView) convertView
					.findViewById(R.id.program_time);
			viewHolder.program_status = (TextView) convertView
					.findViewById(R.id.program_status);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.program_name.setText(programs.getTitle());
		String show_time = programs.getShowtime();
		viewHolder.program_time.setText(show_time);

		int select_week = TvDetailActivity.monclicweekday;
		int today_week = MG_Utils.getTodayWeek();
		if (select_week < today_week) {
			if (mCurTvChannel.getViewback() == 1) {
				playBackBtn(viewHolder);
			} else {
				playNoBtn(viewHolder);
			}
		} else if (select_week == today_week) {
			int i1 = position + 1;
			int i2 = getCount();
			if (i1 < i2) {
				if (nowIsAfterBoolean(show_time).booleanValue()) {
					int i3 = position + 1;
					String str3 = ((Type_Cntv_Programs) mPrograms.get(i3))
							.getShowtime();
					if (!nowIsAfterBoolean(str3).booleanValue()) {
						if (position != 0) {
							int i4 = position - 1;
							String pre = ((Type_Cntv_Programs) mPrograms
									.get(i4)).getShowtime();
							if (nowIsAfterBoolean(pre).booleanValue()) {
								playingLive(viewHolder);
								Message msg = new Message();
								msg.what = 0;
								msg.arg1 = position;
								TvDetailActivity.handler.sendMessage(msg);
							} else {
								if (mCurTvChannel.getViewback() == 1) {
									playBackBtn(viewHolder);
								} else {
									playNoBtn(viewHolder);
								}
							}
						} else {
							playingLive(viewHolder);
							Message msg = new Message();
							msg.what = 0;
							msg.arg1 = position;
							TvDetailActivity.handler.sendMessage(msg);
						}
					} else {
						if (mCurTvChannel.getViewback() == 1) {
							playBackBtn(viewHolder);
						} else {
							playNoBtn(viewHolder);
						}
					}
				} else {
					playRemindBtn(programs, viewHolder);
				}
			} else {
				if (nowIsAfterBoolean(show_time).booleanValue()) {
					if (mCurTvChannel.getViewback() == 1) {
						playBackBtn(viewHolder);
					} else {
						playNoBtn(viewHolder);
					}
				} else {
					playRemindBtn(programs, viewHolder);
				}
			}
		} else if (select_week > today_week) {
			playRemindBtn(programs, viewHolder);
		}
		return convertView;
	}

	class ViewHolder {
		TextView program_name;
		TextView program_status;
		TextView program_time;
	}

	public void notifyDataSetChanged(List<Type_Cntv_Programs> paramList) {
		if ((paramList == null) || (paramList.size() <= 0))
			return;
		this.mPrograms = paramList;
		notifyDataSetChanged();
	}

	public void playRemindBtn(Type_Cntv_Programs paramPrograms,
			ViewHolder viewHolder) {
		// String show_time = paramPrograms.getShowtime()+":00";
		// String time_date = switchDate(show_time);
		// DBUtils dbUtils = new DBUtils(mContext);
		// String title = paramPrograms.getTitle();
		// if (dbUtils.getRemindStatus(title, time_date) == 0) {
		viewHolder.program_status.setText(R.string.tv_time_remind);
		// }else {
		// viewHolder.program_status.setText("已提醒");
		// }
		viewHolder.program_status
				.setBackgroundResource(R.drawable.tv_time_remind);
		viewHolder.program_name.setTextColor(mContext.getResources().getColor(
				R.color.white));
		viewHolder.program_status.setTextColor(mContext.getResources()
				.getColor(R.color.white));
		viewHolder.program_time.setTextColor(mContext.getResources().getColor(
				R.color.white));
	}

	public void playBackBtn(ViewHolder viewHolder) {
		viewHolder.program_status.setText(R.string.tv_time_playback);
		viewHolder.program_status
				.setBackgroundResource(R.drawable.tv_time_playback);
		viewHolder.program_name.setTextColor(mContext.getResources().getColor(
				R.color.white));
		viewHolder.program_status.setTextColor(mContext.getResources()
				.getColor(R.color.white));
		viewHolder.program_time.setTextColor(mContext.getResources().getColor(
				R.color.white));

	}

	public void playNoBtn(ViewHolder viewHolder) {
		viewHolder.program_status.setText("");
		viewHolder.program_status
				.setBackgroundResource(R.drawable.transparent_background);
		viewHolder.program_name.setTextColor(mContext.getResources().getColor(
				R.color.white));
		viewHolder.program_status.setTextColor(mContext.getResources()
				.getColor(R.color.white));
		viewHolder.program_time.setTextColor(mContext.getResources().getColor(
				R.color.white));
	}
}
