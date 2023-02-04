package ru.appromal.calculatorapk.domain.usecase

import ru.appromal.calculatorapk.domain.dll.CreateStringTask
import ru.appromal.calculatorapk.domain.dll.Stack
import ru.appromal.calculatorapk.domain.models.*

class UCUnite {
    private var stackTaskHistory = Stack<DHistoryAddSings>()

    private var paramsAnswerCreateString: EError = EError.NO_ERROR




    fun addSing(dSendCharTask: DSendNewCharTask){
        paramsAnswerCreateString = CreateStringTask(DAddNewCharInTask(dLastStack = stackTaskHistory, dNewChar = dSendCharTask)).execute()
//        val errors = params.dError
//        stackTaskHistory = params.dLastStack
//        if (!errors.isErrorEasyEnum){
//            stackTask = StringToStack().execute(stackTaskHistory.peek().dStringTask)
//            answerCalculator = Calculator(stackTask).execute()
//        }
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

    private fun resultWork(){
        if (!paramsAnswerCreateString.isErrorFatalEnum){

        }
    }
}