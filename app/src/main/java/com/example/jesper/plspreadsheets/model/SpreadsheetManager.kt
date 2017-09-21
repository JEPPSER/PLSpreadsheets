package com.example.jesper.plspreadsheets.model

import com.example.jesper.plspreadsheets.entities.Day
import com.example.jesper.plspreadsheets.entities.Exercise
import com.example.jesper.plspreadsheets.entities.Set
import com.example.jesper.plspreadsheets.entities.Spreadsheet
import com.example.jesper.plspreadsheets.entities.Week

/**
 * Class for managing spreadsheets. Can be used for conversion.
 *
 * @author Jesper Bergstrom
 * @name SpreadsheetManager.kt
 * @version 0.00.00
 */
class SpreadsheetManager {

    val dayNames = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    /**
     * Converts a spreadsheet string to Kotlin/Java objects.
     *
     * @return spreadsheet
     * @param spreadsheet string
     */
    fun convertFromString(sprString: String) : Spreadsheet {
        val spreadsheet = Spreadsheet()
        val parts = sprString.split("\n")
        var i = 0
        while(i < parts.size - 1){
            if(parts[i] == "" || parts[i].get(0).isWhitespace()){
                i++
            }
            if(parts[i].startsWith("[Week")){
                val week = Week()
                var dayIndex = 0
                spreadsheet.weeks.add(week)
                i++
                while(i < parts.size){
                    if(dayIndex < 7 && parts[i] == dayNames[dayIndex]){
                        i++
                        while(i < parts.size - 1){
                            if(parts[i].startsWith("-")){
                                val ex = Exercise()
                                week.days[dayIndex].exercises.add(ex)
                                ex.name = parts[i]
                                i++
                                if(parts[i].length > 0){
                                    while(parts[i].get(0).isDigit()){
                                        val set = Set()
                                        ex.sets.add(set)
                                        set.reps = parts[i].split("x")[0]
                                        set.weight = parts[i].split("x")[1]
                                        i++
                                        if(parts[i].length < 1){
                                            break
                                        }
                                    }
                                }
                            } else {
                                break
                            }
                        }
                        dayIndex++
                    } else {
                        break
                    }
                }
            }
        }
        return spreadsheet
    }
}
