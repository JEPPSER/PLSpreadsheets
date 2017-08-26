package com.example.jesper.plspreadsheets

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.jesper.plspreadsheets.R.layout
import java.util.*

/**
 * Start screen for the app. Here, the user can either create a
 * new spreadsheet or select an already saved spreadsheet to view.
 *
 * @author Jesper Bergstrom
 * @name StartActivity
 * @version 0.00.00
 */
class StartActivity : AppCompatActivity() {

    private var adapter: ArrayAdapter<String>? = null
    private var listItems = ArrayList<String>()
    private var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_start)
        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems)
        listView = findViewById(R.id.listView) as ListView
        (listView as ListView).adapter = adapter
        getSpreadsheets()
    }

    /**
     * Reads from the spreadsheets folder and puts all spreadsheets in
     * a list that will be displayed in the ListView.
     */
    private fun getSpreadsheets(){
        listItems.clear()
        val files = assets
        val filelist = files.list("spreadsheets")
        var i = 0
        while(i < filelist.size){
            println(filelist[i])
            listItems.add(filelist[i])
            i++
        }
    }
}

