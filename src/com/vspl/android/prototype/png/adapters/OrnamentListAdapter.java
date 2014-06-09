package com.vspl.android.prototype.png.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.vspl.android.prototype.png.R;

public class OrnamentListAdapter extends BaseAdapter {

	private Context context;
	private List<String> dataList;
	private LayoutInflater inflater=null;
	
	public OrnamentListAdapter(Context context, List<String> dataList) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.dataList = dataList;
		
		inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataList.size();
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
		
		if (rowView == null) {

			rowView = inflater.inflate(R.layout.sel_list_item, parent, false);
			
			// cache view fields into the holder
	        holder = new ViewHolder();
	        
			holder.img = (ImageView) rowView.findViewById(R.id.img);
			
			// associate the holder with the view for later lookup
	        rowView.setTag(holder);
		}else{
			// view already exists, get the holder instance from the view
	        holder = (ViewHolder) rowView.getTag();	
	        rowView = convertView;
		}	
	
		int ResId = context.getResources().getIdentifier(dataList.get(position), "drawable", context.getPackageName());
		holder.img.setBackgroundResource(ResId);
		
		return rowView;
	}
	
	// somewhere else in your class definition
	class ViewHolder {	    
		ImageView img;
	}
}
