package com.example.jesper.plspreadsheets.model

import java.util.*

/**
 * Represents a week. It will hold 7 days objects, representing
 * the days of the week.
 *
 * @author Jesper Bergstrom
 * @name Week.kt
 * @version 0.00.00
 */
class Week {

    var weekNumber = Int
    var monday = Day()
    var tuesday = Day()
    var wednesday = Day()
    var thursday = Day()
    var friday = Day()
    var saturday = Day()
    var sunday = Day()
    var days = ArrayList<Day>()

    init {
        days.add(monday)
        days.add(tuesday)
        days.add(wednesday)
        days.add(thursday)
        days.add(friday)
        days.add(saturday)
        days.add(sunday)
    }
}