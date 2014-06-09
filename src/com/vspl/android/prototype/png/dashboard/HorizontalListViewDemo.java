package com.vspl.android.prototype.png.dashboard;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.vspl.android.prototype.png.R;
import com.vspl.android.prototype.png.adapters.CustomSpinnerAdapter;
import com.vspl.android.prototype.png.adapters.OrnamentListAdapter;
import com.vspl.android.prototype.png.ui.HorizontalListView;
import com.vspl.android.prototype.png.utils.MethodUtils;

public class HorizontalListViewDemo extends Activity {
	
	private HorizontalListView listView, listViewSelectedItems;
	private List<String> dataList, selItemsList;
	
	private OrnamentListAdapter adapter;
	private OrnamentListAdapter selectedListAdapter;
	
	private ArrayAdapter<String> arrayAdapter;
	private Spinner spinner;
	private String path[] = {"Earrings", "Necklaces"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.listviewdemo);

		initUI();
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				selItemsList.add(dataList.get(position));
				selectedListAdapter.notifyDataSetChanged();
			}
			
		});
		
		listViewSelectedItems.setOnItemLongClickListener(new OnItemLongClickListener() {

	        @Override
	        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
	        	view.findViewById(R.id.btn_remove).setVisibility(View.VISIBLE);
	            return false;
	        }			
	    });
		
	}

	@SuppressLint("DefaultLocale")
	private void initUI() {
		// TODO Auto-generated method stub
		dataList = new ArrayList<String>();
		selItemsList = new ArrayList<String>();
		
		dataList.clear();
        dataList.addAll(MethodUtils.getArrayList(HorizontalListViewDemo.this, path[0].toLowerCase()));

		spinner = (Spinner) findViewById(R.id.spinner);
		
		arrayAdapter = new CustomSpinnerAdapter(
	            HorizontalListViewDemo.this, android.R.layout.simple_spinner_item,
	            path);
		
//		arrayAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, path);
		
		spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                String mStr = spinner.getItemAtPosition(position).toString();
                
                dataList.clear();
                dataList.addAll(MethodUtils.getArrayList(HorizontalListViewDemo.this, mStr.toLowerCase()));
                adapter.notifyDataSetChanged();
                
        		((TextView) spinner.getSelectedView()).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
		
		listView = (HorizontalListView) findViewById(R.id.listview);
		adapter = new OrnamentListAdapter(HorizontalListViewDemo.this, dataList);		
		listView.setAdapter(adapter);
		
		listViewSelectedItems = (HorizontalListView) findViewById(R.id.list_sel_items);
		selectedListAdapter = new OrnamentListAdapter(HorizontalListViewDemo.this, selItemsList);		
		listViewSelectedItems.setAdapter(selectedListAdapter);
	}

	@SuppressWarnings("unused")
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			final int position = listViewSelectedItems.getPositionForView((View) view.getParent());
			selItemsList.remove(position);
			selectedListAdapter.notifyDataSetChanged();
		}
	};

}
