package com.example.listviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_simple_list);

		String[] codelearnChapters = new String[] {
				
				"chapter1", "chapter2","chapter3", "chapter4"
		};
		
		ArrayAdapter<String> codelearnAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, codelearnChapters);
		
		ListView codeLearnLessons = (ListView) findViewById(R.id.listId);
		
		codeLearnLessons.setAdapter(codelearnAdapter);
	}
}
