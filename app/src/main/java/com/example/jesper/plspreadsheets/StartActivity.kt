package com.example.jesper.plspreadsheets

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        getSpreadsheets()
    }

    private fun getSpreadsheets(){
        var files = assets
        val filelist = files.list("")
        println(filelist[1])
        println(filelist[2])
    }
}
