package com.vspl.android.prototype.png.adapters;

import java.util.LinkedHashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.vspl.android.prototype.png.R;
import com.vspl.android.prototype.png.utils.MethodUtils;

public class SelectedOrnamentsListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater=null;
	private OnClickListener listener;
	
	private LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
	public static String[] mKeys;
	 
	public SelectedOrnamentsListAdapter(Context context, LinkedHashMap<String, String> map, OnClickListener listener) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.listener = listener;
		this.map = map;
		
		mKeys = MethodUtils.getKeysArrayFromLinkedHashMap(map);
		
		inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mKeys.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View rowView = convertView;
		ViewHolder holder; // to reference the child views for later actions
		
		String strKey = mKeys[position];
		
		if (rowView == null) {

			rowView = inflater.inflate(R.layout.sel_list_item, parent, false);
			
			// cache view fields into the holder
	        holder = new ViewHolder();
	        
			holder.img = (ImageView) rowView.findViewById(R.id.img);
			holder.btn_close = (ImageView) rowView.findViewById(R.id.btn_remove);
			
			holder.btn_close.setVisibility(View.VISIBLE);
			holder.btn_close.setOnClickListener(listener);
			
			// associate the holder with the view for later lookup
	        rowView.setTag(holder);
		}else{
			// view already exists, get the holder instance from the view
	        holder = (ViewHolder) rowView.getTag();	
	        rowView = convertView;
		}	
	
		int ResId = context.getResources().getIdentifier(map.get(strKey), "drawable", context.getPackageName());
		holder.img.setBackgroundResource(ResId);
		
		return rowView;
	}
	
	// somewhere else in your class definition
	class ViewHolder {	    
		ImageView img;
		ImageView btn_close;
	}
}
