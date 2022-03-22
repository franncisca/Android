package com.example.deschiffresetdeslettres

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val letterPicker by lazy { LetterPicker.buildFromResource(this, R.raw.letter_frequencies) }
    val anagramTextView by lazy { findViewById<TextView>(R.id.drawTextView) }
    val startButton by lazy { findViewById<Button>(R.id.startButton) }
    val validButton by lazy { findViewById<Button>(R.id.validButton) }
    val propositionText by lazy { findViewById<EditText>(R.id.proposition) }
    val displayAnswer by lazy { findViewById<TextView>(R.id.display) }

    var bestAnagrams = listOf<String>();
    var maxSize = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //drawTextView.setOnClickListener{drawTextView.text = letterPicker.pickLetters(9)}
        //Log.d( "msg: ",s)}
        val dict by lazy { loadDictionary("french_dict.txt") }

        startButton.setOnClickListener { anagramTextView.text = letterPicker.pickLetters(9) }

        validButton.setOnClickListener {
            var currentText = propositionText.text

            if(anagramTextView.text.toString().containsAnagram(currentText.toString()) && currentText.toString().containsLegalAnagram(currentText.toString(),dict)){
                    if (currentText.length >= maxSize) {
                        Log.d("size",maxSize.toString())
                        maxSize = currentText.length;
                        bestAnagrams = bestAnagrams + listOf<String>(currentText.toString())
                        displayAnswer.text = bestAnagrams.toString();
                    }
            }
            maxSize = 0

        }
    }

    /* Determine if the parameter is an anagram of `this`
* proposition must contain only letters of `this` (with the same or different order)
*/
    fun String.containsAnagram(proposition: String): Boolean {
        var tabAnagram = IntArray(26);
        var tabPropo= IntArray(26);
        for (c in proposition) {
            if (c !in anagramTextView.text.toString() ) {
                return false
            }
            tabPropo[c-'a']++
        }

        for(letter in anagramTextView.text.toString() ){
            tabAnagram[letter-'a'] ++
        }

        for (index in 0..25){
            if (tabPropo[index] > tabAnagram[index]){
                return false;
            }
        }
        return true;
    }

    fun String.containsLegalAnagram(proposition: String, dictionary: Dictionary): Boolean =
        this.containsAnagram(proposition) && dictionary.binarySearch(proposition) >= 0


}




