package com.mg_movie.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;
import net.youmi.android.offers.OffersManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

import com.mg_movie.AppLog;
import com.mg_movie.MG_Exit;
import com.mg_movie.R;
import com.mg_movie.adapter.MG_HomeAdapter;
import com.mg_movie.adapter.MG__Home_MenuDraw_Adapter;
import com.mg_movie.parser.Parse_home_content;
import com.mg_movie.type.Type_home_content;

public class MG_HOME extends MG_BaseActivity implements OnClickListener {

	public MG_HOME instance;
	private ViewPager viewPager; // android-support-v4中的滑动组件
	private List<ImageView> imageViews; // 滑动的图片集合

	private String[] titles; // 图片标题
	private int[] imageResId; // 图片ID
	private List<View> dots; // 图片标题正文的那些点

	private TextView tv_title;
	private int currentItem = 0; // 当前图片的索引号
	// An ExecutorService that can schedule commands to run after a given delay,
	// or to execute periodically.
	private ScheduledExecutorService scheduledExecutorService;
	private View listViewHeader;
	private ListView listView;
	private List<Type_home_content> lists;
	private MG_HomeAdapter adapter;
	public LayoutInflater inflater;
	private MenuDrawer mMenuDrawer;
	private MG__Home_MenuDraw_Adapter menuAdapter;
	/**
	 * 积分 Mini Banner
	 */
//	OffersBanner mBanner;
	// 切换当前显示的图片
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MG_Exit.getInstance().addActivity(this);
		instance = this;
		writeFirstParm();
		// 设置右侧的menu
		mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND,
				Position.LEFT, MenuDrawer.MENU_DRAG_CONTENT);
		mMenuDrawer.setMenuView(R.layout.mg_home_menudraw);
		mMenuDrawer.setContentView(R.layout.mg_home);
//		// (可选)使用积分Banner-一个新的积分墙入口点，随时随地让用户关注新的积分广告
//		mBanner = new OffersBanner(instance, OffersAdSize.SIZE_MATCH_SCREENx60);
//		RelativeLayout layoutOffersBanner = (RelativeLayout) findViewById(R.id.offersBannerLayout);
//		layoutOffersBanner.addView(mBanner);
		findViewById(R.id.home_top_menudraw).setOnClickListener(this);
		TextView home_top_name = (TextView) findViewById(R.id.home_top_name);
		home_top_name.setText("Home");
		inflater = LayoutInflater.from(this);
		listViewHeader = inflater.inflate(R.layout.mg_banner, null);
		imageResId = new int[] { R.drawable.dot0, R.drawable.dot1,
				R.drawable.dot2, R.drawable.dot3, R.drawable.dot4 };
		titles = new String[imageResId.length];
		titles[0] = "巩俐不低俗，我就不能低俗";
		titles[1] = "扑树又回来啦！再唱经典老歌引万人大合唱";
		titles[2] = "揭秘北京电影如何升级";
		titles[3] = "乐视网TV版大派送";
		titles[4] = "热血屌丝的反杀";

		imageViews = new ArrayList<ImageView>();

		// 初始化图片资源
		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageViews.add(imageView);
		}

		dots = new ArrayList<View>();
		dots.add(listViewHeader.findViewById(R.id.v_dot0));
		dots.add(listViewHeader.findViewById(R.id.v_dot1));
		dots.add(listViewHeader.findViewById(R.id.v_dot2));
		dots.add(listViewHeader.findViewById(R.id.v_dot3));
		dots.add(listViewHeader.findViewById(R.id.v_dot4));

		tv_title = (TextView) listViewHeader.findViewById(R.id.tv_title);
		tv_title.setText(titles[0]);

		viewPager = (ViewPager) listViewHeader.findViewById(R.id.vp);
		viewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
		// 设置一个监听器，当ViewPager中的页面改变时调用
		viewPager.setOnPageChangeListener(new MyPageChangeListener());
		listView = (ListView) findViewById(R.id.listview);
		listView.addHeaderView(listViewHeader, null, true);
		lists = new Parse_home_content().getHomeContent();
		adapter = new MG_HomeAdapter(inflater, lists);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new ListItemOnClik());
		
	}

	@Override
	protected void onStart() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
				TimeUnit.SECONDS);
		super.onStart();
	}

	@Override
	protected void onStop() {
		// 当Activity不可见的时候停止切换
		scheduledExecutorService.shutdown();
		super.onStop();
	}

	/**
	 * 换行切换任务
	 * 
	 * @author jie
	 * 
	 */
	private class ScrollTask implements Runnable {
		public void run() {
			synchronized (viewPager) {
				currentItem = (currentItem + 1) % imageViews.size();
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}
	}

	/**
	 * @author jie
	 */
	private class ListItemOnClik implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (position == 1) {
				OffersManager.getInstance(instance).showOffersWall();
			} else {
				Type_home_content type = lists.get(position - 1);
				AppLog.e(type.getCls_packet());
				if (type.getCls_packet() != null) {
					Intent intent = new Intent();
					intent.setClassName(MG_HOME.this, type.getCls_packet());
					startActivity(intent);
				}
			}
		}

	}

	/**
	 * 当ViewPager中页面的状态发生改变时调用
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;

		/**
		 * This method will be invoked when a new page becomes selected.
		 * position: Position index of the new selected page.
		 */
		public void onPageSelected(int position) {
			currentItem = position;
			tv_title.setText(titles[position]);
			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;
		}

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

	/**
	 * 填充ViewPager页面的适配器
	 */
	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageResId.length;
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(imageViews.get(arg1));
			return imageViews.get(arg1);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}

		@Override
		public void finishUpdate(View arg0) {

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.home_top_menudraw:
			if (mMenuDrawer.isMenuVisible()) {
				mMenuDrawer.closeMenu();
			} else {
				mMenuDrawer.openMenu();
			}
			break;
		}
	}
}
