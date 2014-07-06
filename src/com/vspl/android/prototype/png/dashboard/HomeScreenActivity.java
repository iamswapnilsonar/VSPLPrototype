package com.vspl.android.prototype.png.dashboard;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.vspl.android.prototype.png.R;
import com.vspl.android.prototype.png.logger.Logger;
import com.vspl.android.prototype.png.sliding_menu.BaseActivity;
import com.vspl.android.prototype.png.sliding_menu.SlidingMenu;
import com.vspl.android.prototype.png.utils.FontUtils;

public class HomeScreenActivity extends BaseActivity implements OnClickListener{

	public HomeScreenActivity() {
		super(R.string.home_activity);
		// TODO Auto-generated constructor stub
	}
	
	private TextView tvOrnamentTitle;
	
	@SuppressWarnings("unused")
	private ImageView btnShowSlidingMenu, btnSetting;
	private SlidingMenu sm;

	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private ViewFlipper mViewFlipper;	
	private AnimationListener mAnimationListener;
	private Context mContext;
	
	private Typeface typeface;
	
	@SuppressWarnings({ "deprecation", "unused" })
	private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setSlidingActionBarEnabled(true);
		
		setContentView(R.layout.home_screen_activity);
		initUI();		
	}


	private void initUI(){

		mContext = this;
		
		typeface = FontUtils.getHelveticaNeueCondensedBoldTypeface(mContext);
		
		btnShowSlidingMenu = (ImageView) findViewById(R.id.btn_show_sliding_menu);
		btnSetting = (ImageView) findViewById(R.id.btn_setting);
		
		btnShowSlidingMenu.setOnClickListener(this);
//		btnSetting.setOnClickListener(this);
		
		tvOrnamentTitle = (TextView) findViewById(R.id.tv_ornament_title);
		tvOrnamentTitle.setTypeface(typeface);
		
		// Sliding Menu..
		sm = getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT);
		sm.setShadowDrawable(R.drawable.shadow);
		
		// Ornaments details
		((TextView) findViewById(R.id.tv_collection_name)).setTypeface(typeface);
		((TextView) findViewById(R.id.tv_model_name)).setTypeface(typeface);
		((TextView) findViewById(R.id.tv_diamond_pieces)).setTypeface(typeface);
		((TextView) findViewById(R.id.tv_gold_weight)).setTypeface(typeface);
		((TextView) findViewById(R.id.tv_diamond_weight)).setTypeface(typeface);				
		
		mViewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper);

//		mViewFlipper.setOnTouchListener(new OnTouchListener() {
//			@Override
//			public boolean onTouch(final View view, final MotionEvent event) {
//				detector.onTouchEvent(event);
//				return true;
//			}
//		});
		
		@SuppressWarnings("unused")
		ViewPager pager = new ViewPager(mContext);
		
		//sets auto flipping
		mViewFlipper.setAutoStart(true);
		mViewFlipper.setFlipInterval(5000);
		mViewFlipper.startFlipping();
		// mViewFlipper.stopFlipping();
		
		mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
		mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
		
		mViewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
		      
			public void onAnimationStart(Animation animation) {
		    	
				//animation started event
				mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
				mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
				
				// controlling animation
//				mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
//				mViewFlipper.showNext();
				
//				int currentViewIndex = mViewFlipper.getDisplayedChild();
//		    	Logger.vLog("onAnimationStart", "Current Index : "+currentViewIndex);
//		    	tvOrnamentTitle.setText(ConstantUtils.arr_ornaments_titles[currentViewIndex]);
		      }
		      
			public void onAnimationRepeat(Animation animation) {
				Logger.vLog("onAnimationStart", "Current Index : "+mViewFlipper.getDisplayedChild());
			}
			
		    public void onAnimationEnd(Animation animation) {
		    	//TODO animation stopped event
		    	Logger.vLog("onAnimationStart", "Current Index : "+mViewFlipper.getDisplayedChild());
		    }
		    
		   });
		
		//animation listener
		mAnimationListener = new Animation.AnimationListener() {
			public void onAnimationStart(Animation animation) {
				//animation started event
				mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
				mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.left_out));
			}

			public void onAnimationRepeat(Animation animation) {
			}

			public void onAnimationEnd(Animation animation) {
				//TODO animation stopped event
			}
		};
		
		
	}


	class SwipeGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			try {
				// right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && 
						Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) 
				{
//					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
//					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_out));
					
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.left_out));
					
//					mViewFlipper.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.zoom_in));
					
					// controlling animation
					mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
					mViewFlipper.showNext();
					return true;
					
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE 
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) 
				{
//					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
//					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.left_out));
					
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_out));
					
//					mViewFlipper.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.zoom_in));
					// controlling animation
					mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
					mViewFlipper.showPrevious();
					return true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return false;
		}
	}
	
	@SuppressWarnings("unused")
	private void showTost(String msg){  
        Toast.makeText(HomeScreenActivity.this, msg, Toast.LENGTH_LONG).show();  
    }

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub

		int key = view.getId();

		switch (key) {
		case R.id.btn_show_sliding_menu:
			sm.toggle();			
			break;

		case R.id.btn_setting:

			//sets auto flipping
			mViewFlipper.setAutoStart(true);
			mViewFlipper.setFlipInterval(2000);
			mViewFlipper.startFlipping();

			break;
		}

	}
}


