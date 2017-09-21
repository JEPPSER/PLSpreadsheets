package com.example.jesper.plspreadsheets.entities

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

    val dayNames: Array<String> = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    init {
        days.add(monday)
        days.add(tuesday)
        days.add(wednesday)
        days.add(thursday)
        days.add(friday)
        days.add(saturday)
        days.add(sunday)
    }

    override fun toString() : String {
        var result = ""
        var j = 0
        while(j < days.size){
            result += dayNames[j] + "\n"
            var k = 0
            while(k < days[j].exercises.size){
                result += days[j].exercises[k].name + "\n"
                var l = 0
                while(l < days[j].exercises[k].sets.size){
                    result += days[j].exercises[k].sets[l].reps + "x" + days[j].exercises[k].sets[l].weight + "\n"
                    l++
                }
                k++
            }
            j++
        }
        return result
    }
}
