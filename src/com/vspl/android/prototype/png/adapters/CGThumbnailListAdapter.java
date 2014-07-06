package com.vspl.android.prototype.png.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.vspl.android.prototype.png.R;

public class CGThumbnailListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater=null;
	
	private Integer[] arrThumbnails;
	
	// 0 => Collection Gallery Thumbnail listview
	// 1 => Collection Gallery vertical listview
	private int whichListView;
	
	public CGThumbnailListAdapter(Context context, Integer[] arrThumbnails, int whichListView) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.arrThumbnails = arrThumbnails;
		this.whichListView = whichListView;
		
		inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrThumbnails.length;
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

			if (whichListView == 0) {
				rowView = inflater.inflate(R.layout.thumb_list_item, parent, false);
			}else if(whichListView == 1){
				rowView = inflater.inflate(R.layout.coll_gallery_list_item, parent, false);
			}else if(whichListView == 2){
				rowView = inflater.inflate(R.layout.coll_gallery_grid_item, parent, false);
			}else{
				rowView = inflater.inflate(R.layout.coll_gallery_six_grid_item, parent, false);
			}
			
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
	
		// int ResId = context.getResources().getIdentifier(map.get(strKey), "drawable", context.getPackageName());
		holder.img.setImageResource(arrThumbnails[position]);
		
		return rowView;
	}
	
	// somewhere else in your class definition
	class ViewHolder {	    
		ImageView img;
	}
}
