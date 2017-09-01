package com.example.jesper.plspreadsheets.create

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import com.example.jesper.plspreadsheets.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_week)

        // Get the week that was clicked
        var b = this.intent.extras
        week = b.getSerializable("week") as Week

        // Get Grid
        dayList = findViewById(R.id.dayList) as GridLayout
        createButtons()
        createDays()

        // Get the week text
        weekText = findViewById(R.id.weekText) as TextView
        weekText!!.text = "Week " + week!!.weekNumber
    }

    private fun createButtons(){
        val monBtn = Button(this)
        monBtn.text = "+ Exercise"
        val tueBtn = Button(this)
        tueBtn.text = "+ Exercise"
        val wedBtn = Button(this)
        wedBtn.text = "+ Exercise"
        val thuBtn = Button(this)
        thuBtn.text = "+ Exercise"
        val friBtn = Button(this)
        friBtn.text = "+ Exercise"
        val satBtn = Button(this)
        satBtn.text = "+ Exercise"
        val sunBtn = Button(this)
        sunBtn.text = "+ Exercise"
        btnList.add(monBtn)
        btnList.add(tueBtn)
        btnList.add(wedBtn)
        btnList.add(thuBtn)
        btnList.add(friBtn)
        btnList.add(satBtn)
        btnList.add(sunBtn)
    }

    private fun createDays(){
        val mon = TextView(this)
        mon.text = "Monday"
        dayList!!.addView(mon)
        dayList!!.addView(btnList[0])
        val tue = TextView(this)
        tue.text = "Tuesday"
        dayList!!.addView(tue)
        dayList!!.addView(btnList[1])
        val wed = TextView(this)
        wed.text = "Wednesday"
        dayList!!.addView(wed)
        dayList!!.addView(btnList[2])
        val thu = TextView(this)
        thu.text = "Thursday"
        dayList!!.addView(thu)
        dayList!!.addView(btnList[3])
        val fri = TextView(this)
        fri.text = "Friday"
        dayList!!.addView(fri)
        dayList!!.addView(btnList[4])
        val sat = TextView(this)
        sat.text = "Saturday"
        dayList!!.addView(sat)
        dayList!!.addView(btnList[5])
        val sun = TextView(this)
        sun.text = "Sunday"
        dayList!!.addView(sun)
        dayList!!.addView(btnList[6])
    }
}
