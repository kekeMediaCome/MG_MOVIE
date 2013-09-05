package com.mg_movie.firstloader;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mg_movie.R;
import com.viewpagerindicator.IconPagerAdapter;

class MG_FirstLoadAdapter extends FragmentPagerAdapter implements
		IconPagerAdapter {
	protected static final String[] CONTENT = new String[] { "This", "Is", "A",
			"Test", };
	protected static final int[] ICONS = new int[] {
			R.drawable.perm_group_calendar, R.drawable.perm_group_camera,
			R.drawable.perm_group_device_alarms, R.drawable.perm_group_location };

	private int mCount = CONTENT.length;

	public MG_FirstLoadAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return FirstLoaderFragment.newInstance(CONTENT[position
				% CONTENT.length]);
	}

	@Override
	public int getCount() {
		return mCount;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return MG_FirstLoadAdapter.CONTENT[position % CONTENT.length];
	}

	@Override
	public int getIconResId(int index) {
		return ICONS[index % ICONS.length];
	}

	public void setCount(int count) {
		if (count > 0 && count <= 10) {
			mCount = count;
			notifyDataSetChanged();
		}
	}
}