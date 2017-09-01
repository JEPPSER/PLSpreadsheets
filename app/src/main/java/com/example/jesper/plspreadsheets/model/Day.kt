package com.example.jesper.plspreadsheets.model

import java.io.Serializable
import java.util.*

/**
 * Represents a day in the spreadsheet. It will hold a list of exercises.
 *
 * @author Jesper Bergstrom
 * @name Day.kt
 * @version 0.00.00
 */
class Day : Serializable{

    var exercises: ArrayList<Exercise> ?= null
    var name: String ?= null
}