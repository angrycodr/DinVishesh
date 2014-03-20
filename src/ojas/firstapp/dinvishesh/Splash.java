package ojas.firstapp.dinvishesh;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		final Intent openHome = new Intent(this,Home.class);
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(3000);
				} catch(InterruptedException ie) {
					ie.printStackTrace();
				} finally {
					startActivity(openHome);
				}
			}
		};
		timer.start();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
