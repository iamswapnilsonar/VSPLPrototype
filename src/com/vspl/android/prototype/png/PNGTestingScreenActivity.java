package com.vspl.android.prototype.png;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class PNGTestingScreenActivity extends Activity implements OnClickListener {

	private TextView tvRings, tvEarRings, tvNecklaces, tvMangalsutra;
	private View vFirstSecond, vSecondThird, vThirdFourth;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lin_text_menubar);

		vFirstSecond = (View) findViewById(R.id.vFirstSecond);
		vSecondThird = (View) findViewById(R.id.vSecondThird);
		vThirdFourth = (View) findViewById(R.id.vThirdFourth);
		
		tvRings = (TextView) findViewById(R.id.tvRings);
		tvEarRings = (TextView) findViewById(R.id.tvEarRings);
		tvNecklaces = (TextView) findViewById(R.id.tvNecklaces);
		tvMangalsutra = (TextView) findViewById(R.id.tvMangalsutra);
		
		tvRings.setOnClickListener(this);
		tvEarRings.setOnClickListener(this);
		tvNecklaces.setOnClickListener(this);
		tvMangalsutra.setOnClickListener(this);		
	}

	public void setTvRingsBackgroundColor(){
		
		tvRings.setBackgroundColor(getResources().getColor(R.color.sel_color));		
		tvEarRings.setBackgroundColor(getResources().getColor(R.color.normal_color));
		tvNecklaces.setBackgroundColor(getResources().getColor(R.color.normal_color));
		tvMangalsutra.setBackgroundColor(getResources().getColor(R.color.normal_color));
		
		vFirstSecond.setVisibility(View.INVISIBLE);
		vSecondThird.setVisibility(View.VISIBLE);
		vThirdFourth.setVisibility(View.VISIBLE);
	}

	public void setTvEarRingsBackgroundColor(){
		tvRings.setBackgroundColor(getResources().getColor(R.color.normal_color));		
		tvEarRings.setBackgroundColor(getResources().getColor(R.color.sel_color));
		tvNecklaces.setBackgroundColor(getResources().getColor(R.color.normal_color));
		tvMangalsutra.setBackgroundColor(getResources().getColor(R.color.normal_color));
		
		vFirstSecond.setVisibility(View.INVISIBLE);
		vSecondThird.setVisibility(View.INVISIBLE);
		vThirdFourth.setVisibility(View.VISIBLE);
	}

	public void setTvNecklacesBackgroundColor(){
		tvRings.setBackgroundColor(getResources().getColor(R.color.normal_color));		
		tvEarRings.setBackgroundColor(getResources().getColor(R.color.normal_color));
		tvNecklaces.setBackgroundColor(getResources().getColor(R.color.sel_color));
		tvMangalsutra.setBackgroundColor(getResources().getColor(R.color.normal_color));
		
		vFirstSecond.setVisibility(View.VISIBLE);
		vSecondThird.setVisibility(View.INVISIBLE);
		vThirdFourth.setVisibility(View.INVISIBLE);
	}

	public void setTvMangalsutraBackgroundColor(){
		tvRings.setBackgroundColor(getResources().getColor(R.color.normal_color));		
		tvEarRings.setBackgroundColor(getResources().getColor(R.color.normal_color));
		tvNecklaces.setBackgroundColor(getResources().getColor(R.color.normal_color));
		tvMangalsutra.setBackgroundColor(getResources().getColor(R.color.sel_color));
		
		vFirstSecond.setVisibility(View.VISIBLE);
		vSecondThird.setVisibility(View.VISIBLE);
		vThirdFourth.setVisibility(View.INVISIBLE);
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
		int key = view.getId();
		
		switch (key) {
		case R.id.tvRings:
			setTvRingsBackgroundColor();
			break;

		case R.id.tvEarRings:
			setTvEarRingsBackgroundColor();
			break;
			
		case R.id.tvNecklaces:
			setTvNecklacesBackgroundColor();
			break;
			
		case R.id.tvMangalsutra:
			setTvMangalsutraBackgroundColor();
			break;
		}
		
	}

}
