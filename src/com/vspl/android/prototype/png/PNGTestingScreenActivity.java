package com.vspl.android.prototype.png;

import org.apache.commons.lang3.ArrayUtils;

import com.vspl.android.prototype.png.adapters.CGThumbnailListAdapter;
import com.vspl.android.prototype.png.utils.ConstantUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;

public class PNGTestingScreenActivity extends Activity {

	private GridView gridView;
	private CGThumbnailListAdapter gridAdapter;
	
	private Context mContext; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.temp);

		mContext = this;
		
		gridView =(GridView) findViewById(R.id.gridView);
		
		Integer[] thumbsListData = ArrayUtils.toObject(ConstantUtils.arr_collection_gallery);
		gridAdapter = new CGThumbnailListAdapter(mContext, thumbsListData, 2);
		gridView.setAdapter(gridAdapter);
	}

}
