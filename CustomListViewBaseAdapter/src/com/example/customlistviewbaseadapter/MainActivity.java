package com.example.customlistviewbaseadapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listitem);

	}
	
	public class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			
			return 0;
		}

		@Override
		public Object getItem(int arg0) {

			return null;
		}

		@Override
		public long getItemId(int arg0) {

			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {

			return null;
		}
		
		
	}

}