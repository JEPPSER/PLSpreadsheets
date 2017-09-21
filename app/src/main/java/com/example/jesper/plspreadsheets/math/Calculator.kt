package com.example.jesper.plspreadsheets.math

/**
 * Calculator that can take convert strings to functions and
 * calculate them.
 *
 * @author Jesper Bergstrom
 * @name Calculator.kt
 * @version 0.00.00
 */
class Calculator {

    fun calculate(func : String, max : String) : Double{
        var str = func.replace(",", ".")
        str = str.replace("m", max)
        var result: Double = 0.0
        var temp = ""
        var number1: Double ?= null
        var number2: Double ?= null
        var operator: Char ?= null
        var i = 0
        while(i < str.length){
            if(str.get(i).isDigit() || str.get(i) == '.'){
                temp += str.get(i)
                i++
            } else {
                break
            }
        }
        number1 = temp.toDouble()
        if(i < str.length){
            operator = str.get(i)
            i++
            temp = ""
            while(i < str.length){
                temp += str.get(i)
                i++
            }
            number2 = temp.toDouble()
        }

        if(number2 != null && operator != null){
            if(operator == '*'){
                result = number1 * number2
            } else if(operator == '+'){
                result = number1 + number2
            } else if(operator == '-'){
                result = number1 - number2
            } else if(operator == '/'){
                result = number1 / number2
            }
        } else {
            result = number1
        }
        return result
    }
}
