package com.vspl.android.prototype.png.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vspl.android.prototype.png.R;
import com.vspl.android.prototype.png.utils.FontUtils;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

	private Activity context;
	String[] data = null;

	public CustomSpinnerAdapter(Activity context, int resource, String[] data2) {
		super(context, resource, data2);
		this.context = context;
		this.data = data2;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		((TextView) view).setTypeface(FontUtils.getHelveticaNeueTypeface(context));
		return view;
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		
		View row = convertView;
		if (row == null) {
			// inflate your customlayout for the textview
			LayoutInflater inflater = context.getLayoutInflater();
			row = inflater.inflate(R.layout.spinner_item, parent, false);
		}
		// put the data in it
		String item = data[position];
		if (item != null) {
			TextView text1 = (TextView) row.findViewById(R.id.spinner_dp_text);
			text1.setTypeface(FontUtils.getHelveticaNeueTypeface(context));
			text1.setText(item);
		}

		return row;
	}

}
