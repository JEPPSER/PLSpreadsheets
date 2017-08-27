package com.example.jesper.plspreadsheets.create

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        saveBtn = findViewById(R.id.saveBtn) as Button
        titleText = findViewById(R.id.titleText) as EditText

        onSaveButtonClicked()
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
}
