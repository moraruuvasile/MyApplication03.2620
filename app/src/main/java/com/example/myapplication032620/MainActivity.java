package com.example.myapplication032620;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button = findViewById(R.id.button);
		EditText editText = findViewById(R.id.editText3);
		TextView textView = findViewById(R.id.textView);
		textView.setMovementMethod(new ScrollingMovementMethod());
		textView.setText("");
		editText.setText("");
		button.setOnClickListener(view -> {
			String text = editText.getText().toString();
			textView.append(text + "\n");
			editText.setText("");

		});

	}
}
