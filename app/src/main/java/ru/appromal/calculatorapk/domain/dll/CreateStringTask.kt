package ru.appromal.calculatorapk.domain.dll

import ru.appromal.calculatorapk.domain.models.DAddNewCharInTask
import ru.appromal.calculatorapk.domain.models.DHistoryAddSigns

/*
readStack()                 - проверяем если стек не пустой считываем значения.
rewriteLastNumbers()        - если целое число начинается с 0 меняем на введённый символ
 */

class CreateStringTask(dAddNewCharInTask: DAddNewCharInTask) {

    private var stringTask: String = ""
    private var lastChar: Char = ' '
    private var countSign: Int = 0
    private var countNumbers: Int = 0
    private var countNumbersAfterDot: Int = 0
    private var isDouble: Boolean = false
    private var countBracket: Int = 0

    private var isErrorAddNewSign: Boolean = false

    private val lastStack = dAddNewCharInTask.dLastStack
    private val newCharSign =  dAddNewCharInTask.dNewChar.dSign

    // Вызываемая функция с других классов класса
    fun execute() {
        readStack()
        if (countSign > 99){
            isErrorAddNewSign = true
        }
        else {
            when (newCharSign) {
                in '0'..'9' -> validationInt()
                '.' -> validationDot()
                '+', '-', '*', '/' -> validationSign()
                '(', ')'-> validationBracket(newCharSign)
                else -> isErrorAddNewSign = true
            }
        }

        val params = DHistoryAddSigns(
            dStringTask = stringTask,
            dLastChar = lastChar,
            dCountSign = countSign,
            dCountNumbers = countNumbers,
            dCountNumbersAfterDot = countNumbersAfterDot,
            dIsDouble = isDouble,
            dCountBracket = countBracket
        )
        if (!isErrorAddNewSign)
            lastStack.push(params)
    }

    private fun validationInt() {
        if (countNumbers > 14 || countNumbersAfterDot > 9 || lastChar == ')') {
            isErrorAddNewSign = true
            return
        }
        if (countNumbers == 1 && lastChar == '0') {
            rewriteLastNumbers()
            return
        }
        rewriteVariables(addCountNumbers = true, addCountNumbersAfterDot =  isDouble)
    }

    // Добавление точки в десятичную дробь
    private fun validationDot() {
        if (countNumbers > 14 || isDouble || countNumbers < 1) {
            isErrorAddNewSign = true
            return
        }
        rewriteVariables(addCountNumbers = true, addCountNumbersAfterDot =  false)
        isDouble = true
    }

    private fun validationSign() {
        if (countSign == 0 || lastChar == '(') {
            isErrorAddNewSign = true
            return
        }
        if(lastChar == '/' || lastChar == '*' || lastChar == '-' || lastChar == '+') {
            rewriteLastNumbers()
            return
        }
        rewriteVariables(addCountNumbers = false, addCountNumbersAfterDot =  false)
        isDouble = false
    }


    private fun validationBracket(bracketType: Char) {
        val errorBrackets =
            if (bracketType == '(') {
                lastChar in '0'..'9' || lastChar == ')' || lastChar == '.'
            }
            else {
                countBracket == 0 || lastChar == '/' || lastChar == '*' || lastChar == '-' || lastChar == '+'
            }
        if (errorBrackets) {
            isErrorAddNewSign = true
            return
        }
        rewriteVariables(addCountNumbers = false, addCountNumbersAfterDot = false)
        if (bracketType == '(')
            countBracket++
        else
            countBracket--
    }

    // Служит для перезаписи математического знака или 0 в начале числа
    private fun rewriteLastNumbers() {
        stringTask = stringTask.substring(0, stringTask.length - 1) + newCharSign
        lastChar = newCharSign
        // Удаляем последний элемент в стеке символов, в дальнейшем он перезапишится
        lastStack.pop()
    }

    // Считываем из стека последнюю сохранённую информацию о знаке
    private fun readStack() {
        if (lastStack.isEmpty())
            return
        val params = lastStack.peek()
        stringTask = params.dStringTask
        lastChar = params.dLastChar
        countSign = params.dCountSign
        countNumbers = params.dCountNumbers
        countNumbersAfterDot = params.dCountNumbersAfterDot
        isDouble = params.dIsDouble
        countBracket = params.dCountBracket
    }

    // Обновляем переменные
    private fun rewriteVariables(addCountNumbers: Boolean, addCountNumbersAfterDot: Boolean){
        stringTask += newCharSign
        lastChar = newCharSign
        countSign++
        if(addCountNumbers)
            countNumbers++
        else
            countNumbers = 0
        if(addCountNumbersAfterDot)
            countNumbersAfterDot++
        else
            countNumbersAfterDot = 0
    }
}