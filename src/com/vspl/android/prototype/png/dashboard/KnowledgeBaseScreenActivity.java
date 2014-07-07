package com.vspl.android.prototype.png.dashboard;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.vspl.android.prototype.png.R;
import com.vspl.android.prototype.png.sliding_menu.BaseActivity;
import com.vspl.android.prototype.png.sliding_menu.SlidingMenu;

public class KnowledgeBaseScreenActivity extends BaseActivity implements OnClickListener{

	public KnowledgeBaseScreenActivity() {
		super(R.string.knowledge_base_activity);
		// TODO Auto-generated constructor stub
	}

	private Button btnShowSlidingMenu;
	private SlidingMenu sm;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// Override how this activity is animated into view
		// The new activity is pulled in from the left and the current activity is kept still
		// This has to be called before onCreate
		overridePendingTransition(R.anim.pull_in_from_right, R.anim.hold);
		
		setContentView(R.layout.knowledge_base_activity);
		initUI();
	}
	
	@Override
    protected void onPause() {
		// Whenever this activity is paused (i.e. looses focus because another activity is started etc)
		// Override how this activity is animated out of view
		// The new activity is kept still and this activity is pushed out to the left
        overridePendingTransition(R.anim.hold, R.anim.push_out_to_right);
        super.onPause();
    }

	private void initUI() {
		// TODO Auto-generated method stub
		
		btnShowSlidingMenu = (Button) findViewById(R.id.btn_temp_show_sidebar);
		btnShowSlidingMenu.setOnClickListener(this);
		
		// Sliding Menu..
		sm = getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT);
		sm.setShadowDrawable(R.drawable.shadow);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
		int key = view.getId();

		switch (key) {
		
		case R.id.btn_temp_show_sidebar:
			sm.toggle();			
			break;
			
		}
		
	}

}
