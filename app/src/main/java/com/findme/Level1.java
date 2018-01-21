package com.findme;

import android.content.Context;

import java.util.Vector;

public class Level1
{
    private String[] array = {"RAM","mémoire","java",".net","disk",",souris","ecran"};
    private Context context;
    public int time;
    public Vector<Word> words;
    private BDD bdd;

    public Level1(Context context)
    {
        bdd = new BDD(context);
        this.context=context;
        this.time = 40;
        this.words = getWords();

        if(words==null || words.size()==0)
        {
            insertWords();
        }
    }

    public Vector<Word> getWords()
    {
        return bdd.getWordByLevel("1");
    }

    private void insertWords()
    {
        for(int i = 0 ; i<array.length ; i++)
        {
            Word word = new Word( 0 , array[i],"1");
            bdd.insertWord(word);
        }
    }
}
