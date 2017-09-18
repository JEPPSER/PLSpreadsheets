package com.example.jesper.plspreadsheets.create

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.ScrollView
import com.example.jesper.plspreadsheets.R
import java.util.*

/**
 * Activity for creating and editing spreadsheets.
 *
 * @autor Jesper Bergstrom
 * @name CreateActivity.kt
 * @version 0.00.00
 */
class CreateActivity : AppCompatActivity() {

    private var saveBtn: Button? = null
    private var titleText: EditText? = null
    private var weekBtn: Button? = null
    private var scrollView: ScrollView? = null
    private var gridLayout: GridLayout? = null

    val dayNames = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    var resultStrings = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        saveBtn = findViewById(R.id.saveBtn) as Button
        titleText = findViewById(R.id.titleText) as EditText
        weekBtn = findViewById(R.id.weekBtn) as Button
        scrollView = findViewById(R.id.scrollView) as ScrollView
        gridLayout = findViewById(R.id.gridLayout) as GridLayout

        onSaveButtonClicked()
        onWeekButtonClicked()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        val week = data.extras.getString("week")
        resultStrings[requestCode] = week
    }


    /**
     * Adds listener to "save" button. When the button is pressed,
     * a new spreadsheet should get saved in the spreadsheets folder.
     */
    private fun onSaveButtonClicked(){
        saveBtn!!.setOnClickListener(View.OnClickListener {

            println(titleText!!.text)
        })
    }

    /**
     * Adds listener to "+1 week" button. Clicking it will
     * add a new week to the program.
     */
    private fun onWeekButtonClicked(){
        weekBtn!!.setOnClickListener(View.OnClickListener({

            val weekBtn = Button(this)
            weekBtn.text = "Week " + (resultStrings.size + 1)
            val count = resultStrings.size + 1

            val delete = Button(this)
            delete.text = "Delete"

            var str = "[" + weekBtn.text.toString() + "]" + "\n"
            var i = 0
            while(i < 7){
                str += dayNames[i] + "\n"
                i++
            }
            resultStrings.add(str)
            gridLayout!!.addView(weekBtn)
            gridLayout!!.addView(delete)

            // Add listener to new week button
            weekBtn.setOnClickListener(View.OnClickListener {
                val create: Intent = Intent(this@CreateActivity, CreateWeekActivity::class.java)
                create.putExtra("week", resultStrings[gridLayout!!.indexOfChild(weekBtn) / 2])
                startActivityForResult(create, gridLayout!!.indexOfChild(weekBtn) / 2)
            })

            // Add listener to new delete button
            delete.setOnClickListener(View.OnClickListener {
                println(resultStrings[gridLayout!!.indexOfChild(weekBtn) / 2])
                resultStrings.remove(resultStrings[gridLayout!!.indexOfChild(weekBtn) / 2])
                gridLayout!!.removeView(delete)
                gridLayout!!.removeView(weekBtn)

                var j = 0
                while(j < resultStrings.size){
                    resultStrings[j] = "[Week " + (j + 1) + resultStrings[j].substring(7)
                    (gridLayout!!.getChildAt(j * 2) as Button).text = "Week " + (j + 1)
                    j++
                }
            })

            var j = 0
            while(j < resultStrings.size){
                (gridLayout!!.getChildAt(j * 2) as Button).text = "Week " + (j + 1)
                j++
            }
        }))
    }
}
