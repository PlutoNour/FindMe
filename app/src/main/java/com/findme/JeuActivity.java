package com.findme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class JeuActivity extends Activity implements View.OnClickListener {

    TextView time;
    TextView txt_score;
    EditText editMot;
    LinearLayout list_lettres;
    LayoutInflater inflater;

    CountDownTimer timer;

    int level = 1; // current level
    int score = 0;
    Vector<Word> words = new Vector<>(); // liste des mots

    Word currentWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        time = (TextView) findViewById(R.id.time);
        txt_score = (TextView) findViewById(R.id.score);
        editMot = (EditText) findViewById(R.id.editMot);
        list_lettres = (LinearLayout) findViewById(R.id.list_lettres);
        findViewById(R.id.btnOk).setOnClickListener(this);
        inflater = getLayoutInflater();

        Bundle b = getIntent().getExtras();
        if(b!=null && b.containsKey("level"))
        {
            level = b.getInt("level");
        }

        switch (level)
        {
            case 1:
                Level1 level1 = new Level1(getApplicationContext());
                words = level1.getWords();
                currentWord = randomWord();
                if(currentWord!=null)
                {
                    initLettres(currentWord.getWord());
                }
                Timer(level1.time);
                break;

            case 2:
                Level2 level2 = new Level2(getApplicationContext());
                words = level2.getWords();
                currentWord = randomWord();
                if(currentWord!=null)
                {
                    initLettres(currentWord.getWord());
                }
                Timer(level2.time);
                break;

            case 3:
                Level3 level3 = new Level3(getApplicationContext());
                words = level3.getWords();
                currentWord = randomWord();
                if(currentWord!=null)
                {
                    initLettres(currentWord.getWord());
                }
                Timer(level3.time);
                break;
        }

    }

    private void Timer(int time_stage)
    {
        timer = new CountDownTimer(time_stage*1000, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                time.setText("" + millisUntilFinished / 1000);
            }
            public void onFinish()
            {
                Intent intent = new Intent(JeuActivity.this , EndLevelActivity.class);
                Bundle b = new Bundle();
                b.putInt("score" , score);
                b.putInt("level" , level);
                intent.putExtras(b);
                startActivity(intent);
                finish();
            }

        }.start();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        timer.cancel();
    }

    // get random word from list and delete that word from the list
    private Word randomWord()
    {
        if(words.size()>0)
        {
            Random r = new Random();
            int randomIndex = r.nextInt(words.size());
            Word word = words.get(randomIndex);
            words.remove(randomIndex);
            return word;
        }
        else
        {
            return null;
        }

    }

    private void initLettres(String mot)
    {
        String[] array = mot.split("(?!^)");
        array = shuffleArray(array);

        int nbrLignes = array.length/4; // chaque ligne peut contenir jusqu'a 4 lettres
        nbrLignes++;

        list_lettres.removeAllViews();
        for(int i = 0 ; i < nbrLignes ; i++)
        {
            View v = inflater.inflate(R.layout.item_line_lettres,null);
            LinearLayout line = (LinearLayout) v.findViewById(R.id.list_lettres);
            for(int j = 0 ; j < 4 ; j++)
            {
                if(j+(4*i)<array.length)
                {
                    View lettre = inflater.inflate(R.layout.item_lettre , null);
                    ((TextView)lettre.findViewById(R.id.lettre)).setText(array[j+(4*i)]);
                    line.addView(lettre);
                }
            }
            list_lettres.addView(v);

        }

    }

    private String[] shuffleArray(String[] array)
    {
        // converting array to a List
        List<String> list = Arrays.asList(array);

        // Shuffling list elements
        Collections.shuffle(list);

        String[] retour = new String[list.size()];
        retour = list.toArray(retour);

        return retour;
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnOk:

                if(editMot.getText().toString().equals(currentWord.getWord()))
                {
                    Toast.makeText(getApplicationContext(), "Bravo", Toast.LENGTH_SHORT).show();
                    score+=10;
                    txt_score.setText(""+score);
                    currentWord = randomWord();
                    if(currentWord!=null)
                    {
                        initLettres(currentWord.getWord());
                    }
                    else
                    {
                        Intent intent = new Intent(JeuActivity.this , EndLevelActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("score" , score);
                        b.putInt("level" , level);
                        intent.putExtras(b);
                        startActivity(intent);
                        finish();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Réessayer", Toast.LENGTH_SHORT).show();
                }
                editMot.setText("");

                break;
        }
    }
}
