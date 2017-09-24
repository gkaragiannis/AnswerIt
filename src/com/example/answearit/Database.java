package com.example.answearit;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "triviaQuiz";
	// tasks table name
	private static final String TABLE_QUEST = "quest";
	// tasks Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; //correct option
	private static final String KEY_OPTA= "opta"; //option a
	private static final String KEY_OPTB= "optb"; //option b
	private static final String KEY_OPTC= "optc"; //option c
	private static final String KEY_OPTD= "optd"; //option d
	private static final String KEY_TYPE= "type"; 
	private SQLiteDatabase dbase;
	
	public Database(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		dbase=db;
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
				+ " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
				+KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, "
				+KEY_OPTD +" TEXT, "+KEY_TYPE+" TEXT)";
											
		db.execSQL(sql);		
		addQuestions();
		//db.close();
	}
	
	private void addQuestions()
	{
		//============================================= GEWGRAFIA ====================================================================
		//Question q1=new Question("What is JP?","Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "Jasa Programmer");
		Question q1=new Question("���� ����� � ���������� ��� ����������;","���������", "�������", " ���������", " ����","�������","����������");
		this.addQuestion(q1);
		//Question q2=new Question("where the JP place?", "Monas, Jakarta", "Gelondong, Bangun Tapan, bantul", "Gelondong, Bangun Tapan, bandul", "Gelondong, Bangun Tapan, bantul");
		Question q2=new Question("���� ��������� ���� ���� ���� ������������� ���������;","��������", "�������", "�������", "������","�������","����������");
		this.addQuestion(q2);
		//Question q3=new Question("who is CEO of the JP?","Usman and Jack", "Jack and Rully","Rully and Usman", "Rully and Usman" );
		Question q3=new Question("���� ��� ��� �������� ����� ��� ��������� � ������� ��������;","��������", "�������", "�������", "��������","�������","����������");
		this.addQuestion(q3);
		//Question q4=new Question("what do you know about JP?", "JP is programmer home", "JP also realigy home", "all answer is true","all answer is true");
		Question q4=new Question("���� ��� ��� �������� ����� ���� �� ���������� ������;","�������", "������", "�������", "������","������","����������");
		this.addQuestion(q4);
		//Question q5=new Question("what do you learn in JP?","Realigy","Programming","all answer is true","all answer is true");
		Question q5=new Question("����� ����� � ������ ��� ���� 3 ���������� ������� �� �� ������� ���� , ����� , �������;","��������", "�������", "�������", "������","�������","����������");
		this.addQuestion(q5);
		//============================================= ISTORIA ====================================================================
		/*Question q6=new Question("� ��������� ������� ��� ������� ��� ���� ��� ��������. ����� ���� � ������� ��� ������ �� ���� �� ����;","����������", "������� ", "����������", "������","����������","��������");
		this.addQuestion(q6);
		Question q7=new Question("���� ����� � �������� ��� ���������;","470 �.�.", "490 �.�.", "480 �.�.", "500 �.�.","480 �.�.","��������");
		this.addQuestion(q7);
		Question q8=new Question("����� ������� ������������ ��� ���� �� �������� ��� �������� ���� �� �������� ��� ������ ��� ����������������� (1453);","5.000", "80.000", "20.000", "10.000","80.000","��������");
		this.addQuestion(q8);
		Question q9=new Question("���� ����� � ���� ��� �������;","1815", "1810", "1820", "1805","1815","��������");
		this.addQuestion(q9);
		Question q10=new Question("����� ��������� ��� 1821 ���� ������ �� � ������������� ��� ����� ��� ���� ��� �������;","������ ���������", "�����������", "�������� ����������", "�������� �����������","�������� ����������","��������");
		this.addQuestion(q10);
		//============================================= EPISTHMES ====================================================================
		Question q11=new Question("���� ����� ������� �� ������� ��� �������� ��� ������� ��� �����������","78%", "33%", "50%", "21%","21%","���������");
		this.addQuestion(q11);*/
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV)
	{
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		// Create tables again
		onCreate(db);
	}
	
	// Adding new question
	public void addQuestion(Question quest) 
	{
			//SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(KEY_QUES, quest.getQUESTION()); 
			values.put(KEY_ANSWER, quest.getANSWER());
			values.put(KEY_OPTA, quest.getOPTA());
			values.put(KEY_OPTB, quest.getOPTB());
			values.put(KEY_OPTC, quest.getOPTC());
			values.put(KEY_OPTD, quest.getOPTD());
			values.put(KEY_TYPE, quest.getTYPE());
			// Inserting Row
			dbase.insert(TABLE_QUEST, null, values);		
	}
	
	public List<Question> getAllQuestions() 
	{
		List<Question> quesList = new ArrayList<Question>();
		
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) 
		{
			do 
			{
				Question quest = new Question();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setOPTA(cursor.getString(3));
				quest.setOPTB(cursor.getString(4));
				quest.setOPTC(cursor.getString(5));
				quest.setOPTD(cursor.getString(6));
				quest.setTYPE(cursor.getString(7));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	
	public int rowcount()
	{
		int row=0;
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row=cursor.getCount();
		return row;
	}
}
