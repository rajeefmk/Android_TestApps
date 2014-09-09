package us.jaaga.jaagatestapp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public static final String productUrl = "https://api.uber.com/v1/products";
	
	Button mButtonProduct;
	TextView mTextViewProduct;
	String paramString;
	static String response = null;
	ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mButtonProduct = (Button) findViewById(R.id.product_button);
		mTextViewProduct = (TextView) findViewById(R.id.product_textview);
		
		
		
		
		
		
		
		mButtonProduct.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {

				AsyncHelper mAsyncHelper = new AsyncHelper();
				mAsyncHelper.execute();
				
			}
		});
		
	}
	
	private class AsyncHelper extends AsyncTask<Void, Void, String>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			mProgressDialog = new ProgressDialog(MainActivity.this);
			mProgressDialog.setMessage("Loading Cars");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
			
		}
		
		@Override
		protected String doInBackground(Void... url) {
			
			
			try{
				
				DefaultHttpClient mHttpClient = new DefaultHttpClient();
				HttpEntity mHttpEntity = null;
				HttpResponse mHttpResponse = null;
				
				List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
				
				params.add(new BasicNameValuePair("server_token", "NFnH9ZFyJZeKfBlFI-fTplzmIfiafyeBPergceCF"));
				params.add(new BasicNameValuePair("latitude", "37.775818"));
				params.add(new BasicNameValuePair("longitude", "-122.418028"));
				
				paramString = URLEncodedUtils.format(params, "utf-8");
				
				String fullUrl = productUrl+ "?" + paramString;
				HttpGet mHttpGet = new HttpGet(fullUrl);
				
				
				
				mHttpResponse = mHttpClient.execute(mHttpGet);
				
				mHttpEntity = mHttpResponse.getEntity();
				
				response = EntityUtils.toString(mHttpEntity);
				
			} catch(UnsupportedEncodingException e) {
				e.printStackTrace();
				
			} catch(ClientProtocolException e){
				e.printStackTrace();
				
			} catch(IOException e) {
				e.printStackTrace();
				
			} catch(IllegalArgumentException e) {
				e.printStackTrace();
				
			} catch(RuntimeException e) {
				e.printStackTrace();
				
			}
			
			
			return response;
		}
		
	@Override
	protected void onPostExecute(String result) {
		if(mProgressDialog.isShowing())
			mProgressDialog.dismiss();
		mTextViewProduct.setText(result);
	}
		
	}
	
	

}
