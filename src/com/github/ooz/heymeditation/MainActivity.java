package com.github.ooz.heymeditation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public final static String TIMER_MESSAGE = "com.github.ooz.heymeditation.TIMER_MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
			Toast.makeText(this, getString(R.string.about_message), Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}

		return true;
	}
	
	public void sendTime(View view) {
		Intent intent = new Intent(this, TimerActivity.class);
		
		if (R.id.button1 == view.getId()) {
			intent.putExtra(TIMER_MESSAGE, 1);
		} else if (R.id.button2 == view.getId()) {
			intent.putExtra(TIMER_MESSAGE, 3);
		}
		
		startActivity(intent);
	}
}
