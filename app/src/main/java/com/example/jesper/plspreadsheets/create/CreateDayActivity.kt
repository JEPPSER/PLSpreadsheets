package com.example.jesper.plspreadsheets.create

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.TextView
import com.example.jesper.plspreadsheets.R
import com.example.jesper.plspreadsheets.model.Day

/**
 * Popup window for creating a Day.
 *
 * @author Jesper Bergstrom
 * @name CreateDayActivity.kt
 * @version 0.00.00
 */
class CreateDayActivity : Activity() {

    var day: Day ?= null
    var dayText: TextView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_day)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels
        getWindow().setLayout((width * 0.8).toInt(), (height * 0.8).toInt())

        // Get the day that was clicked
        val b = this.intent.extras
        day = b.getSerializable("day") as Day

        // Get dayText
        dayText = findViewById<View>(R.id.dayText) as TextView
        dayText!!.text = day!!.name
    }
}
