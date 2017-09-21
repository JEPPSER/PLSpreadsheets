package com.example.jesper.plspreadsheets.entities

import java.util.*

/**
 * Class representing a spreadsheet. Here, all data for
 * a spreadsheet will get stored.
 *
 * @autor Jesper Bergstrom
 * @name Spreadsheet.kt
 * @version 0.00.00
 */
class Spreadsheet {

    var title = String
    var weeks = ArrayList<Week>()

    val dayNames: Array<String> = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    init {

    }

    override fun toString() : String {
        var result = ""
        var i = 0
        while(i < weeks.size){
            result += "[Week " + (i + 1) + "]\n"
            var j = 0
            while(j < weeks[i].days.size){
                result += dayNames[j] + "\n"
                var k = 0
                while(k < weeks[i].days[j].exercises.size){
                    result += weeks[i].days[j].exercises[k].name + "\n"
                    var l = 0
                    while(l < weeks[i].days[j].exercises[k].sets.size){
                        result += weeks[i].days[j].exercises[k].sets[l].reps + "x" + weeks[i].days[j].exercises[k].sets[l].weight + "\n"
                        l++
                    }
                    k++
                }
                j++
            }
            i++
        }
        return result
    }
}
