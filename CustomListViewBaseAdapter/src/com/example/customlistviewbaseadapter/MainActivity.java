package com.example.customlistviewbaseadapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public final String TAG = "Task:";
	
	//Objects for the arraylist is created from this class.
	public class codeLearnChapter {

		String chapterName;
		String chapterDescription;
	}
	
	//Defined Custome Adapter.
	MyAdapter mMyAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_simple_list);
		Log.i(TAG, "listview is set as contentview");
		
		//Initialized custom adapter.
		mMyAdapter = new MyAdapter();
		Log.i(TAG,"New adapter mMyAdapter created");
		
		ListView codeLearnLessons = (ListView) findViewById(R.id.listId);
		Log.i(TAG,"ListView is inflated");
		
		codeLearnLessons.setAdapter(mMyAdapter);
		Log.i(TAG, "mMyAdapter is set to ListView codeLearnLessons");
	}
	
	public class MyAdapter extends BaseAdapter{
		
		//Data for the arraylist codeLearnChapterList is obtained from getDataForListView() method.
		List<codeLearnChapter> codeLearnChapterList = getDataForListView();

		@Override
		public int getCount() {
			
			Log.i(TAG, "Arraylist size is sent");
			return codeLearnChapterList.size();
			
		}

		@Override
		public Object getItem(int arg0) {
			
			Log.i(TAG, "Object of particular row is sent");
			return codeLearnChapterList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {

			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {

			if(arg1==null){
				
				LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
				arg1 = inflater.inflate(R.layout.listitem, arg2, false);
				Log.i(TAG, "row of listitem is inflater");
			}
			
			TextView mChapterName = (TextView) arg1.findViewById(R.id.textView1);
			Log.i(TAG,"main description is referenced to its value");
			
			TextView mChapterDescription = (TextView) arg1.findViewById(R.id.textView2);
			Log.i(TAG, "sub description is referenced to its value");
			
			codeLearnChapter mcodeLearnChapter = codeLearnChapterList.get(arg0);
			Log.i(TAG, "particular of object corresponding to row arg0 is passed to object mcodeLearnChapter");
			
			mChapterName.setText(mcodeLearnChapter.chapterName);
			Log.i(TAG, "chapterName is Set");
			
			mChapterDescription.setText(mcodeLearnChapter.chapterDescription);
			Log.i(TAG,"chapterDescription is Set");
			
			return arg1;
		} 
		
	}
	
	public List<codeLearnChapter> getDataForListView(){
		
		List<codeLearnChapter> codeLearnChapterList = new ArrayList<codeLearnChapter>();
		
		//The arraylist codeLearnChapterList is populated with 3 objects.
		for(int i=0;i<3;i++){
			
			codeLearnChapter mcodeLearnChapter = new codeLearnChapter();			
			mcodeLearnChapter.chapterName = "Chapter"+ i;
			mcodeLearnChapter.chapterDescription = "Description for Chapter"+i;
			
			codeLearnChapterList.add(mcodeLearnChapter);
		}
		
		Log.i(TAG, "Data (List of objects ) populated in arraylist ");
		return codeLearnChapterList;
		
	}
	
}