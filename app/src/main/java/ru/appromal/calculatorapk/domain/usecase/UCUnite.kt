package ru.appromal.calculatorapk.domain.usecase

import ru.appromal.calculatorapk.domain.dll.CreateStringTask
import ru.appromal.calculatorapk.domain.dll.Stack
import ru.appromal.calculatorapk.domain.dll.StringToStack
import ru.appromal.calculatorapk.domain.models.DAddNewCharInTask
import ru.appromal.calculatorapk.domain.models.DHistoryAddSings
import ru.appromal.calculatorapk.domain.models.DSendNewCharTask

class UCUnite {
    private var stackTaskHistory = Stack<DHistoryAddSings>()
    private var stackTask = Stack<String>()




    fun addSing(dSendCharTask: DSendNewCharTask){
        val params = CreateStringTask(DAddNewCharInTask(dLastStack = stackTaskHistory, dNewChar = dSendCharTask)).execute()
        val errors = params.dError
        stackTaskHistory = params.dLastStack
        if (!errors.isErrorEasyEnum){
            stackTask = StringToStack().execute(stackTaskHistory.peek().dStringTask)
        }
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
}