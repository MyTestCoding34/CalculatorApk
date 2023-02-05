package ru.appromal.calculatorapk.domain.dll

import ru.appromal.calculatorapk.domain.models.DAddNewCharInTask
import ru.appromal.calculatorapk.domain.models.DHistoryAddSigns
import ru.appromal.calculatorapk.domain.models.EError

/*
readStack()                 - проверяем если стек не пустой считываем значения.
rewriteLastNumbers()        - если целое число начинается с 0 меняем на введённый символ
 */

class CreateStringTask(dAddNewCharInTask: DAddNewCharInTask) {

    private var stringTask: String = ""
    private var lastChar: String = ""
    private var countSign: Int = 0
    private var countNumbers: Int = 0
    private var countNumbersAfterDot: Int = 0
    private var isDouble: Boolean = false
    private var countBracket: Int = 0

    private var isEError: EError = EError.NO_ERROR

    private var isErrorAddNewSign: Boolean = false

    private val lastStack = dAddNewCharInTask.dLastStack
    private val newCharInfo = dAddNewCharInTask.dNewChar.dType
    private val newCharSign =  dAddNewCharInTask.dNewChar.dSign

    // Вызываемая функция с других классов класса
    fun execute(): EError {
        readStack()
        if (countSign > 99){
            isEError = EError.MAX_SIGN
            isErrorAddNewSign = true
        }
        else {
            when (newCharInfo) {
                "INT" -> validationInt()
                "DOT" -> validationDot()
                "SIGN" -> validationSign()
                "BR_OPEN" -> validationBrOpen()
                "BR_CLOSE" -> validationBrClose()
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
        if (countBracket>0)
            isEError = EError.BRACKET
        return isEError
    }

    private fun validationInt() {
        if (countNumbers > 14 ) {
            isEError = EError.MAX_NUMBERS
            isErrorAddNewSign = true
            return
        }
        if (countNumbersAfterDot > 9) {
            isEError = EError.MAX_NUMBERS_DOUBLE
            isErrorAddNewSign = true
            return
        }
        if (lastChar == ")") {
            isErrorAddNewSign = true
            return
        }
        if (countNumbers == 1 && lastChar == "0") {
            rewriteLastNumbers()
            return
        }
        rewriteVariables(addCountNumbers = true, addCountNumbersAfterDot =  isDouble)
    }

    // Добавление точки в десятичную дробь
    private fun validationDot() {
        if (countNumbers > 14 ) {
            isEError = EError.MAX_NUMBERS
            isErrorAddNewSign = true
            return
        }
        if (isDouble || countNumbers < 1) {
            isErrorAddNewSign = true
            return
        }
        rewriteVariables(addCountNumbers = true, addCountNumbersAfterDot =  false)
        isDouble = true
    }

    private fun validationSign() {
        if (countSign == 0 || lastChar == "(") {
            isErrorAddNewSign = true
            return
        }
        if(lastChar == "/" || lastChar == "*" || lastChar == "-" || lastChar == "+") {
            rewriteLastNumbers()
            isEError = EError.SIGN_END
            return
        }
        isEError = EError.SIGN_END
        rewriteVariables(addCountNumbers = false, addCountNumbersAfterDot =  false)
        isDouble = false
    }

    private fun validationBrOpen() {
        if (lastChar in "0".."9" || lastChar == ")" || lastChar == ".") {
            isErrorAddNewSign = true
            return
        }
        rewriteVariables(addCountNumbers = false, addCountNumbersAfterDot =  false)
        isDouble = false
        countBracket++
    }

    private fun validationBrClose() {
        if (countBracket == 0 || lastChar == "/" || lastChar == "*" || lastChar == "-" || lastChar == "+") {
            isErrorAddNewSign = true
            return
        }
        rewriteVariables(addCountNumbers = false, addCountNumbersAfterDot =  false)
        isDouble = false
        countBracket--
    }

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