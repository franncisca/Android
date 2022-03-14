package com.example.tp1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DiceGameActivity extends AppCompatActivity {
    private int first;
    private int second;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        //more flexible
        /*
        Button b = (Button)findViewById(R.id.quitButton);
        b.setOnClickListener(button -> {
            Toast t = Toast.makeText(this, "Let's quit the activity", Toast.LENGTH_SHORT);
            t.show();
        });

        */

        TextView tv = findViewById(R.id.questionText);
        tv.setOnClickListener(tvl -> {
            tv.setText("lancelot");
        });
        //void Callback<Arg>
    }


    public int displayRandomValue(View view){
        Random random = new Random();
        int value = random.nextInt(5)+1;
        return value;
    }


    //less flexible
    public void onQuitClicked(View view) {
        Toast t = Toast.makeText(this, getResources().getString(R.string.name_of_the_string), Toast.LENGTH_SHORT);
        t.show();
        finish();
    }
}
