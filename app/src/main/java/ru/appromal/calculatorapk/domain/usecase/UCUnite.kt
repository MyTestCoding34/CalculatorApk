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

    fun returnAnswer(reset: Boolean = false): String {
        var result = ""
        var resetF = reset
        if (!stackTaskHistory.isEmpty()) {
            val lastCallStack = stackTaskHistory.peek()
            if (lastCallStack.dLastChar in listOf('/', '*', '-', '+') || lastCallStack.dCountBracket > 0) {
                resetF = false
            }
            else{
                StringToStack().execute(lastCallStack.dStringTask, stackTask)
                try{
                    result =" = ${Calculator(stackTask).execute().dStringAnswer}"
                }
                catch (e: Exception){
                    result =e.toString().split(":")[1]
                    resetF = false
                }
            }
        }
        if (resetF)
            deleteTask()
        return result
    }
}