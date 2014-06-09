package com.vspl.android.prototype.png.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;

import com.vspl.android.prototype.png.R;

/**
 * This is Dashboard screen activity which have Metro Look like Windows 8.
 * @Date 29 May, 2014
 * @author VSPL
 */
public class DashboardScreenActivity extends Activity {

	private ImageView imgDazzleYourSelf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.dashboard_screen_activity);

		intiUI();
	}

	private void intiUI() {
		// TODO Auto-generated method stub
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		imgDazzleYourSelf = (ImageView) findViewById(R.id.img_dazzleyourself);
		
		imgDazzleYourSelf.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				startActivity(new Intent(DashboardScreenActivity.this,
						DazzleScreenActivity.class));
			}
		});
	}

}
