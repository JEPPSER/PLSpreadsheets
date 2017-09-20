package com.example.jesper.plspreadsheets.create

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import com.example.jesper.plspreadsheets.R
import java.util.*

/**
 * Popup window for creating a Day.
 *
 * @author Jesper Bergstrom
 * @name CreateDayActivity.kt
 * @version 0.00.00
 */
class CreateDayActivity : Activity() {

    var grid: GridLayout ?= null
    var setBtn: Button?= null
    var doneBtn: Button ?= null
    var count: Int = 0
    var repList = ArrayList<EditText>()
    var weightList = ArrayList<EditText>()
    var inputName: EditText ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_day)
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels
        window.setLayout((width * 0.8).toInt(), (height * 0.8).toInt())

        // Get the day that was clicked

        grid = findViewById<View>(R.id.grid) as GridLayout
        setBtn = findViewById<View>(R.id.setBtn) as Button
        doneBtn = findViewById<View>(R.id.doneBtn) as Button
        inputName = findViewById<View>(R.id.inputName) as EditText

        onSetButtonClicked()
        onDoneButtonClicked()
    }

    private fun onDoneButtonClicked(){
        doneBtn!!.setOnClickListener(View.OnClickListener {
            if(inputName!!.text.toString() == ""){
                alert("No exercise name has been given.")
            } else {
                var isFilled = true
                var i = 0
                while(i < repList.size){
                    if(repList[i].text.toString() == ""){
                        isFilled = false
                    }
                    if(weightList[i].text.toString() == ""){
                        isFilled = false
                    }
                    i++
                }
                if(!isFilled){
                    alert("All fields are not filled")
                } else {
                    addExercise()
                }
            }
        })
    }

    private fun addExercise(){
        var i: Int = 0
        var result = "-" + inputName!!.text.toString() + "\n"
        // Create sets
        while(i < repList.size){
            result += repList[i].text.toString() + "x" + weightList[i].text.toString() + "\n"
            i++
        }
        val resultIntent = Intent()
        resultIntent.putExtra("ex", result)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED, Intent())
        finish()
    }

    private fun alert(message : String){
        val builder1 = AlertDialog.Builder(this)
        builder1.setMessage(message)
        builder1.setCancelable(true)

        builder1.setPositiveButton(
                "Ok"
        ) { dialog, id -> dialog.cancel() }

        val alert11 = builder1.create()
        alert11.show()
    }

    private fun onSetButtonClicked(){
        setBtn!!.setOnClickListener(View.OnClickListener {
            count++

            val text = TextView(this)
            text.text = count.toString() + ". "
            text.textSize = 20F
            grid!!.addView(text)

            val inReps = EditText(this)
            inReps.inputType = InputType.TYPE_CLASS_NUMBER
            grid!!.addView(inReps)
            inReps.width = 150
            repList.add(inReps)

            val inWeight = EditText(this)
            grid!!.addView(inWeight)
            inWeight.width = 300
            weightList.add(inWeight)
        })
    }
}
