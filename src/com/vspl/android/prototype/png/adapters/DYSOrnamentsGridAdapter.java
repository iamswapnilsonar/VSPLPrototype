package com.vspl.android.prototype.png.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.vspl.android.prototype.png.R;
import com.vspl.android.prototype.png.dashboard.DYSOrnamentsGridItems;

public class DYSOrnamentsGridAdapter extends BaseAdapter {

	Context context;
	
	int images[] = { 	R.drawable.ic_product_one, R.drawable.ic_product_two, R.drawable.ic_product_three, 
			R.drawable.ic_product_four, R.drawable.ic_product_five, R.drawable.ic_product_one };

	public class ViewHolder {
		public ImageView imageView;
//		public TextView textTitle;
	}

	private DYSOrnamentsGridItems[] items;
	private LayoutInflater mInflater;

	public DYSOrnamentsGridAdapter(Context context, DYSOrnamentsGridItems[] locations) {

		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		items = locations;

	}

	public DYSOrnamentsGridItems[] getItems() {
		return items;
	}

	public void setItems(DYSOrnamentsGridItems[] items) {
		this.items = items;
	}

	@Override
	public int getCount() {
		if (items != null) {
			return items.length;
		}
		return 0;
	}

	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}

	@Override
	public Object getItem(int position) {
		if (items != null && position >= 0 && position < getCount()) {
			return items[position];
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		if (items != null && position >= 0 && position < getCount()) {
			return items[position].id;
		}
		return 0;
	}

	public void setItemsList(DYSOrnamentsGridItems[] locations) {
		this.items = locations;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		ViewHolder viewHolder;

		if (view == null) {

			view = mInflater.inflate(R.layout.custom, parent, false);
			viewHolder = new ViewHolder();
			
			viewHolder.imageView = (ImageView) view.findViewById(R.id.grid_item_image);
//			viewHolder.imageView.setBackgroundResource(R.color.sel_color);
//			viewHolder.textTitle = (TextView) view.findViewById(R.id.grid_item_label);
			
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		DYSOrnamentsGridItems gridItems = items[position];
		setCatImage(position, viewHolder, gridItems.title);
		return view;
	}

	private void setCatImage(int pos, ViewHolder viewHolder, String catTitle) {
		
		viewHolder.imageView.setImageResource(images[pos]);
//		viewHolder.textTitle.setText(catTitle);
		
	}
}
