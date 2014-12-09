package com.vspl.android.prototype.png.dashboard;

import org.apache.commons.lang3.ArrayUtils;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher.ViewFactory;

import com.vspl.android.prototype.png.R;
import com.vspl.android.prototype.png.adapters.CGThumbnailListAdapter;
import com.vspl.android.prototype.png.logger.Logger;
import com.vspl.android.prototype.png.sliding_menu.BaseActivity;
import com.vspl.android.prototype.png.sliding_menu.SlidingMenu;
import com.vspl.android.prototype.png.ui.HorizontalListView;
import com.vspl.android.prototype.png.utils.ConstantUtils;
import com.vspl.android.prototype.png.utils.FontUtils;

public class CollectionGalleryScreenActivity extends BaseActivity implements OnClickListener {

	public CollectionGalleryScreenActivity() {
		super(R.string.collection_gallery_activity);
		// TODO Auto-generated constructor stub
	}
	
	private ImageView btnShowSlidingMenu;
	private SlidingMenu sm;
	
	private Button btnPrevious, btnNext;	
	private ImageSwitcher imageSwitcher;
	
	private HorizontalListView lvThumbnails;
	private CGThumbnailListAdapter thumbsListAdapter, collGalleryListAdapter, gridAdapter, gridSixAdapter;
	
	private ListView lvCollectionGallery;
	private GridView gridView, sixGridView;
	private Button btnSingleGrid, btnFourGrid, btnSixGrid;  
	private ViewFlipper viewFlipper;
	
	private int mArrLength;
	private int mIndex;
	
	private Context mContext;
	private LayoutInflater inflater=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// Override how this activity is animated into view
		// The new activity is pulled in from the left and the current activity is kept still
		// This has to be called before onCreate
		overridePendingTransition(R.anim.pull_in_from_right, R.anim.hold);
		
		setContentView(R.layout.collection_gallery_activity);
		
		mContext = this;
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
	
	private void initUI() {
		// TODO Auto-generated method stub
		
		viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
		viewFlipper.setDisplayedChild(0);
		
		btnShowSlidingMenu = (ImageView) findViewById(R.id.btn_show_sliding_menu);
		btnShowSlidingMenu.setOnClickListener(this);
		
		// Sliding Menu..
		sm = getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT);
		sm.setShadowDrawable(R.drawable.shadow);
		
		btnSingleGrid = (Button) findViewById(R.id.btn_single_grid);
		btnFourGrid = (Button) findViewById(R.id.btn_four_grid);
		btnSixGrid = (Button) findViewById(R.id.btn_six_grid);
		
		btnSingleGrid.setOnClickListener(this);
		btnFourGrid.setOnClickListener(this);
		btnSixGrid.setOnClickListener(this);		
		
		btnPrevious = (Button) findViewById(R.id.btn_previous);
		btnNext = (Button) findViewById(R.id.btn_next);
		
		btnPrevious.setOnClickListener(this);
		btnNext.setOnClickListener(this);
		
		((TextView) findViewById(R.id.tv_row_title)).setTypeface(
				FontUtils.getHelveticaNeueCondensedBoldTypeface(mContext));
		
		lvThumbnails = (HorizontalListView) findViewById(R.id.lv_coll_gallery_thumbs);
		
		Integer[] thumbsListData = ArrayUtils.toObject(ConstantUtils.arr_thumb_coll_gallery);
		thumbsListAdapter = new CGThumbnailListAdapter(mContext, thumbsListData, 0);
		
		lvThumbnails.setAdapter(thumbsListAdapter);
		
		lvCollectionGallery = (ListView) findViewById(R.id.lv_collection_gallery);
		Integer[] collectionGalleryListData = ArrayUtils.toObject(ConstantUtils.arr_coll_gallery);
		collGalleryListAdapter = new CGThumbnailListAdapter(mContext, collectionGalleryListData, 1);
		
		lvCollectionGallery.setAdapter(collGalleryListAdapter);
		
