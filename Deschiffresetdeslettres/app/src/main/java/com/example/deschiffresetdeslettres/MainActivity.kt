package com.example.deschiffresetdeslettres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    val letterPicker by lazy { LetterPicker.buildFromResource(this, R.raw.letter_frequencies) }
    val drawTextView by lazy { findViewById<TextView>(R.id.drawTextView) }
    val startButton by lazy { findViewById<Button>(R.id.startButton) }
    val validButton by lazy { findViewById<Button>(R.id.validButton) }
    val propositionText by lazy { findViewById<EditText>(R.id.proposition) }
    val answerText by lazy { findViewById<TextView>(R.id.answer) }

    var bestAnagrams = listOf<String>();
    var biggestSize = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //drawTextView.setOnClickListener{drawTextView.text = letterPicker.pickLetters(9)}
        //Log.d( "msg: ",s)}

        startButton.setOnClickListener { drawTextView.text = letterPicker.pickLetters(9) }

        validButton.setOnClickListener {
            var currentText = propositionText.text
            if (currentText.length >= biggestSize) {
                biggestSize = currentText.length;
                bestAnagrams = bestAnagrams + listOf<String>(currentText.toString())
                answerText.text = bestAnagrams.toString();
            }
        }
    }


}

/* Determine if the parameter is an anagram of `this`
* proposition must contain only letters of `this` (with the same or different order)
*/
fun String.containsAnagram(proposition: String): Boolean {
    var answer = "answer";
    for (s in proposition) {
        if (s !in answer) {
            return false
        }

    }
    return false
}


