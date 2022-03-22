package com.example.deschiffresetdeslettres

import android.content.Context
import java.util.*

typealias Dictionary = List<String>

fun Context.loadDictionary(fileName: String) : Dictionary {
    val inputStream = this.assets.open(fileName)
    return inputStream.bufferedReader().useLines {
        lineSeq -> lineSeq.map { it.trim() }.filter { line -> line.isNotEmpty() }.toList().sorted()
    }

}