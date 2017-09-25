package com.example.jesper.plspreadsheets.entities

import com.example.jesper.plspreadsheets.math.Calculator
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
    private var calculator = Calculator()

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

    fun toString(maxes : ArrayList<String>) : String {
        var result = ""
        var j = 0
        while(j < days.size){
            result += dayNames[j] + "\n"
            var k = 0
            while(k < days[j].exercises.size){
                result += "    " + days[j].exercises[k].name + "\n"
                var l = 0
                while(l < days[j].exercises[k].sets.size){
                    var max = "0"
                    if(maxes.size > 0){
                        max = maxes[k].split(": ")[1]
                    }
                    if(max == ""){
                        max = "0"
                    }
                    result += "        " + days[j].exercises[k].sets[l].reps + "x" + calculator.calculate(days[j].exercises[k].sets[l].weight, max) + "\n"
                    l++
                }
                k++
            }
            j++
        }
        return result
    }
}
