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
    private var weekCount = 0
    private var scrollView: ScrollView? = null
    private var gridLayout: GridLayout? = null

    var resultString: String = ""

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
        val parts = resultString.split("\n")
        resultString = ""
        var i = 0
        while(i < parts.size){
            if(parts[i].equals("[Week " + (requestCode + 1) + "]")){
                var j = 0
                while(j < parts.size){
                    resultString += parts[j] + "\n"
                    if(j == i){
                        resultString += week
                    }
                    j++
                }
                break
            }
            i++
        }
        println(resultString)
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
            weekBtn.text = "Week " + (weekCount + 1)
            val count = weekCount + 1

            resultString += "[" + weekBtn.text.toString() + "]" + "\n"

            // Add listener to new week button
            weekBtn.setOnClickListener(View.OnClickListener {
                val create: Intent = Intent(this@CreateActivity, CreateWeekActivity::class.java)
                create.putExtra("week", weekBtn.text.toString())
                startActivityForResult(create, count - 1)
            })
            val delete = Button(this)
            delete.text = "Delete"

            // Add listener to new delete button
            delete.setOnClickListener(View.OnClickListener {
                println("delete " + count + 1)
            })
            gridLayout!!.rowCount = weekCount + 2
            gridLayout!!.addView(weekBtn)
            gridLayout!!.addView(delete)
            weekCount++
        }))
    }
}
