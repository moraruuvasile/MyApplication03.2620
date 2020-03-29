package com.example.myapplication032620;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	private TextView textView;

	private static final String TAG = "MainActivity";
	private final String SAVED_STATE = "Saved State";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate: Start");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


	}
}
