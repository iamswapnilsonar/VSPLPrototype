package com.vspl.android.prototype.png;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.viewpagerindicator.PageIndicator;
import com.vspl.android.prototype.png.adapters.SelectedOrnamentsListAdapter;
import com.vspl.android.prototype.png.sliding_menu.SlidingMenu;
import com.vspl.android.prototype.png.ui.HorizontalListView;
import com.vspl.android.prototype.png.utils.MethodUtils;

public class MainActivity extends BaseActivity implements OnClickListener{

	public MainActivity() {
		super(R.string.sample_activity);
		// TODO Auto-generated constructor stub
	}
	
	private Context mContext;
	
	public PageIndicator mIndicator;
	private ViewPager awesomePager;
	private PagerAdapter pm;

	private SlidingMenu sm;
	private ImageView btnShowSlidingMenu;
	
	// Choosed Ornaments List
	private HorizontalListView lvSelectedOrnaments;
	public static LinkedHashMap<String, String> mapForSelectedOrnaments = new LinkedHashMap<String, String>();
	public static SelectedOrnamentsListAdapter selectedListAdapter;
	
	private String deviceNames_1[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	private String deviceNames_2[] = { "AB", "BC", "CD", "DE", "EF", "FG", "GH", "HI", "IJ", "JK"};
	private String deviceNames_3[] = { "ABC", "BCD", "CDE", "DEF", "EFG", "FGH", "GHI", "HIJ", "IJK", "JKL",};
	private String deviceNames_4[] = { "ABCD", "BCDE", "CDEF", "DEFG", "EFGH", "FGHI", "GHIJ", "HIJK", "IJKL", "JKLM"};

	private List<String[]> listOfDevicesArray;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_dazzle_screen_activity);

		initUI();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		mContext = MainActivity.this;
		
		// Sliding Menu..
		sm = getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT);
		sm.setShadowDrawable(R.drawable.shadow);
		
		btnShowSlidingMenu = (ImageView) findViewById(R.id.btn_show_sliding_menu);
		
		btnShowSlidingMenu.setOnClickListener(this);
		
		awesomePager = (ViewPager) findViewById(R.id.pager);
		mIndicator = (PageIndicator) findViewById(R.id.pagerIndicator);

		ArrayList<String> arrDeviceNames = new ArrayList<String>();

		listOfDevicesArray = new ArrayList<String[]>();

		listOfDevicesArray.add(deviceNames_1);
		listOfDevicesArray.add(deviceNames_2);
		listOfDevicesArray.add(deviceNames_3);
		listOfDevicesArray.add(deviceNames_4);

		List<OrnamentsGridFragment> gridFragments = new ArrayList<OrnamentsGridFragment>();
		Log.v("MainActivity : ", "DeviceNames Size : "+arrDeviceNames.size());

		for (int j = 0; j < listOfDevicesArray.size(); j++) {

			ArrayList<OrnamentsGridItems> gridItemList = new ArrayList<OrnamentsGridItems>();
			String[] arrDevicesNames = listOfDevicesArray.get(j);

			for (int k = 0; k < arrDevicesNames.length; k++) {				
				OrnamentsGridItems gridItem = new OrnamentsGridItems(k, arrDevicesNames[k]);
				gridItemList.add(gridItem);
			}		

			OrnamentsGridItems[] gp = {};
			OrnamentsGridItems[] gridPage = gridItemList.toArray(gp);
			gridFragments.add(new OrnamentsGridFragment(gridPage, MainActivity.this));
		}

		pm = new PagerAdapter(getSupportFragmentManager(), gridFragments);
		awesomePager.setAdapter(pm);
		mIndicator.setViewPager(awesomePager);
		
		lvSelectedOrnaments = (HorizontalListView) findViewById(R.id.list_sel_items);
		selectedListAdapter = new SelectedOrnamentsListAdapter(mContext, mapForSelectedOrnaments, listener);		
		lvSelectedOrnaments.setAdapter(selectedListAdapter);
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			final int position = lvSelectedOrnaments.getPositionForView((View) view.getParent());

			String str = SelectedOrnamentsListAdapter.mKeys[position];
			mapForSelectedOrnaments.remove(str);

			SelectedOrnamentsListAdapter.mKeys = MethodUtils.getKeysArrayFromLinkedHashMap(mapForSelectedOrnaments);
			selectedListAdapter.notifyDataSetChanged();

			// refresh the dazzled image 
			// showDazzledModelImage(mapForSelectedOrnaments);
		}
	};
	
	private class PagerAdapter extends FragmentStatePagerAdapter {
		private List<OrnamentsGridFragment> fragments;

		public PagerAdapter(FragmentManager fm, List<OrnamentsGridFragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}

		@Override
		public Fragment getItem(int position) {
			return this.fragments.get(position);
		}

		@Override
		public int getCount() {
			return this.fragments.size();
		}
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub

		int key = view.getId();

		switch (key) {
		case R.id.btn_show_sliding_menu:
			sm.toggle();
			break;
		}

	}
}