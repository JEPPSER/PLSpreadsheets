package com.example.jesper.plspreadsheets.create

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import com.example.jesper.plspreadsheets.R
import com.example.jesper.plspreadsheets.model.Day
import com.example.jesper.plspreadsheets.model.Exercise
import com.example.jesper.plspreadsheets.model.Week
import java.util.*

/**
 * Activity for creating a week in the spreadsheet.
 *
 * @author Jesper Bergstrom
 * @name CreateWeekActivity.kt
 * @version 0.00.00
 */
class CreateWeekActivity : AppCompatActivity() {

    var week: Week ?= null
    var weekText: TextView ?= null
    var dayList: GridLayout ?= null
    var btnList: ArrayList<Button> = ArrayList<Button>()
    var days = ArrayList<Day>()

    val dayNames = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_week)

        // Get the week that was clicked
        val b = this.intent.extras
        week = b.getSerializable("week") as Week

        // Get Grid
        dayList = findViewById(R.id.dayList) as GridLayout
        createDays()

        // Get the week text
        weekText = findViewById(R.id.weekText) as TextView
        weekText!!.text = "Week " + week!!.weekNumber
    }

    /**
     * Creates all the Day objects for the week and all its components.
     */
    private fun createDays(){
        var i = 0
        while(i < 7){
            val day = Day()
            day.exercises = ArrayList<Exercise>()
            day.name = dayNames[i]
            days.add(day)
            val btn = Button(this)
            btn.text = "+ Exercise"
            btnList.add(btn)
            btn.setOnClickListener(OnClickListener {
                val create = Intent(this@CreateWeekActivity, CreateDayActivity::class.java)
                val b = Bundle()
                b.putSerializable("day", day)
                create.putExtras(b)
                startActivity(create)
            })
            val dayText = TextView(this)
            dayText.text = dayNames.get(i)
            dayList!!.addView(dayText)
            dayList!!.addView(btn)
            i++
        }
    }
}
