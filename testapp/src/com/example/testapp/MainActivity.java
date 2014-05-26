package com.example.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView tvDisplay;
	EditText etEnter;
	Button Submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		tvDisplay = (TextView) findViewById(R.id.tvDisplay);
		etEnter = (EditText) findViewById(R.id.etEnter);
		Submit = (Button) findViewById(R.id.submit);
		
		Submit.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				
				String str = etEnter.getText().toString();
				
				tvDisplay.setText(str);
			}
			
		});
				
				
				
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	

}
