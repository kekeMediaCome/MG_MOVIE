package com.mg_movie.activity;

import com.mg_movie.R;
import com.mg_movie.firstloader.FirstLoaderFragment;
import com.mg_movie.imageloader.AbsListViewBaseFramActivity;
import com.mg_movie.live.Live_1;
import com.viewpagerindicator.IconPagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MG_Live extends AbsListViewBaseFramActivity {
	private static final String[] CONTENT = new String[] { "直播室-1",
			"直播室-2", "直播室-3", "收藏" };
	private static final int[] ICONS = new int[] {
			R.drawable.perm_group_calendar, R.drawable.perm_group_camera,
			R.drawable.perm_group_device_alarms,
			R.drawable.perm_group_location, };

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.mg_live_1);

		FragmentPagerAdapter adapter = new GoogleMusicAdapter(
				getSupportFragmentManager());

		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter);

		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);
	}

	class GoogleMusicAdapter extends FragmentPagerAdapter implements
			IconPagerAdapter {
		public GoogleMusicAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			if (position == 0) {
				return Live_1.getFragment();
			}else {
				return FirstLoaderFragment.newInstance(CONTENT[position % CONTENT.length]);
			}
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return  CONTENT[position % CONTENT.length].toUpperCase();
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

}
