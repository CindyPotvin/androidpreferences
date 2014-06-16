package com.cindypotvin.androidpreferences;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Main activity to which the preferences applies. The Settings activity can be
 * accessed from the action bar.
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu to add it to the action bar
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			// Starts the Settings activity on top of the current activity
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

		return true;
	}

	@Override
	public void onResume() {
		super.onResume();
		 // To use the preferences when the activity starts and when the user navigates back from the settings activity.
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

		TextView welcomeTextView = (TextView) findViewById(R.id.hello_world_textview);
		
		String defaultWelcomeText = getResources().getString(R.string.pref_default_welcome_text);
		String welcomeText = preferences.getString("welcome_text", defaultWelcomeText);
		welcomeTextView.setText(welcomeText);
		
		String defaultWelcomeTextColor = getResources().getString(R.string.pref_default_welcome_text_color);
		String welcomeTextColor = preferences.getString("welcome_text_color", defaultWelcomeTextColor);
		welcomeTextView.setTextColor(Color.parseColor(welcomeTextColor));
		
		boolean showWelcomeText = preferences.getBoolean("show_welcome_text", true /*showWelcomeText*/);
		if (showWelcomeText)
			welcomeTextView.setVisibility(View.VISIBLE);
		else
			welcomeTextView.setVisibility(View.INVISIBLE);
	}
}
