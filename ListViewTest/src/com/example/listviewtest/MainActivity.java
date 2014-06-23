package com.example.listviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//ListView layout (of the whole screen) is initialized in activity_main_simple_list.xml
		setContentView(R.layout.activity_main_simple_list);

		//String contains data required by the Adapter.
		String[] codelearnChapters = new String[] {
				
				"chapter1", "chapter2","chapter3", "chapter4"
		};
		
		// Adapter uses "this" context, the layout (of individual row) used to
		//bind data from above is taken from default android layout,
		//the third parameter in the ArrayAdapter is the "source of data" which
		// is the codelearnChapters String array
		
		ArrayAdapter<String> codelearnAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, codelearnChapters);
		
		// ListView just like TextView and Edit Text , we inflate the layout 
		// listId is the id given to ListView in activity_main_simple_list.xml
		
		ListView codeLearnLessons = (ListView) findViewById(R.id.listId);
		
		// setAdapter sets the adapter(codelearnAdapter) to the
		//listView codelearnlessons
		
		codeLearnLessons.setAdapter(codelearnAdapter);
	}
}
