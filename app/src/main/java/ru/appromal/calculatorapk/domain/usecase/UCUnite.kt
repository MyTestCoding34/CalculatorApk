package ru.appromal.calculatorapk.domain.usecase

import android.util.Log
import ru.appromal.calculatorapk.domain.dll.CreateStringTask
import ru.appromal.calculatorapk.domain.dll.Stack
import ru.appromal.calculatorapk.domain.models.DAddNewCharInTask
import ru.appromal.calculatorapk.domain.models.DHistoryAddSings
import ru.appromal.calculatorapk.domain.models.DSendNewCharTask

class UCUnite {
    private var stackTask = Stack<DHistoryAddSings>()




    fun addSing(dSendCharTask: DSendNewCharTask){
        stackTask = CreateStringTask(DAddNewCharInTask(dLastStack = stackTask, dNewChar = dSendCharTask)).execute()
    }
    fun returnText(): String{
        if(stackTask.isEmpty())
            return ""
        return stackTask.peek().dStringTask
    }

    fun deleteTask() {
        stackTask.clearAll()
    }

    fun deleteChar() {
        if(!stackTask.isEmpty()) {
            stackTask.pop()
        }
    }
}