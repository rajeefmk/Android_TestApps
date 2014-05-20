package eu.erikw.pulltorefresh.sample;

import info.androidhive.jsonparsing.R;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import us.jaaga.mooctracker.MainActivity;
import us.jaaga.mooctracker.ServiceHandler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;
import eu.erikw.pulltorefresh.sample.PullToRefreshListViewSampleActivity.PullToRefreshListViewSampleAdapter.ViewHolder;

public class PullToRefreshListViewSampleActivity extends Activity { 



	private PullToRefreshListView listView;
	private PullToRefreshListViewSampleAdapter adapter;

	/*// IDs for the context menu actions
	private final int idEdit = 1;
	private final int idDelete = 2;*/
	
	private ProgressDialog pDialog;
	
	// URL to get contacts JSON
	
		private static String url = "http://10.0.2.2:8000/api/students/";

		// JSON Node names
		private static final String TAG_ID = "id";
		private static final String TAG_NAME = "name";
		
		// contacts JSONArray
		JSONArray contacts = null;

		// Hashmap for ListView
		ArrayList<HashMap<String, String>> contactList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		listView = (PullToRefreshListView) findViewById(R.id.pull_to_refresh_listview);

		// OPTIONAL: Disable scrolling when list is refreshing
		// listView.setLockScrollWhileRefreshing(false);

		// OPTIONAL: Uncomment this if you want the Pull to Refresh header to show the 'last updated' time
		// listView.setShowLastUpdatedText(true);

		// OPTIONAL: Uncomment this if you want to override the date/time format of the 'last updated' field
		// listView.setLastUpdatedDateFormat(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));

		// OPTIONAL: Uncomment this if you want to override the default strings
		// listView.setTextPullToRefresh("Pull to Refresh");
		// listView.setTextReleaseToRefresh("Release to Refresh");
		// listView.setTextRefreshing("Refreshing");

		// MANDATORY: Set the onRefreshListener on the list. You could also use
		// listView.setOnRefreshListener(this); and let this Activity
		// implement OnRefreshListener.
		
		listView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// Your code to refresh the list contents goes here

				// for example:
				// If this is a webservice call, it might be asynchronous so
				// you would have to call listView.onRefreshComplete(); when
				// the webservice returns the data
				adapter.loadData();
				
				// Make sure you call listView.onRefreshComplete()
				// when the loading is done. This can be done from here or any
				// other place, like on a broadcast receive from your loading
				// service or the onPostExecute of your AsyncTask.

				// For the sake of this sample, the code will pause here to
				// force a delay when invoking the refresh
				listView.postDelayed(new Runnable() {

					
					@Override
					public void run() {
						listView.onRefreshComplete();
					}
				}, 2000);
			}
		});

		adapter = new PullToRefreshListViewSampleAdapter() {};
		listView.setAdapter(adapter);
		
		// Request the adapter to load the data
					adapter.loadData();
		
		

		// click listener
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				ViewHolder viewHolder = (ViewHolder) arg1.getTag();
				if (viewHolder.name != null){
					Toast.makeText(PullToRefreshListViewSampleActivity.this, viewHolder.name.getText(), Toast.LENGTH_SHORT).show();
				}					
			}
		});

		// Register the context menu for actions
		//registerForContextMenu(listView);
	}


	/**
	 * Create the context menu with the Edit and Delete options
	 */
/*	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		// Add any actions you need. Implement the logic in onContextItemSelected
		menu.add(Menu.NONE, idEdit, Menu.NONE, R.string.edit);
		menu.add(Menu.NONE, idDelete, Menu.NONE, R.string.delete);
	}
*/

	/**
	* Event called after an option from the context menu is selected
	*/
	/*@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();		
		
		switch (item.getItemId()) {
		case idEdit:
			
			// Put your code here for Edit action
			// just as an example a toast message
			Toast.makeText(this, getString(R.string.edit) + " " + adapter.getItem(info.position-1), Toast.LENGTH_SHORT).show();
			return true;
		case idDelete:
			
			// Put your code here for Delete action
			// just as an example a toast message
			Toast.makeText(this, getString(R.string.delete) + " " + adapter.getItem(info.position-1), Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}
	*/

	/**
	 * The adapter used to display the results in the list
	 * 
	 */
	public abstract class PullToRefreshListViewSampleAdapter extends android.widget.BaseAdapter {

		private ArrayList<String> items = new ArrayList<String>();;
		
		public class ViewHolder {
			public String id;
			public TextView name;
		}

		/**
		 * Loads the data. 
		 */
		public void loadData() {
			
			// Here add your code to load the data for example from a webservice or DB
			
			// Calling async task to get json
			new GetContacts().execute();
			
			/*items = new ArrayList<String>();

			items.add("Ajax Amsterdam");
			items.add("Barcelona");
			items.add("Manchester United");
			items.add("Chelsea");
			items.add("Real Madrid");
			items.add("Bayern Munchen");
			items.add("Internazionale");
			items.add("Valencia");
			items.add("Arsenal");
			items.add("AS Roma");
			items.add("Tottenham Hotspur");
			items.add("PSV");
			items.add("Olympique Lyon");
			items.add("AC Milan");
			items.add("Dortmund");
			items.add("Schalke 04");
			items.add("Twente");
			items.add("Porto");
			items.add("Juventus");
*/
			
			// MANDATORY: Notify that the data has changed
			notifyDataSetChanged();
		}

		/*@Override
		public int getCount() {
			return items.size();
		}*/

		/*@Override
		public Object getItem(int position) {
			return items.get(position);
		}*/

		/*@Override
		public long getItemId(int position) {
			return position;
		}*/

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View rowView = convertView;

			String record = (String) getItem(position);

			LayoutInflater inflater = PullToRefreshListViewSampleActivity.this.getLayoutInflater();

			ViewHolder viewHolder = new ViewHolder();

			if (convertView == null){
				rowView = inflater.inflate(R.layout.list_item,null);

				viewHolder.name = (TextView) rowView.findViewById(R.id.textView1);

				rowView.setTag(viewHolder);
			}

			final ViewHolder holder = (ViewHolder) rowView.getTag();

			holder.name.setText(record); 

			return rowView;
		}
	}

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(PullToRefreshListViewSampleActivity.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}
																																																																																																																																																																																							
		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					
					// Getting JSON Array node
					/*contacts = jsonObj.getJSONArray(TAG_CONTACTS);*/
					
					JSONArray jsonarray = new JSONArray(jsonStr);

					// looping through All Contacts
					for (int i = 0; i < jsonarray.length(); i++) {
						
						JSONObject c = jsonarray.getJSONObject(i);
						
						String id = c.getString(TAG_ID);
						String name = c.getString(TAG_NAME);

						// tmp hashmap for single contact
						HashMap<String, String> contact = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						contact.put(TAG_ID, id);
						contact.put(TAG_NAME, name);

						// adding contact to contact list
						contactList.add(contact);
						
						
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			
			/*ListAdapter adapter = new SimpleAdapter(
					MainActivity.this, contactList,
					R.layout.list_item, new String[] { TAG_NAME}, new int[] { R.id.name});
			
			
			setListAdapter(adapter);*/
			
			
		}

	}

}

