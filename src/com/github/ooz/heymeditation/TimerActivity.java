package com.github.ooz.heymeditation;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class TimerActivity extends Activity {
	
	private final static int NUM_MESSAGES = 3;
	private final static int DEFAULT_DURATION = 1;
	
	private CountDownTimer preparationTimer = null;
	private CountDownTimer meditationTimer = null;
	private int meditationDuration = TimerActivity.DEFAULT_DURATION;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer);
		
		Intent intent = getIntent();
		this.meditationDuration = intent.getIntExtra(MainActivity.TIMER_MESSAGE, 
													 TimerActivity.DEFAULT_DURATION);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		createTimers();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// action with ID action_refresh was selected
		case R.id.action_about:
			Toast.makeText(this, "Hey! Meditation by Oliver Zeit\nIcon created with the Android Asset Studio", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}

		return true;
	}
	
	private void createTimers() {
		final TextView text = (TextView) findViewById(R.id.textView1);
		
		this.preparationTimer = new CountDownTimer(10000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				if (millisUntilFinished <= 4000) {
					text.setText("Close your eyes!");
				}
			}
			
			@Override
			public void onFinish() {
				meditationTimer = new CountDownTimer(meditationDuration * 60 * 1000, 1000) {
					@Override
					public void onTick(long millisUntilFinished) {
						text.setText("" + millisUntilFinished / 1000);
					}
					
					@Override
					public void onFinish() {
						Random rand = new Random();
						int msgNumber = rand.nextInt(TimerActivity.NUM_MESSAGES);
						switch (msgNumber) {
						case 0:
							text.setText("Hey! You are perfect!");
							break;
						case 1:
							text.setText("Hey! You can do it!");
							break;
						default:
							text.setText("Hey!");
						}
						
						// Play the notification sound. Taken from:
						// http://stackoverflow.com/questions/4441334/how-to-play-an-android-notification-sound
						try {
						    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
						    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
						    r.play();
						} catch (Exception e) {
						    e.printStackTrace();
						}
					}
				};
				meditationTimer.start();
			}
		};
		this.preparationTimer.start();
	}
	
//	@Override
//	protected void onStart() {
//		super.onStart();
//		
//	}
	
//	@Override
//	protected void onStop() {
//		super.onStop();
//		
//	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if (this.preparationTimer != null) {
			this.preparationTimer.cancel();
			this.preparationTimer = null;
		}
		if (this.meditationTimer != null) {
			this.meditationTimer.cancel();
			this.meditationTimer = null;
		}
	}
}
