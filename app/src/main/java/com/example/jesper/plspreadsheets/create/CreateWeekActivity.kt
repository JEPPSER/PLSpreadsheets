package com.example.jesper.plspreadsheets.create

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.jesper.plspreadsheets.R
import com.example.jesper.plspreadsheets.model.Week

/**
 * Activity for creating a week in the spreadsheet.
 *
 * @author Jesper Bergstrom
 * @name CreateWeekActivity.kt
 * @version 0.00.00
 */
class CreateWeekActivity : AppCompatActivity() {

    var week: Week ?= null
    var weekText: TextView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_week)

        // Get the week that was clicked
        var b = this.intent.extras
        week = b.getSerializable("week") as Week

        // Get the week text
        weekText = findViewById(R.id.weekText) as TextView
        weekText!!.text = "Week " + week!!.weekNumber
    }
}
