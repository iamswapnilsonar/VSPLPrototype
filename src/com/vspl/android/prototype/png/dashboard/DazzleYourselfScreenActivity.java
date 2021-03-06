package com.vspl.android.prototype.png.dashboard;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.PageIndicator;
import com.vspl.android.prototype.png.R;
import com.vspl.android.prototype.png.adapters.SelectedOrnamentsListAdapter;
import com.vspl.android.prototype.png.gesturedetectors.MoveGestureDetector;
import com.vspl.android.prototype.png.gesturedetectors.RotateGestureDetector;
import com.vspl.android.prototype.png.gesturedetectors.ShoveGestureDetector;
import com.vspl.android.prototype.png.logger.Logger;
import com.vspl.android.prototype.png.sliding_menu.BaseActivity;
import com.vspl.android.prototype.png.sliding_menu.SlidingMenu;
import com.vspl.android.prototype.png.ui.HorizontalListView;
import com.vspl.android.prototype.png.utils.FontUtils;
import com.vspl.android.prototype.png.utils.MethodUtils;

public class DazzleYourselfScreenActivity extends BaseActivity implements OnClickListener, OnTouchListener{

	public DazzleYourselfScreenActivity() {
		super(R.string.dazzel_yourself_activity);
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
	
	private String deviceNames_1[] = { "A", "B", "C", "D", "E", "F"};
	private String deviceNames_2[] = { "AB", "BC", "CD", "DE", "EF", "FG"};
	private String deviceNames_3[] = { "ABC", "BCD", "CDE", "DEF", "EFG", "FGH"};
	private String deviceNames_4[] = { "ABCD", "BCDE", "CDEF", "DEFG", "EFGH", "FGHI"};

	private List<String[]> listOfDevicesArray;
	
	// ---- Image transformation section ----//
	private ImageView view; 
	
	@SuppressWarnings("unused")
	private FrameLayout frameLayout;		
	
	private Matrix mMatrix = new Matrix();
    private float mScaleFactor = .4f;
    private float mRotationDegrees = 0.f;
    private float mFocusX = 0.f;
    private float mFocusY = 0.f;  
    private int mAlpha = 255;
    private int mImageHeight, mImageWidth;

    private ScaleGestureDetector mScaleDetector;
    private RotateGestureDetector mRotateDetector;
    private MoveGestureDetector mMoveDetector;
    private ShoveGestureDetector mShoveDetector; 
	// ---- End Image transformation section ----//
    
    // ---- Horizontal text menubar ----//
    private Typeface typeface;
    private TextView tvRings, tvEarRings, tvNecklaces, tvMangalsutra;
	@SuppressWarnings("unused")
	private View vFirstSecond, vSecondThird, vThirdFourth;
    // ---- End Horizontal text menubar ----//    
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Override how this activity is animated into view
		// The new activity is pulled in from the left and the current activity is kept still
		// This has to be called before onCreate
		overridePendingTransition(R.anim.pull_in_from_right, R.anim.hold);
		
		setContentView(R.layout.new_dazzle_screen_activity);

		initUI();
	}

	@Override
    protected void onPause() {
		// Whenever this activity is paused (i.e. looses focus because another activity is started etc)
		// Override how this activity is animated out of view
		// The new activity is kept still and this activity is pushed out to the left
        overridePendingTransition(R.anim.hold, R.anim.push_out_to_right);
        super.onPause();
    }
	
