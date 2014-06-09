package com.vspl.android.prototype.png.dashboard;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vspl.android.prototype.png.R;
import com.vspl.android.prototype.png.adapters.CustomSpinnerAdapter;
import com.vspl.android.prototype.png.adapters.OrnamentListAdapter;
import com.vspl.android.prototype.png.adapters.SelectedOrnamentsListAdapter;
import com.vspl.android.prototype.png.logger.Logger;
import com.vspl.android.prototype.png.ui.HorizontalListView;
import com.vspl.android.prototype.png.utils.ConstantUtils;
import com.vspl.android.prototype.png.utils.FontUtils;
import com.vspl.android.prototype.png.utils.MethodUtils;

/**
 * This is Dazzle screen activity which have complete functionality of 
 * dazzle to MODEL with variety of ornaments .
 * @Date 28 May, 2014
 * @author VSPL
 */
public class DazzleScreenActivity extends Activity {

	// List for particular type of Ornaments
	private HorizontalListView lvOrnaments;
	private List<String> dataOfOrnamentList;
	private OrnamentListAdapter adapter;
	
	// Choosed Ornaments List
	private HorizontalListView lvSelectedOrnaments;
	private LinkedHashMap<String, String> mapForSelectedOrnaments = new LinkedHashMap<String, String>();
	private SelectedOrnamentsListAdapter selectedListAdapter;
	
	// Spinner
	private ArrayAdapter<String> spinnerAdapter;
	private Spinner spinner;
	private String arrOfOrnamentTypes[] = {"Earrings", "Necklaces"};
	
	private ImageView imgDazzledModel;
	private TextView tvDazzleYourself;
	
	private ImageView btnHome;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.dazzle_screen_activity);
		
		initUI();
		
		lvOrnaments.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
				String strKey = spinner.getSelectedItem().toString();
				mapForSelectedOrnaments.put(strKey, dataOfOrnamentList.get(position));
				
				showDazzledModelImage(mapForSelectedOrnaments);
				
				SelectedOrnamentsListAdapter.mKeys = MethodUtils.getKeysArrayFromLinkedHashMap(mapForSelectedOrnaments);
				
				Logger.vLog(" onItemClick ", "Key : "+strKey+" Value : "+dataOfOrnamentList.get(position)+" Map Size :"+mapForSelectedOrnaments.size());
				
				selectedListAdapter.notifyDataSetChanged();
			}

			
			
		});
		
	}

	private void showDazzledModelImage(LinkedHashMap<String, String> map) {
		// TODO Auto-generated method stub
		
		String mImageName = ConstantUtils.models[2];
		
		for (int i = 0; i < arrOfOrnamentTypes.length; i++) {
			String strOrnamentType = map.get(arrOfOrnamentTypes[i]);
			
			if (strOrnamentType != null) {
				mImageName = mImageName.concat(strOrnamentType);
			}
		}
			
		Logger.vLog("showDazzledModelImage", "Resource Name : "+mImageName);
		
		int ResId = getResources().getIdentifier(mImageName, "drawable", getPackageName());
		
		if (ResId != 0) {
			imgDazzledModel.setImageResource(ResId);
		}else{
			Toast.makeText(DazzleScreenActivity.this, "No resource found for "+mImageName, Toast.LENGTH_SHORT).show();
		}
		
		
	}
	
	@SuppressLint("DefaultLocale")
	private void initUI() {
		// TODO Auto-generated method stub
		dataOfOrnamentList = new ArrayList<String>();
		
		dataOfOrnamentList.clear();
        dataOfOrnamentList.addAll(MethodUtils.getArrayList(DazzleScreenActivity.this, arrOfOrnamentTypes[0].toLowerCase()));

		spinner = (Spinner) findViewById(R.id.spinner);
		
		spinnerAdapter = new CustomSpinnerAdapter(DazzleScreenActivity.this, 
								android.R.layout.simple_spinner_item, arrOfOrnamentTypes);
		
		spinner.setAdapter(spinnerAdapter);
		
		lvOrnaments = (HorizontalListView) findViewById(R.id.listview);
		adapter = new OrnamentListAdapter(DazzleScreenActivity.this, dataOfOrnamentList);		
		lvOrnaments.setAdapter(adapter);
		
		lvSelectedOrnaments = (HorizontalListView) findViewById(R.id.list_sel_items);
		selectedListAdapter = new SelectedOrnamentsListAdapter(DazzleScreenActivity.this, mapForSelectedOrnaments, listener);		
		lvSelectedOrnaments.setAdapter(selectedListAdapter);
		
		imgDazzledModel = (ImageView) findViewById(R.id.img_dazzled_model);		
		tvDazzleYourself = (TextView) findViewById(R.id.tv_dazzleyourself);
		btnHome = (ImageView) findViewById(R.id.btn_home);
		
		btnHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		// set underline to text
		String mystring=new String("Dazzle Yourself");
		SpannableString content = new SpannableString(mystring);
		content.setSpan(new UnderlineSpan(), 0, mystring.length(), 0);
		tvDazzleYourself.setText(content);
		
		tvDazzleYourself.setTypeface(FontUtils.getRobotoBackTypeface(DazzleScreenActivity.this));
		
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                
            	dataOfOrnamentList.clear();
            	String mStr = spinner.getItemAtPosition(position).toString();
                
                dataOfOrnamentList.addAll(MethodUtils.getArrayList(DazzleScreenActivity.this, mStr.toLowerCase()));
                               
//                Iterator<String> iterator = dataOfOrnamentList.iterator();
//                while (iterator.hasNext()) {
//                	Logger.vLog("onItemSelected : ", "Item : "+iterator.next());
//                }
                
                adapter.notifyDataSetChanged();
                
        		((TextView) spinner.getSelectedView()).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
	}

	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			final int position = lvSelectedOrnaments.getPositionForView((View) view.getParent());
			
			String str = SelectedOrnamentsListAdapter.mKeys[position];
			mapForSelectedOrnaments.remove(str);
			
			SelectedOrnamentsListAdapter.mKeys = MethodUtils.getKeysArrayFromLinkedHashMap(mapForSelectedOrnaments);
			selectedListAdapter.notifyDataSetChanged();

			// refresh the dazzled image 
			showDazzledModelImage(mapForSelectedOrnaments);
		}
	};
	
}
