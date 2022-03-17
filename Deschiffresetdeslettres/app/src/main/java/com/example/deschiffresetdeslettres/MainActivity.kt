package com.example.deschiffresetdeslettres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val letterPicker by lazy { LetterPicker.buildFromResource(this, R.raw.letter_frequencies)}
    val drawTextView by lazy { findViewById<TextView>(R.id.drawTextView) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //drawTextView.setOnClickListener{drawTextView.text = letterPicker.pickLetters(9)}
        //Log.d( "msg: ",s)}

        val button by lazy { findViewById<Button>(R.id.nextButton) }
        button.setOnClickListener{drawTextView.text = letterPicker.pickLetters(9)}
    }



}