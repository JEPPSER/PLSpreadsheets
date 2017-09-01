package com.example.jesper.plspreadsheets.model

import java.io.Serializable
import java.util.*

/**
 * Represents a week. It will hold 7 days objects, representing
 * the days of the week.
 *
 * @author Jesper Bergstrom
 * @name Week.kt
 * @version 0.00.00
 */
class Week : Serializable {

    var weekNumber: Int ?= null
    var monday: Day ?= null
    var tuesday : Day ?= null
    var wednesday: Day ?= null
    var thursday: Day ?= null
    var friday: Day ?= null
    var saturday: Day ?= null
    var sunday: Day ?= null
    var days: ArrayList<Day> ?= null
}