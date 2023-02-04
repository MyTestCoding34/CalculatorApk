package ru.appromal.calculatorapk.domain.dll

import ru.appromal.calculatorapk.domain.models.DAddNewCharInTask
import ru.appromal.calculatorapk.domain.models.DHistoryAddSings
import ru.appromal.calculatorapk.domain.models.DReturnNewStackWithTask
import ru.appromal.calculatorapk.domain.models.EError

/*
readStack()                 - проверяем если стек не пустой считываем значения.
rewriteLastNumbers()        - если целое число начинается с 0 меняем на введённый символ
 */

class CreateStringTask(dAddNewCharInTask: DAddNewCharInTask) {

    private var stringTask: String = ""
    private var lastChar: String = ""
    private var countSing: Int = 0
    private var countNumbers: Int = 0
    private var countNumbersAfterDot: Int = 0
    private var isDouble: Boolean = false
    private var countBracket: Int = 0

    private var isEError: EError = EError.NO_ERROR

    private var isErrorAddNewSing: Boolean = false

    private val lastStack = dAddNewCharInTask.dLastStack
    private val newCharInfo = dAddNewCharInTask.dNewChar.type
    private val newCharSing =  dAddNewCharInTask.dNewChar.sing

    // Вызываемая функция с других классов класса
    fun execute(): DReturnNewStackWithTask {
        readStack()
        if (countSing > 99){
            isEError = EError.MAX_SING
            isErrorAddNewSing = true
        }
        else {
            when (newCharInfo) {
                "INT" -> validationInt()
                "DOT" -> validationDot()
                "SING" -> validationSing()
                "BR_OPEN" -> validationBrOpen()
                "BR_CLOSE" -> validationBrClose()
            }
        }

        val params = DHistoryAddSings(
            dStringTask = stringTask,
            dLastChar = lastChar,
            dCountSing = countSing,
            dCountNumbers = countNumbers,
            dCountNumbersAfterDot = countNumbersAfterDot,
            dIsDouble = isDouble,
            dCountBracket = countBracket
        )
        if (!isErrorAddNewSing)
            lastStack.push(params)
        if (countBracket>0)
            isEError = EError.BRACKET
        return DReturnNewStackWithTask(dLastStack = lastStack,dError = isEError)
    }

    private fun validationInt() {
        if (countNumbers > 14 ) {
            isEError = EError.MAX_NUMBERS
            isErrorAddNewSing = true
            return
        }
        if (countNumbersAfterDot > 9) {
            isEError = EError.MAX_NUMBERS_DOUBLE
            isErrorAddNewSing = true
            return
        }
        if (lastChar == ")") {
            isErrorAddNewSing = true
            return
        }
        if (countNumbers == 1 && lastChar == "0") {
            rewriteLastNumbers()
            return
        }
        rewriteVariables(addCountNumders = true, addCountNumbersAfterDot =  isDouble)
    }

    // Добавление точки в десятичную дробь
    private fun validationDot() {
        if (countNumbers > 14 ) {
            isEError = EError.MAX_NUMBERS
            isErrorAddNewSing = true
            return
        }
        if (isDouble || countNumbers < 1) {
            isErrorAddNewSing = true
            return
        }
        rewriteVariables(addCountNumders = true, addCountNumbersAfterDot =  false)
        isDouble = true
    }

    private fun validationSing() {
        if (countSing == 0 || lastChar == "(") {
            isErrorAddNewSing = true
            return
        }
        if(lastChar == "/" || lastChar == "*" || lastChar == "-" || lastChar == "+") {
            rewriteLastNumbers()
            isEError = EError.SING_END
            return
        }
        rewriteVariables(addCountNumders = false, addCountNumbersAfterDot =  false)
        isDouble = false
    }

    private fun validationBrOpen() {
        if (lastChar in "0".."9" || lastChar == ")") {
            isErrorAddNewSing = true
            return
        }
        rewriteVariables(addCountNumders = false, addCountNumbersAfterDot =  false)
        isDouble = false
        countBracket++
    }

    private fun validationBrClose() {
        if (countBracket == 0 || lastChar == "/" || lastChar == "*" || lastChar == "-" || lastChar == "+") {
            isErrorAddNewSing = true
            return
        }
        rewriteVariables(addCountNumders = false, addCountNumbersAfterDot =  false)
        isDouble = false
        countBracket--
    }

    private fun rewriteLastNumbers() {
        stringTask = stringTask.substring(0, stringTask.length - 1) + newCharSing
        lastChar = newCharSing
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
        countSing = params.dCountSing
        countNumbers = params.dCountNumbers
        countNumbersAfterDot = params.dCountNumbersAfterDot
        isDouble = params.dIsDouble
        countBracket = params.dCountBracket
    }

    // Обновляем переменные
    private fun rewriteVariables(addCountNumders: Boolean, addCountNumbersAfterDot: Boolean){
        stringTask += newCharSing
        lastChar = newCharSing
        countSing++
        if(addCountNumders)
            countNumbers++
        else
            countNumbers = 0
        if(addCountNumbersAfterDot)
            countNumbersAfterDot++
        else
            countNumbersAfterDot = 0
    }
}