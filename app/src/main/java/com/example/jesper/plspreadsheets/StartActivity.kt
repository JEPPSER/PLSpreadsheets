package com.example.jesper.plspreadsheets

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.jesper.plspreadsheets.R.layout
import com.example.jesper.plspreadsheets.create.CreateActivity
import com.example.jesper.plspreadsheets.view.ViewActivity
import java.io.File
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
    private var fileList = ArrayList<File>()
    private var listView: ListView? = null
    private var newBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_start)

        val file = this.filesDir
        val dir = File(file.absolutePath + "/spreadsheets")
        if(!dir.exists()){
            dir.mkdirs()
        }

        // Setting up a list connected to the ListView.
        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems)
        listView = findViewById(R.id.listView) as ListView
        (listView as ListView).adapter = adapter
        registerForContextMenu(listView);

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
            startActivityForResult(create, 1)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if(resultCode == Activity.RESULT_CANCELED){

        } else if(resultCode == Activity.RESULT_OK){
            val name = data.extras.getString("name")
            val old = data.extras.getString("old")
            fileList.add(File(this.filesDir.absolutePath + File.separator + "spreadsheets" + File.separator + name))
            listItems.add(name)
            (listView as ListView).adapter = adapter
            if(old != null){
                val index = listItems.indexOf(old)
                listItems.removeAt(index)
                fileList.removeAt(index)
                (listView as ListView).adapter = adapter
            }
        }
    }

    /**
     * Reads from the spreadsheets folder and puts all spreadsheets in
     * a list that will be displayed in the ListView.
     */
    private fun getSpreadsheets(){
        listItems.clear()
        fileList.clear()
        val files = File(this.filesDir.absolutePath + File.separator + "spreadsheets").listFiles()
        var i = 0
        while(i < files.size){
            listItems.add(files[i].name)
            fileList.add(files[i])
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
                var result = ""
                val scan = Scanner(fileList[position])
                while(scan.hasNextLine()){
                    result += scan.nextLine() + "\n"
                }

                val viewIntent = Intent(this@StartActivity, ViewActivity::class.java)
                viewIntent.putExtra("weeks", result)
                viewIntent.putExtra("title", fileList[position].name)
                startActivity(viewIntent)
            }
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View,
                            menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.add("delete")
        menu.add("edit")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        super.onContextItemSelected(item)
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        if(item.title == "delete"){
            fileList.get(info.id.toInt()).delete()
            listItems.removeAt(info.id.toInt())
            fileList.removeAt(info.id.toInt())
            (listView as ListView).adapter = adapter
        } else if(item.title == "edit"){
            val create = Intent(this@StartActivity, CreateActivity::class.java)
            create.putExtra("spreadsheet", listItems.get(info.id.toInt()))
            startActivityForResult(create, 1)
        }
        return true
    }
}

