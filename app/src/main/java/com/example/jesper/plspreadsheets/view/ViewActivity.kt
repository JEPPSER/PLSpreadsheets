package com.example.jesper.plspreadsheets.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.example.jesper.plspreadsheets.R
import com.example.jesper.plspreadsheets.R.*
import com.example.jesper.plspreadsheets.adapters.ExpandableListAdapter
import com.example.jesper.plspreadsheets.create.CreateActivity
import com.example.jesper.plspreadsheets.entities.Spreadsheet
import com.example.jesper.plspreadsheets.math.Calculator
import com.example.jesper.plspreadsheets.model.SpreadsheetManager
import java.util.*

/**
 * Activity for viewing and using a spreadsheet. Here, the user can
 * enter their 1 rep max and be given the appropriate weights to
 * be used each day.
 *
 * @author Jesper Bergstrom
 * @name ViewActivity.kt
 * @version 0.00.00
 */
class ViewActivity : AppCompatActivity() {

    var titleText: TextView?= null
    var textView: TextView?= null
    var maxBtn: Button?= null
    var calcBtn: Button?= null
    var exList: ExpandableListView?= null
    var calculator = Calculator()
    var adapter = ExpandableListAdapter(this@ViewActivity)
    var spreadsheet: Spreadsheet ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_view)

        // Initialize graphic elements
        titleText = findViewById(id.titleText) as TextView
        maxBtn = findViewById(id.maxBtn) as Button
        calcBtn = findViewById(id.calcBtn) as Button
        exList = findViewById(id.exList) as ExpandableListView

        val sprString = intent.getStringExtra("weeks")
        val manager = SpreadsheetManager()
        spreadsheet = manager.convertFromString(sprString)

        var title = intent.getStringExtra("title")
        title = title.replace(".spr", "")
        titleText!!.text = title

        onCalculateButtonClicked(spreadsheet!!)
        onMaxButtonClicked()
    }

    private fun onMaxButtonClicked(){
        maxBtn!!.setOnClickListener(View.OnClickListener {
            var list = ArrayList<String>()
            var i = 0
            while(i < spreadsheet!!.weeks.size){
                var j = 0
                while(j < spreadsheet!!.weeks[i].days.size){
                    var k = 0
                    while(k < spreadsheet!!.weeks[i].days[j].exercises.size){
                        if(!list.contains(spreadsheet!!.weeks[i].days[j].exercises[k].name)){
                            list.add(spreadsheet!!.weeks[i].days[j].exercises[k].name)
                        }
                        k++
                    }
                    j++
                }
                i++
            }
            var intent = Intent(this@ViewActivity, MaxActivity::class.java)
            var exString = ""
            i = 0
            while(i < list.size - 1){
                exString += list[i] + ","
                i++
            }
            exString += list[list.size - 1]
            intent.putExtra("exercises", exString)
            startActivityForResult(intent, 1)
        })
    }

    private fun onCalculateButtonClicked(spreadsheet : Spreadsheet){
        calcBtn!!.setOnClickListener(View.OnClickListener {
            adapter.groupNames.clear()
            adapter.childNames.clear()
            var i = 0
            while(i < spreadsheet.weeks.size){
                adapter.groupNames.add("Week " + (i + 1))
                var list = ArrayList<String>()
                //list.add(spreadsheet.weeks[i].toString(inputMax!!.text.toString()))
                adapter.childNames.add(list)
                i++
            }
            exList!!.setAdapter(adapter)
        })
    }
}
