package com.example.jesper.plspreadsheets.create

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
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
    var saveBtn: Button ?= null
    var textViewList = ArrayList<TextView>()

    var resultString: String = ""

    val dayNames = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_week)

        resultString = intent.extras.getString("week")

        // Get save button
        saveBtn = findViewById(R.id.saveBtn) as Button

        // Get Grid
        dayList = findViewById(R.id.dayList) as GridLayout
        createDays()

        // Get the week text
        weekText = findViewById(R.id.weekText) as TextView
        weekText!!.text = resultString.split("\n")[0]

        onSaveButtonClicked()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if(resultCode == Activity.RESULT_CANCELED){

        } else if(resultCode == Activity.RESULT_OK){
            var ex = data.extras.getString("ex")
            val parts = resultString.split("\n")
            resultString = ""
            var i = 0
            while(i < parts.size){
                if(parts[i].equals(dayNames[requestCode - 1])){
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
                if((dayList!!.getChildAt(i) as TextView).text.equals(dayNames[requestCode - 1])){
                    i+=2
                    // update grid layout
                    if(ex.get(ex.length - 1) == '\n'){
                        ex = ex.substring(0, ex.length - 1)
                    }
                    var exName = TextView(this) // Exercise name
                    exName.text = ex
                    dayList!!.addView(exName, i)
                    var delete = Button(this) // Delete button
                    delete.text = "delete"
                    dayList!!.addView(delete, i + 1)

                    var j = 1
                    var counter = 0
                    val temp = resultString.split("\n")
                    // while loop for counting "-"
                    while(temp[j] != dayNames[requestCode - 1]){
                        if(temp[j].startsWith("-")){
                            counter++
                        }
                        j++
                    }

                    // Delete Button Listener
                    deleteButtonListener(delete, ex, exName)
                    break
                }
                i++
            }
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED, Intent())
        finish()
    }

    private fun deleteButtonListener(delete : Button, ex : String, exName : TextView){
        delete.setOnClickListener(OnClickListener {
            alertWeek(delete, ex, exName)
        })
    }

    private fun alertWeek(delete : Button, ex : String, exName : TextView){
        val builder1 = AlertDialog.Builder(this)
        builder1.setMessage("Do you want to delete this exercise?")
        builder1.setCancelable(true)

        builder1.setPositiveButton(
                "Yes"
        ) { dialog, id ->
            deleteExercise(delete, ex, exName)
            dialog.cancel() }

        builder1.setNegativeButton(
                "No"
        ) { dialog, id -> dialog.cancel() }

        val alert11 = builder1.create()
        alert11.show()
    }

    private fun deleteExercise(delete : Button, ex : String, exName : TextView){
        resultString = resultString.replace(ex, "")
        dayList!!.removeView(delete)
        dayList!!.removeView(exName)
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
        var i = 1
        var dayIndex = 0
        val parts = resultString.split("\n")
        while(i < parts.size){
            if(dayIndex < 7 && parts[i].equals(dayNames[dayIndex])){
                val btn = Button(this)
                btn.text = "+ Exercise"
                btnList.add(btn)
                val num = dayIndex + 1
                btn.setOnClickListener(OnClickListener {
                    val create = Intent(this@CreateWeekActivity, CreateDayActivity::class.java)
                    startActivityForResult(create, num)
                })
                val dayText = TextView(this)
                dayText.text = dayNames.get(dayIndex)
                dayList!!.addView(dayText)
                dayList!!.addView(btn)
                dayIndex++
                i++
            } else {
                var result = ""
                var count = 0
                while(i < parts.size){
                    if(dayIndex == 7 || !parts[i].equals(dayNames[dayIndex])){
                        if(count != 0 && parts[i].startsWith("-") || i == parts.size - 1) {
                            if(!result.equals("") && !result.get(0).equals('\n')){
                                if(result.get(result.length - 1) == '\n'){
                                    result = result.substring(0, result.length - 1)
                                }
                                val ex = TextView(this)
                                ex.text = result
                                dayList!!.addView(ex)
                                val btn = Button(this)
                                btn.text = "delete"
                                dayList!!.addView(btn)

                                // Delete Button Listener
                                deleteButtonListener(btn, result, ex)
                            }
                            count = 0
                            result = ""
                        }
                        result += parts[i] + "\n"
                        count++
                        i++
                    } else if (parts[i].equals(dayNames[dayIndex])){
                        if(!result.equals("") && !result.get(0).equals('\n')){
                            if(result.get(result.length - 1) == '\n'){
                                result = result.substring(0, result.length - 1)
                            }
                            val ex = TextView(this)
                            ex.text = result
                            dayList!!.addView(ex)
                            val btn = Button(this)
                            btn.text = "delete"
                            dayList!!.addView(btn)

                            // Delete Button Listener
                            deleteButtonListener(btn, result, ex)
                        }
                        break
                    } else {
                        break
                    }
                }
            }
        }
    }
}
