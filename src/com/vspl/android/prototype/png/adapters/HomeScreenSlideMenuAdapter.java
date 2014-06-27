package com.vspl.android.prototype.png.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vspl.android.prototype.png.R;
import com.vspl.android.prototype.png.logger.Logger;
import com.vspl.android.prototype.png.utils.ConstantUtils;
import com.vspl.android.prototype.png.utils.FontUtils;

public class HomeScreenSlideMenuAdapter extends BaseAdapter{

	private Context context;
	private LayoutInflater inflater=null;
	
	private List<String> listSlideMenuOptions;
	 
	public HomeScreenSlideMenuAdapter(Context context, List<String> listSlideMenuOptions) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.listSlideMenuOptions = listSlideMenuOptions;
		
		inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);			
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listSlideMenuOptions.size();
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

			rowView = inflater.inflate(R.layout.row, parent, false);
			
			// cache view fields into the holder
	        holder = new ViewHolder();
	        
			holder.img_slidemenu_item = (ImageView) rowView.findViewById(R.id.row_icon);
			
			holder.tv_slidemenu_item = (TextView) rowView.findViewById(R.id.row_title);
			holder.tv_slidemenu_item.setTypeface(FontUtils.getHelveticaNeueCondensedBoldTypeface(context));
			
			// associate the holder with the view for later lookup
	        rowView.setTag(holder);
		}else{
			// view already exists, get the holder instance from the view
	        holder = (ViewHolder) rowView.getTag();	
	        rowView = convertView;
		}	
		
		Logger.vLog("getView : ", "String Value : "+listSlideMenuOptions.get(position));
		
//		int mStrResorceID = context.getResources().getIdentifier(mNameofResource, "strings", context.getPackageName());
		holder.img_slidemenu_item.setImageResource(ConstantUtils.arr_slidemenu_icons[position]);
		holder.tv_slidemenu_item.setText(listSlideMenuOptions.get(position));
		
		return rowView;
	}
	
	// somewhere else in your class definition
	class ViewHolder {	    
		ImageView img_slidemenu_item;
		TextView tv_slidemenu_item;
	}

	
}
