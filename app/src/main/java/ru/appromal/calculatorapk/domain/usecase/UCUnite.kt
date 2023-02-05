package ru.appromal.calculatorapk.domain.usecase

import ru.appromal.calculatorapk.domain.dll.Calculator
import ru.appromal.calculatorapk.domain.dll.CreateStringTask
import ru.appromal.calculatorapk.domain.dll.Stack
import ru.appromal.calculatorapk.domain.dll.StringToStack
import ru.appromal.calculatorapk.domain.models.*
/*
stackTaskHistory        - стек под историю добавления символов

 */
class UCUnite {
    private var stackTaskHistory = Stack<DHistoryAddSigns>()
    private var stackTask = Stack<String>()
    private var answerCalculator: DReturnAnswerTask = DReturnAnswerTask(dStringAnswer = "", dError = EError.NO_ERROR)

    fun addSign(dSendCharTask: DAddSign){
        CreateStringTask(DAddNewCharInTask(dLastStack = stackTaskHistory, dNewChar = dSendCharTask)).execute()
    }

    fun returnText(): String{
        if(stackTaskHistory.isEmpty())
            return ""
        return stackTaskHistory.peek().dStringTask
    }

    fun deleteTask() {
        stackTaskHistory.clearAll()
    }

    fun deleteChar() {
        if(!stackTaskHistory.isEmpty()) {
            stackTaskHistory.pop()
        }
    }

    fun returnAnswer(): String {
        if (!stackTaskHistory.isEmpty()) {
            val lastCallStack = stackTaskHistory.peek()
            if (lastCallStack.dLastChar != '/' && lastCallStack.dLastChar != '*' && lastCallStack.dLastChar != '-' && lastCallStack.dLastChar != '+' && lastCallStack.dCountBracket == 0) {
                StringToStack().execute(lastCallStack.dStringTask, stackTask)
                answerCalculator = Calculator(stackTask).execute()
            }
            else
                return ""
        }
        return answerCalculator.dStringAnswer
    }
}