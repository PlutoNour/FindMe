package com.findme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

public class BDD {


	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "findme.db";

	//
		private static final String TABLE_WORDS = "words";

		private static final String COL_ID_WORDS = "id";
		private static final int NUM_COL_ID_WORDS = 0;

		private static final String COL_WORD = "word";
		private static final int NUM_COL_WORD = 1;

		private static final String COL_LEVEL = "level";
		private static final int NUM_COL_LEVEL = 2;

	//
		private static final String TABLE_SCORE = "score";

		private static final String COL_ID_SCORE = "id";
		private static final int NUM_COL_ID_SCORE = 0;

		private static final String COL_NICKNAME = "nickname";
		private static final int NUM_COL_NICKNAME = 1;

		private static final String COL_SCORE = "score";
		private static final int NUM_COL_SCORE = 2;
	//

	private SQLiteDatabase bdd;
 
	private BaseSQLite maBaseSQLite;
 
	public BDD(Context context)
	{
		maBaseSQLite = new BaseSQLite(context, NOM_BDD, null, VERSION_BDD);
	}
 
	public void open()
	{
		bdd = maBaseSQLite.getWritableDatabase();
	}
 
	public void close()
	{
		bdd.close();
	}
 
	public SQLiteDatabase getBDD()
	{
		return bdd;
	}


////////

	public long insertWord(Word word) {

		open();
		
		ContentValues values = new ContentValues();
		values.put(COL_WORD, word.getWord());
		values.put(COL_LEVEL, word.getLevel());

		long retour= bdd.insert(TABLE_WORDS, null, values);
		close();
		return retour;
	}
 
	public int updateWord(Word word) {
		open();
		
		ContentValues values = new ContentValues();
		values.put(COL_WORD, word.getWord());
		values.put(COL_LEVEL, word.getLevel());

		int retour = bdd.update(TABLE_WORDS, values, COL_ID_WORDS + " = " +word.getId(), null);
		close();
		return retour;
		
	}
 
	public int removeWordWithID(int id) {
		open();
		
		int retour= bdd.delete(TABLE_WORDS , COL_ID_WORDS+" = "+id , null);
		close();
		return retour;
	}
	
	public int removeAllWord() {
		open();
		
		int retour= bdd.delete(TABLE_WORDS, null, null);
		close();
		return retour;
	}

	public Vector<Word> getAllWord() {

		open();

		Cursor c = bdd.query(TABLE_WORDS, new String[] {COL_ID_WORDS , COL_WORD ,COL_LEVEL}, null , null, null, null, COL_ID_WORDS+" ASC");

		Vector<Word> v=new Vector<Word>();

		if(c != null)
		{
			while(c.moveToNext())
			{
				v.add(new Word(c.getInt(NUM_COL_ID_WORDS), c.getString(NUM_COL_WORD), c.getString(NUM_COL_LEVEL)));
			}

		}
		c.close();
		close();
		return v;
	}

	public Vector<Word> getWordByLevel(String level) {

		open();

		Cursor c = bdd.query(TABLE_WORDS, new String[] {COL_ID_WORDS , COL_WORD ,COL_LEVEL}, COL_LEVEL + " = " + level , null, null, null, COL_ID_WORDS+" ASC");

		Vector<Word> v=new Vector<Word>();

		if(c != null)
		{
			while(c.moveToNext())
			{
				v.add(new Word(c.getInt(NUM_COL_ID_WORDS), c.getString(NUM_COL_WORD), c.getString(NUM_COL_LEVEL)));
			}

		}
		c.close();
		close();
		return v;
	}


///////




}
