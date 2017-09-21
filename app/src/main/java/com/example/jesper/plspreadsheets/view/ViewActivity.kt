package com.example.jesper.plspreadsheets.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.example.jesper.plspreadsheets.R
import com.example.jesper.plspreadsheets.R.*
import com.example.jesper.plspreadsheets.adapters.ExpandableListAdapter
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
    var inputMax: EditText?= null
    var calcBtn: Button?= null
    var exList: ExpandableListView?= null
    var calculator = Calculator()
    var adapter = ExpandableListAdapter(this@ViewActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_view)

        // Initialize graphic elements
        titleText = findViewById(id.titleText) as TextView
        textView = findViewById(id.textView) as TextView
        inputMax = findViewById(id.inputMax) as EditText
        calcBtn = findViewById(id.calcBtn) as Button
        exList = findViewById(id.exList) as ExpandableListView

        val sprString = intent.getStringExtra("weeks")
        val manager = SpreadsheetManager()
        val spreadsheet = manager.convertFromString(sprString)

        var title = intent.getStringExtra("title")
        title = title.replace(".spr", "")
        titleText!!.text = title

        onCalculateButtonClicked(spreadsheet)
    }

    private fun onCalculateButtonClicked(spreadsheet : Spreadsheet){
        calcBtn!!.setOnClickListener(View.OnClickListener {
            adapter.groupNames.clear()
            adapter.childNames.clear()
            var i = 0
            while(i < spreadsheet.weeks.size){
                adapter.groupNames.add("Week " + (i + 1))
                var list = ArrayList<String>()
                list.add(spreadsheet.weeks[i].toString(inputMax!!.text.toString()))
                adapter.childNames.add(list)
                i++
            }
            exList!!.setAdapter(adapter)
        })
    }
}
