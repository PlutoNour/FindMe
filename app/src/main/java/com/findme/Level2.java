package com.findme;

import android.content.Context;

import java.util.Vector;

public class Level2
{
    private String[] array = {"cloud","chrome","logiciel","driver","android","base","bigdata"};
    private Context context;
    public int time;
    public Vector<Word> words;
    private BDD bdd;

    public Level2(Context context)
    {
        bdd = new BDD(context);
        this.context=context;
        this.time = 30;
        this.words = getWords();

        if(words==null || words.size()==0)
        {
            insertWords();
        }
    }

    public Vector<Word> getWords()
    {
        return bdd.getWordByLevel("2");
    }

    private void insertWords()
    {
        for(int i = 0 ; i<array.length ; i++)
        {
            Word word = new Word( 0 , array[i],"2");
            bdd.insertWord(word);
        }
    }
}
