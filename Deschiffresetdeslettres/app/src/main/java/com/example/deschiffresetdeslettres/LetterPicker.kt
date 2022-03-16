package com.example.deschiffresetdeslettres

import android.content.Context
import java.security.AccessControlContext

data class CumulateFrequency(val letter: Char, val frequency: Int)

fun List<CumulateFrequency>.pickLetter(): Char{
    //r: a random element with frequency from 0 to last frequency
    val r = (0 until this.last().frequency).random()
    return first{it.frequency > r}.letter
}

//Another way to pick a letter
fun List<CumulateFrequency>.pickLetter2() =
    (0 until this.last().frequency).random().let { r -> first{it.frequency > r}.letter }

fun String.toCharIntPair(): Pair<Char, Int>? {
    return split("\t").let{ l ->
        val c = l.getOrNull(0)?.getOrNull(0)
        val f = l.getOrNull(1)?.toIntOrNull()

        if (c != null && f != null)
            Pair(c,f)
        else
            null
    }
}

class LetterPicker(frequencies: Map<Char, Int>) {
    val cumulateFrequency: List<CumulateFrequency> = kotlin.run {
        val list = mutableListOf<CumulateFrequency>()
        var sum = 0
        frequencies.forEach{ entry ->
            list += CumulateFrequency(entry.key, sum + entry.value)
            sum += entry.value
        }
        list
    }

    fun pickLetters(n: Int) =
        (0 until n).map { cumulateFrequency.pickLetter() }.joinToString("")

    companion object {
        fun buildFromResource(context: Context, resourceId: Int = R.raw.letter_frequencies): LetterPicker{
            val inputStream = context.resources.openRawResource(resourceId)
            val frequencyMap = inputStream.bufferedReader()
                .useLines { lineSeq -> lineSeq.mapNotNull { it.toCharIntPair() }.toMap()
                }

            return LetterPicker(frequencyMap)
        }


        fun buildFromResource2(context: Context, resourceId: Int = R.raw.letter_frequencies) =
            context.resources.openRawResource(resourceId).bufferedReader()
                .useLines { lineSeq -> lineSeq.mapNotNull { it.toCharIntPair() }.toMap()
                    .let { LetterPicker(it) }
                }

    }


}
