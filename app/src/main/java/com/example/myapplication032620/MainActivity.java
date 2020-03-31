package com.example.myapplication032620;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";

	private EditText result;
	private EditText number;
	private TextView displaySign;

	private Double nr1;
	private Double nr2;
	private String temp;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		result = findViewById(R.id.result);
		number = findViewById(R.id.number);
		displaySign = findViewById(R.id.sign);

		Button button0 = findViewById(R.id.button0);
		Button button1 = findViewById(R.id.button1);
		Button button2 = findViewById(R.id.button2);
		Button button3 = findViewById(R.id.button3);
		Button button4 = findViewById(R.id.button4);
		Button button5 = findViewById(R.id.button5);
		Button button6 = findViewById(R.id.button6);
		Button button7 = findViewById(R.id.button7);
		Button button8 = findViewById(R.id.button8);
		Button button9 = findViewById(R.id.button9);
		Button button_dot = findViewById(R.id.buttondot);

		Button button_multiply = findViewById(R.id.buttonmultiply);
		Button button_devide = findViewById(R.id.buttondevide);
		Button button_add = findViewById(R.id.buttonadd);
		Button button_minus = findViewById(R.id.buttonminus);
		Button button_equal = findViewById(R.id.buttonequal);


		Button ce = findViewById(R.id.buttonCE);
		ce.setOnClickListener((view -> {
			nr1 = null;
			nr2 = null;
			result.setText("");
			number.setText("");
			displaySign.setText("");
		}));


		View.OnClickListener digit_listener =
				(view -> number.append(((Button)view).getText().toString()));

		button0.setOnClickListener(digit_listener);
		button1.setOnClickListener(digit_listener);
		button2.setOnClickListener(digit_listener);
		button3.setOnClickListener(digit_listener);
		button4.setOnClickListener(digit_listener);
		button5.setOnClickListener(digit_listener);
		button6.setOnClickListener(digit_listener);
		button7.setOnClickListener(digit_listener);
		button8.setOnClickListener(digit_listener);
		button9.setOnClickListener(digit_listener);
		button_dot.setOnClickListener(digit_listener);


		View.OnClickListener operation_listener =
				(view -> {
					String op = ((Button)view).getText().toString();
					displaySign.setText(op);

					String nr = number.getText().toString();
//					result.setText(nr);

					try {
						Double nr1 = Double.valueOf(nr);
						performOperation(nr1, op);

					} catch (Exception e){
						number.setText("");
					};


				} );

		button_add.setOnClickListener(operation_listener);
		button_devide.setOnClickListener(operation_listener);
		button_multiply.setOnClickListener(operation_listener);
		button_minus.setOnClickListener(operation_listener);
		button_equal.setOnClickListener(operation_listener);



		}
	private void performOperation(Double nr,String op){
		if(null == nr1){
			nr1 = nr;
			temp = op;

		} else
		{
			nr2 = nr;
			if(op.equals("=")){
				op = temp;
			} else
			{
				temp = op;
			}


			switch(op){
				case "+":
					nr1 += nr2;
				break;

				case "-":
					nr1 -= nr2;
				break;

				case "*":
					nr1 *= nr2;
				break;
			}
			result.setText(nr1.toString());
		}
		number.setText("");
	}

}
