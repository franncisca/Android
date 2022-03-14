package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        TextView tv = findViewById(R.id.Question);
        String tvString = tv.getText().toString();
        tv.setOnClickListener(tvCurrent -> {
            int diceValue = getRandomValue();
            tv.setBackground(Color.rgb(0,diceValue*(255/6),0));
            tv.setText(tvString + " " + diceValue);
        });
    }


    public int getRandomValue(){
        Random random = new Random();
        return random.nextInt(6)+1;
    }

    //less flexible
    public void onQuitClicked() {
        Toast t = Toast.makeText(this, getResources().getString(R.string.name_of_the_string), Toast.LENGTH_SHORT);
        t.show();
        finish();
    }


}