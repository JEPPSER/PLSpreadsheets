package com.example.jesper.plspreadsheets.create

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import com.example.jesper.plspreadsheets.R
import java.io.Serializable
import java.util.*

/**
 * Activity for creating a week in the spreadsheet.
 *
 * @author Jesper Bergstrom
 * @name CreateWeekActivity.kt
 * @version 0.00.00
 */
class CreateWeekActivity : AppCompatActivity(), Serializable {

    var weekText: TextView ?= null
    var dayList: GridLayout ?= null
    var btnList: ArrayList<Button> = ArrayList<Button>()
    var exerciseCount = ArrayList<Int>()
    var saveBtn: Button ?= null
    var deleteBtns = ArrayList<Button>()
    var textViewList = ArrayList<TextView>()

    var resultString: String = ""

    val dayNames = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_week)

        var i = 0
        while(i < dayNames.size){
            resultString += dayNames[i] + "\n"
            i++
        }
        //println(resultString)

        // Get the week that was clicked
        val week = getIntent().extras.getString("week")

        // Get save button
        saveBtn = findViewById(R.id.saveBtn) as Button

        // Get Grid
        dayList = findViewById(R.id.dayList) as GridLayout
        createDays()

        // Get the week text
        weekText = findViewById(R.id.weekText) as TextView
        weekText!!.text = week

        onSaveButtonClicked()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        val ex = data.extras.getString("ex")
        var parts = resultString.split("\n")
        resultString = ""
        var i = 0
        while(i < parts.size){
            if(parts[i].equals(dayNames[requestCode])){
                var j = 0
                while(j < parts.size){
                    resultString += parts[j] + "\n"
                    if(j == i){
                        resultString += ex
                    }
                    j++
                }
                break
            }
            i++
        }

        i = 0
        while(true){
            if((dayList!!.getChildAt(i) as TextView).text.equals(dayNames[requestCode])){
                i+=2
                // update grid layout
                var exName = TextView(this) // Exercise name
                exName.text = ex
                dayList!!.addView(exName, i)
                var delete = Button(this) // Delete button
                delete.text = "delete"
                dayList!!.addView(delete, i + 1)
                deleteBtns.add(delete)
                break
            }
            i++
        }
    }

    private fun onSaveButtonClicked(){
        saveBtn!!.setOnClickListener(OnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("week", resultString)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        })
    }

    /**
     * Creates all the Day objects for the week and all its components.
     */
    private fun createDays(){
        var i = 0
        while(i < 7){
            exerciseCount.add(0)
            val btn = Button(this)
            btn.text = "+ Exercise"
            btnList.add(btn)
            val num = i
            btn.setOnClickListener(OnClickListener {
                val create = Intent(this@CreateWeekActivity, CreateDayActivity::class.java)
                startActivityForResult(create, num)
            })
            val dayText = TextView(this)
            dayText.text = dayNames.get(i)
            dayList!!.addView(dayText)
            dayList!!.addView(btn)
            i++
        }
    }
}
