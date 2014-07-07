package com.vspl.android.prototype.png.dashboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.vspl.android.prototype.png.R;
import com.vspl.android.prototype.png.adapters.DYSOrnamentsGridAdapter;
import com.vspl.android.prototype.png.adapters.SelectedOrnamentsListAdapter;
import com.vspl.android.prototype.png.utils.ConstantUtils;
import com.vspl.android.prototype.png.utils.MethodUtils;

@SuppressLint("ValidFragment")
public class DYSOrnamentsGridFragment extends Fragment {

	private GridView mGridView;
	private DYSOrnamentsGridAdapter mGridAdapter;
	private DYSOrnamentsGridItems[] gridItems = {};
	private Activity activity;

	public DYSOrnamentsGridFragment(){
		// Default constructor
	}
	
	public DYSOrnamentsGridFragment(DYSOrnamentsGridItems[] gridItems, Activity activity) {
		this.gridItems = gridItems;
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view;
		view = inflater.inflate(R.layout.gridview, container, false);
		mGridView = (GridView) view.findViewById(R.id.gridView);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		if (activity != null) {

			mGridAdapter = new DYSOrnamentsGridAdapter(activity, gridItems);
			if (mGridView != null) {
				mGridView.setAdapter(mGridAdapter);
			}

			mGridView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//					onGridItemClick((GridView) parent, view, position, id);
					
					DazzleYourselfScreenActivity.mapForSelectedOrnaments.put("temp", ConstantUtils.arr_grid_ornaments[position]);
					
					SelectedOrnamentsListAdapter.mKeys = MethodUtils.getKeysArrayFromLinkedHashMap(DazzleYourselfScreenActivity.mapForSelectedOrnaments);
					
					((DazzleYourselfScreenActivity)activity).showDazzledModelImage(DazzleYourselfScreenActivity.mapForSelectedOrnaments);
					
//					Logger.vLog(" onItemClick ", "Key : "+strKey+" Value : "+dataOfOrnamentList.get(position)+" Map Size :"+mapForSelectedOrnaments.size());
					
					DazzleYourselfScreenActivity.selectedListAdapter.notifyDataSetChanged();
				}
			});
		}
	}

	public void onGridItemClick(GridView g, View v, int position, long id) {
		
		Toast.makeText(activity, 
				"Position Clicked: - " + position + " & " + "Text is: - "+ gridItems[position].title, 
				Toast.LENGTH_LONG).show();
		
		Log.e("TAG", "POSITION CLICKED " + position);
	}
}
