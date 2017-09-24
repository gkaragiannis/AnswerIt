package com.example.answearit;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity  
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Thread t = new Thread(){//(new Runnable(){
			
			public void run()
			{
				try
				{
					//Thread.sleep(3000);
					Thread.sleep(3000);
					//Intent in = new Intent(getApplicationContext(),SecondActivity.class);
					//startActivity(in);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				
				//Intent in = new Intent(getApplicationContext(),SecondActivity.class);
				//startActivity(in);
				Intent in = new Intent(MainActivity.this,SecondActivity.class);
				startActivity(in);
				finish();
			}
		};//);
		t.start();
		
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

	/*
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
}
