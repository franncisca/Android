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
    val displayAnswer by lazy { findViewById<TextView>(R.id.display) }


    //val anagram = letterPicker.pickLetters(9);

    var bestAnagrams = listOf<String>();
    var maxSize = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //drawTextView.setOnClickListener{drawTextView.text = letterPicker.pickLetters(9)}
        //Log.d( "msg: ",s)}

        startButton.setOnClickListener { drawTextView.text = letterPicker.pickLetters(9) }

        validButton.setOnClickListener {
            var currentText = propositionText.text

            if (! bestAnagrams.contains(currentText.toString()) && currentText.length >= maxSize) {
                maxSize = currentText.length;
                bestAnagrams = bestAnagrams + listOf<String>(currentText.toString())
                displayAnswer.text = bestAnagrams.toString();
            }
        }
    }

    /* Determine if the parameter is an anagram of `this`
* proposition must contain only letters of `this` (with the same or different order)
*/
    fun String.containsAnagram(proposition: String): Boolean {
        var tabAnagram = IntArray(26);
        var tabPropo= IntArray(26);
        for (c in proposition) {
            if (c !in drawTextView.text.toString() ) {
                return false
            }
            tabPropo[c - 'a']++
        }

        for(letter in drawTextView.text.toString() ){
            tabAnagram[letter-'a'] ++
        }

        for (index in 0..26){
            if (tabPropo[index] > tabAnagram[index]){
                return false;
            }
        }
        return true;
    }


}




