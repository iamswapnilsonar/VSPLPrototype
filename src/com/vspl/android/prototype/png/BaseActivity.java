package com.vspl.android.prototype.png;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;

import com.vspl.android.prototype.png.sliding_menu.SlidingMenu;
import com.vspl.android.prototype.png.sliding_menu.app.SlidingFragmentActivity;

// https://github.com/jfeinstein10/SlidingMenu
public class BaseActivity extends SlidingFragmentActivity {

	private int mTitleRes;
	protected Fragment mFrag;

	public BaseActivity(int titleRes) {
		mTitleRes = titleRes;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle(mTitleRes);

		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		
		if (savedInstanceState == null) {
			
			FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
			mFrag = new SampleListFragment();
			t.replace(R.id.menu_frame, mFrag);
			t.commit();
			
		} else {
			mFrag = (Fragment) this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
		}

		Display display = getWindowManager().getDefaultDisplay(); 
		
		@SuppressWarnings("deprecation")
		int width = display.getWidth();  // deprecated
		
		int mBehindWidthValue = (int) (0.32f * width); 
		 
		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);

//		sm.setBehindWidthRes(R.dimen.slidingmenu_behind_width);
		sm.setFadeDegree(0.4f);
		
		sm.setBackgroundResource(android.R.color.transparent);
		// No matter WINDOW/CONTENT because we are not dealing with ActionBar 
		sm.setTouchModeAbove(SlidingMenu.SLIDING_WINDOW);
		sm.setBehindWidth(mBehindWidthValue);
		
//		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

}
