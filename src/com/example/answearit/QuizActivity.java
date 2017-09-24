package com.example.answearit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity
{
	int i;
	TextView tv;
	TextView tvScore;
	Button btn1,btn2,btn3,btn4;
	String question[]={"+ Χρυσές μπάλες;","+perissotera prwta8lhmata?","1os Score 2016?"};
	String answear[]={"Messi","Osfp","Fortounhs"};
	String options[]={"Ronaldo","Messi","Pele","Maradona","Osfp","Pao","Aek","Paok","Berg","Claus","Aravidis","Fortounhs"};
	int nques=0;//Counter gia ton ari8mo twn erwthsewn
	public static int correct=0;
	private static int progress;
	private ProgressBar progressBar;
	private int progressStatus=0;
	private int lastProgressStatus;
	private Handler handler = new Handler();
	public static int score;//krataei to score sumfwna me to xrono apokrishs
	public static int winnerFlag;
	public static int wrongAnswer;
	int pace=1;//to bhma meiwshs ths progressBar
	//-------------------------------------------------------------
	List<Question> questionList;
	//List<Integer> alreadyChoosed;//boh8htikh lista.pairnei timh 1 otan epilegei h erwthsh
	int questID=0;
	//int questID;
	Question currentQ;
	int MAX_QUESTIONS=5;                                           //<---- Na to pairnw apo ton xrhsth
	int isPlayed=0;//krataei ton ari8mo erwthsewn pou exoun paixtei(einai gia ton elegxo termatismoy tou paixnidioy)
	//-------------------------------------------------------------
	ArrayList<Integer> alreadyChoosed = new ArrayList<Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		
		tv =(TextView)findViewById(R.id.tvquestion);
		tvScore =(TextView)findViewById(R.id.score);
		btn1 =(Button)findViewById(R.id.button1);
		btn2 =(Button)findViewById(R.id.button2);
		btn3 =(Button)findViewById(R.id.button3);
		btn4 =(Button)findViewById(R.id.button4);
		progressBar=(ProgressBar)findViewById(R.id.progressBar1);
		
		//allagh grammatoseiras sto textView ths erwthshs kai tou score
		Typeface amphion = Typeface.createFromAsset(getAssets(), getString(R.string.typeface_amphion));
		tv.setTypeface(amphion);
		tvScore.setTypeface(amphion);
		
		//progressBar.setMax(200);
		progress =100;
		lastProgressStatus=0;
		score=0;
		winnerFlag=0;
		wrongAnswer=0;
		//--------------------------------------------------------------------
		Database db=new Database(this);
		questionList= db.getAllQuestions();
		//arxikopoiw thn lista me mhdenika
		//System.out.println(questionList.size());
		for(i=0;i<questionList.size();i++)
		{
			//alreadyChoosed.set(i,0);
			//alreadyChoosed.add(i, 0);
			alreadyChoosed.add(0);
			//showI(i);
		}
		//showSize();
		//questID=getRandomNumber();//questionRandomChoose();//!!!!!!
		questID=questionRandomChoose();
		currentQ=questionList.get(questID);
		//--------------------------------------------------------------------
		
		//+++++++++++++++++PROBOLH THS PRWTHS ERWTHSHS+++++++++++++++++++++++++++++
		/*tv.setText(question[nques]);
		btn1.setText(options[0]);
		btn2.setText(options[1]);
		btn3.setText(options[2]);
		btn4.setText(options[3]);
		*/
		//++++++++++++++++++++++++++++++++++++++++++++++
		tvScore.setText(String.valueOf(score));
		showNewQuestion();
		
		progressBarOperation(pace,0,nques);
		/*while(progressStatus < 100)
		{
			try{Thread.sleep(20);}
			catch(InterruptedException e){
				e.printStackTrace();}
			
			progressStatus = progress++;
			//Update the bar
			handler.post(new Runnable(){
					public void run(){
						progressBar.setProgress(progressStatus);
					}
			});
		}*/
		
		/*handler.post(new Runnable(){
			
			public void run()
			{
				progressBar.setVisibility(8);
			}
		});*/
		
		//Button 1
		btn1.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0)
			{
				lastProgressStatus = progressStatus;
				showProgressStatusLeft();
				//============= BAR=========================
				progressBarOperation(pace,1,nques);
				/*progressStatus = 100;
				new Thread(new Runnable(){
					public void run(){
						while(progressStatus>0){
							progressStatus -=1;
							handler.post(new Runnable(){
								public void run(){
									progressBar.setProgress(progressStatus);
								}
							});
							try{Thread.sleep(100);}
							catch(InterruptedException e){
								e.printStackTrace();}
						}
					}
				}).start();*/
				//=============================================
				String ansText = btn1.getText().toString();
				
				//An einai h swsth apanthsh s' auto to button
				if(ansText.equalsIgnoreCase( currentQ.getANSWER() ) ) //if(ansText.equalsIgnoreCase(answear[nques]))
				{
					try{Thread.sleep(500);}
					catch(InterruptedException e){
						e.printStackTrace();}
					//btn1.setBackgroundColor(Color.GREEN); 
					correct++;
					nques++;
					score +=lastProgressStatus;
					tvScore.setText(String.valueOf(score));
					//btn1.postDelayed(null, 3000);
					
					
					//An uparxoun erwthseis
					if(isPlayed<MAX_QUESTIONS)//if(questID<MAX_QUESTIONS)//if(nques<question.length)MAX_QUESTIONS
					{
						try{Thread.sleep(500);}
						catch(InterruptedException e){
							e.printStackTrace();}
						//Ananaiwnw erwthsh k apanthseis
						//++++++++++++++++++++++++++++++++++
						/*tv.setText(question[nques]);
						btn1.setText(options[nques*4]);
						btn2.setText(options[nques*4+1]);
						btn3.setText(options[nques*4+2]);
						btn4.setText(options[nques*4+3]);
						btn1.setBackgroundColor(Color.GRAY);*/
						//++++++++++++++++++++++++++++++++++
						//questID=questionRandomChoose();//getRandomNumber();//questionRandomChoose();//!!!!!!
						questID=questionRandomChoose();
						currentQ=questionList.get(questID);
						showNewQuestion();
					}
					else
					{
						//Termatisthke to paixnidi
						winnerFlag=1;
						progressBarOperation(0,1,10);
						Intent in = new Intent(QuizActivity.this,ResultActivity.class);
						startActivity(in);
						finish();
					}
				}
				else//if(!ansText.equalsIgnoreCase(answear[nques]))
				{
					//game over
					//btn1.setBackgroundColor(Color.RED);
					progressBarOperation(0,1,404);
					wrongAnswer=1;
					//Intent in = new Intent(getApplicationContext(),ResultActivity.class);
					//Intent in = new Intent(getBaseContext(),ResultActivity.class);
					Intent in = new Intent(QuizActivity.this,ResultActivity.class);
					startActivity(in);
					finish();
				}
			}
		});
		
		//Button 2
		btn2.setOnClickListener(new OnClickListener(){
					
			@Override
			public void onClick(View arg0)
			{
				lastProgressStatus = progressStatus;
				showProgressStatusLeft();
				//============= BAR=========================
				progressBarOperation(pace,2,nques);
				/*progressStatus = 100;
				new Thread(new Runnable(){
					public void run(){
						while(progressStatus>0){
							progressStatus -=1;
							handler.post(new Runnable(){
								public void run(){
									progressBar.setProgress(progressStatus);
								}
							});
							try{Thread.sleep(100);}
							catch(InterruptedException e){
								e.printStackTrace();}
						}
					}
				}).start();*/
				//=============================================
				String ansText = btn2.getText().toString();
						
				//An einai h swsth apanthsh s' auto to button
				if(ansText.equalsIgnoreCase( currentQ.getANSWER() ) )//if(ansText.equalsIgnoreCase(answear[nques]))
				{
					try{Thread.sleep(500);}
					catch(InterruptedException e){
						e.printStackTrace();}
					//btn2.setBackgroundColor(Color.GREEN); 
					nques++;
					correct++;
					score +=lastProgressStatus;
					tvScore.setText(String.valueOf(score));
					//btn2.postDelayed(null, 3000);
					
					
					//An uparxoun erwthseis
					if(isPlayed<MAX_QUESTIONS)//if(questID<MAX_QUESTIONS)//if(nques<question.length)
					{
						try{Thread.sleep(500);}
						catch(InterruptedException e){
							e.printStackTrace();}
						//Ananaiwnw erwthsh k apanthseis
						//++++++++++++++++++++++++++++++++++
						/*tv.setText(question[nques]);
						btn1.setText(options[nques*4]);
						btn2.setText(options[nques*4+1]);
						btn3.setText(options[nques*4+2]);
						btn4.setText(options[nques*4+3]);
						btn2.setBackgroundColor(Color.GRAY);*/
						//++++++++++++++++++++++++++++++++++
						//questID=getRandomNumber();//questionRandomChoose();//!!!!!!
						questID=questionRandomChoose();
						currentQ=questionList.get(questID);
						showNewQuestion();
					}
					else
					{
						//Termatisthke to paixnidi
						progressBarOperation(0,2,10);
						winnerFlag=1;
						Intent in = new Intent(QuizActivity.this,ResultActivity.class);
						startActivity(in);
						finish();
					}
				}				
				else//if(!ansText.equalsIgnoreCase(answear[nques]))
				{
					//game over
					progressBarOperation(0,2,404);
					//btn2.setBackgroundColor(Color.RED);
					wrongAnswer=1;
					finish();
					//Intent in = new Intent(getApplicationContext(),ResultActivity.class);
					//Intent in = new Intent(getBaseContext(),ResultActivity.class);
					Intent in = new Intent(QuizActivity.this,ResultActivity.class);
					startActivity(in);
					finish();
					
				}
			}
			});
		
		//Button 3
		btn3.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0)
			{
				lastProgressStatus = progressStatus;
				showProgressStatusLeft();
				//============= BAR=========================
				progressBarOperation(pace,3,nques);
				/*progressStatus = 100;
				new Thread(new Runnable(){
					public void run(){
						while(progressStatus>0){
							progressStatus -=1;
							handler.post(new Runnable(){
								public void run(){
									progressBar.setProgress(progressStatus);
								}
							});
							try{Thread.sleep(100);}
							catch(InterruptedException e){
								e.printStackTrace();}
						}
					}
				}).start();*/
				//=============================================
				String ansText = btn3.getText().toString();
								
				//An einai h swsth apanthsh s' auto to button
				if(ansText.equalsIgnoreCase( currentQ.getANSWER() ) )//if(ansText.equalsIgnoreCase(answear[nques]))
				{
					try{Thread.sleep(500);}
					catch(InterruptedException e){
						e.printStackTrace();}
					//btn3.setBackgroundColor(Color.GREEN); 
					nques++;
					correct++;
					score +=lastProgressStatus;
					tvScore.setText(String.valueOf(score));
					//btn3.postDelayed(null, 3000);
					
					
					//An uparxoun erwthseis
					if(isPlayed<MAX_QUESTIONS)//if(questID<MAX_QUESTIONS)//if(nques<question.length)
					{
						try{Thread.sleep(500);}
						catch(InterruptedException e){
							e.printStackTrace();}
						//Ananaiwnw erwthsh k apanthseis
						//++++++++++++++++++++++++++++++++++
						/*tv.setText(question[nques]);
						btn1.setText(options[nques*4]);
						btn2.setText(options[nques*4+1]);
						btn3.setText(options[nques*4+2]);
						btn4.setText(options[nques*4+3]);
						btn3.setBackgroundColor(Color.GRAY);*/
						//++++++++++++++++++++++++++++++++++
						//questID=getRandomNumber();//questionRandomChoose();//!!!!!!
						questID=questionRandomChoose();
						currentQ=questionList.get(questID);
						showNewQuestion();
					}
					else
					{
						//Termatisthke to paixnidi
						progressBarOperation(0,3,10);
						winnerFlag=1;
						Intent in = new Intent(QuizActivity.this,ResultActivity.class);
						startActivity(in);
						finish();
					}
				}
				else//if(!ansText.equalsIgnoreCase(answear[nques]))
				{
					//game over
					progressBarOperation(0,3,404);
					wrongAnswer=1;
					//btn3.setBackgroundColor(Color.RED);
					finish();
					//Intent in = new Intent(getApplicationContext(),ResultActivity.class);
					//Intent in = new Intent(getBaseContext(),ResultActivity.class);
					Intent in = new Intent(QuizActivity.this,ResultActivity.class);
					startActivity(in);
					finish();
					
				}
			}
			});
		
		//Button 4
		btn4.setOnClickListener(new OnClickListener(){
				
			@Override
			public void onClick(View arg0)
			{
				lastProgressStatus = progressStatus;
				showProgressStatusLeft();
				//============= BAR=========================
				progressBarOperation(pace,4,nques);
				/*progressStatus = 100;
				new Thread(new Runnable(){
					public void run(){
						while(progressStatus>0){
							progressStatus -=1;
							handler.post(new Runnable(){
								public void run(){
									progressBar.setProgress(progressStatus);
								}
							});
							try{Thread.sleep(100);}
							catch(InterruptedException e){
								e.printStackTrace();}
						}
					}
				}).start();*/
				//=============================================
				String ansText = btn4.getText().toString();
										
				//An einai h swsth apanthsh s' auto to button
				if(ansText.equalsIgnoreCase( currentQ.getANSWER() ) )//if(ansText.equalsIgnoreCase(answear[nques]))
				{
					try{Thread.sleep(500);}
					catch(InterruptedException e){
						e.printStackTrace();}
					//btn4.setBackgroundColor(Color.GREEN); 
					nques++;
					correct++;
					//btn4.postDelayed(null, 3000);
					score +=lastProgressStatus;
					tvScore.setText(String.valueOf(score));
					
					//An uparxoun erwthseis
					if(isPlayed<MAX_QUESTIONS)//if(questID<MAX_QUESTIONS)//if(nques<question.length)
					{
						try{Thread.sleep(500);}
						catch(InterruptedException e){
							e.printStackTrace();}
						//Ananaiwnw erwthsh k apanthseis
						//+++++++++++++++++++++++++++++++
						/*tv.setText(question[nques]);
						btn1.setText(options[nques*4]);
						btn2.setText(options[nques*4+1]);
						btn3.setText(options[nques*4+2]);
						btn4.setText(options[nques*4+3]);
						btn4.setBackgroundColor(Color.GRAY);*/
						//+++++++++++++++++++++++++++++++
						//questID=getRandomNumber();//questionRandomChoose();//!!!!!!
						questID=questionRandomChoose();
						currentQ=questionList.get(questID);
						showNewQuestion();
					}
					else
					{
						//Termatisthke to paixnidi
						progressBarOperation(0,4,10);
						winnerFlag=1;
						Intent in = new Intent(QuizActivity.this,ResultActivity.class);
						startActivity(in);
						finish();
					}
				}
				else//if(!ansText.equalsIgnoreCase(answear[nques]))
				{
					//game over
					progressBarOperation(0,4,404);
					wrongAnswer=1;
					//btn4.setBackgroundColor(Color.RED);
					finish();
					//Intent in = new Intent(getApplicationContext(),ResultActivity.class);
					//Intent in = new Intent(getBaseContext(),ResultActivity.class);
					Intent in = new Intent(QuizActivity.this,ResultActivity.class);
					startActivity(in);
					finish();
					
				}
			}
			});
		
		/*public public progrBarFilling()
		{
			while(progressStatus < 100)
			{
				Thread.sleep(50);
				progressStatus = progress++;
				
				//Update the bar
				handler.post(new Runnable(){
						
						public void run(){
							progressBar.setProgress(progressStatus);
						}
				});
			}
		}//telos progressBarOperation()*/
		
		//}//telos while
	}//telos onCreate()
	
	public void progressBarOperation(final int p,final int b,final int q)
	{
		progressStatus = 100;
		new Thread(new Runnable(){
			public void run(){
				while(progressStatus>0){
					//progressStatus -=1;
					progressStatus -=p;
					handler.post(new Runnable(){
						public void run(){
							if(progressStatus==0 && winnerFlag==0 && wrongAnswer==0)
							{
								//showScore();
								//finish();
								barEnd();
								root(p,b,q);
								Intent in = new Intent(QuizActivity.this,ResultActivity.class);
								startActivity(in);
								finish();
							}
							progressBar.setProgress(progressStatus);
						}
					});
					try{Thread.sleep(100);}
					catch(InterruptedException e){
						e.printStackTrace();}
					
					/*if(progressStatus==0)
					{
						//showScore();
						//finish();
						Intent in = new Intent(QuizActivity.this,ResultActivity.class);
						startActivity(in);
						//finish();
					}*/
				}
			}
		}).start();
	}
	
	//====================Boh8htika Mhnumata=========================================
	public void showProgressStatusLeft()
	{
		//messages = "ProgressBar="+ String.valueOf(progressStatus);
		Toast.makeText(this,"progressStatus="+progressStatus, Toast.LENGTH_SHORT).show();
	}
	
	public void showScore()
	{
		//messages = "ProgressBar="+ String.valueOf(progressStatus);
		Toast.makeText(this,"Score="+score, Toast.LENGTH_SHORT).show();
	}
	
	public void barEnd()
	{
		//messages = "ProgressBar="+ String.valueOf(progressStatus);
		Toast.makeText(this,"Bar is finished", Toast.LENGTH_SHORT).show();
	}
	
	public void root(int p,int b,int q)
	{
		//messages = "ProgressBar="+ String.valueOf(progressStatus);
		String message;
		message="p:"+p+"b:"+b+"q"+q;
		Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
	}
	public void showSize()
	{
		//messages = "ProgressBar="+ String.valueOf(progressStatus);
		Toast.makeText(this,"Size="+questionList.size(), Toast.LENGTH_SHORT).show();
	}
	
	public void showI(int i)
	{
		//messages = "ProgressBar="+ String.valueOf(progressStatus);
		Toast.makeText(this,"I="+i, Toast.LENGTH_SHORT).show();
	}
	
	//========================================================================
	public int questionRandomChoose()
	{
		int randNumber;
		
		do{
			Random rand = new Random();
			randNumber =rand.nextInt(questionList.size());
			//randNumber =rand.nextInt(5);<---- to dokimasa k leitourgei
			
		}while(alreadyChoosed.get(randNumber)!=0);//elegxw an exw ksanaparei thn erwthsh

		alreadyChoosed.set(randNumber,1);//bazw 1 sto index pou tha parw erwthsh  
										//wste na mhn thn ksanaparw

		return randNumber;
	}
	//Generate a random integer
	public int getRandomNumber()
	{
		int number;
		
		Random rand = new Random();
		number =rand.nextInt(5);
		//System.out.println(number);
		return number;
	}
	
	private void showNewQuestion()
	{
		tv.setText(currentQ.getQUESTION());
		btn1.setText(currentQ.getOPTA());
		btn2.setText(currentQ.getOPTB());
		btn3.setText(currentQ.getOPTC());
		btn4.setText(currentQ.getOPTD());
		/*txtQuestion.setText(currentQ.getQUESTION());
		rda.setText(currentQ.getOPTA());
		rdb.setText(currentQ.getOPTB());
		rdc.setText(currentQ.getOPTC());*/
		//!!!questID++;
		isPlayed++;
	}
}
