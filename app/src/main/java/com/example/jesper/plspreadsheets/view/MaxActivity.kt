package com.example.jesper.plspreadsheets.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import com.example.jesper.plspreadsheets.R

class MaxActivity : Activity() {

    var grid: GridLayout?= null
    var doneBtn2: Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_max)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels
        window.setLayout((width * 0.8).toInt(), (height * 0.8).toInt())

        grid = findViewById<View>(R.id.grid) as GridLayout
        doneBtn2 = findViewById<View>(R.id.doneBtn2) as Button

        val exString = intent.extras.getString("exercises")
        val maxes = intent.extras.getString("maxes")
        loadGrid(exString, maxes)
        onDoneButtonClicked()
    }

    private fun loadGrid(ex : String, maxes : String){
        val parts = ex.split(",")
        val maxParts = maxes.split(",")
        println(maxes)
        var i = 0
        while(i < parts.size){
            val exText = TextView(this)
            exText.text = parts[i] + ": "
            exText.textSize = 20F
            val exEdit = EditText(this)
            exEdit.width = 200
            exEdit.inputType = InputType.TYPE_CLASS_NUMBER
            if(maxes != ""){
                exEdit.setText(maxParts[i].split(": ")[1], TextView.BufferType.EDITABLE)
            }
            grid!!.addView(exText)
            grid!!.addView(exEdit)
            i++
        }
    }

    private fun onDoneButtonClicked(){
        doneBtn2!!.setOnClickListener(View.OnClickListener {
            var resultString = ""
            var i = 0
            while(i < grid!!.childCount - 3){
                resultString += (grid!!.getChildAt(i) as TextView).text.toString() + (grid!!.getChildAt(i+1) as EditText).text.toString() + ","
                i += 2
            }
            resultString += (grid!!.getChildAt(i) as TextView).text.toString() + (grid!!.getChildAt(i+1) as EditText).text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra("maxes", resultString)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        })
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED, Intent())
        finish()
    }
}
