package com.findme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BaseSQLite extends SQLiteOpenHelper {


	/////////////

		private static final String TABLE_WORDS = "words";

		private static final String COL_ID_WORDS = "id";
		private static final String COL_WORD = "word";
		private static final String COL_LEVEL = "level";

		private static final String CREATE_TABLE_WORDS = "CREATE TABLE " + TABLE_WORDS + " (" + COL_ID_WORDS + " INTEGER PRIMARY KEY AUTOINCREMENT , "+ COL_WORD + " TEXT , " + COL_LEVEL + " TEXT ); ";

	/////////////

		private static final String TABLE_SCORE = "score";

		private static final String COL_ID_SCORE = "id";
		private static final String COL_NICKNAME = "nickname";
		private static final String COL_SCORE = "score";

		private static final String CREATE_TABLE_SCORE = "CREATE TABLE " + TABLE_SCORE + " (" + COL_ID_SCORE + " INTEGER PRIMARY KEY AUTOINCREMENT , "+ COL_NICKNAME + " TEXT , " + COL_SCORE + " TEXT );";

	/////////////
	
	public BaseSQLite(Context context, String name, CursorFactory factory, int version)
	{
		super(context, name, factory, version);
	}
 
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(CREATE_TABLE_WORDS);
		db.execSQL(CREATE_TABLE_SCORE);
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE " + TABLE_WORDS + ";");
		db.execSQL("DROP TABLE " + TABLE_SCORE + ";");

		onCreate(db);
	}
 
}