	@SuppressWarnings("deprecation")
	private void initUI() {
		// TODO Auto-generated method stub
		mContext = DazzleYourselfScreenActivity.this;
		
		typeface = FontUtils.getVerdanaTypeface(mContext);
		
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

		List<DYSOrnamentsGridFragment> gridFragments = new ArrayList<DYSOrnamentsGridFragment>();
		Log.v("MainActivity : ", "DeviceNames Size : "+arrDeviceNames.size());

		for (int j = 0; j < listOfDevicesArray.size(); j++) {

			ArrayList<DYSOrnamentsGridItems> gridItemList = new ArrayList<DYSOrnamentsGridItems>();
			String[] arrDevicesNames = listOfDevicesArray.get(j);

			for (int k = 0; k < arrDevicesNames.length; k++) {				
				DYSOrnamentsGridItems gridItem = new DYSOrnamentsGridItems(k, arrDevicesNames[k]);
				gridItemList.add(gridItem);
			}		

			DYSOrnamentsGridItems[] gp = {};
			DYSOrnamentsGridItems[] gridPage = gridItemList.toArray(gp);
			gridFragments.add(new DYSOrnamentsGridFragment(gridPage, DazzleYourselfScreenActivity.this));
		}

		pm = new PagerAdapter(getSupportFragmentManager(), gridFragments);
		awesomePager.setAdapter(pm);
		mIndicator.setViewPager(awesomePager);
		
		lvSelectedOrnaments = (HorizontalListView) findViewById(R.id.list_sel_items);
		selectedListAdapter = new SelectedOrnamentsListAdapter(mContext, mapForSelectedOrnaments, listener);		
		lvSelectedOrnaments.setAdapter(selectedListAdapter);
		
		// Determine the center of the screen to center 'earth'
		Display display = getWindowManager().getDefaultDisplay();
		mFocusX = display.getWidth()/4f;
		mFocusY = display.getHeight()/4f;
		
		// FrameLayout for model image
		frameLayout = (FrameLayout) findViewById(R.id.frmlayout_img_mdl);
		
		// Set this class as touchListener to the ImageView
		view = (ImageView) findViewById(R.id.imageView);
		view.setOnTouchListener(this);
		
//		ViewTreeObserver vto = view.getViewTreeObserver();
//		vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//		    public boolean onPreDraw() {
//		    	view.getViewTreeObserver().removeOnPreDrawListener(this);
//		        finalHeight = view.getMeasuredHeight();
//		        finalWidth = view.getMeasuredWidth();
//		        tv.setText("Height: " + finalHeight + " Width: " + finalWidth);
//		        return true;
//		    }
//		});
		
//		ViewTreeObserver vto = frameLayout.getViewTreeObserver();
//		vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//		    public boolean onPreDraw() {
//		    	
//		    	frameLayout.getViewTreeObserver().removeOnPreDrawListener(this);
//		        finalHeight = frameLayout.getMeasuredHeight()/2f;
//		        finalWidth = frameLayout.getMeasuredWidth()/2f;
//		       
//		        return true;
//		    }
//		});
		
//		mFocusX = finalWidth;
//		mFocusY = finalHeight;
		
		Logger.vLog("initUI", "mFocusX : "+mFocusX +" mFocusY : "+mFocusY);
		
		// Determine dimensions of 'earth' image
		Drawable d 		= this.getResources().getDrawable(R.drawable.center_one);
		mImageHeight 	= d.getIntrinsicHeight();
		mImageWidth 	= d.getIntrinsicWidth();

		// View is scaled and translated by matrix, so scale and translate initially
		float scaledImageCenterX = (mImageWidth*mScaleFactor)/2; 
		float scaledImageCenterY = (mImageHeight*mScaleFactor)/2;

		mMatrix.postScale(mScaleFactor, mScaleFactor);
		mMatrix.postTranslate(mFocusX - scaledImageCenterX, mFocusY - scaledImageCenterY);
		view.setImageMatrix(mMatrix);

		// Setup Gesture Detectors
		mScaleDetector 	= new ScaleGestureDetector(getApplicationContext(), new ScaleListener());
		mRotateDetector = new RotateGestureDetector(getApplicationContext(), new RotateListener());
		mMoveDetector 	= new MoveGestureDetector(getApplicationContext(), new MoveListener());
		mShoveDetector 	= new ShoveGestureDetector(getApplicationContext(), new ShoveListener());
		
		vFirstSecond = (View) findViewById(R.id.vFirstSecond);
		vSecondThird = (View) findViewById(R.id.vSecondThird);
		vThirdFourth = (View) findViewById(R.id.vThirdFourth);
//		vFirstSecond.setVisibility(View.INVISIBLE);
		
		tvRings = (TextView) findViewById(R.id.tvRings);
		tvEarRings = (TextView) findViewById(R.id.tvEarRings);
		tvNecklaces = (TextView) findViewById(R.id.tvNecklaces);
		tvMangalsutra = (TextView) findViewById(R.id.tvMangalsutra);
		
		tvRings.setTypeface(typeface);
		tvEarRings.setTypeface(typeface);
		tvNecklaces.setTypeface(typeface);
		tvMangalsutra.setTypeface(typeface);
				
		tvRings.setOnClickListener(this);
		tvEarRings.setOnClickListener(this);
		tvNecklaces.setOnClickListener(this);
		tvMangalsutra.setOnClickListener(this);	
		
		setTvEarRingsBackgroundColor();
	}

