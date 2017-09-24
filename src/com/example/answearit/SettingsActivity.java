package com.example.answearit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity
{
	TextView tv;
	Button btnSet;
	RadioGroup rg;
	RadioButton rb5,rb10,rb15;
	static int max_quest=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		tv =(TextView)findViewById(R.id.textView1);
		btnSet =(Button)findViewById(R.id.btnReset);
		rg =(RadioGroup)findViewById(R.id.radioGroup1);
		rb5 =(RadioButton)findViewById(R.id.radio5);
		rb10 =(RadioButton)findViewById(R.id.radio10);
		rb15 =(RadioButton)findViewById(R.id.radio15);
		
		Typeface amphion = Typeface.createFromAsset(getAssets(), getString(R.string.typeface_amphion));
		tv.setTypeface(amphion);
		rb5.setTypeface(amphion);
		rb10.setTypeface(amphion);
		rb15.setTypeface(amphion);
		
		tv.setText("Επιλογή παιχνιδιού με:");
		rb5.setText("5 Ερωτήσεις.");
		rb10.setText("10 Ερωτήσεις.");
		rb15.setText("15 Ερωτήσεις.");
		
		btnSet.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0)
			{
				RadioButton option = (RadioButton)findViewById(rg.getCheckedRadioButtonId());
				String ques=option.getText().toString();
				
				if(ques.equals(rb5.getText()))
				{
					max_quest=5;
				}
				
				if(ques.equals(rb10.getText()))
				{
					max_quest=10;
				}
				
				if(ques.equals(rb15.getText()))
				{
					max_quest=15;
				}
				
				message();
				//Intent in = new Intent(SettingsActivity.this,QuizActivity.class);
				//startActivity(in);
			}
		});
		
	}
	
	public void message()
	{
		Toast.makeText(this,max_quest, Toast.LENGTH_SHORT).show();
	}
}
