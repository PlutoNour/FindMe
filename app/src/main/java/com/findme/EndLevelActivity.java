package com.findme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class EndLevelActivity extends Activity implements View.OnClickListener {

    private int score = 0;
    private int level = 1;
    private TextView txt_score , txt;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_level);

        txt_score = (TextView)findViewById(R.id.txt_score);
        txt = (TextView)findViewById(R.id.txt);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        if(b!=null && b.containsKey("score"))
        {
            score = b.getInt("score");
            txt_score.setText(""+score);
            if(score>20)
            {
                txt.setText("Bravo votre score:");
                btn.setText("NEXT LEVEL");
            }
            else
            {
                txt.setText("Votre score:");
                btn.setText("RETRY");
            }
        }
        if(b!=null && b.containsKey("level"))
        {
            level = b.getInt("level");
        }

    }



    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn:

                if(score>20) // next level
                {
                    level++;
                    Intent intent = new Intent(EndLevelActivity.this , JeuActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("level" , level);
                    intent.putExtras(b);
                    startActivity(intent);
                    finish();
                }
                else // retry
                {
                    Intent intent = new Intent(EndLevelActivity.this , JeuActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("level" , level);
                    intent.putExtras(b);
                    startActivity(intent);
                    finish();
                }

                break;
        }
    }
}
