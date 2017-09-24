package com.example.answearit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Process;

public class SecondActivity extends Activity
{
	Button btnSettings;
	Button btnNewGame;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		btnSettings =(Button)findViewById(R.id.Exit);
		btnNewGame =(Button)findViewById(R.id.newGame);
		
		//New Game
		btnNewGame.setOnClickListener(new OnClickListener(){
			
			public void onClick(View arg0)
			{
				Intent in = new Intent(getApplicationContext(),QuizActivity.class);
				startActivity(in);
			}
		});
		
		//Show settings
		btnSettings.setOnClickListener(new OnClickListener(){
			
			public void onClick(View arg0)
			{
				//finish();
				//Intent in = new Intent(getApplicationContext(),SettingsActivity.class);
				//startActivity(in);
				try{
					Intent inte = new Intent(getApplicationContext(),com.example.answearit.SettingsActivity.class);
				//Intent inte = new Intent(SecondActivity.this,SettingsActivity.class);
				//Intent inte = new Intent("com.example.answearit.SettingsActivity");
					startActivity(inte);
				//finish();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				//System.exit(0);
			}
		});
	}
	
	/*protected void onDestroy()
	{
		finish();
		Process.killProcess(Process.myPid());
		super.onDestroy();
	}*/

}
