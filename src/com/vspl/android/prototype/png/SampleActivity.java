package com.vspl.android.prototype.png;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.vspl.android.prototype.png.sliding_menu.SlidingMenu;

public class SampleActivity extends BaseActivity implements OnClickListener{
	
	private ImageView imgShowSlidingMenu, imgSetting;
	private SlidingMenu sm;
	
	public SampleActivity() {
		super(R.string.sample_activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setSlidingActionBarEnabled(true);
		
		setContentView(R.layout.activity_main);
		
		initUI();
		
//		 // configure the SlidingMenu
//        SlidingMenu menu = new SlidingMenu(this);
//        menu.setMode(SlidingMenu.LEFT);
//        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//        menu.setShadowWidthRes(R.dimen.shadow_width);
//        menu.setShadowDrawable(R.drawable.icon);
//        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//        menu.setFadeDegree(0.35f);
//        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
//        menu.setMenu(R.layout.menu);
		
		sm = getSlidingMenu();
		
		sm.setMode(SlidingMenu.LEFT);
		sm.setShadowDrawable(R.drawable.shadow);
	}

	private void initUI(){
		
		imgShowSlidingMenu = (ImageView) findViewById(R.id.img_show_sliding_menu);
		imgSetting = (ImageView) findViewById(R.id.img_setting);
		
		imgShowSlidingMenu.setOnClickListener(this);
		imgSetting.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
		int key = view.getId();
		
		switch (key) {
		case R.id.img_show_sliding_menu:
			sm.toggle();
			break;

		case R.id.img_setting:
			showTost("Setting Button");
			break;

		}
		
	}

	private void showTost(String msg){  
        Toast.makeText(SampleActivity.this, msg, Toast.LENGTH_LONG).show();  
    }
}
