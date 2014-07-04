package com.vspl.android.prototype.png;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

import com.vspl.android.prototype.png.utils.ConstantUtils;

public class PNGDiamondsTestingScreenActivity extends Activity implements OnClickListener {

	private Button btnPrevious, btnNext;
	private ImageView imgOrnament;
	private ImageSwitcher imageSwitcher;
	
	private int mArrLength;
	private int mIndex;
	
	private Context mContext;
	private LayoutInflater inflater=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_collection_gallery);
		
		mContext = this;
		
		btnPrevious = (Button) findViewById(R.id.btn_previous);
		btnNext = (Button) findViewById(R.id.btn_next);
		
		btnPrevious.setOnClickListener(this);
		btnNext.setOnClickListener(this);
		
		inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
		imageSwitcher.setFactory(new ViewFactory() {
			
			@Override
			public View makeView() {
				// TODO Auto-generated method stub
				return inflater.inflate(R.layout.imageview, null);
			}
		});
		
//		imgOrnament = (ImageView) findViewById(R.id.img_ornament);
		
		mArrLength = ConstantUtils.arr_collection_gallery.length;
		
		if (mIndex == 0) {
			// we want to disable these button..
			btnPrevious.setEnabled(false);
			btnNext.setEnabled(true);
		}
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		int key = view.getId();
		
		switch (key) {
			
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
			
//			Animation prev_slide_out_right = AnimationUtils.loadAnimation(this, R.anim.right_in);
//			Animation prev_slide_in_left = AnimationUtils.loadAnimation(this, R.anim.left_out);
			
			imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
			imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
			imageSwitcher.setImageResource(ConstantUtils.arr_collection_gallery[mIndex]);
			
			// search the first image from Gallery
			if (mIndex == 0) {
				// we want to disable these button..
				btnPrevious.setEnabled(false);
				btnNext.setEnabled(true);
			}

			if (mIndex > 0) {
				btnNext.setEnabled(true);
				btnPrevious.setEnabled(true);
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
			
			Animation next_slide_in_left = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
			Animation next_slide_out_right = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
			
//			imageSwitcher.setInAnimation(next_slide_in_left);
//			imageSwitcher.setOutAnimation(next_slide_out_right);
			
			imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
			imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
			
			imageSwitcher.setImageResource(ConstantUtils.arr_collection_gallery[mIndex]);
			
			// search the last image from Gallery
			if (mIndex == mArrLength - 1) {
				// we want to disable these button..
				btnNext.setEnabled(false);
				btnPrevious.setEnabled(true);
			}

			if (mIndex < mArrLength - 1) {
				btnNext.setEnabled(true);
				btnPrevious.setEnabled(true);
			}
			
			break;
		}
		
	}

}
