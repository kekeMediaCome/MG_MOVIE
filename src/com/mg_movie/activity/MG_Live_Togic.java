package com.mg_movie.activity;

import java.util.ArrayList;

import com.mg_movie.MG_Exit;
import com.mg_movie.R;
import com.mg_movie.firstloader.FirstLoaderFragment;
import com.mg_movie.live.Live_gangtai;
import com.mg_movie.live.Live_shaoer;
import com.mg_movie.live.Live_tiyu;
import com.mg_movie.live.Live_weishi;
import com.mg_movie.live.Live_xinwen;
import com.mg_movie.live.Live_yangshi;
import com.mg_movie.live.Live_yingshi;
import com.mg_movie.type.Type_live_togic_1;
import com.mg_movie.utils.DBUtils;
import com.viewpagerindicator.IconPagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MG_Live_Togic extends FragmentActivity {
	private static final String[] CONTENT = new String[] { "央视", "卫视", "地方",
			"港台", "体育", "影视", "新闻", "少儿" };
	private static final int[] ICONS = new int[] {
			R.drawable.perm_group_calendar, R.drawable.perm_group_camera,
			R.drawable.perm_group_device_alarms,
			R.drawable.perm_group_location, R.drawable.perm_group_calendar,
			R.drawable.perm_group_camera, R.drawable.perm_group_calendar,
			R.drawable.perm_group_camera };
	public DBUtils dbUtils;
	public ArrayList<Type_live_togic_1> list_yangshi, list_weishi, list_tiyu,
			list_yingshi, list_xinwen, list_shaoer, list_gangtai, list_taiwan;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.mg_live_1);
		MG_Exit.getInstance().addActivity(this);
		dbUtils = new DBUtils(this);
		FragmentPagerAdapter adapter = new GoogleMusicAdapter(
				getSupportFragmentManager());

		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter);

		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);
		String[] parms = { "category", "1" };
		list_yangshi = dbUtils.getRoundLives(parms);
		parms[1] = "2";
		list_weishi = dbUtils.getRoundLives(parms);
		parms[1] = "4";
		list_tiyu = dbUtils.getRoundLives(parms);
		parms[1] = "6";
		list_yingshi = dbUtils.getRoundLives(parms);
		parms[1] = "7";
		list_xinwen = dbUtils.getRoundLives(parms);
		parms[1] = "8";
		list_shaoer = dbUtils.getRoundLives(parms);
		parms[0] = "province";
		parms[1] = "香港";
		list_gangtai = dbUtils.getRoundLives(parms);
		parms[1] = "台湾";
		list_taiwan = dbUtils.getRoundLives(parms);
		for (Type_live_togic_1 live : list_taiwan) {
			list_gangtai.add(live);
		}
	}

	class GoogleMusicAdapter extends FragmentPagerAdapter implements
			IconPagerAdapter {
		public GoogleMusicAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			if (position == 0) {
				Fragment live_yangshi = Live_yangshi.getFragment();
				Bundle bundle = new Bundle();
				bundle.putSerializable("yangshi", list_yangshi);
				live_yangshi.setArguments(bundle);
				return live_yangshi;
			} else if (position == 1) {
				Fragment live_weishi = Live_weishi.getFragment();
				Bundle bundle = new Bundle();
				bundle.putSerializable("weishi", list_weishi);
				live_weishi.setArguments(bundle);
				return live_weishi;
			} else if (position == 3) {
				Fragment live_gangtai = Live_gangtai.getFragment();
				Bundle bundle = new Bundle();
				bundle.putSerializable("gangtai", list_gangtai);
				live_gangtai.setArguments(bundle);
				return live_gangtai;
			} else if (position == 4) {
				Fragment live_tiyu = Live_tiyu.getFragment();
				Bundle bundle = new Bundle();
				bundle.putSerializable("tiyu", list_tiyu);
				live_tiyu.setArguments(bundle);
				return live_tiyu;
			} else if (position == 5) {
				Fragment live_yingshi = Live_yingshi.getFragment();
				Bundle bundle = new Bundle();
				bundle.putSerializable("yingshi", list_yingshi);
				live_yingshi.setArguments(bundle);
				return live_yingshi;
			} else if (position == 6) {
				Fragment live_xinwen = Live_xinwen.getFragment();
				Bundle bundle = new Bundle();
				bundle.putSerializable("xinwen", list_xinwen);
				live_xinwen.setArguments(bundle);
				return live_xinwen;
			} else if (position == 7) {
				Fragment live_shaoer = Live_shaoer.getFragment();
				Bundle bundle = new Bundle();
				bundle.putSerializable("shaoer", list_shaoer);
				live_shaoer.setArguments(bundle);
				return live_shaoer;
			} else {
				return FirstLoaderFragment.newInstance("sdfsdf");
			}
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return CONTENT[position % CONTENT.length];
		}

		@Override
		public int getIconResId(int index) {
			return ICONS[index];
		}

		@Override
		public int getCount() {
			return CONTENT.length;
		}
	}

	@Override
	protected void onDestroy() {
		dbUtils.close();
		super.onDestroy();
	}

}