	public void showDazzledModelImage(LinkedHashMap<String, String> map) {
		// TODO Auto-generated method stub
		
		String mImageName = null;
		String strOrnamentType = map.get("temp");

		if (strOrnamentType != null) {
			mImageName = strOrnamentType.concat("_center");
		}		
			
		Logger.vLog("showDazzledModelImage", "Resource Name : "+mImageName);
		int ResId = getResources().getIdentifier(mImageName, "drawable", getPackageName());
		
		if (ResId != 0) {
			view.setImageResource(ResId);
		}else{
			Toast.makeText(mContext, "No resource found for "+mImageName, Toast.LENGTH_SHORT).show();
		}
		
	}
	
	public void removeOrnamentsFromModelImage(){
		view.setImageResource(0);
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
			
			// Remove ornaments from model
			removeOrnamentsFromModelImage();
		}
	};
	
	private class PagerAdapter extends FragmentStatePagerAdapter {
		private List<DYSOrnamentsGridFragment> fragments;

		public PagerAdapter(FragmentManager fm, List<DYSOrnamentsGridFragment> fragments) {
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
			
		case R.id.tvRings:
			setTvRingsBackgroundColor();
			break;

		case R.id.tvEarRings:
			setTvEarRingsBackgroundColor();
			break;
			
		case R.id.tvNecklaces:
			setTvNecklacesBackgroundColor();
			break;
			
		case R.id.tvMangalsutra:
			setTvMangalsutraBackgroundColor();
			break;
			
		}

	}
	
	public void setTvRingsBackgroundColor(){
				
//		tvRings.setBackgroundColor(getResources().getColor(R.color.sel_color));
		tvRings.setBackgroundColor(Color.TRANSPARENT);
		tvRings.setTextColor(getResources().getColor(R.color.text_color_png_logo));
		tvRings.setTypeface(tvRings.getTypeface(), Typeface.BOLD);
//		tvRings.setTextSize( TypedValue.COMPLEX_UNIT_PX, 
//				mContext.getResources().getDimensionPixelSize(R.dimen.style_tv_lin_text_menubar_selected));
		
		
		tvEarRings.setBackgroundColor(Color.TRANSPARENT);
		tvEarRings.setTextColor(getResources().getColor(R.color.normal_text_color));
		tvEarRings.setTypeface(tvEarRings.getTypeface(), Typeface.NORMAL);
//		tvEarRings.setTextSize( TypedValue.COMPLEX_UNIT_PX, 
//				mContext.getResources().getDimensionPixelSize(R.dimen.style_tv_lin_text_menubar));
		
		tvNecklaces.setBackgroundColor(Color.TRANSPARENT);
		tvNecklaces.setTextColor(getResources().getColor(R.color.normal_text_color));
		tvNecklaces.setTypeface(tvNecklaces.getTypeface(), Typeface.NORMAL);
//		tvNecklaces.setTextSize( TypedValue.COMPLEX_UNIT_PX, 
//				mContext.getResources().getDimensionPixelSize(R.dimen.style_tv_lin_text_menubar));
		
		tvMangalsutra.setBackgroundColor(Color.TRANSPARENT);
		tvMangalsutra.setTextColor(getResources().getColor(R.color.normal_text_color));
		tvMangalsutra.setTypeface(tvMangalsutra.getTypeface(), Typeface.NORMAL);
//		tvMangalsutra.setTextSize( TypedValue.COMPLEX_UNIT_PX, 
//				mContext.getResources().getDimensionPixelSize(R.dimen.style_tv_lin_text_menubar));
		
//		vFirstSecond.setVisibility(View.INVISIBLE);
//		vSecondThird.setVisibility(View.VISIBLE);
//		vThirdFourth.setVisibility(View.VISIBLE);
	}

	public void setTvEarRingsBackgroundColor(){
		
		tvRings.setBackgroundColor(Color.TRANSPARENT);	
		tvRings.setTextColor(getResources().getColor(R.color.normal_text_color));
		tvRings.setTypeface(tvRings.getTypeface(), Typeface.NORMAL);
//		tvRings.setTextSize( TypedValue.COMPLEX_UNIT_PX, 
//				mContext.getResources().getDimensionPixelSize(R.dimen.style_tv_lin_text_menubar));
		
//		tvEarRings.setBackgroundColor(getResources().getColor(R.color.sel_color));
		tvEarRings.setBackgroundColor(Color.TRANSPARENT);
		tvEarRings.setTextColor(getResources().getColor(R.color.text_color_png_logo));
		tvEarRings.setTypeface(tvEarRings.getTypeface(), Typeface.BOLD);
//		tvEarRings.setTextSize( TypedValue.COMPLEX_UNIT_PX, 
//				mContext.getResources().getDimensionPixelSize(R.dimen.style_tv_lin_text_menubar_selected));
		
		tvNecklaces.setBackgroundColor(Color.TRANSPARENT);
		tvNecklaces.setTextColor(getResources().getColor(R.color.normal_text_color));
		tvNecklaces.setTypeface(tvNecklaces.getTypeface(), Typeface.NORMAL);
//		tvNecklaces.setTextSize( TypedValue.COMPLEX_UNIT_PX, 
//				mContext.getResources().getDimensionPixelSize(R.dimen.style_tv_lin_text_menubar));
		
		tvMangalsutra.setBackgroundColor(Color.TRANSPARENT);
		tvMangalsutra.setTextColor(getResources().getColor(R.color.normal_text_color));
		tvMangalsutra.setTypeface(tvMangalsutra.getTypeface(), Typeface.NORMAL);
//		tvMangalsutra.setTextSize( TypedValue.COMPLEX_UNIT_PX, 
//				mContext.getResources().getDimensionPixelSize(R.dimen.style_tv_lin_text_menubar));
		
//		vFirstSecond.setVisibility(View.INVISIBLE);
//		vSecondThird.setVisibility(View.INVISIBLE);
//		vThirdFourth.setVisibility(View.VISIBLE);
	}

	public void setTvNecklacesBackgroundColor(){
		
		tvRings.setBackgroundColor(Color.TRANSPARENT);
		tvRings.setTextColor(getResources().getColor(R.color.normal_text_color));
		tvRings.setTypeface(tvRings.getTypeface(), Typeface.NORMAL);
//		tvRings.setTextSize( TypedValue.COMPLEX_UNIT_PX, 
//				mContext.getResources().getDimensionPixelSize(R.dimen.style_tv_lin_text_menubar));
		
		tvEarRings.setBackgroundColor(Color.TRANSPARENT);
		tvEarRings.setTextColor(getResources().getColor(R.color.normal_text_color));
		tvEarRings.setTypeface(tvEarRings.getTypeface(), Typeface.NORMAL);
//		tvEarRings.setTextSize( TypedValue.COMPLEX_UNIT_PX, 
//				mContext.getResources().getDimensionPixelSize(R.dimen.style_tv_lin_text_menubar));
				
//		tvNecklaces.setBackgroundColor(getResources().getColor(R.color.sel_color));
		tvNecklaces.setBackgroundColor(Color.TRANSPARENT);
		tvNecklaces.setTextColor(getResources().getColor(R.color.text_color_png_logo));
		tvNecklaces.setTypeface(tvNecklaces.getTypeface(), Typeface.BOLD);
//		tvNecklaces.setTextSize( TypedValue.COMPLEX_UNIT_PX, 
//				mContext.getResources().getDimensionPixelSize(R.dimen.style_tv_lin_text_menubar_selected));
		
		
		tvMangalsutra.setBackgroundColor(Color.TRANSPARENT);
		tvMangalsutra.setTextColor(getResources().getColor(R.color.normal_text_color));
		tvMangalsutra.setTypeface(tvMangalsutra.getTypeface(), Typeface.NORMAL);
//		tvMangalsutra.setTextSize( TypedValue.COMPLEX_UNIT_PX, 
//				mContext.getResources().getDimensionPixelSize(R.dimen.style_tv_lin_text_menubar));
		
//		vFirstSecond.setVisibility(View.VISIBLE);
//		vSecondThird.setVisibility(View.INVISIBLE);
//		vThirdFourth.setVisibility(View.INVISIBLE);
	}

	public void setTvMangalsutraBackgroundColor(){
		
		tvRings.setBackgroundColor(Color.TRANSPARENT);
		tvRings.setTextColor(getResources().getColor(R.color.normal_text_color));
		tvRings.setTypeface(tvRings.getTypeface(), Typeface.NORMAL);
//		tvRings.setTextSize( TypedValue.COMPLEX_UNIT_PX, 
//				mContext.getResources().getDimensionPixelSize(R.dimen.style_tv_lin_text_menubar));
		
		tvEarRings.setBackgroundColor(Color.TRANSPARENT);
		tvEarRings.setTextColor(getResources().getColor(R.color.normal_text_color));
		tvEarRings.setTypeface(tvEarRings.getTypeface(), Typeface.NORMAL);
//		tvEarRings.setTextSize( TypedValue.COMPLEX_UNIT_PX, 
//				mContext.getResources().getDimensionPixelSize(R.dimen.style_tv_lin_text_menubar));
		
		tvNecklaces.setBackgroundColor(Color.TRANSPARENT);
		tvNecklaces.setTextColor(getResources().getColor(R.color.normal_text_color));
		tvNecklaces.setTypeface(tvNecklaces.getTypeface(), Typeface.NORMAL);
//		tvNecklaces.setTextSize( TypedValue.COMPLEX_UNIT_PX, 
//				mContext.getResources().getDimensionPixelSize(R.dimen.style_tv_lin_text_menubar));
				
//		tvMangalsutra.setBackgroundColor(getResources().getColor(R.color.sel_color));
		tvMangalsutra.setBackgroundColor(Color.TRANSPARENT);
		tvMangalsutra.setTextColor(getResources().getColor(R.color.text_color_png_logo));
		tvMangalsutra.setTypeface(tvMangalsutra.getTypeface(), Typeface.BOLD);
//		tvMangalsutra.setTextSize( TypedValue.COMPLEX_UNIT_PX, 
//				mContext.getResources().getDimensionPixelSize(R.dimen.style_tv_lin_text_menubar_selected));
		
		
//		vFirstSecond.setVisibility(View.VISIBLE);
//		vSecondThird.setVisibility(View.VISIBLE);
//		vThirdFourth.setVisibility(View.INVISIBLE);
	}
	
	@SuppressWarnings("deprecation")
	public boolean onTouch(View v, MotionEvent event) {
        mScaleDetector.onTouchEvent(event);
        mRotateDetector.onTouchEvent(event);
        mMoveDetector.onTouchEvent(event);
        mShoveDetector.onTouchEvent(event);

        float scaledImageCenterX = (mImageWidth*mScaleFactor)/2;
        float scaledImageCenterY = (mImageHeight*mScaleFactor)/2;
        
        mMatrix.reset();
        mMatrix.postScale(mScaleFactor, mScaleFactor);
        mMatrix.postRotate(mRotationDegrees,  scaledImageCenterX, scaledImageCenterY);
        mMatrix.postTranslate(mFocusX - scaledImageCenterX, mFocusY - scaledImageCenterY);
        
		ImageView view = (ImageView) v;
		view.setImageMatrix(mMatrix);
		view.setAlpha(mAlpha);

		return true; // indicate event was handled
	}

	private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			mScaleFactor *= detector.getScaleFactor(); // scale change since previous event
			
			// Don't let the object get too small or too large.
			mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f)); 

			return true;
		}
	}
	
	private class RotateListener extends RotateGestureDetector.SimpleOnRotateGestureListener {
		@Override
		public boolean onRotate(RotateGestureDetector detector) {
			mRotationDegrees -= detector.getRotationDegreesDelta();
			return true;
		}
	}	
	
	private class MoveListener extends MoveGestureDetector.SimpleOnMoveGestureListener {
		@Override
		public boolean onMove(MoveGestureDetector detector) {
			PointF d = detector.getFocusDelta();
			mFocusX += d.x;
			mFocusY += d.y;		

			// mFocusX = detector.getFocusX();
			// mFocusY = detector.getFocusY();
			return true;
		}
	}		
	
	private class ShoveListener extends ShoveGestureDetector.SimpleOnShoveGestureListener {
		@Override
		public boolean onShove(ShoveGestureDetector detector) {
			mAlpha += detector.getShovePixelsDelta();
			if (mAlpha > 255)
				mAlpha = 255;
			else if (mAlpha < 0)
				mAlpha = 0;
			
			return true;
		}
	}
}