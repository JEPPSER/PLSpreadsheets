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
        var number3: Double ?= null
        var operator1: Char ?= null
        var operator2: Char ?= null
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
        if(i < str.length - 1){
            operator1 = str.get(i)
            i++
        }
        temp = ""
        while(i < str.length){
            if(str.get(i).isDigit() || str.get(i) == '.'){
                temp += str.get(i)
                i++
            } else {
                break
            }
        }
        if(temp != ""){
            number2 = temp.toDouble()
        }
        if(i < str.length - 1){
            operator2 = str.get(i)
            i++
        }
        temp = ""
        while(i < str.length){
            temp += str.get(i)
            i++
        }
        if(temp != ""){
            number3 = temp.toDouble()
        }
        if(operator2 != null && number3 != null){
            if(operator1 == '+' && operator2 == '*' ||
                    operator1 == '+' && operator2 == '/' ||
                    operator1 == '-' && operator2 == '*' ||
                    operator1 == '-' && operator2 == '/'){
                val tempNum = number1
                number1 = number3
                number3 = tempNum
                val tempOp = operator1
                operator1 = operator2
                operator2 = tempOp
            }
        }
        if(number2 != null && operator1 != null){
            if(operator1 == '*'){
                result = number1 * number2
            } else if(operator1 == '+'){
                result = number1 + number2
            } else if(operator1 == '-'){
                result = number1 - number2
            } else if(operator1 == '/'){
                result = number1 / number2
            }
        } else {
            result = number1
        }

        if(number3 != null && operator2 != null){
            if(operator2 == '*'){
                result = result * number3
            } else if(operator2 == '+'){
                result = result + number3
            } else if(operator2 == '-'){
                result = result - number3
            } else if(operator2 == '/'){
                result = result / number3
            }
        }
        return result
    }
}
