package com.example.myapplication032620;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate: " + "Start ");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DownloadData downloadData = new DownloadData();
		downloadData.execute("test");
		Log.d(TAG, "onCreate: " + "Finish");
	}
	
	
	private class DownloadData extends AsyncTask<String, Void, String>{
		private static final String TAG = "DownloadData";

		@Override
		protected void onPostExecute(String s) {
			Log.d(TAG, "onPostExecute: " + s);
			super.onPostExecute(s);
		}

		@Override
		protected String doInBackground(String... strings) {
			Log.d(TAG, "doInBackground: " + strings[0]);
			return "Done in background";
		}
	}
}
