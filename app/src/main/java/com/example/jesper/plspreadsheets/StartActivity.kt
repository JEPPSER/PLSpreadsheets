package com.example.jesper.plspreadsheets

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.jesper.plspreadsheets.R.layout
import com.example.jesper.plspreadsheets.create.CreateActivity
import java.util.*

/**
 * Start screen for the app. Here, the user can either create a
 * new spreadsheet or select an already saved spreadsheet to view.
 *
 * @author Jesper Bergstrom
 * @name StartActivity.kt
 * @version 0.00.00
 */
class StartActivity : AppCompatActivity() {

    private var adapter: ArrayAdapter<String>? = null
    private var listItems = ArrayList<String>()
    private var listView: ListView? = null
    private var newBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_start)

        // Setting up a list connected to the ListView.
        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems)
        listView = findViewById(R.id.listView) as ListView
        (listView as ListView).adapter = adapter

        // Getting the "new" button.
        newBtn = findViewById(R.id.newBtn) as Button

        getSpreadsheets()
        addListListener()
        onNewButtonClicked()
    }

    /**
     * Adds an onClickListener to the "new" button. When the button is pressed,
     * A CreateActivity is started.
     */
    private fun onNewButtonClicked(){
        newBtn!!.setOnClickListener(View.OnClickListener {
            val create = Intent(this@StartActivity, CreateActivity::class.java)
            startActivity(create)
        })
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
            listItems.add(filelist[i])
            i++
        }
    }

    /**
     * Creates a Listener for the ListView. It will handle user input
     * when an item in the ListView is clicked.
     */
    private fun addListListener(){
        (listView as ListView).onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parentView: AdapterView<*>, childView: View,
                                     position: Int, id: Long) {
                println(listItems[position])
            }
        }
    }
}