		gridView =(GridView) findViewById(R.id.gridView);
		Integer[] collectionGalleryGridData = ArrayUtils.toObject(ConstantUtils.arr_collection_gallery);
		gridAdapter = new CGThumbnailListAdapter(mContext, collectionGalleryGridData, 2);
		gridView.setAdapter(gridAdapter);
		
		sixGridView =(GridView) findViewById(R.id.sixGridView);
		gridSixAdapter = new CGThumbnailListAdapter(mContext, collectionGalleryGridData, 3);
		sixGridView.setAdapter(gridSixAdapter);
		
		inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
		imageSwitcher.setFactory(new ViewFactory() {
			
			@Override
			public View makeView() {
				// TODO Auto-generated method stub
				return inflater.inflate(R.layout.imageview, null);
			}
		});
		
		mArrLength = ConstantUtils.arr_collection_gallery.length;
		
		if (mIndex == 0) {
			// we want to disable these button..
			btnPrevious.setVisibility(View.INVISIBLE);
			btnNext.setVisibility(View.VISIBLE);
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
		
		case R.id.btn_single_grid:
			viewFlipper.setDisplayedChild(0);
			break;
		
		case R.id.btn_four_grid:
			viewFlipper.setDisplayedChild(1);
			break;
			
		case R.id.btn_six_grid:
			viewFlipper.setDisplayedChild(2);
			break;
			
		case R.id.btn_previous:
			
//			mIndex--;
//			
//			imgOrnament.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_in));
//			imgOrnament.setImageResource(ConstantUtils.arr_collection_gallery[mIndex]);
////			imgOrnament.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_in));
//			
//			// search the first image from Gallery
//			if (mIndex == 0) {
//				// we want to disable these button..
//				btnPrevious.setEnabled(false);
//				btnNext.setEnabled(true);
//			}
//
//			if (mIndex > 0) {
//				btnNext.setEnabled(true);
//				btnPrevious.setEnabled(true);
//			}
			
			
			mIndex--;
			
			imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
			imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
			
			Logger.vLog("Button Previous : ", "mIndex : "+mIndex);
			
			if (mIndex >= 0) {
				imageSwitcher.setImageResource(ConstantUtils.arr_collection_gallery[mIndex]);
			}
			
			// search the first image from Gallery
			if (mIndex == 0) {
				// we want to disable these button..
				btnPrevious.setVisibility(View.INVISIBLE);
				btnNext.setVisibility(View.VISIBLE);
			}

			if (mIndex > 0) {
				btnNext.setVisibility(View.VISIBLE);
				btnPrevious.setVisibility(View.VISIBLE);
			}
			
			break;
			
		case R.id.btn_next:
			
//			mIndex++;
//			imgOrnament.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_in));
//			imgOrnament.setImageResource(ConstantUtils.arr_collection_gallery[mIndex]);
////			imgOrnament.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_in));
//
//			// search the last image from Gallery
//			if (mIndex == mArrLength - 1) {
//				// we want to disable these button..
//				btnNext.setEnabled(false);
//				btnPrevious.setEnabled(true);
//			}
//
//			if (mIndex < mArrLength - 1) {
//				btnNext.setEnabled(true);
//				btnPrevious.setEnabled(true);
//			}
			
			
			mIndex++;
			
//			Animation next_slide_in_left = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
//			Animation next_slide_out_right = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
			
			imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
			imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
			
			Logger.vLog("Button Next : ", "mIndex : "+mIndex);
			
			if (mIndex < ConstantUtils.arr_collection_gallery.length) {
				imageSwitcher.setImageResource(ConstantUtils.arr_collection_gallery[mIndex]);
			}	
			
			// search the last image from Gallery
			if (mIndex == mArrLength - 1) {
				// we want to disable these button..
				btnNext.setVisibility(View.INVISIBLE);
				btnPrevious.setVisibility(View.VISIBLE);
			}

			if (mIndex < mArrLength - 1) {
				btnNext.setVisibility(View.VISIBLE);
				btnPrevious.setVisibility(View.VISIBLE);
			}
			
			break;
		}
		
	}

}
