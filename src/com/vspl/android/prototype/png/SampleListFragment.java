package com.vspl.android.prototype.png;

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

import com.vspl.android.prototype.png.adapters.HomeScreenSlideMenuAdapter;
import com.vspl.android.prototype.png.utils.ConstantUtils;
import com.vspl.android.prototype.png.utils.FontUtils;

public class SampleListFragment extends Fragment {
	
	private List<String> mArrayList;		
	private List<String> mResMutableList = null;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.sliding_menu, container, false);
		
		ListView listView = (ListView) view.findViewById(R.id.list_sliding_menu);  
		
		((TextView) view.findViewById(R.id.row_title)).setTypeface(
				FontUtils.getHelveticaNeueCondensedBoldTypeface(getActivity()));
		
		// returns immutable string converts to MUTABLE
		mArrayList = Arrays.asList(ConstantUtils.arr_slidemenu);
		mResMutableList = new ArrayList<String>(mArrayList);
		
		HomeScreenSlideMenuAdapter adapter = new HomeScreenSlideMenuAdapter(getActivity(), mResMutableList);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
//				showTost("Position : "+position);
				startActivity(new Intent(getActivity(), MainActivity.class));
			}
			
		});
		
		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	private void showTost(String msg){  
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();  
    }
}
