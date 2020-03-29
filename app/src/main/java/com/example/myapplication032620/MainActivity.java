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
	protected void onSaveInstanceState(@NonNull Bundle outState) {
		outState.putString(SAVED_STATE, textView.getText().toString());
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		textView.setText(savedInstanceState.getString(SAVED_STATE));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate: Start");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button = findViewById(R.id.button);
		EditText editText = findViewById(R.id.editText3);
		textView = findViewById(R.id.textView);
		textView.setMovementMethod(new ScrollingMovementMethod());
		textView.setText("");
		editText.setText("");
		button.setOnClickListener(view -> {
			String text = editText.getText().toString();
			textView.append(text + "\n");
			editText.setText("");
			Log.d(TAG, "onCreate: Finish");
		});


	}
}
