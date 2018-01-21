package com.findme;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        /*findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);*/

    }

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent(MainActivity.this , JeuActivity.class);
        Bundle b = new Bundle();

        switch (v.getId())
        {
            case R.id.btn1:
                b.putInt("level" , 1);
                intent.putExtras(b);
                startActivity(intent);
                break;

            case R.id.btn2:
                b.putInt("level" , 2);
                intent.putExtras(b);
                startActivity(intent);
                break;

            case R.id.btn3:
                b.putInt("level" , 3);
                intent.putExtras(b);
                startActivity(intent);
                break;
        }

    }
}
