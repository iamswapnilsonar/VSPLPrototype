package com.vspl.android.prototype.png.sliding_menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vspl.android.prototype.png.R;
import com.vspl.android.prototype.png.adapters.SlideMenuAdapter;
import com.vspl.android.prototype.png.dashboard.CollectionGalleryScreenActivity;
import com.vspl.android.prototype.png.dashboard.DazzleYourselfScreenActivity;
import com.vspl.android.prototype.png.dashboard.HomeScreenActivity;
import com.vspl.android.prototype.png.dashboard.KnowledgeBaseScreenActivity;
import com.vspl.android.prototype.png.dashboard.ProductGalleryScreenActivity;
import com.vspl.android.prototype.png.utils.ConstantUtils;
import com.vspl.android.prototype.png.utils.FontUtils;

public class SlidingMenuFragment extends Fragment {
	
	private List<String> mArrayList;		
	private List<String> mResMutableList = null;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.sliding_menu_content, container, false);
		
		ListView listView = (ListView) view.findViewById(R.id.list_sliding_menu);  
		
		((TextView) view.findViewById(R.id.row_title)).setTypeface(
				FontUtils.getHelveticaNeueCondensedBoldTypeface(getActivity()));
		
		// returns immutable string converts to MUTABLE
		mArrayList = Arrays.asList(ConstantUtils.arr_slidemenu);
		mResMutableList = new ArrayList<String>(mArrayList);
		
		SlideMenuAdapter adapter = new SlideMenuAdapter(getActivity(), mResMutableList);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Class<?> cls = null;
				String activity_title = ConstantUtils.arr_slidemenu[position];
				
				if (activity_title.equals("Home")) {
					cls = HomeScreenActivity.class;	
				} else if (activity_title.equals("Dazzel Yourself")) {
					cls = DazzleYourselfScreenActivity.class;
				} else if (activity_title.equals("Product Gallery")){
					cls = ProductGalleryScreenActivity.class;	
				} else if (activity_title.equals("Collection Gallery")){
					cls = CollectionGalleryScreenActivity.class;
				} else if (activity_title.equals("Knowledge Base")){
					cls = KnowledgeBaseScreenActivity.class;
				}
				
				if (cls != null) {
					Intent intent = new Intent(getActivity(), cls);
					startActivity(intent);
				}
				
				// close opened slidemenu before leave to activity 
				((BaseActivity)getActivity()).closeSlideMenu();
			}
			
		});
		
		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@SuppressWarnings("unused")
	private void showTost(String msg){  
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();  
    }
}
