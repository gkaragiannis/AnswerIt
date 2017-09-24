package com.example.answearit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

public class ResultActivity extends Activity
{
	//TextView tv;
	TextView game;
	TextView over;
	TextView tvScore;
	Button btnRestart;
	String result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		//tv =(TextView)findViewById(R.id.tvresult);
		tvScore =(TextView)findViewById(R.id.tvScore);
		btnRestart =(Button)findViewById(R.id.btnReset);
		game =(TextView)findViewById(R.id.game);
		over =(TextView)findViewById(R.id.over);
		
		//StringBuffer buffer = new StringBuffer();
		//buffer.append("Game Over");
		//buffer.append("You answeared "+QuizActivity.correct+" correct!");
		result = "Your score: "+QuizActivity.score;
		
		Typeface amphion = Typeface.createFromAsset(getAssets(), getString(R.string.typeface_amphion));
		//tv.setTypeface(amphion);
		tvScore.setTypeface(amphion);
		game.setTypeface(amphion);
		over.setTypeface(amphion);
		
		if(QuizActivity.winnerFlag==1)
		{
			game.setText("Winner!!!");
			over.setText("");
		}
		
		//tvScore.setText(buffer);
		tvScore.setText(result);
		
		//QuizActivity.correct=0;
		QuizActivity.score=0;
		
		btnRestart.setOnClickListener(new OnClickListener(){
			
			public void onClick(View arg0)
			{
				Intent in = new Intent(getApplicationContext(),SecondActivity.class);
				startActivity(in);
			}
		});
	}
}
