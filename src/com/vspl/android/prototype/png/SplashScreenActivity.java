package com.vspl.android.prototype.png;

import com.vspl.android.prototype.png.dashboard.HomeScreenActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

/**
 * This is Splash screen activity.
 * @Date 27 May, 2014
 * @author VSPL
 */
public class SplashScreenActivity extends Activity {

	private float splashTime = 2000;
	private boolean paused = false;
	private boolean splashActive = true;
	private float mProgress;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Draw the splash screen
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_screen_activity);

		// start splash timer
		new splashTimer().execute();

	}

	class splashTimer extends AsyncTask<Void, String, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			try {
				// Wait loop
				float ms = 0;
				while (splashActive && ms < splashTime) {
					// if splash is active & ms is less than splashTime for
					// which it should be then sleep for 1sec
					Thread.sleep(100);
					if (!paused)
						ms = ms + 100;

					if (ms != 0)
						mProgress = (float) (ms / splashTime) * 100;
					mProgress = (int) mProgress;
					publishProgress(Float.toString(mProgress));

				}

				// Once splash time is finished start next screen
				startActivity(new Intent(SplashScreenActivity.this,HomeScreenActivity.class));

			} catch (Exception e) {
				Log.e("Splash", e.toString());
			} finally {
				finish();
			}

			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			finish();
		}

	}
}
