package com.findme;

import android.content.Context;

import java.util.Vector;

public class Level3
{
    private String[] array = {"kubernates","rancher","emulateur","docker","travel","night","screen"};
    private Context context;
    public int time;
    public Vector<Word> words;
    private BDD bdd;

    public Level3(Context context)
    {
        bdd = new BDD(context);
        this.context=context;
        this.time = 35;
        this.words = getWords();

        if(words==null || words.size()==0)
        {
            insertWords();
        }
    }

    public Vector<Word> getWords()
    {
        return bdd.getWordByLevel("3");
    }

    private void insertWords()
    {
        for(int i = 0 ; i<array.length ; i++)
        {
            Word word = new Word( 0 , array[i],"3");
            bdd.insertWord(word);
        }
    }
}
