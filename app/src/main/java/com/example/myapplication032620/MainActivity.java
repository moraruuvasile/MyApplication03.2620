package com.example.myapplication032620;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";
	ListView listView;
	private String feedUrl = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=%d/xml";
	private int feedLimit = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate: " + "Start ");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = findViewById(R.id.view_of_listview);

		downloadUrl(String.format(feedUrl, feedLimit));
		Log.d(TAG, "onCreate: " + "Finish");

	}

	private void downloadUrl(String url){
		DownloadData downloadData = new DownloadData();
		downloadData.execute(url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.feeds_menu, menu);
		if(feedLimit == 10) {
			menu.findItem(R.id.menuGrup10).setChecked(true);
		} else {
			menu.findItem(R.id.menuGrup25).setChecked(true);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {

		int id = item.getItemId();

		switch(id) {
			case R.id.menuFreeApps:
				feedUrl = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=%d/xml";
				break;
			case R.id.menuPayedApps:
				feedUrl = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/toppaidapplications/limit=%d/xml";
				break;
			case R.id.menuSongs:
				feedUrl = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=%d/xml";
				break;
			case R.id.menuGrup10:
			case R.id.menuGrup25:
				if(!item.isChecked()) {
					item.setChecked(true);
					feedLimit = 35 - feedLimit;
					Log.d(TAG, "onOptionsItemSelected: " + item.getTitle() + " setting feedLimit to " + feedLimit);
				} else {
					Log.d(TAG, "onOptionsItemSelected: " + item.getTitle() + " feedLimit unchanged");
				}
				break;
			default:
				return super.onOptionsItemSelected(item);

		}
		downloadUrl(String.format(feedUrl, feedLimit));
		return true;
	}

	private class DownloadData extends AsyncTask<String, Void, String>{
		private static final String TAG = "DownloadData";

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			Log.d(TAG, "onPostExecute: " + s);
			ParseXMLApp parseXMLApp = new ParseXMLApp();
			parseXMLApp.parse(s);

//			ArrayAdapter<FeedEntry> arrayAdapter= new ArrayAdapter<FeedEntry>(
//					MainActivity.this, R.layout.list_item, parseXMLApp.getApplications()
//			);
//			listView.setAdapter(arrayAdapter);

			FeedAdapter feedAdapter = new FeedAdapter(
					MainActivity.this, R.layout.list_adapter_xml, parseXMLApp.getApplications()
			);
			listView.setAdapter(feedAdapter);
		}

		@Override
		protected String doInBackground(String... strings) {
			Log.d(TAG, "doInBackground: " + strings[0]);
			String rssFeed = downloadData(strings[0]);
			if (rssFeed == null) {
				Log.e(TAG, "doInBackground: error downloading");
			}
			return rssFeed;
		}

		private String downloadData(String urlPath){
			StringBuilder xmlResult = new StringBuilder();
			try {
				URL url = new URL(urlPath);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				int response = connection.getResponseCode();
				Log.d(TAG, "downloadXML: The response code was " + response);

				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				int charsRead;
				char[] inputBuffer = new char[500];
				while(true) {
					charsRead = reader.read(inputBuffer);
					if(charsRead < 0) {
						break;
					}
					if(charsRead > 0) {
						xmlResult.append(String.copyValueOf(inputBuffer, 0, charsRead));
					}
				}
				reader.close();

				return xmlResult.toString();
			} catch(MalformedURLException e) {
				Log.e(TAG, "downloadXML: Invalid URL " + e.getMessage());
			} catch(IOException e) {
				Log.e(TAG, "downloadXML: IO Exception reading data: " + e.getMessage());
			} catch(SecurityException e) {
				Log.e(TAG, "downloadXML: Security Exception.  Needs permisson? " + e.getMessage());
			}

			return null;
		}
	}
}

