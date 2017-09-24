package com.example.jesper.plspreadsheets.view

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import com.example.jesper.plspreadsheets.R

class MaxActivity : Activity() {

    var grid: GridLayout?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_max)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels
        window.setLayout((width * 0.8).toInt(), (height * 0.8).toInt())

        grid = findViewById<View>(R.id.grid) as GridLayout

        val exString = intent.extras.getString("exercises")
        println(exString)
        loadGrid(exString)
    }

    private fun loadGrid(ex : String){
        val parts = ex.split(",")
        var i = 0
        while(i < parts.size){
            val exText = TextView(this)
            exText.text = parts[i] + ": "
            exText.textSize = 20F
            val exEdit = EditText(this)
            exEdit.width = 200
            grid!!.addView(exText)
            grid!!.addView(exEdit)
            i++
        }
    }
}
